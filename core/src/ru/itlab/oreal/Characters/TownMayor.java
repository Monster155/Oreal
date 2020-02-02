package ru.itlab.oreal.Characters;

import com.badlogic.gdx.graphics.Texture;

public class TownMayor extends NPC {

    public TownMayor(boolean isScary) {
        super();
        setName("Town Mayor");
        numberOfDialogWithMe = new int[][]{{0}, {4}, {0}, {0}, {1}};
        if (isScary) {
            emotions = new Texture("Emotions/exclamation-red.png");
        } else {
            emotions = new Texture("Emotions/exclamation.png");
        }
        texture = new Texture("Characters/gorodnici.png");
    }

}
