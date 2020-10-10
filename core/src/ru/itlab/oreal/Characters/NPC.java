package ru.itlab.oreal.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class NPC extends Actor {

    public boolean canSay;
    protected Texture emotions;
    protected int[][] numberOfDialogWithMe;
    protected float speed;
    //Animation
    protected Animation idleAnimation;
    protected Texture[] idleFrames;
    protected float stateTime;
    protected Texture currentFrame;

    private boolean isMove;

    public NPC() {
        canSay = false;
        isMove = false;
        speed = 60;
        setSize(30, 40);
        //Animation
        stateTime = 0;
        idleFrames = getAnimationTextures();
        idleAnimation = new Animation(0.5f, idleFrames);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(currentFrame, getX(), getY(), getWidth(), getHeight());
        if (canSay && !isMove)
            batch.draw(emotions, getX(), getY() + getHeight(), 20, 20);
    }

    public Texture getTexture() {
        return (Texture) idleAnimation.getKeyFrame(0, false);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        stateTime += delta;
        if (idleAnimation.getAnimationDuration() < stateTime)
            stateTime -= idleAnimation.getAnimationDuration();
        currentFrame = (Texture) idleAnimation.getKeyFrame(stateTime, true);
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

    protected abstract Texture[] getAnimationTextures();
}
