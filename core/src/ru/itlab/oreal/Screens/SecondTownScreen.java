package ru.itlab.oreal.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import ru.itlab.oreal.Characters.Cab;
import ru.itlab.oreal.Characters.Kenney;
import ru.itlab.oreal.Characters.Man;
import ru.itlab.oreal.Characters.NPC;
import ru.itlab.oreal.Characters.NotSteve;
import ru.itlab.oreal.Characters.TrainWoman;
import ru.itlab.oreal.PlayerClasses.Camera;
import ru.itlab.oreal.PlayerClasses.UI;
import ru.itlab.oreal.Utils.DialogWindow;
import ru.itlab.oreal.Utils.TiledObjectUtil;

public class SecondTownScreen implements Screen {
    Stage stage, dialogStage;
    StretchViewport viewport;
    World world;
    TiledMap map;
    OrthogonalTiledMapRenderer tmr;
    Box2DDebugRenderer b2ddr;
    Array<Fixture> mapBody;

    Kenney kenney;
    Camera camera;
    Array<NPC> npcs;
    UI ui;
    DialogWindow dialogWindow;
    private Main main;

    public SecondTownScreen(Main main) {
        this.main = main;
    }

    @Override
    public void show() {
        camera = new Camera(new Vector2(0, 0));
        viewport = new StretchViewport(640, 360, camera.camera);
        stage = new Stage(viewport);
        dialogStage = new Stage(new StretchViewport(640, 360));

        world = new World(new Vector2(0, 0), true);
        b2ddr = new Box2DDebugRenderer();

        dialogWindow = new DialogWindow(dialogStage, stage, 3);
        ui = new UI(dialogWindow);
        npcs = new Array<>();
        npcs.add(new Cab(), new Man(), new NotSteve(), new TrainWoman());
        npcs.get(0).setPosition(642, 518);
        npcs.get(1).setPosition(641, 380);
        npcs.get(2).setPosition(837, 400);
        npcs.get(3).setPosition(1196, 107);

        kenney = new Kenney(ui, world, npcs, 2);
        kenney.body.getBody().setTransform(579, 512, 0);

        for (NPC b : npcs) {
            stage.addActor(b);
        }

        dialogStage.addActor(dialogWindow);

        stage.addActor(ui);
        stage.addActor(kenney);

        camera.update(kenney.body.getBody().getPosition(), ui);

        mapBody = new Array<>();
        map = new TmxMapLoader().load("SecondTown/Maps2.tmx");
        tmr = new OrthogonalTiledMapRenderer(map, 3);
        mapBody = TiledObjectUtil.buildBuildingsBodies(map, world, 3);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        world.step(delta, 6, 2);
        if (dialogWindow.useDialogStage) {
            dialogStage.act();
            dialogStage.draw();
        } else {
            tmr.setView((OrthographicCamera) stage.getCamera());

            Gdx.gl.glClearColor(0, 0.5f, 0.5f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            tmr.render();

            stage.act();
            camera.update(kenney.body.getBody().getPosition().x, kenney.body.getBody().getPosition().y + kenney.getHeightSize(), ui);
            stage.draw();

//            b2ddr.render(world, stage.getCamera().combined);
        }

        if (dialogWindow.getDialogNumber() >= 6) {
            main.setScreen(main.thirdTownScreen);
            main.isGameEnd = false;
        }

        if (dialogWindow.getDialogNumber() == 4 && !main.isGameEnd) {
            main.setScreen(main.cardsGameScreen);
            main.oldScreen = this;
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        dialogStage.getViewport().update(width, height, true);
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
}
