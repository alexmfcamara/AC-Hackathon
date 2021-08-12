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
import maindalorian.game.characters.enemies.Mother;

public class PhaseTwoMotherScreen extends AbstractScreen implements Screen {
    Texture background;
    Sprite bgSprite;
    Texture txtBox;
    Sprite txtSprite;
    Enemy soldier;
    Player player;
    private String msg;
    private String choices;

    public PhaseTwoMotherScreen(Sandbox sandbox, Player player) {
        super(sandbox);
        this.player = player;
        msg = "Mother: Come my baby, under my wing. Mama will keep you cozy and warm."
                + "\nJohnny: Why do I need to take these pills?";
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
        soldier = new Mother(170);
        soldier.setScreen(this);
        player.texture = new Texture(Gdx.files.internal("hero-2.png"));
        player.sprite.set(new Sprite(player.texture, 112, 246));
        player.sprite.setPosition(160, 197);
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

            msg = "Johnny: Ok mother, so what should i do?"
                    + "\nMother: Just let Mama put all of her fears into you...";
            //player.setCurrentHP(0);
            //player.setCurrentWillPower(0);

            playTurn(enemy);
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_2))
        {
            player.attackPA(enemy);

            msg = "Johnny: I think it's time to learn to fly and not just be under your wing. "
                    + "\nMother: Mama won't let you fly but she might let you sing.";
            //player.setCurrentHP(0);
            //player.setCurrentWillPower(0);

            playTurn(enemy);
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_3))
        {
            player.attackA(enemy);

            msg = "Johnny: Mother I won't listen to you!"
                    + "\nMother: Then Mama's gonna make all of your nightmares come true.";

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
            player.setCurrentHP(player.getCurrentHP() + 50);
            sandbox.setScreen(new PhaseTwoBossScreen(sandbox,player));

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
