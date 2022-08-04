package namelessarsenal.contents;

import arc.graphics.Color;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.gen.Sounds;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.defense.turrets.*;
import namelessarsenal.world.blocks.defense.turrets.GatlingTurret;

public class Namelessblock {
    public static Block
    //tesla turret
    spark, thunder, lightning, storm, mjollnir, thor,
    //chainguns
    moderato, allegretto, allegro, vivace, presto, accelerando,
    //Missile launchers
    troposphere, stratosphere, mesosphere, thermosphere, exosphere, vacuum,
    //Liquidjet cutter
    misty, drizzle, sprinkle, shower, downpour, hail,
    //Orbital strike beacon
    halley, Wild, tempel, mcnaught, hartley,
    //Weak point scanner
    thales, archimedes, fermat, riemann, euler, laplace;

    public static void load(){
        moderato = new GatlingTurret("moderato") {{
            //materials to build this block and category of it
            requirements(Category.turret, ItemStack.with(Items.copper, 50, Items.lead, 50));
            // block health & size
            health = 200;
            size = 1;
            // main stat(reload,shootCone,rotateSpeed,range,targetAir)
            reload = 12f; //60f = 1 second
            shootCone = 10f;
            rotateSpeed = 3f; //1f = 60 degree per second
            range = 96f; //8f = 1 block
            targetAir = true;
            // Visual & Sound effect(shootEffect,heatColor,recoil,shootSound)
            shootEffect = Fx.lightningShoot;
            heatColor = Color.red;
            recoil = 0.2f;
            shootSound = Sounds.shoot;
            // GatlingTurret stats(maxAcceleration,)
            maxHeat = 10f;
            maxAcceleration = 10;
            maxCooldown = 0.3f;

            ammo(
                    Items.copper,  new BasicBulletType(8f, 6){{
                        width = 3.5f;
                        height = 9f;
                        lifetime = 12f;
                        ammoMultiplier = 2;
                    }}
            );
        }};

        allegretto = new GatlingTurret("allegretto") {{
            //materials to build this block and category of it
            requirements(Category.turret, ItemStack.with(Items.copper, 120, Items.lead, 75, Items.silicon, 30));
            // block health & size
            health = 1000;
            size = 2;
            // main stat(reload,shootCone,rotateSpeed,range,targetAir)
            reload = 10f; //60f = 1 second
            shootCone = 10f;
            rotateSpeed = 3f; //1f = 60 degree per second
            range = 144f; //8f = 1 block
            targetAir = true;
            // Visual & Sound effect(shootEffect,heatColor,recoil,shootSound)
            shootEffect = Fx.lightningShoot;
            heatColor = Color.red;
            recoil = 0.2f;
            shootSound = Sounds.shoot;
            // GatlingTurret stats(maxAcceleration,)
            maxHeat = 10f;
            maxAcceleration = 12;
            maxCooldown = 0.25f;

            ammo(
                    Items.lead,  new BasicBulletType(8f, 10){{
                        width = 3.5f;
                        height = 12f;
                        lifetime = 18f;
                        ammoMultiplier = 2;
                    }}
            );
        }};

        allegro = new GatlingTurret("allegro") {{
            //materials to build this block and category of it
            requirements(Category.turret, ItemStack.with(Items.copper, 300, Items.lead, 150, Items.silicon, 90));
            // block health & size
            health = 1100;
            size = 3;
            // main stat(reload,shootCone,rotateSpeed,range,targetAir)
            reload = 6f; //60f = 1 second
            shootCone = 10f;
            rotateSpeed = 3f; //1f = 60 degree per second
            range = 176f; //8f = 1 block
            targetAir = true;
            // Visual & Sound effect(shootEffect,heatColor,recoil,shootSound)
            shootEffect = Fx.lightningShoot;
            heatColor = Color.red;
            recoil = 0.2f;
            shootSound = Sounds.shoot;
            // GatlingTurret stats(maxAcceleration,)
            maxHeat = 10f;
            maxAcceleration = 20;
            maxCooldown = 0.15f;

            ammo(
                    Items.lead,  new BasicBulletType(8f, 14){{
                        width = 3.5f;
                        height = 12f;
                        lifetime = 22f;
                        ammoMultiplier = 2;
                    }}
            );
        }};

        spark = new PowerTurret("spark"){{
            requirements(Category.turret, ItemStack.with(Items.copper, 50, Items.lead, 50)); //materials to build this block and category of it
            // block health & size
            health = 390;
            size = 1;
            // main stat(reload,shootCone,rotateSpeed,range,targetAir)
            reload = 18f; //60f = 1 second
            shootCone = 20f;
            rotateSpeed = 8f;
            range = 56f; //8f = 1 block
            targetAir = true;
            // Visual & Sound effect(shootEffect,heatColor,recoil,shootSound)
            shootEffect = Fx.lightningShoot;
            heatColor = Color.red;
            recoil = 0.2f;
            shootSound = Sounds.spark;
            // Coolant & Power consume per tick
            consumePower(3.3f);
            coolant = consumeCoolant(0.1f);

            shootType = new LightningBulletType(){{
                damage = 10;
                lightningLength = 16;
                collidesAir = true;
                ammoMultiplier = 1f;

                lightningType = new BulletType(0.0001f, 0f){{
                    lifetime = Fx.lightning.lifetime;
                    hitEffect = Fx.hitLancer;
                    despawnEffect = Fx.none;
                    status = StatusEffects.shocked;
                    statusDuration = 10f;
                    hittable = false;
                    lightColor = Color.white;
                    collidesAir = true;
                    buildingDamageMultiplier = 0.25f; //damage multiplier to buildings
                }};
            }};
        }};
    }
}
