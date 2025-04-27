package namelessarsenal.world.blocks.defense.turrets;

import arc.graphics.Color;
import arc.graphics.g2d.*;
import arc.math.Mathf;
import arc.scene.style.Drawable;
import arc.util.Time;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.entities.Mover;
import mindustry.entities.bullet.*;
import mindustry.graphics.*;
import mindustry.ui.Bar;
import namelessarsenal.ui.CustomBar;
import universecore.graphics.g2d.ScaledNinePatchClipDrawable;

public class GatlingTurret extends ItemTurret {

    // GatlingTurret main stats(maxAccel, maxWindup, cooltime, cooldownDuration)
    // Max multiplier of firerate
    public float maxAccel = 2f;
    // How much shot needed for maximum windup
    public int maxWindup = 0;
    // How much seconds needed to start cooldown
    public float cooltime = 0f;
    // how much seconds needed to fully cool down.
    public float cooldownDuration = 4f;

    // GatlingTurret extra stats(Barrelcount,Barreldist)
    // alternate firing positions based on this stat
    public int barrelCount = 1;
    // distance between barrels
    public float barrelDist = 0;

    // GatlingTurret stats(enableHeat, maxHeat, overheatCooltime)
    // Value that enables heat system
    public boolean enableHeat = false;
    // How much seconds turret can withstand before overheat. minimum 1.
    public float maxHeat = 0f;
    // How much seconds needed to disable overheat. minimum 1.
    public float overheatCooltime = 0f;

    public float ui_heat = 0;
    public float ui_windup = 0;

    public GatlingTurret(String name) {
        super(name);
        hasItems = true;


    }



    @Override
    public void setBars() {
        super.setBars();
        // Windup UI
        this.addBar("Windup", (e) -> {
            return new Bar("stat.windup",Color.cyan,() -> {
                return (float) ui_windup;
            });
        });
        // Heat UI
        if(enableHeat)
        {
            this.addBar("Heat", (e) -> {
                return new Bar("stat.heat",Color.red,() -> {
                    return (float) ui_heat;
                });
            });
        }
    }

    public class GatlingTurretBuild extends ItemTurretBuild {
        // windup stat affects attack speed
        public float windup = 0f;
        // cooldown stats
        public float cooldown = 0;
        public boolean cooldownSwitch = true;
        // heat stats
        public float heat = 0f;
        public boolean heatSwitch = false;
        // UI related
        public float barrelSeq = 0;

        @Override
        public void created() {
            super.created();

            if (maxHeat < 1) maxHeat = Math.max(1, maxHeat);
            if (overheatCooltime < 1) overheatCooltime = Math.max(1, overheatCooltime);
        }

        @Override
        public void updateTile() {
            super.updateTile();

            ui_windup = Mathf.lerpDelta(ui_windup, windup / maxWindup, 0.05f);
            ui_heat = Mathf.lerpDelta(ui_heat, heat / maxHeat, 0.2f);

            if(cooldownSwitch && !heatSwitch) {
                if (enableHeat) {
                    if (heat > 0) heatReset();
                    else windupReset();
                }
                else {
                    windupReset();
                }
            }
            else {
                if (cooldown == cooltime) {
                    cooldownSwitch = true;
                }
                else {
                    cooldown = Mathf.clamp(cooldown + (Time.delta / 60), 0, cooltime);
                }
            }

            if (heat == maxHeat && !heatSwitch) {
                heatSwitch = true;
                windup = 0;
                heat = maxHeat;
            }

            if (heatSwitch) overheatReset();
        }

        @Override
        protected void shoot(BulletType type) {
            if(heatSwitch && enableHeat) return;
            super.shoot(type);

            if(windup < maxWindup) {
                windup = Mathf.clamp(windup + 1,0, maxWindup);
            }
            else {
                if (enableHeat) {
                    heat = Mathf.clamp(heat + (1 / ((60 * maxAccel) / reload)),0,maxHeat); // heat must increase by 1 per second
                }
            }

            cooldownSwitch = false;
            cooldown = 0f;
        }

        @Override
        protected void bullet(BulletType type, float xOffset, float yOffset, float angleOffset, Mover mover) {
            super.bullet(type, xOffset + barrelSequence(), yOffset, angleOffset, mover);

            if (barrelSeq < barrelCount - 1) barrelSeq++;
            else barrelSeq = 0;
        }

        @Override
        protected void updateShooting() {
            if(this.heatSwitch) return;
            super.updateShooting();
        }

        @Override
        protected float baseReloadSpeed() {
            return this.efficiency() * (1f + ((maxAccel -1) * (windup / maxWindup)));
        }

        @Override
        public void drawSelect() {
            super.drawSelect();
        }

        @Override
        public void draw() {
            super.draw();

            if (isControlled()) {

                Draw.z(Layer.effect);
                Draw.color(new Color(0.0f, 0.5f+(ui_windup/2f), 1.0f));
                Fill.rect((x - Math.max(16, size * 4)) + (ui_windup * Math.max(16, size * 4)), y - 2 - (size * 4), (Math.max(32, size * 8) * ui_windup), 4);

                if(maxHeat > 0) {
                    Draw.color(new Color(0.75F, 0.75f, 0.25f));
                    Fill.rect((x - Math.max(16, size * 4)) + (ui_heat * Math.max(16, size * 4)), y - 2 - (size * 4), (32 * ui_heat), 4);
                }

                Draw.z(Layer.overlayUI);
                Draw.color(Color.darkGray);
                Lines.rect(x - Math.max(16, size * 4), y - 4 - (size * 4), Math.max(32, size * 8), 4);
                Draw.z(Layer.bullet);


            }
            Draw.reset();
        }

        float barrelSequence() {
            return (-barrelDist * (barrelCount - 1) / 2 + barrelSeq * barrelDist);
        }

        void windupReset()
        {
            windup = Math.max(windup - ((Time.delta / 60) * (maxWindup / cooldownDuration)), 0);
        }

        void heatReset()
        {
            heat = Math.max(heat - (Time.delta / 60), 0);
        }

        void overheatReset()
        {
            if (heat != 0) {
                heat = Math.max(heat - ((Time.delta / 60) * maxHeat) / overheatCooltime, 0);
            }
            else {
                heatSwitch = false;
            }

        }

    }
}

