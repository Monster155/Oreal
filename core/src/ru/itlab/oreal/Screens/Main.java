package ru.itlab.oreal.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector2;

import ru.itlab.oreal.Screens.MiniGames.Cards.CardsGameScreen;
import ru.itlab.oreal.Screens.MiniGames.Revolver.RevolverGameScreen;
import ru.itlab.oreal.Screens.MiniGames.WoodenRoof.WoodenRoofGameScreen;

public class Main extends Game {

    public boolean isGameEnd;
    public boolean isDone;
    StartMapScreen startMapScreen;
    FirstTownScreen firstTownScreen;
    SecondTownScreen secondTownScreen;
    ThirdTownScreen thirdTownScreen;
    RevolverGameScreen revolverGameScreen;
    WoodenRoofGameScreen woodenRoofGameScreen;
    CardsGameScreen cardsGameScreen;
    Music music;

    Screen oldScreen;
    Vector2 kenneyPosition;

    @Override
    public void create() {
        startMapScreen = new StartMapScreen(this);
        firstTownScreen = new FirstTownScreen(this);
        secondTownScreen = new SecondTownScreen(this);
        thirdTownScreen = new ThirdTownScreen(this);

        revolverGameScreen = new RevolverGameScreen(this);
        woodenRoofGameScreen = new WoodenRoofGameScreen(this);
        cardsGameScreen = new CardsGameScreen(this);
        isGameEnd = false;
        isDone = false;

        setScreen(firstTownScreen);//TODO don't forget to change this
    }

    @Override
    public void render() {
        super.render();
        if (isDone) {
            isDone = false;
            getScreen().dispose();
            setScreen(oldScreen);
            if (oldScreen.equals(firstTownScreen)) {
                firstTownScreen.step = 6;
                firstTownScreen.dialogWindow.setDialogNumber(3);
                firstTownScreen.kenney.body.getBody().setTransform(kenneyPosition, 0);
            } else if (oldScreen.equals(secondTownScreen)) {
                secondTownScreen.dialogWindow.setDialogNumber(4);
            } else if (oldScreen.equals(thirdTownScreen)) {
                thirdTownScreen.dialogWindow.setDialogNumber(4);
            }
        }
//        music = Gdx.audio.newMusic(Gdx.files.internal("gav.mp3"));
//        music.setLooping(true);
//        music.play();
    }

    @Override
    public void dispose() {
        startMapScreen.dispose();
        firstTownScreen.dispose();
        secondTownScreen.dispose();
        thirdTownScreen.dispose();
    }
}
