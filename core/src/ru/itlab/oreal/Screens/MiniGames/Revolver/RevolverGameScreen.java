package ru.itlab.oreal.Screens.MiniGames.Revolver;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import ru.itlab.oreal.Screens.Main;

public class RevolverGameScreen implements Screen {

    Stage stage;
    boolean isLampActivated;
    Array<ButtonAndSpring> bas;
    Main main;

    public RevolverGameScreen(Main main) {
        this.main = main;
    }

    @Override
    public void show() {
        stage = new Stage();
        isLampActivated = false;
        bas = new Array<>();

        stage.addActor(new BackgroundAndLamp());

        for (int i = 0; i < 3; i++) {
            bas.add(new ButtonAndSpring(i, this));
            stage.addActor(bas.get(i));
        }

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();

        if (check()) {
            main.isGameEnd = true;
            main.isDone = true;
        }
    }

    public void dropResults() {
        for (ButtonAndSpring b : bas) {
            b.dropValues();
        }
    }

    private boolean check() {
        for (ButtonAndSpring b : bas) {
            if (!b.getIsPassed())
                return false;
        }
        return true;
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    private class BackgroundAndLamp extends Actor {
        private Texture lampActivatedTexture, lampDeactivatedTexture, backgroundTexture;

        public BackgroundAndLamp() {
            super();
            setBounds(0, 0, 640, 360);
            backgroundTexture = new Texture("MiniGames/Revolver/background.png");
            lampActivatedTexture = new Texture("MiniGames/Revolver/lampA.png");
            lampDeactivatedTexture = new Texture("MiniGames/Revolver/lampD.png");
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            super.draw(batch, parentAlpha);
            batch.draw(backgroundTexture, getX(), getY(), getWidth(), getHeight());
            if (isLampActivated)
                batch.draw(lampActivatedTexture, 580, 20, 40, 40);
            else
                batch.draw(lampDeactivatedTexture, 580, 20, 40, 40);
        }

        @Override
        public void act(float delta) {
            super.act(delta);
        }
    }
}
