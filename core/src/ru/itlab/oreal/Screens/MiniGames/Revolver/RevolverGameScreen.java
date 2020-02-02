package ru.itlab.oreal.Screens.MiniGames.Revolver;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import ru.itlab.oreal.Screens.Main;

public class RevolverGameScreen implements Screen {
    public boolean isBusy;
    Stage stage;
    Array<ButtonAndSpring> actions;
    private Main main;

    public RevolverGameScreen(Main main) {
        this.main = main;
    }

    @Override
    public void show() {
        isBusy = false;

        stage = new Stage(new StretchViewport(640, 360));
        stage.addActor(new Background());

        actions = new Array<>();
        for (int i = 0; i < 3; i++) {
            actions.add(new ButtonAndSpring(i, this));
            stage.addActor(actions.get(i));
        }

        Gdx.input.setInputProcessor(stage);
    }

    public void dropResults() {
        for (ButtonAndSpring bas : actions) {
            bas.dropResult();
        }
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

    private boolean check() {
        for (ButtonAndSpring bas : actions) {
            if (!bas.getIsPassed())
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
        stage.dispose();
    }

    private class Background extends Actor {
        private Texture texture, textureLampActivated, textureLampDeactivated;

        public Background() {
            super();
            setBounds(0, 0, 640, 360);
            texture = new Texture("MiniGames/Revolver/background.png");
            textureLampActivated = new Texture("MiniGames/Revolver/lampA.png");
            textureLampDeactivated = new Texture("MiniGames/Revolver/lampD.png");
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            super.draw(batch, parentAlpha);
            batch.draw(texture, getX(), getY(), getWidth(), getHeight());
            if (isBusy)
                batch.draw(textureLampActivated, 580, 20, 40, 40);
            else
                batch.draw(textureLampDeactivated, 580, 20, 40, 40);
        }
    }
}
