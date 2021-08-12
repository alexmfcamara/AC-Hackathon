package maindalorian.game.characters.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import maindalorian.game.RandomGenerator;
import maindalorian.game.characters.Player;

public class Soldier extends Enemy{

    Texture texture;
    Sprite sprite;

    public Soldier(int maxHP) {
        super(maxHP);
        texture = new Texture(Gdx.files.internal("soldiers.png"));
        sprite = new Sprite(texture, 398, 238);
        sprite.setPosition(800, 430);
    }

    @Override
    public void draw(SpriteBatch batch){
        sprite.draw(batch);
        screen.font.draw(screen.batch, "Soldier\nHP:" + currentHP, 220, 650);
    }

    @Override
    public int attack(Player player)
    {
        int getAttack = RandomGenerator.getRandomNumber(2);

        switch (getAttack){
            case 1:
                return fire(player);
            case 2:
                return placeholdernameporagora(player);
            default:
                return 0;
        }
    }

    private int placeholdernameporagora(Player player){
        return player.takeWillPowerDmg(5);
    }

    private int fire(Player player){
        return player.takeDmg(5);
    }

    @Override
    public Enemy invokeNextEnemy(SpriteBatch batch)
    {
        return null;
    }
}
