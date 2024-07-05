package namelessarsenal.world.blocks.defense.turrets;

import arc.graphics.Color;
import arc.graphics.g2d.*;
import arc.math.Mathf;
import arc.util.Log;
import arc.util.Time;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.entities.Mover;
import mindustry.entities.bullet.*;
import mindustry.graphics.*;

public class GatlingTurret extends ItemTurret {

    // GatlingTurret main stats(maxAccel,maxWindup,cooltime)
    public float maxAccel = 2f; // Max multiplier of firerate
    public int maxWindup = 0; // How much shot needed for maximum windup
    public float cooltime = 0f; // How much seconds needed to start cooling
    // GatlingTurret extra stats
    public int bulletOffset = 0; // bullet offset when firing.
    public int Barrelcount = 1;

    public GatlingTurret(String name) {
        super(name);
        hasItems = true;
    }

    public class GatlingTurretBuild extends ItemTurretBuild {
        public float windup = 0f;
        public float cooldown = 0;
        public boolean cooldownSwitch = true;
        public float ui_windup = 0;


        @Override
        public void updateTile() {
            super.updateTile();

            ui_windup = Mathf.lerpDelta(ui_windup, windup / maxWindup, 0.05f);

            if (cooldownSwitch) {
                if (windup > 0)
                {
                    windup -= 1;
                    Log.info("Windup: @", windup);
                }
            }
            else {
                cooldown += Time.delta / 60;
                if (cooldown >= cooltime) {
                    cooldownSwitch = true;
                    cooldown = cooltime;
                }
            }
        }

        @Override
        protected void shoot(BulletType type) {
            super.shoot(type);

            if(windup < maxWindup) {
                windup += 1;
                cooldownSwitch = false;
                cooldown = 0f;
            }
            // Debug only Script.
            // Log.info("Firerate: @",(1f + ((maxAccel -1) * (windup / maxWindup))));
        }

        @Override
        protected void bullet(BulletType type, float xOffset, float yOffset, float angleOffset, Mover mover) {
            super.bullet(type, xOffset+Mathf.random(-bulletOffset, bulletOffset), yOffset, angleOffset, mover);
        }

        @Override
        protected void updateShooting() {
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
                Draw.z(99f);
                Draw.color(new Color(0.0f, 0.5f+(ui_windup/2f), 1.0f));
                Fill.rect((x-16)+(ui_windup * 16), (y - 2) - (size * 4), (32 * ui_windup), 4);

                Draw.z(Layer.overlayUI);
                Draw.color(Color.darkGray);
                Lines.rect(x-16, (y - 4) - (size * 4), 32, 4);
                Draw.z(Layer.effect);
            }

            Draw.reset();
        }
    }
}

