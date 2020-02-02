package ru.itlab.oreal.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class NPC extends Actor {

    public boolean canSay;
    protected Texture emotions;
    protected int[][] numberOfDialogWithMe;
    protected Texture texture;
    protected float speed;
    private boolean isMove;

    public NPC() {
        canSay = false;
        isMove = false;
        speed = 30;
        setSize(30, 40);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
        if (canSay && !isMove)
            batch.draw(emotions, getX(), getY() + getHeight(), 20, 20);
    }

    public Texture getTexture() {
        return texture;
    }

    public boolean moveTo(float x, float y) {
        isMove = true;
        if (range(getX(), x, getY(), y) <= speed * Gdx.graphics.getDeltaTime()) {
            setPosition(x, y);
            isMove = false;
            return true;
        }
        setPosition(getX() - (speed * Gdx.graphics.getDeltaTime() * (getX() - x)) / range(getX(), x, getY(), y),
                getY() - (speed * Gdx.graphics.getDeltaTime() * (getY() - y)) / range(getX(), x, getY(), y));
        return false;
    }

    private float range(float x1, float x2, float y1, float y2) {
        return (float) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }
}
