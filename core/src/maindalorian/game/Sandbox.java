package maindalorian.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import maindalorian.game.screens.MainMenu;

public class Sandbox extends Game {

	private Music music;
	@Override
	public void create () {
		music = Gdx.audio.newMusic(Gdx.files.internal("8bit.mp3"));
		music.setLooping(true);
		music.play();
		this.setScreen(new MainMenu(this));

	}

	public void render() {
		super.render();
	}

	public void dispose() {

	}

}
