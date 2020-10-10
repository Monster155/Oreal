package ru.itlab.oreal.Characters;

import com.badlogic.gdx.graphics.Texture;

public class Cab extends NPC {
    public Cab() {
        super();
        setName("Cab");
        numberOfDialogWithMe = new int[][]{{0}, {5, 8}, {1}};
        emotions = new Texture("Emotions/exclamation.png");
    }

    @Override
    protected Texture[] getAnimationTextures() {
        return new Texture[]{new Texture("Characters/muzik.png")};
    }
}
