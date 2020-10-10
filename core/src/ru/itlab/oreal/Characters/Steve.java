package ru.itlab.oreal.Characters;

import com.badlogic.gdx.graphics.Texture;

public class Steve extends NPC {
    public Steve() {
        super();
        setName("Steve");
        numberOfDialogWithMe = new int[][]{{0}, {0}, {0}, {2, 3, 4}};
        emotions = new Texture("Emotions/exclamation.png");
    }

    @Override
    protected Texture[] getAnimationTextures() {
        return new Texture[]{new Texture("Characters/ded.png")};
    }
}
