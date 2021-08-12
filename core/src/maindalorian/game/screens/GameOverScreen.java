package maindalorian.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import maindalorian.game.Sandbox;
import maindalorian.game.characters.Player;
import maindalorian.game.characters.enemies.Enemy;

public class GameOverScreen extends AbstractScreen implements Screen {

    Texture background;
    Sprite bgSprite;
    private Sound sound;

    GameOverScreen(Sandbox sandbox) {
        super(sandbox);
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
        batch.setProjectionMatrix(camera.combined);
        background = new Texture(Gdx.files.internal("game-over.jpg"));
        bgSprite = new Sprite(background, 0, 0, 1280, 720);
        bgSprite.setPosition(0, 0);
        sound = Gdx.audio.newSound(Gdx.files.internal("game-over.mp3"));
        sound.play();
    }

    @Override
    public void render(float delta) {
        batch.begin();
        bgSprite.draw(batch);
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            sandbox.setScreen(new PhaseOneSoldierScreen(sandbox));
        }
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
