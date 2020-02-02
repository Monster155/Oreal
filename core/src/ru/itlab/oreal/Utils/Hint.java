package ru.itlab.oreal.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Hint extends Actor {
    private Texture background;
    private BitmapFont font;
    private GlyphLayout glyph;
    private boolean isDraw;
    private float x, y;

    public Hint() {
        super();
        background = new Texture("BackDialog.png");
        font = new BitmapFont(Gdx.files.internal("RuEn.fnt"));
        glyph = new GlyphLayout(font, "");
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (isDraw) {
            batch.draw(background, getX() - 20, getY() + 20, glyph.width + 40, -glyph.height - 40);
            font.draw(batch, glyph, getX(), getY());
        } else {
            toBack();
        }
    }

    public void setText(String text, float x, float y, boolean isInCenter) {
        isDraw = true;
        glyph.setText(font, text);
        if (isInCenter) {
            setPosition(x - glyph.width / 2, y + glyph.height);
        } else {
            setPosition(x - glyph.width, y + glyph.height);
        }
        toFront();
    }

    public void setIsDraw(boolean isDraw) {
        this.isDraw = isDraw;
    }
}
