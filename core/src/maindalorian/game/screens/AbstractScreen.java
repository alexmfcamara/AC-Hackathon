package maindalorian.game.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import maindalorian.game.Sandbox;

public abstract class AbstractScreen {
    public final Sandbox sandbox;
    public OrthographicCamera camera;
    public SpriteBatch batch;
    public BitmapFont font;
    AbstractScreen(Sandbox sandbox){
        this.sandbox = sandbox;
    }

}
