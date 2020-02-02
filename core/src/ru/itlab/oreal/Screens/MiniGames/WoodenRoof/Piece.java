package ru.itlab.oreal.Screens.MiniGames.WoodenRoof;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Piece extends Actor {
    private Texture texture;
    private int num;

    public Piece(int num) {
        super();
        this.num = num;
        setBounds(70 * num, 20, 70, 70);
        texture = new Texture("MiniGames/WoodenRoof/" + num + ".png");
    }

    public int getNum() {
        return num;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }
}
