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
import maindalorian.game.characters.enemies.Soldier;

public class PhaseOneSoldierScreen extends AbstractScreen implements Screen {

    Texture background;
    Sprite bgSprite;
    Texture txtBox;
    Sprite txtSprite;
    Enemy soldier;
    Player player;
    private String msg;
    private String choices;
    boolean isPussy;

    public PhaseOneSoldierScreen(Sandbox sandbox) {
        super(sandbox);
        msg = "Johnny: Have you guys seen my father?"
                + "\nSoldiers: Go home, kid! Don't you know we are at war? (says the soldier aggressively)";
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
        soldier = new Soldier(30);
        soldier.setScreen(this);
        player = new Player(200, 50);
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
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1) && !isPussy)
        {
            //player.attackP(enemy);
            msg = "Johnny runs and hides, scared and overwhelmed, waiting for the soldiers to leave."
            + "\nAt least he went home.\nPress Enter to continue.";
            choices = "";
            player.setCurrentWillPower(10);
            player.setCurrentHP(50);
            //playTurn(enemy);
            isPussy = true;
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_2) && !isPussy)
        {
            player.attackPA(enemy);

            msg = "What's this war for? Why does everybody need to fight? Do you guys really want it or is it just orders?"
            + "\nEveryone left their families behind to fight for our country kid.";
            player.setCurrentWillPower(75);

            playTurn(enemy);
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_3) && !isPussy)
        {
            player.attackA(enemy);

            msg = "Give me back my father! (throws rock)" + "\nSoldiers tie him to a tree.";
            player.setCurrentHP(player.getCurrentHP() - 40);
            player.setCurrentWillPower(player.getCurrentWillPower() - 40);

            playTurn(enemy);
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER) && isPussy){
            sandbox.setScreen(new PhaseOneGeneralScreen(sandbox, player));
        }
    }

    public void playTurn(Enemy enemy)
    {
        battle(enemy);

        if(enemy.isDead())
        {
            sandbox.setScreen(new PhaseOneGeneralScreen(sandbox, player));

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
