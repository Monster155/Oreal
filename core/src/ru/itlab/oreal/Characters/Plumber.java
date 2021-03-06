package ru.itlab.oreal.Characters;

import com.badlogic.gdx.graphics.Texture;

public class Plumber extends NPC {
    public Plumber() {
        super();
        setName("Plumber");
        numberOfDialogWithMe = new int[][]{{0}, {0}, {0}, {1}};
        emotions = new Texture("Emotions/exclamation.png");
    }

    @Override
    protected Texture[] getAnimationTextures() {
        return new Texture[]{new Texture("Characters/kek.png")};
    }
}
