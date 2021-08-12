package maindalorian.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import maindalorian.game.Sandbox;
import maindalorian.game.characters.Player;
import maindalorian.game.characters.enemies.Enemy;
import maindalorian.game.characters.enemies.General;
import maindalorian.game.characters.enemies.Soldier;

public class PhaseOneGeneralScreen extends AbstractScreen implements Screen {


    Texture background;
    Sprite bgSprite;
    Texture txtBox;
    Sprite txtSprite;
    Enemy soldier;
    Player player;
    private String msg;
    private String choices;

    public PhaseOneGeneralScreen(Sandbox sandbox,Player player) {
        super(sandbox);
        this.player = player;
        msg = "Johnny: Are you the one who can bring my Father back?"
        + "\nGeneral: You shouldn't be here kid, much less meddle in matters of war.";
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
        background = new Texture(Gdx.files.internal("bg.png"));
        bgSprite = new Sprite(background, 0, 0, 1280, 720);
        bgSprite.setPosition(0, 0);
        txtBox = new Texture(Gdx.files.internal("textbox.png"));
        txtSprite = new Sprite(txtBox, 0, 0, 1280, 720);
        txtSprite.setPosition(0, 0);
        soldier = new General(150);
        soldier.setScreen(this);
        player.setScreen(this);
    }

    @Override
    public void render(float delta) {
        batch.begin();
        bgSprite.draw(batch);
        txtSprite.draw(batch);
        drawInstances();
        chooseAttack(soldier);
        font.draw(batch, msg, 160, 150);
        font.draw(batch, choices, 150, 50);
        batch.end();
    }

    public void chooseAttack(Enemy enemy)
    {
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1))
        {
            player.attackP(enemy);

            msg ="(Johnny starts crying)" + "\nGeneral: Someone please take this kid home!";

            //player.setCurrentHP(0);
            //player.setCurrentWillPower(0);

            playTurn(enemy);
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_2))
        {
            player.attackPA(enemy);

            msg = "Johnny: I don't want to meddle in matters of war. I just want my Father back!"
                    + "\nGeneral: Wake up, kid! No one can bring your father back.";

            //player.setCurrentHP(0);
            //player.setCurrentWillPower(0);

            playTurn(enemy);
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_3))
        {
            player.attackA(enemy);

            msg = "Johnny: Give me back my father! (attempts to steal gun)"
                    + "\nGeneral: You have gone too far kid!!";

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
            sandbox.setScreen(new PhaseOneBossScreen(sandbox, player));

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
