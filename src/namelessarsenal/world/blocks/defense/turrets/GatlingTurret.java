package namelessarsenal.world.blocks.defense.turrets;


import arc.util.Log;
import arc.util.Time;
import mindustry.entities.bullet.BulletType;
import mindustry.world.blocks.defense.turrets.*;

public class GatlingTurret extends ItemTurret {
    public float heatCapacity = 10f; // how much seconds turret can withstand before overheat
    public int maxAcceleration = 20; // max multiplier of fire rate

    public GatlingTurret(String name){
        super(name);
        hasItems = true;
    }

    public class GatlingTurretBuild extends ItemTurretBuild {
        public float acceleration = 0f;
        public float heat = 0f;
        public float cooldown = 3f;
        public boolean heatSwitch = false;

        @Override
        public void updateTile() {
            super.updateTile();
            if (heat >= heatCapacity && !heatSwitch) {
                heatSwitch = true;
                heat = 10f;
            } else if (heatSwitch && heat > 0) {
                heat -= Time.delta /30;
            } else if (heatSwitch && heat <= 0) {
                heatSwitch = false;
                heat = 0f;
            }

            Log.info(acceleration);
            Log.info(heat);
            Log.info(cooldown);
            Log.info(heatSwitch);

        }

        @Override
        protected void shoot(BulletType type) {
            if(!heatSwitch) {
                super.shoot(type);
                if(acceleration < maxAcceleration) {
                    acceleration += 1;
                } else if(acceleration == maxAcceleration){
                    heat += Time.delta / 60;
                }
            }
        }

        @Override
        protected void updateShooting() {
            if(this.heatSwitch) return;
            super.updateShooting();
        }

        @Override
        protected float baseReloadSpeed() {
            return this.efficiency() * (1f + acceleration / 20);
        }

    }
}
