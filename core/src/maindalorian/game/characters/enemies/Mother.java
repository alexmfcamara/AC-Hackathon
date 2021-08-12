package maindalorian.game.characters.enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import maindalorian.game.RandomGenerator;
import maindalorian.game.characters.Player;

public class Mother extends Enemy{
    Texture texture;
    Sprite sprite;

    public Mother(int maxHP)
    {
        super(maxHP);
        texture = new Texture(Gdx.files.internal("mother.png"));
        sprite = new Sprite(texture, 300, 286);
        sprite.setPosition(940, 400);
    }

    public void draw(SpriteBatch batch){
        sprite.draw(batch);
        screen.font.draw(screen.batch, "Mother\nHP:" + currentHP, 220, 650);
    }

    public int fire(Player player){
        return player.takeDmg(5);
    }

    public int placeholdernameporagora(Player player){
        return player.takeWillPowerDmg(5);
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
}
