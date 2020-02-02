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

import ru.itlab.oreal.Characters.Bob;
import ru.itlab.oreal.Characters.Cab;
import ru.itlab.oreal.Characters.Kenney;
import ru.itlab.oreal.Characters.NPC;
import ru.itlab.oreal.Characters.TownMayor;
import ru.itlab.oreal.PlayerClasses.Camera;
import ru.itlab.oreal.PlayerClasses.UI;
import ru.itlab.oreal.Utils.DialogWindow;
import ru.itlab.oreal.Utils.TiledObjectUtil;

public class FirstTownScreen implements Screen {

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

    Main main;

    public FirstTownScreen(Main main) {
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

        dialogWindow = new DialogWindow(dialogStage, stage, 2);
        ui = new UI(dialogWindow);
        npcs = new Array<>();
        npcs.add(new TownMayor(false), new Bob(), new Cab());
        npcs.get(0).setPosition(921, 535);
        npcs.get(1).setPosition(470, 310);
        npcs.get(2).setPosition(150, 155);

        kenney = new Kenney(ui, world, npcs, 1);
        kenney.body.getBody().setTransform(395, 310, 0);

        for (NPC b : npcs) {
            stage.addActor(b);
        }

        dialogStage.addActor(dialogWindow);

        stage.addActor(ui);
        stage.addActor(kenney);

        camera.update(kenney.body.getBody().getPosition(), ui);

        mapBody = new Array<>();
        map = new TmxMapLoader().load("FirstTown/Maps1.tmx");
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
        if (dialogWindow.getDialogNumber() >= 9) {
            main.setScreen(main.secondTownScreen);
            main.isGameEnd = false;
        }

        if (dialogWindow.getDialogNumber() == 6) {
            npcs.get(1).setPosition(678, 265);
        }

        if (dialogWindow.getDialogNumber() == 3 && !main.isGameEnd) {
            main.setScreen(main.woodenRoofGameScreen);
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
        stage.dispose();
        tmr.dispose();
        map.dispose();
        for (Fixture f : mapBody) {
            world.destroyBody(f.getBody());
        }
        world.dispose();
    }
}
