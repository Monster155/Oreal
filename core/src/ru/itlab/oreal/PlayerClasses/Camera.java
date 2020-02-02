package ru.itlab.oreal.PlayerClasses;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;

public class Camera {
    public OrthographicCamera camera;

    public Camera(Vector2 pos) {
        camera = new OrthographicCamera(640, 360);
        camera.position.set(pos.x, pos.y, camera.position.z);
    }

    public void update(Vector2 pos, Group group) {
        group.setPosition(pos.x - 320, pos.y - 180);
        camera.position.set(pos.x, pos.y, camera.position.z);
        camera.update();
    }

    public void update(float x, float y, Group group) {
        update(new Vector2(x, y), group);
    }
}