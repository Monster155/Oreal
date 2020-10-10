package ru.itlab.oreal.Characters;

import com.badlogic.gdx.graphics.Texture;

public class NotSteve extends NPC {
    public NotSteve() {
        super();
        setName("NotSteve");
        numberOfDialogWithMe = new int[][]{{0}, {0}, {3, 4}};
        emotions = new Texture("Emotions/exclamation.png");
    }

    @Override
    protected Texture[] getAnimationTextures() {
        return new Texture[]{new Texture("Characters/kek.png")};
    }
}
