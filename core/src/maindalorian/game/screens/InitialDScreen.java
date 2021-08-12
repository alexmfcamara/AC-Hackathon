package maindalorian.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import maindalorian.game.Sandbox;

public class InitialDScreen extends AbstractScreen implements Screen {
    Texture background;
    Sprite bgSprite;
    int count;

    InitialDScreen(Sandbox sandbox) {
        super(sandbox);
        count = 0;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
        batch.setProjectionMatrix(camera.combined);
        background = new Texture(Gdx.files.internal("intro-1.jpg"));
        bgSprite = new Sprite(background, 0, 0, 1280, 720);
        bgSprite.setPosition(0, 0);
    }

    @Override
    public void render(float delta) {
        batch.begin();
        bgSprite.draw(batch);
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            if (count == 0){
                background = new Texture(Gdx.files.internal("intro-2.jpg"));
                bgSprite.set(new Sprite(background, 0, 0, 1280, 720));
                bgSprite.setPosition(0, 0);
            }
            if (count == 1){
                background = new Texture(Gdx.files.internal("intro-3.jpg"));
                bgSprite.set(new Sprite(background, 0, 0, 1280, 720));
                bgSprite.setPosition(0, 0);
            }
            if(count == 2) {
                sandbox.setScreen(new PhaseOneSoldierScreen(sandbox));
            }
            count++;
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
