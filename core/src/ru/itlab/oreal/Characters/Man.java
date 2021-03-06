package ru.itlab.oreal.Characters;

import com.badlogic.gdx.graphics.Texture;

public class Man extends NPC {
    public Man() {
        super();
        setName("Man");
        numberOfDialogWithMe = new int[][]{{0}, {0}, {2}};
        emotions = new Texture("Emotions/exclamation.png");
    }

    @Override
    protected Texture[] getAnimationTextures() {
        return new Texture[]{new Texture("Characters/kek.png")};
    }
}
