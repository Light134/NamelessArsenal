package namelessarsenal.world.blocks.defense.turrets;

import arc.graphics.Color;
import arc.graphics.g2d.*;
import arc.math.Mathf;
import arc.util.Time;
import mindustry.entities.Mover;
import mindustry.entities.bullet.*;
import mindustry.graphics.*;

public class HeatGatlingTurret extends GatlingTurret {

    // GatlingTurret main stats(maxAccel,maxHeat,maxWindup,overheatCooltime,cooltime)
    public float maxAccel = 2f; // Max multiplier of firerate
    public int maxWindup = 0; // How much shot needed for maximum windup
    public float maxHeat = 10f; // How much seconds turret can withstand before overheat
    public float overheatCooltime = 3f; // How much seconds needed to disable overheat
    public float cooltime = 0f; // How much seconds needed to start cooling
    // GatlingTurret extra stats
    public int bulletOffset = 0; // bullet offset when firing.

    public HeatGatlingTurret(String name) {
        super(name);
        hasItems = true;
    }

    public class HeatGatlingTurretBuild extends GatlingTurretBuild {
        public float heat = 0f;
        public float windup = 0f;

        public float cooldown = 0;
        public float cooldownDelay = 0;

        public boolean heatSwitch = false;
        public boolean cooldownSwitch = true;

        public float ui_windup = 0;
        public float ui_heat = 0;
        float ui_heatLimit = 0.5f;
        float ui_heatWarning = Math.max(ui_heat-ui_heatLimit , 0f) * 1/(1-ui_heatLimit);

        @Override
        public void updateTile() {
            super.updateTile();

            ui_windup = Mathf.lerpDelta(ui_windup, windup / maxWindup, 0.05f);
            ui_heat = Mathf.lerpDelta(ui_heat, heat / maxHeat, 0.2f);

            if (Math.floor(heat*100)/100 >= maxHeat && !heatSwitch) {
                heatSwitch = true;
                windup = 0;
                heat = maxHeat;
            }

            if (heatSwitch) {
                if(heat > 0) heat -= ((maxHeat / overheatCooltime) * (Time.delta / 60));
                else {
                    heatSwitch = false;
                    heat = 0;
                }
            }
            else if (cooldownSwitch) {
                if (heat > 0)heat -= Time.delta / 30;
                else
                {
                    heat = 0;

                    cooldownDelay += Time.delta / 60;
                    if (cooldownDelay > cooltime && windup > 0) {
                        windup -= 1;
                        cooldownDelay = 0;
                    }
                }
            }

            if(!cooldownSwitch) {
                cooldown += Time.delta / 60;
                if (cooldown >= cooltime) {
                    cooldownSwitch = true;
                    cooldown = cooltime;
                }
            }
        }

        @Override
        protected void shoot(BulletType type) {
            if(heatSwitch) return;
            super.shoot(type);

            if(windup < maxWindup) {
                windup += 1;
            } else if(windup == maxWindup) {
                heat += 1 / ((60 * maxAccel) / reload); // heat must increase by 1 per second
            }
            cooldownSwitch = false;
            cooldown = 0f;
            // Debug only Script.
            // Log.info("Firerate: @",(1f + ((maxAccel -1) * (windup / maxWindup))));
            // Log.info("Heat: @", Math.round(heat*100)/100f);
            // Log.info("ui_heatWarning: @", ui_heatWarning);
        }

        @Override
        protected void bullet(BulletType type, float xOffset, float yOffset, float angleOffset, Mover mover) {
            super.bullet(type, xOffset+Mathf.random(-bulletOffset, bulletOffset), yOffset, angleOffset, mover);
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

            if(isControlled()) {
                ui_heatWarning = Math.max(ui_heat-ui_heatLimit , 0f) * 1/(1-ui_heatLimit);

                Draw.z(99f);
                Draw.color(new Color(0.0f, 0.5f+(ui_windup/2f), 1.0f));
                Fill.rect((x-16)+(ui_windup * 16), (y - 2) - (size * 8), (32 * ui_windup), 4);

                Draw.z(Layer.effect);
                Draw.color(new Color(1.0F, (165/255f)*(1-(ui_heatWarning/2f)), ui_heatWarning));
                Fill.rect((x-16)+(ui_heat * 16), (y - 2) - (size * 8), (32 * ui_heat), 4);
            }

            Draw.reset();
        }
    }
}

