package ru.itlab.oreal.Characters;

import com.badlogic.gdx.graphics.Texture;

public class TrainWoman extends NPC {
    public TrainWoman() {
        super();
        setName("TrainWoman");
        numberOfDialogWithMe = new int[][]{{0}, {0}, {5}};
        emotions = new Texture("Emotions/exclamation.png");
    }

    @Override
    protected Texture[] getAnimationTextures() {
        return new Texture[]{new Texture("Characters/woman2.png")};
    }
}
