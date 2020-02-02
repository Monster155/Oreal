package ru.itlab.oreal.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;

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

        setScreen(firstTownScreen);
    }

    @Override
    public void render() {
        super.render();
        if (isDone) {
            isDone = false;
            setScreen(oldScreen);
            if (oldScreen.equals(firstTownScreen)) {
                firstTownScreen.dialogWindow.setDialogNumber(3);
            } else if (oldScreen.equals(secondTownScreen)) {
                firstTownScreen.dialogWindow.setDialogNumber(4);
            } else if (oldScreen.equals(thirdTownScreen)) {
                firstTownScreen.dialogWindow.setDialogNumber(4);
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
