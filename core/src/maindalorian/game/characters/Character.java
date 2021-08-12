package maindalorian.game.characters;

import maindalorian.game.screens.AbstractScreen;
import maindalorian.game.screens.PhaseOneGeneralScreen;
import maindalorian.game.screens.PhaseOneSoldierScreen;

public abstract class Character {
    private int maxHP;
    protected int currentHP;
    protected AbstractScreen screen;


    public Character(int maxHP)
    {
        this.maxHP = maxHP;
        currentHP = maxHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public void setScreen(AbstractScreen screen) {
        this.screen = screen;
    }

    public boolean isDead()
    {
        return currentHP <=0;
    }

    public int getCurrentHP()
    {
        return currentHP;
    }

    public int getMaxHP()
    {
        return maxHP;
    }

    public int takeDmg(int damage)
    {
        int result = currentHP - damage;
        currentHP -= damage;
        return result;
    }
}
