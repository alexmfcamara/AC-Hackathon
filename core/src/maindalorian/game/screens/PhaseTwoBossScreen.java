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
import maindalorian.game.characters.enemies.Mother;
import maindalorian.game.characters.enemies.Teacher;

public class PhaseTwoBossScreen extends AbstractScreen implements Screen {
    Texture background;
    Sprite bgSprite;
    Texture txtBox;
    Sprite txtSprite;
    Enemy soldier;
    Player player;
    private String msg;
    private String choices;
    private Sound sound;

    public PhaseTwoBossScreen(Sandbox sandbox, Player player) {
        super(sandbox);
        this.player = player;
        msg = "Johnny: Teacher why do we have so many rules?"
                + "\nTeacher: Rules are to avoid danger. They are our last wall of protection.";
        choices = "1. Passive Approach        2. Neutral Approach        3. Aggressive Approach";
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(2);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
        batch.setProjectionMatrix(camera.combined);
        background = new Texture(Gdx.files.internal("bg-2.png"));
        bgSprite = new Sprite(background, 0, 0, 1280, 720);
        bgSprite.setPosition(0, 0);
        txtBox = new Texture(Gdx.files.internal("textbox.png"));
        txtSprite = new Sprite(txtBox, 0, 0, 1280, 720);
        txtSprite.setPosition(0, 0);
        soldier = new Teacher(350);
        sound = Gdx.audio.newSound(Gdx.files.internal("boss.mp3"));
        sound.play();
        soldier.setScreen(this);
        player.setScreen(this);
    }

    @Override
    public void render(float delta) {
        batch.begin();
        bgSprite.draw(batch);
        txtSprite.draw(batch);
        font.draw(batch, msg, 160, 150);
        font.draw(batch, choices, 150, 50);
        drawInstances();
        chooseAttack(soldier);
        batch.end();
    }

    public void chooseAttack(Enemy enemy)
    {
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1))
        {
            player.attackP(enemy);

            msg = "Johnny: I think I understand. Then we are all meant to be part of this protection."
                    + "\nTeacher: In the end we are all another brick on the wall.";
            //player.setCurrentHP(0);
            //player.setCurrentWillPower(0);

            playTurn(enemy);
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_2))
        {
            player.attackPA(enemy);

            msg = "Johnny: I don't think they are just meant to protect us." +
                    "\nTeacher: You are just a kid after all. Maybe you will get it after some punishment.";
            //player.setCurrentHP(0);
            //player.setCurrentWillPower(0);

            playTurn(enemy);
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_3))
        {
            player.attackA(enemy);

            msg = "Johnny: You're just trying to brainwash us, I will not let you do it to me!!" +
                    "\nTeacher: DO NOT CONTEST THE RULES!";

            //player.setCurrentHP(0);
            //player.setCurrentWillPower(0);

            playTurn(enemy);
        }
    }

    public void playTurn(Enemy enemy)
    {
        battle(enemy);

        if(enemy.isDead())
        {
            sandbox.setScreen(new WallScreen(sandbox, player));

        }

        if(player.isDead())
        {
            sandbox.setScreen(new GameOverScreen(sandbox));
        }
    }

    public void battle(Enemy enemy)
    {
        enemy.attack(player);
    }

    public void drawInstances(){
        player.draw(batch);
        soldier.draw(batch);
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
