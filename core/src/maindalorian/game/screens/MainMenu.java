package maindalorian.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import maindalorian.game.Sandbox;

public class MainMenu implements Screen {

    final Sandbox sandbox;
    OrthographicCamera camera;
    Texture background;
    Sprite bgSprite;
    SpriteBatch batch;


    public MainMenu(Sandbox sandbox) {
        this.sandbox = sandbox;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
        batch.setProjectionMatrix(camera.combined);
        background = new Texture(Gdx.files.internal("main-menu.jpg"));
        bgSprite = new Sprite(background, 0, 0, 1280, 720);
        bgSprite.setPosition(0, 0);
    }

    @Override
    public void render(float delta) {
        batch.begin();
        bgSprite.draw(batch);
        startGame();
        batch.end();
    }


    private void startGame() {
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            sandbox.setScreen(new InitialDScreen(sandbox));
        }
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
    }
}
