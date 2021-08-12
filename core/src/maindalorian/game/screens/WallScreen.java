package maindalorian.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import maindalorian.game.Sandbox;
import maindalorian.game.characters.Player;
import maindalorian.game.characters.enemies.Enemy;
import maindalorian.game.characters.enemies.Teacher;
import maindalorian.game.characters.enemies.Wall;

public class WallScreen extends AbstractScreen implements Screen {
    Texture background;
    Sprite bgSprite;
    Texture txtBox;
    Sprite txtSprite;
    Player player;
    Enemy wall;
    private String msg;
    private String choices;
    private Sound sound;
    int count = 0;
    int countN = 0;

    public WallScreen(Sandbox sandbox, Player player) {
        super(sandbox);
        this.player = player;
        msg ="Yes.";
        choices = "1. Submit                   2. Break the Wall";
    }

    @Override
    public void show() {
        wall = new Wall(1);
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(2);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
        batch.setProjectionMatrix(camera.combined);
        background = new Texture(Gdx.files.internal("wall-1.jpg"));
        bgSprite = new Sprite(background, 0, 0, 1280, 720);
        bgSprite.setPosition(0, 0);
        txtBox = new Texture(Gdx.files.internal("textbox.png"));
        txtSprite = new Sprite(txtBox, 0, 0, 1280, 720);
        txtSprite.setPosition(0, 0);
        sound = Gdx.audio.newSound(Gdx.files.internal("boss.mp3"));
        sound.play();
        player.setScreen(this);
    }

    @Override
    public void render(float delta) {
        batch.begin();
        bgSprite.draw(batch);
        txtSprite.draw(batch);
        font.draw(batch, msg, 160, 150);
        font.draw(batch, choices, 150, 50);
        chooseAttack(wall);
        drawInstances();
        batch.end();
    }

    public void chooseAttack(Enemy enemy)
    {
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1))
        {
            if(countN >= 2) {
                sandbox.setScreen(new GameOverScreen(sandbox));
            }
            msg = "Are you sure you want to be another brick in the Wall?";
            countN++;
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_2))
        {
            if(count == 1){
                background = new Texture(Gdx.files.internal("wall-2.jpg"));
                bgSprite.set(new Sprite(background, 0, 0, 1280, 720));
                bgSprite.setPosition(0, 0);
            }
            if(count == 2){
                background = new Texture(Gdx.files.internal("wall-3.jpg"));
                bgSprite.set(new Sprite(background, 0, 0, 1280, 720));
                bgSprite.setPosition(0, 0);
            }
            if(count == 3){
                background = new Texture(Gdx.files.internal("wall-4.jpg"));
                bgSprite.set(new Sprite(background, 0, 0, 1280, 720));
                bgSprite.setPosition(0, 0);
            }
            if(count == 4){
                background = new Texture(Gdx.files.internal("wall-5.jpg"));
                bgSprite.set(new Sprite(background, 0, 0, 1280, 720));
                bgSprite.setPosition(0, 0);
            }
            if(count >= 5){
                sandbox.setScreen(new WinScreen(sandbox));
            }
            msg = "DOWN TO THE WALL!!!!1!";
            count++;
        }

    }

    public void drawInstances(){
        player.draw(batch);
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
