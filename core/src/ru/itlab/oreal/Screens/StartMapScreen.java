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

import ru.itlab.oreal.Characters.Deputy;
import ru.itlab.oreal.Characters.Housekeeper;
import ru.itlab.oreal.Characters.Kenney;
import ru.itlab.oreal.Characters.NPC;
import ru.itlab.oreal.PlayerClasses.Camera;
import ru.itlab.oreal.PlayerClasses.UI;
import ru.itlab.oreal.Utils.Animations;
import ru.itlab.oreal.Utils.DialogWindow;
import ru.itlab.oreal.Utils.TiledObjectUtil;

public class StartMapScreen implements Screen {
    Main main;

    Camera camera;
    StretchViewport viewport;
    Stage stage, dialogStage;

    World world;
    TiledMap map;
    OrthogonalTiledMapRenderer tmr;
    Box2DDebugRenderer b2ddr;
    Array<Fixture> mapBody;

    Kenney kenney;
    DialogWindow dialogWindow;
    UI ui;
    Array<NPC> npcs;

    int step;
    Vector2 playerPos;

    Animations anims;

    public StartMapScreen(Main main) {
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

        mapBody = new Array<>();
        map = new TmxMapLoader().load("StartMap/sample_indoor.tmx");
        tmr = new OrthogonalTiledMapRenderer(map, 3.2f);
        mapBody = TiledObjectUtil.buildBuildingsBodies(map, world, 3.2f);

        npcs = new Array<>();
        npcs.add(new Deputy(), new Housekeeper());
        npcs.get(0).setPosition(980, 500);
        npcs.get(1).setPosition(1040, 760);
        dialogWindow = new DialogWindow(dialogStage, stage, 1);
        ui = new UI(dialogWindow);
        kenney = new Kenney(ui, world, npcs, 0);

        anims = new Animations();
        ui.addActor(anims);
        anims.toBack();

        dialogStage.addActor(dialogWindow);

        for (NPC n : npcs) {
            stage.addActor(n);
        }

        stage.addActor(ui);
        anims.toBack();
        stage.addActor(kenney);
        kenney.body.getBody().setTransform(930, 470, 0);
        camera.update(kenney.body.getBody().getPosition(), ui);

        Gdx.input.setInputProcessor(stage);

        step = 1;
        playerPos = new Vector2(kenney.body.getBody().getPosition());
    }

    @Override
    public void render(float delta) {
        world.step(delta, 6, 2);
        switch (step) {
            case 1:
                ui.getHint().setText("Используй джойстик слева,\nчтобы двигаться по уровню", 320, 280, true);
                if (!kenney.body.getBody().getPosition().equals(playerPos)) {
                    step++;
                }
                break;
            case 2:
                ui.getHint().setText("Используй кнопку справа\nи узнай, что здесь случилось", 320, 280, true);
                if (dialogWindow.useDialogStage)
                    step++;
                break;
            case 3:
                ui.getHint().setIsDraw(false);
                dialogStage.act();
                if (!dialogWindow.useDialogStage)
                    step++;
                break;
            case 4:
                ui.setCanContinue(false); // i can't speak before smbd went
                if (npcs.get(0).moveTo(1030, 570)) {
                    step++;
                    ui.setCanContinue(true);
                }
                break;
            case 5:
            case 8:
                if (dialogWindow.useDialogStage)
                    step++;
                break;
            case 6:
            case 9:
                dialogStage.act();
                if (!dialogWindow.useDialogStage)
                    step++;
                break;
            case 7:
                ui.setCanContinue(false);
                if (npcs.get(0).moveTo(950, 460)) {
                    stage.getActors().removeValue(npcs.get(0), true);
                    npcs.removeIndex(0);
                    step++;
                    ui.setCanContinue(true);
                }
                break;
            case 10:
                anims.canStart = true;
                kenney.toBack();
                anims.toFront();
                if (anims.isEnded) step++;
                break;
            case 11:
                main.setScreen(main.firstTownScreen);
                break;
        }
        if (!dialogWindow.useDialogStage) {
            tmr.setView((OrthographicCamera) stage.getCamera());
            stage.act();
            if (step == 7) npcs.get(npcs.size - 1).canSay = false;
            camera.update(kenney.body.getBody().getPosition().x, kenney.body.getBody().getPosition().y + kenney.getHeightSize(), ui);
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tmr.render();
        stage.draw();
//        b2ddr.render(world, stage.getCamera().combined);
        if (dialogWindow.useDialogStage)
            dialogStage.draw();
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
