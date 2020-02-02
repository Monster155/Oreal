package ru.itlab.oreal.Characters;

import com.badlogic.gdx.graphics.Texture;

public class Bob extends NPC {

    public Bob() {
        super();
        setName("Bob");
        numberOfDialogWithMe = new int[][]{{0}, {1, 2, 3, 6, 7}};
        emotions = new Texture("Emotions/exclamation.png");
        texture = new Texture("Characters/Bob.png");
    }
}
