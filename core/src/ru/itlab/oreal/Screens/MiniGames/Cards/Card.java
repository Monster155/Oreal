package ru.itlab.oreal.Screens.MiniGames.Cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Card extends Actor {

    private int num;
    private Texture card;
    private Texture reverced;
    private boolean isReversed;
    private float time;


    public Card(int num, int x, int y) {
        this.card = new Texture(num + ".png");
        this.num = num;
        this.reverced = new Texture("0.png");
        this.isReversed = true;
        this.time = 4;

        setBounds(320 + (x - 4) * 60, 180 + (y - 1) * 90,
                card.getWidth(), card.getHeight());
        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isReversed = false;
                return true;
            }
        });
    }

    @Override
    public void act(float delta) {
        time += delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(!isReversed || time < 1){
            batch.draw(card, getX(), getY(), getWidth(), getHeight());
        }
        else{
            batch.draw(reverced, getX(), getY(), getWidth(), getHeight());
        }
    }

    public int getNum() {
        return num;
    }


    public boolean getIsReversed(){
        return isReversed;
    }

    public void setIsReversed(boolean isReversed){
        this.isReversed = isReversed;
        time = 0;
    }
}
