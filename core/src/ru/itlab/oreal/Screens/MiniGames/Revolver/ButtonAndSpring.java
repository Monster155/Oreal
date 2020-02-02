package ru.itlab.oreal.Screens.MiniGames.Revolver;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class ButtonAndSpring extends Actor {
    private Texture textureRed, textureGreen, textureSpring;
    private boolean isPassed, isPressed;
    private float time, maxTime, reductionRate, timeAfter, maxTimeAfter;
    private Rectangle rectSpring;
    private RevolverGameScreen gs;

    public ButtonAndSpring(int num, final RevolverGameScreen gs) {
        super();
        this.gs = gs;
        isPassed = false;
        isPressed = false;
        reductionRate = 20 * (num + 1);
        setBounds(20, 100 * (3 - num) - 50, 50, 50);
        maxTime = (float) (Math.random() * (500 / reductionRate));
        time = maxTime + 1;
        maxTimeAfter = 0.5f;
        timeAfter = maxTimeAfter + 1;

        textureSpring = new Texture("MiniGames/Revolver/spring.png");
        rectSpring = new Rectangle(600, 100 * (3 - num) - 50, -500, 50);

        textureRed = new Texture("MiniGames/Revolver/button-red.png");
        textureGreen = new Texture("MiniGames/Revolver/button-green.png");
        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (gs.isBusy) return false;
                isPressed = true;
                gs.isBusy = true;
                time = 0;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isPressed = false;
                if (timeAfter <= maxTimeAfter)
                    isPassed = true;
                else gs.dropResults();
            }
        });
    }

    public void dropResult() {
        isPassed = false;
        maxTime = (float) (Math.random() * (500 / reductionRate));
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (isPressed)
            rectSpring.width += reductionRate * delta;
        time += delta;
        timeAfter += delta;
        if (time >= maxTime) {
            gs.isBusy = false;
            timeAfter = 0;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(textureSpring, rectSpring.x, rectSpring.y, rectSpring.width, rectSpring.height);
        if (isPassed)
            batch.draw(textureGreen, getX(), getY(), getWidth(), getHeight());
        else
            batch.draw(textureRed, getX(), getY(), getWidth(), getHeight());
    }

    public boolean getIsPassed() {
        return isPassed;
    }
}
