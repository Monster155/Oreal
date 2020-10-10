package ru.itlab.oreal.Characters;

import com.badlogic.gdx.graphics.Texture;

public class Deputy extends NPC {
    public Deputy() {
        super();
        setName("Deputy");
        numberOfDialogWithMe = new int[][]{{1, 2}};
        emotions = new Texture("Emotions/exclamation.png");
    }

    @Override
    protected Texture[] getAnimationTextures() {
        return new Texture[]{new Texture("Characters/deputy.png")};
    }
}
