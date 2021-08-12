package maindalorian.game.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import maindalorian.game.characters.enemies.Enemy;

public class Player extends Character{
    private int maxWillPower;
    private int currentWillPower;
    public Texture texture;
    public Sprite sprite;

    public Player(int maxHP, int willPower)
    {
        super(maxHP);
        this.maxWillPower = willPower;
        currentWillPower = maxWillPower;
        texture = new Texture(Gdx.files.internal("hero-1.png"));
        sprite = new Sprite(texture, 112, 246);
        sprite.setPosition(160, 197);
    }

    public void setCurrentWillPower(int currentWillPower)
    {
        this.currentWillPower = currentWillPower;
    }

    public void setMaxWillPower(int maxWillPower)
    {
        this.maxWillPower = maxWillPower;
    }

    public int getCurrentWillPower()
    {
        return currentWillPower;
    }

    public int getMaxWillPower()
    {
        return maxWillPower;
    }

    public int takeWillPowerDmg(int dmg){
        return currentWillPower - dmg;
    }

    public int attackP(Enemy enemy)
    {
        return enemy.takeDmg(5);
    }

    public int attackPA(Enemy enemy)
    {
        return enemy.takeDmg(7);
    }

    public int attackA(Enemy enemy)
    {
        return enemy.takeDmg(9);
    }

    public void draw(SpriteBatch batch){
        sprite.draw(batch);
        screen.font.draw(screen.batch, "Johnny Boy\nHP:" + currentHP + "\nWill:" + currentWillPower, 947, 343);
    }

    @Override
    public boolean isDead(){
        return (currentHP <= 0 || currentWillPower <= 0);
    }
}
