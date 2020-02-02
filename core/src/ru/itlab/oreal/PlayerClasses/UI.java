package ru.itlab.oreal.PlayerClasses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import ru.itlab.oreal.Characters.Kenney;
import ru.itlab.oreal.Characters.NPC;
import ru.itlab.oreal.Utils.DialogWindow;
import ru.itlab.oreal.Utils.Hint;

public class UI extends Group {
    public InteractButton interactButton;
    public DialogWindow dialogWindow;
    private NPC npc;
    private Joystick joystick;
    private Kenney player;
    private Hint hint;
    private boolean canContinue;

    public UI(DialogWindow dialogWindow) {
        super();
        this.dialogWindow = dialogWindow;

        hint = new Hint();
        addActor(hint);

        joystick = new Joystick();
        addActor(joystick);

        interactButton = new InteractButton();
        addActor(interactButton);
    }

    public Joystick getJoystick() {
        return joystick;
    }

    public Hint getHint() {
        return hint;
    }

    public void setCanContinue(boolean canContinue) {
        this.canContinue = canContinue;
    }

    public class InteractButton extends Actor {
        private Texture up, down;
        private boolean isPressed, isDisable;
        private Vector2 relativePosition;

        public InteractButton() {
            super();
            isPressed = false;
            isDisable = true;
            canContinue = true;
            up = new Texture("BtnUnpressed.png");
            down = new Texture("BtnPressed.png");
            relativePosition = new Vector2(590, 50);
            setBounds(relativePosition.x, relativePosition.y, 40, 40);
            addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    if (isDisable || !canContinue) return true;
                    isPressed = true;
                    dialogWindow.startDialog(npc, player);
                    npc.canSay = false;
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    isPressed = false;
                }
            });
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            super.draw(batch, parentAlpha);
            if (isPressed || isDisable || !canContinue)
                batch.draw(down, getX(), getY(), getWidth(), getHeight());
            else
                batch.draw(up, getX(), getY(), getWidth(), getHeight());
        }

        public void toggle(boolean isDisable, NPC npc, Kenney player) {
            this.isDisable = isDisable;
            UI.this.npc = npc;
            UI.this.player = player;
        }
    }
}
