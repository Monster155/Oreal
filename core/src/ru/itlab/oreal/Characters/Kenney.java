package ru.itlab.oreal.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

import ru.itlab.oreal.PlayerClasses.Joystick;
import ru.itlab.oreal.PlayerClasses.UI;
import ru.itlab.oreal.Utils.CreateFixture;

public class Kenney extends Actor {

    public Fixture body;
    private Texture texture;
    private Joystick joystick;
    private UI ui;
    private float speed = 5000, range;
    private Array<NPC> npcs;
    private int mapNumber;

    public Kenney(UI ui, World world, Array<NPC> npcs, int mapNumber) {
        super();
        this.mapNumber = mapNumber;
        this.npcs = npcs;
        range = 50;
        this.joystick = ui.getJoystick();
        this.ui = ui;
        setBounds(320, 180, 30, 40);
        body = CreateFixture.createBox(world, new Vector2(getX() + getWidth() / 2, getY()),
                new Vector2(getWidth() / 2, 5), false, "Kenney", (short) 1);
        texture = new Texture("Characters/main_hero.png");
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(texture,
                body.getBody().getPosition().x - getWidth() / 2,
                body.getBody().getPosition().y - 5,
                getWidth() + 0,
                getHeight() + 0);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        body.getBody().setLinearVelocity(joystick.cos * speed * delta, joystick.sin * speed * delta);
        for (NPC n : npcs) {
            for (int i = 0; i < n.numberOfDialogWithMe[mapNumber].length; i++) {
                n.canSay = n.numberOfDialogWithMe[mapNumber][i] == ui.dialogWindow.getDialogNumber();
                if (n.canSay) break;
            }
            if (isInRange(body.getBody().getPosition().x, n.getX(), body.getBody().getPosition().y, n.getY(), range) && n.canSay) {
                ui.interactButton.toggle(false, n, this);
                break;
            } else {
                ui.interactButton.toggle(true, n, this);
            }
        }
//        Gdx.app.log("Pos", body.getBody().getPosition().toString());
    }

    public float getHeightSize() {
        return getHeight() / 2 - 5; // check values in CreateFixture.createBox(...)
    }

    protected boolean isInRange(float x1, float x2, float y1, float y2, float r) {
        Vector3 range = new Vector3();
        range.x = (x1 - x2) * (x1 - x2);
        range.y = (y1 - y2) * (y1 - y2);
        range.z = r * r;

        return range.x + range.y <= range.z;
    }

    public Texture getTexture() {
        return texture;
    }
}
