package namelessarsenal;

import namelessarsenal.contents.Namelessblock;
import mindustry.mod.Mod;

public class NamelessArsenal extends Mod{

    @Override
    public void init(){
    }

    @Override
    public void loadContent(){
        Namelessblock.load();
    }
}
