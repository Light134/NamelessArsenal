package namelessarsenal;

import namelessarsenal.contents.NamelessTurret;
import mindustry.mod.Mod;

public class NamelessArsenal extends Mod{

    @Override
    public void init(){
    }

    @Override
    public void loadContent(){
        NamelessTurret.load();
    }
}
