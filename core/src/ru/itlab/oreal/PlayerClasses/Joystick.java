package ru.itlab.oreal.PlayerClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Joystick extends Actor {

    public float sin = 0, cos = 0;
    private Circle backCircle, stickCircle;
    private Texture stickTexture, backTexture;
    private Vector2 relativePosition;

    public Joystick() {
        relativePosition = new Vector2(0, 0);
        setBounds(relativePosition.x, relativePosition.y, 450, 360);
        stickTexture = new Texture(Gdx.files.internal("joystick/stick.png"));
        backTexture = new Texture(Gdx.files.internal("joystick/circle.png"));
        backCircle = new Circle(80, 80, 75);
        stickCircle = new Circle(backCircle.x, backCircle.y, 30);
        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                backCircle.setPosition(x, y);
                stickCircle.setPosition(x, y);
                return true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                stickCircle.setPosition(x, y);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                stickCircle.setPosition(backCircle.x, backCircle.y);
            }
        });
    }

    @Override
    public void act(float delta) {
        float x = stickCircle.x - backCircle.x;
        float y = stickCircle.y - backCircle.y;
        float radius = (float) (Math.sqrt(x * x + y * y));

        if (radius == 0) radius = 1;
        sin = y / radius;
        cos = x / radius;

        if (!backCircle.contains(stickCircle.x, stickCircle.y)) {
            stickCircle.x = backCircle.radius * cos + backCircle.x;
            stickCircle.y = backCircle.radius * sin + backCircle.y;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(backTexture,
                backCircle.x - backCircle.radius, backCircle.y - backCircle.radius,
                2 * backCircle.radius, 2 * backCircle.radius);
        batch.draw(stickTexture,
                stickCircle.x - stickCircle.radius, stickCircle.y - stickCircle.radius,
                2 * stickCircle.radius, 2 * stickCircle.radius);
    }

}
