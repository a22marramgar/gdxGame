package gdx.game;

import com.badlogic.gdx.Game;

import gdx.game.helpers.AssetManager;
import gdx.game.screens.GameScreen;

public class Drop extends Game {
    @Override
    public void create() {
        AssetManager.load();
        setScreen(new GameScreen());
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetManager.dispose();
    }
}
