package namelessarsenal.ui;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.scene.Element;
import arc.scene.style.Drawable;
import arc.scene.style.ScaledNinePatchDrawable;
import mindustry.gen.Tex;
import mindustry.graphics.Drawf;
import namelessarsenal.NamelessArsenal;
import universecore.graphics.g2d.ScaledNinePatchClipDrawable;

public class CustomBar extends Element {

    public static ScaledNinePatchDrawable background, foreground;


    public static void load()
    {
        background = (ScaledNinePatchDrawable)Core.atlas.drawable("custombar_back");
        foreground = (ScaledNinePatchDrawable)Core.atlas.drawable("custombar_fore");
    }

    @Override
    public void draw()
    {
        // 1. 가속/열 퍼센트를 표시하는 UI를 Drawable로 만들기
        // 2. 그것을 ClipNinePatchDrawable로 Wrap하기
        // 3. 이후에는 그걸 Draw 함수에서 사용
        // 네

        // 위에 코드는 지울게요.

        //background.draw();// 지금 하죠 그럼
    }
}

