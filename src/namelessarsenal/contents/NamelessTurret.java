package namelessarsenal.contents;

import arc.graphics.Color;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.gen.Sounds;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.defense.turrets.*;

import namelessarsenal.world.blocks.defense.turrets.*;

public class NamelessTurret {
    public static Block
    // tesla coils
    spark, thunder, lightning, storm, mjollnir, thor,
    // chainguns
    andantino, moderato, allegretto, allegro, vivace, presto, allegrissimo, vivacissimo, prestissimo,
    // Missile launchers
    troposphere, stratosphere, mesosphere, thermosphere, exosphere, vacuum,
    // Liquidjet cutter
    misty, drizzle, sprinkle, shower, downpour, hail,
    // Orbital strike beacon
    halley, Wild, tempel, mcnaught, hartley,
    // scanner
    thales, archimedes, fermat, riemann, euler, laplace;

    public static void load(){
        andantino = new HeatGatlingTurret("andantino") {{
            //materials to build this block and category of it
            requirements(Category.turret, ItemStack.with(Items.copper, 50, Items.lead, 50));
            // block health & size
            health = 200;
            size = 1;
            // main stat(reload,shootCone,rotateSpeed,range,targetAir)
            reload = 12f; //60f = 1 second
            shootCone = 15f;
            rotateSpeed = 3f; //1f = 60 degree per second
            range = 96f; //8f = 1 block
            targetAir = true;
            // Visual & Sound effect (shootEffect,heatColor,recoil,shootSound)
            shootEffect = Fx.shootSmall;
            heatColor = Color.red;
            recoil = 0.2f;
            shootSound = Sounds.shoot;
            // HeatGatlingTurret main stats (maxAccel,maxHeat,maxWindup,overheatCooltime,cooltime)
            maxAccel = 2f;
            maxHeat = 5f;
            maxWindup = 15;
            overheatCooltime = 3f;
            cooltime = 0.2f;
            // HeatGatlingTurret extras (bulletOffset)
            bulletOffset = 0;

            ammo(
                    Items.copper,  new BasicBulletType(8f, 6){{
                        width = 3.5f;
                        height = 9f;
                        lifetime = 12f;
                        ammoMultiplier = 2;
                    }}
            );
        }};

        moderato = new HeatGatlingTurret("moderato") {{
            //materials to build this block and category of it
            requirements(Category.turret, ItemStack.with(Items.copper, 120, Items.lead, 75, Items.silicon, 30));
            // block health & size
            health = 1000;
            size = 2;
            // main stat(reload,shootCone,rotateSpeed,range,targetAir)
            reload = 15f; //60f = 1 second
            shootCone = 10f;
            rotateSpeed = 3f; //1f = 60 degree per second
            range = 144f; //8f = 1 block
            targetAir = true;
            // Visual & Sound effect (shootEffect,heatColor,recoil,shootSound)
            shootEffect = Fx.lightningShoot;
            heatColor = Color.red;
            recoil = 0.2f;
            shootSound = Sounds.shoot;
            // HeatGatlingTurret main stats (maxAccel,maxHeat,maxWindup,overheatCooltime,cooltime)
            maxAccel = 5f;
            maxHeat = 10f;
            maxWindup = 60;
            overheatCooltime = 4f;
            cooltime = 0.25f;
            // HeatGatlingTurret extras (bulletOffset)
            bulletOffset = 4;

            ammo(
                    Items.lead,  new BasicBulletType(8f, 10){{
                        width = 3.5f;
                        height = 12f;
                        lifetime = 18f;
                        ammoMultiplier = 2;
                    }}
            );
        }};

        allegretto = new HeatGatlingTurret("allegretto") {{
            //materials to build this block and category of it
            requirements(Category.turret, ItemStack.with(Items.copper, 300, Items.lead, 150, Items.silicon, 90));
            // block health & size
            health = 1100;
            size = 3;
            // main stat(reload,shootCone,rotateSpeed,range,targetAir)
            reload = 6f; //60f = 1 second
            shootCone = 10f;
            rotateSpeed = 2f; //1f = 60 degree per second
            range = 176f; //8f = 1 block
            targetAir = true;
            // Visual & Sound effect(shootEffect,heatColor,recoil,shootSound)
            shootEffect = Fx.lightningShoot;
            heatColor = Color.red;
            recoil = 0.2f;
            shootSound = Sounds.shoot;
            // HeatGatlingTurret main stats(maxAccel,maxHeat,maxWindup,overheatCooltime,cooltime)
            maxAccel = 3f;
            maxHeat = 15f;
            maxWindup = 60;
            overheatCooltime = 5f;
            cooltime = 0.1f;
            // HeatGatlingTurret extras (bulletOffset)
            bulletOffset = 4;

            ammo(
                    Items.lead,  new BasicBulletType(8f, 14){{
                        width = 3.5f;
                        height = 12f;
                        lifetime = 22f;
                        ammoMultiplier = 2;
                    }}
            );
        }};

        allegro = new GatlingTurret("allegro") {{
            //materials to build this block and category of it
            requirements(Category.turret, ItemStack.with(Items.copper, 1000, Items.graphite, 150, Items.silicon, 150, Items.titanium, 200, Items.surgeAlloy, 250));
            // block health & size
            health = 1100;
            size = 4;
            // main stat(reload,shootCone,rotateSpeed,range,targetAir)
            reload = 12f; //60f = 1 second
            shootCone = 10f;
            rotateSpeed = 2f; //1f = 60 degree per second
            range = 192f; //8f = 1 block
            targetAir = true;
            // Visual & Sound effect(shootEffect,heatColor,recoil,shootSound)
            shootEffect = Fx.lightningShoot;
            heatColor = Color.red;
            recoil = 0.2f;
            shootSound = Sounds.shootBig;
            // GatlingTurret main stats(maxAccel,maxWindup,cooltime)
            maxAccel = 10f;
            maxWindup = 275;
            cooltime = 0.2f;
            // GatlingTurret extras (bulletOffset)
            bulletOffset = 6; //4 = 1 block

            ammo(
                    Items.titanium,  new BasicBulletType(16f, 16){{
                        width = 3.5f;
                        height = 14f;
                        lifetime = 11.5f;
                        ammoMultiplier = 5;
                    }}
            );
        }};

        allegrissimo = new GatlingTurret("allegrissimo") {{
            //materials to build this block and category of it
            requirements(Category.turret, ItemStack.with(Items.copper, 1000, Items.graphite, 150, Items.silicon, 150, Items.titanium, 200, Items.surgeAlloy, 250));
            // block health & size
            health = 1100;
            size = 5;
            // main stat(reload,shootCone,rotateSpeed,range,targetAir)
            reload = 20f; //60f = 1 second
            shootCone = 10f;
            rotateSpeed = 2f; //1f = 60 degree per second
            range = 192f; //8f = 1 block
            targetAir = true;
            // Visual & Sound effect(shootEffect,heatColor,recoil,shootSound)
            shootEffect = Fx.lightningShoot;
            heatColor = Color.red;
            recoil = 0.2f;
            shootSound = Sounds.shootBig;
            // GatlingTurret main stats(maxAccel,maxWindup,cooltime)
            maxAccel = 20f;
            maxWindup = 630;
            cooltime = 0.3333f;
            // GatlingTurret extras (bulletOffset)
            bulletOffset = 6; //4 = 1 block

            ammo(
                    Items.titanium,  new BasicBulletType(16f, 16){{
                        width = 3.5f;
                        height = 14f;
                        lifetime = 11.5f;
                        ammoMultiplier = 6;
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
