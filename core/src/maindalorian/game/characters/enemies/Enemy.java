package maindalorian.game.characters.enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import maindalorian.game.Sandbox;
import maindalorian.game.characters.Character;
import maindalorian.game.characters.Player;

public abstract class Enemy extends Character {
    public Enemy(int maxHP)
    {
        super(maxHP);
    }

    public int attack(Player player){
        return 0;
    }

    public void draw(SpriteBatch batch){

    }
    public Enemy invokeNextEnemy(SpriteBatch batch) {
        return null;
    }



}
