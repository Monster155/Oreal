package ru.itlab.oreal.Screens.MiniGames.Revolver;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class ButtonAndSpring extends Actor {
    private Texture springTexture, buttonRedTexture, buttonGreenTexture;
    private Rectangle rectSpring;
    private boolean isPassed, isPressed;
    private float value, limit, time, speed;
    private RevolverGameScreen rgs;

    public ButtonAndSpring(int num, final RevolverGameScreen rgs) {
        super();
        this.rgs = rgs;
        setBounds(20, 100 * (3 - num) - 50, 50, 50);
        isPassed = false;
        value = 0;
        time = 10 - num;
        speed = 500 / time;
        limit = (float) (Math.random() * (time - 6) + 4);

        springTexture = new Texture("MiniGames/Revolver/spring.png");
        rectSpring = new Rectangle(600, 100 * (3 - num) - 50, -500, 50);

        buttonRedTexture = new Texture("MiniGames/Revolver/button-red.png");
        buttonGreenTexture = new Texture("MiniGames/Revolver/button-green.png");

        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isPressed = true;
                rgs.isLampActivated = true;
                value = 0;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isPressed = false;
                rgs.isLampActivated = false;
                rectSpring.width = -500;

                if (value - limit < 1 && value > limit) {
                    isPassed = true;
                } else {
                    rgs.dropResults();
                }
                limit = (float) (Math.random() * (time - 6) + 4);
            }
        });
    }

    public void dropValues() {
        isPassed = false;
    }

    public boolean getIsPassed() {
        return isPassed;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(springTexture, rectSpring.x, rectSpring.y, rectSpring.width, rectSpring.height);
        if (isPassed)
            batch.draw(buttonGreenTexture, getX(), getY(), getWidth(), getHeight());
        else
            batch.draw(buttonRedTexture, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (isPressed) {
            value += delta;
            if (rectSpring.width < -10) {
                rectSpring.width += delta * speed;
            } else {
                rectSpring.width = -10;
            }
            if (value >= limit) {
                rgs.isLampActivated = false;
            }
        }
    }
}
