package ru.itlab.oreal.Characters;

import com.badlogic.gdx.graphics.Texture;

public class Housekeeper extends NPC {
    public Housekeeper() {
        super();
        setName("Housekeeper");
        numberOfDialogWithMe = new int[][]{{3}};
        emotions = new Texture("Emotions/exclamation.png");
    }

    @Override
    protected Texture[] getAnimationTextures() {
        return new Texture[]{new Texture("Characters/woman1.png")};
    }
}
