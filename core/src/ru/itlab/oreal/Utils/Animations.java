package ru.itlab.oreal.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Animations extends Actor {
    public boolean canStart, isEnded;
    Animation walkAnimation;
    Texture[] walkFrames;
    Texture currentFrame;
    float stateTime;

    public Animations() {
        canStart = false;
        isEnded = false;
        setBounds(0, 0, 640, 360);
        walkFrames = new Texture[250];
        for (int i = 0; i < 250; i++) {
            if (i < 10)
                walkFrames[i] = new Texture("Video1images/Cat 1_0000" + i + ".jpg");
            else if (i < 100)
                walkFrames[i] = new Texture("Video1images/Cat 1_000" + i + ".jpg");
            else
                walkFrames[i] = new Texture("Video1images/Cat 1_00" + i + ".jpg");
        }
        walkAnimation = new Animation(0.04f, walkFrames);
        stateTime = 0f;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (!canStart) return;
        Gdx.app.log("Anim", "act()");
        stateTime += delta;
        currentFrame = (Texture) walkAnimation.getKeyFrame(stateTime, false);
        if (stateTime >= 8) isEnded = true;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (!canStart) return;
        batch.draw(currentFrame, getX(), getY(), getWidth(), getHeight());
    }
}
