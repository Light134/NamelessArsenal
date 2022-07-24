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
        spark = new PowerTurret("spark"){{
            requirements(Category.turret, ItemStack.with(Items.copper, 50, Items.lead, 50)); //materials to build this block and category of it
            // block health & size
            health = 260;
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

        moderato = new GatlingTurret("moderato") {{
            requirements(Category.turret, ItemStack.with(Items.copper, 50, Items.lead, 50)); //materials to build this block and category of it
            // block health & size
            health = 260;
            size = 1;
            // main stat(reload,shootCone,rotateSpeed,range,targetAir)
            reload = 12f; //60f = 1 second
            shootCone = 20f;
            rotateSpeed = 8f;
            range = 56f; //8f = 1 block
            targetAir = true;
            // Visual & Sound effect(shootEffect,heatColor,recoil,shootSound)
            shootEffect = Fx.lightningShoot;
            heatColor = Color.red;
            recoil = 0.2f;
            shootSound = Sounds.spark;

            ammo(
                    Items.copper,  new BasicBulletType(2.5f, 9){{
                        width = 7f;
                        height = 9f;
                        lifetime = 60f;
                        ammoMultiplier = 2;
                    }}
            );
        }};
    }
}
