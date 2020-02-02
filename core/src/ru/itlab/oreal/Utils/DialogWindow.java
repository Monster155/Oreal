package ru.itlab.oreal.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import ru.itlab.oreal.Characters.Kenney;
import ru.itlab.oreal.Characters.NPC;

public class DialogWindow extends Actor {
    public boolean useDialogStage;
    private NPC npc;
    private Kenney player;
    private Vector2 relativePosition;
    private Stage stage;
    private int dialogNumber, dialogPhraseNumber;
    private Array<DialogHolder> dialogHolder;
    private BitmapFont font;
    private GlyphLayout glyph;
    private Texture percent70, textBackground;
    private int mapNumber;

    public DialogWindow(Stage myStage, final Stage mainStage, int mapNumber) {
        super();
        this.mapNumber = mapNumber;
        stage = myStage;
        useDialogStage = false;
        dialogNumber = 1;
        dialogPhraseNumber = 0;
        percent70 = new Texture("70percent.png");

        font = new BitmapFont(Gdx.files.internal("RuEn.fnt"));
        font.getData().setScale(0.4f);
        glyph = new GlyphLayout(font, "");

        textBackground = new Texture("ramka.png");
        relativePosition = new Vector2(0, 0);
        setBounds(relativePosition.x, relativePosition.y, 640, 360);
        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                dialogPhraseNumber++;
                if (dialogHolder.size <= dialogPhraseNumber) {
                    dialogNumber++;
                    Gdx.input.setInputProcessor(mainStage);
                    useDialogStage = false;
                    toBack();
                    return true;
                }
                glyph.setText(font, dialogHolder.get(dialogPhraseNumber).getText(), Color.WHITE, 540, Align.left, true);
                Gdx.app.log("Dialog Phrase Number", dialogPhraseNumber + "");
                return true;
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (useDialogStage) {
            if (dialogHolder.get(dialogPhraseNumber).getIsPlayerTell()) {
                batch.draw(npc.getTexture(), getX() + 400, getY() + 0, npc.getWidth() / npc.getHeight() * 300, 300);
                batch.draw(percent70, 0, 0, 640, 360);
                batch.draw(player.getTexture(), getX() + 30, getY() + 0, player.getWidth() / player.getHeight() * 300, 300);
            } else {
                batch.draw(player.getTexture(), getX() + 30, getY() + 0, player.getWidth() / player.getHeight() * 300, 300);
                batch.draw(percent70, 0, 0, 640, 360);
                batch.draw(npc.getTexture(), getX() + 400, getY() + 0, npc.getWidth() / npc.getHeight() * 300, 300);
            }
            batch.draw(textBackground, getX(), getY(), getWidth(), getHeight());
            font.draw(batch, glyph, 50, 85);
        }
    }

    public void startDialog(NPC npc, Kenney player) {
        this.npc = npc;
        this.player = player;
        dialogPhraseNumber = 0;
        dialogHolder = DialogsList.getDialog(dialogNumber, mapNumber);
        glyph.setText(font, dialogHolder.get(dialogPhraseNumber).getText(), Color.WHITE, 540, Align.left, true);
        toFront();
        useDialogStage = true;
        Gdx.input.setInputProcessor(stage);
    }

    public int getDialogNumber() {
        return dialogNumber;
    }

    public void setDialogNumber(int dialogNumber) {
        this.dialogNumber = dialogNumber;
    }
}
