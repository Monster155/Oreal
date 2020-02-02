package ru.itlab.oreal.Screens.MiniGames.Cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import ru.itlab.oreal.Screens.Main;

public class CardsGameScreen implements Screen {

    private StretchViewport viewport;
    private Stage stage;
    private Array<Card> cards;
    private int currentNum, currentNumIndex;
    private Main main;

    public CardsGameScreen(Main main) {
        this.main = main;
    }

    @Override
    public void show() {
        viewport = new StretchViewport(640, 360);
        stage = new Stage(viewport);

        cards = new Array<>();
        for (int i = 0; i < 16; i++) {
            cards.add(new Card((i + 2) / 2, i / 2, i % 2));
            stage.addActor(cards.get(i));
        }
        for (Card card : cards) {
            int idk = (int) (Math.random() * cards.size);
            float x = card.getX(), y = card.getY();

            card.setPosition(cards.get(idk).getX(), cards.get(idk).getY());
            cards.get(idk).setPosition(x, y);
            cards.swap(cards.indexOf(card, true), idk);
        }

        currentNum = 0;
        currentNumIndex = -1;
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        for (Card card : cards) {
            if (!card.getIsReversed()) {
                if (currentNum == 0) {
                    currentNum = card.getNum();
                    currentNumIndex = cards.indexOf(card, true);
                } else {
                    if (currentNumIndex != cards.indexOf(card, true)) {
                        if (currentNum == card.getNum()) {
                            cards.removeIndex(currentNumIndex);
                            cards.removeIndex(cards.indexOf(card, true));
                        } else {
                            cards.get(currentNumIndex).setIsReversed(true);
                            card.setIsReversed(true);
                        }
                        currentNum = 0;
                        currentNumIndex = -1;
                        break;
                    }
                }
            }
        }

        if (cards.size <= 0) {
            main.isGameEnd = true;
            main.isDone = true;
        }

        stage.act();
        stage.draw();
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

}
