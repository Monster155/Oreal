package ru.itlab.oreal.Screens.MiniGames.WoodenRoof;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import ru.itlab.oreal.Screens.Main;

public class WoodenRoofGameScreen implements Screen {
    Stage stage;
    Array<Piece> pieces;
    private Main main;

    public WoodenRoofGameScreen(Main main) {
        this.main = main;
    }

    @Override
    public void show() {
        stage = new Stage(new StretchViewport(640, 360));
        stage.addActor(new Background());

        pieces = new Array<>();

        for (int i = 1; i < 9; i++) {
            pieces.add(new Piece(i));
        }
        pieces.add(new Piece(0));
        for (int k = 0; k < 5; k++) {
            for (int i = 0; i < 9; i++) {
                pieces.swap((int) (Math.random() * pieces.size), i);
            }
        }
        for (int i = 0; i < 9; i++) {
            pieces.get(i).setPosition(320 + (i % 3 - 1.5f) * pieces.get(i).getWidth(),
                    180 - (i / 3 - 0.5f) * pieces.get(i).getHeight());
            stage.addActor(pieces.get(i));
        }

        stage.addActor(new Foreground());
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();

        if (checkForEnded()) {
            main.isGameEnd = true;
            main.isDone = true;
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
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

    private boolean checkForEnded() {
        for (int i = 0; i < 9; i++) {
            if (i != pieces.get(i).getNum()) {
                return false;
            }
        }
        return true;
    }

    private class Background extends Actor {
        private Texture texture;

        public Background() {
            super();
            setBounds(0, 0, 640, 360);
            texture = new Texture("MiniGames/WoodenRoof/background.png");
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            super.draw(batch, parentAlpha);
            batch.draw(texture, getX(), getY(), getWidth(), getHeight());
        }
    }

    private class Foreground extends Actor {
        int b = 0;
        private Vector2 lastPos;
        private boolean isFirstTime;

        public Foreground() {
            super();
            setBounds(0, 0, 640, 360);
            isFirstTime = true;
            addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    lastPos = new Vector2(x, y);
                    b++;
                    if (b > 5) {
                        main.isGameEnd = true;
                        main.isDone = true;
                    }
                    isFirstTime = true;
                    return true;
                }

                @Override
                public void touchDragged(InputEvent event, float x, float y, int pointer) {
                    if (!isFirstTime) return;
                    if (Math.abs(lastPos.x - x) > 10 || Math.abs(lastPos.y - y) > 10) { //движение в принципе, мб случайно
                        if (Math.abs(Math.abs(lastPos.x - x) - Math.abs(lastPos.y - y)) > 10) { //движение по диагонали
                            if (Math.abs(lastPos.x - x) > Math.abs(lastPos.y - y)) { //движ. по Х больше
                                //move by X
                                if (x - lastPos.x > 0) {
                                    //swipe left
                                    for (int i = 0; i < 9; i++) {
                                        if (i % 3 > 0) {
                                            if (pieces.get(i - 1).getNum() == 0) {
                                                pieces.swap(i, i - 1);
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    //swipe right
                                    for (int i = 0; i < 9; i++) {
                                        if (i % 3 < 2) {
                                            if (pieces.get(i + 1).getNum() == 0) {
                                                pieces.swap(i, i + 1);
                                                break;
                                            }
                                        }
                                    }
                                }
                            } else {
                                //move by Y
                                if (y - lastPos.y > 0) {
                                    //swipe up
                                    for (int i = 5; i >= 0; i--) {
                                        if (pieces.get(i + 3).getNum() == 0) {
                                            pieces.swap(i, i + 3);
                                            break;
                                        }
                                    }
                                } else {
                                    //swipe down
                                    for (int i = 3; i < 9; i++) {
                                        if (pieces.get(i - 3).getNum() == 0) {
                                            pieces.swap(i, i - 3);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    isFirstTime = false;
                    for (int i = 0; i < 9; i++) {
                        pieces.get(i).setPosition(320 + (i % 3 - 1.5f) * pieces.get(i).getWidth(),
                                180 - (i / 3 - 0.5f) * pieces.get(i).getHeight());
                    }
                }
            });
        }
    }
}
