package namelessarsenal.world.blocks.defense.turrets;


import arc.util.Log;
import arc.util.Time;
import arc.util.Timer;
import mindustry.entities.bullet.BulletType;
import mindustry.world.blocks.defense.turrets.*;

public class GatlingTurret extends ItemTurret {
    public float maxHeat = 10f; // how much seconds turret can withstand before overheat
    public int maxAcceleration = 0; // how much shot needed for maximum acceleration
    public float maxCooldown = 0f; //how much seconds needed to start cooldown

    public GatlingTurret(String name){
        super(name);
        hasItems = true;
    }

    public class GatlingTurretBuild extends ItemTurretBuild {
        public float heat = 0f;
        public float acceleration = 0f;
        public float cooldown = 0;
        public float cooldownDelay = 0;
        public boolean heatSwitch = false;
        public boolean cooldownSwitch = true;

        @Override
        public void updateTile() {
            super.updateTile();
            if (heat > maxHeat && !heatSwitch) {
                heatSwitch = true;
                acceleration = 0;
                heat = maxHeat;
            }

            if (heatSwitch){
                if(heat > 0) heat -= Time.delta / 30;
                else {
                    heatSwitch = false;
                    heat = 0;
                }
            }else if (cooldownSwitch){
                if(heat > 0 && !heatSwitch) heat -= Time.delta / 30;
                else heat = 0;
                if(acceleration != 0) {
                    cooldownDelay += Time.delta / 60;
                    if (cooldownDelay > maxCooldown){
                        acceleration -= 1;
                        cooldownDelay = 0;
                    }
                }
            }

            if(!cooldownSwitch) {
                cooldown += Time.delta / 60;
                if (cooldown > maxCooldown){
                    cooldownSwitch = true;
                    cooldown = 1;
                }
            }
        }

        @Override
        protected void shoot(BulletType type) {
            if(!heatSwitch) {
                super.shoot(type);
                if(acceleration < maxAcceleration) {
                    acceleration += 1;
                } else if(acceleration == maxAcceleration) {
                    heat += Time.delta / (120 / reload);
                }
                cooldownSwitch = false;
                cooldown = 0f;
            }
        }

        @Override
        protected void updateShooting() {
            if(this.heatSwitch) return;
            super.updateShooting();
        }

        @Override
        protected float baseReloadSpeed() {
            return this.efficiency() * (1f + acceleration / maxAcceleration);
        }

    }
}
