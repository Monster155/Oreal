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

import ru.itlab.oreal.Characters.Kenney;
import ru.itlab.oreal.Characters.NPC;
import ru.itlab.oreal.Characters.Plumber;
import ru.itlab.oreal.Characters.Steve;
import ru.itlab.oreal.PlayerClasses.Camera;
import ru.itlab.oreal.PlayerClasses.UI;
import ru.itlab.oreal.Utils.DialogWindow;
import ru.itlab.oreal.Utils.TiledObjectUtil;

public class ThirdTownScreen implements Screen {
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

    public ThirdTownScreen(Main main) {
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

        dialogWindow = new DialogWindow(dialogStage, stage, 4);
        ui = new UI(dialogWindow);
        npcs = new Array<>();
        npcs.add(new Plumber(), new Steve());
        npcs.get(0).setPosition(1104, 310);
        npcs.get(1).setPosition(135, 535);

        kenney = new Kenney(ui, world, npcs, 3);
        kenney.body.getBody().setTransform(1154, 156, 0);

        for (NPC b : npcs) {
            stage.addActor(b);
        }

        dialogStage.addActor(dialogWindow);

        stage.addActor(ui);
        stage.addActor(kenney);

        camera.update(kenney.body.getBody().getPosition(), ui);

        mapBody = new Array<>();
        map = new TmxMapLoader().load("ThirdTown/Maps3.tmx");
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
        if (dialogWindow.getDialogNumber() == 3) {
            npcs.get(1).setPosition(542, 677);
        }
        if (dialogWindow.getDialogNumber() == 4 && !main.isGameEnd) {
            main.setScreen(main.revolverGameScreen);
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
