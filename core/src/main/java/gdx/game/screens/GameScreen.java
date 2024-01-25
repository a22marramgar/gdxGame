package gdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;

import gdx.game.helpers.AssetManager;
import gdx.game.helpers.InputHandler;
import gdx.game.objects.Droplet;
import gdx.game.objects.Life;
import gdx.game.objects.Player;
import gdx.game.utils.Settings;

public class GameScreen implements Screen {

    private Stage stage;
    private Player player;
    private Preferences prefs;
    private ArrayList<Droplet> droplets;
    private Array<Life> lives;

    private long lastDropTime;
    public GameScreen(){
        OrthographicCamera camera = new OrthographicCamera(Settings.GAME_WIDTH, Settings.GAME_HEIGHT);
        camera.setToOrtho(false, Settings.GAME_WIDTH,Settings.GAME_HEIGHT);
        stage = new Stage();
        droplets = new ArrayList<>();
        player = new Player(Settings.PLAYER_STARTX, Settings.PLAYER_STARTY, Settings.PLAYER_WIDTH, Settings.PLAYER_HEIGHT);
        stage.addActor(player);
        Gdx.input.setInputProcessor(new InputHandler(this));
        player.setName("player");
        lives = new Array<Life>();
        for(int i = 0 ; i<Settings.HP; i++){
            Life life = new Life(((Settings.LIVE_SIZE+Settings.LIVE_SPACE_BETWEEN)*i) + Settings.LIVE_SPACE_BETWEEN, Settings.GAME_HEIGHT-Settings.LIVE_SIZE, Settings.LIVE_SIZE, Settings.LIVE_SIZE);
            lives.add(life);
            stage.addActor(life);
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        stage.draw();
        stage.act(delta);

        for(int i = 0; i<droplets.size(); i++){
            if(droplets.get(i).collides(player)){
                droplets.get(i).remove();
                droplets.remove(i);
                //to remove a life
                //lives.pop().remove();

            }
        }

        if(TimeUtils.nanoTime() - lastDropTime > Settings.DROPLET_COUNTER) spawnRaindrop();

    }

    public void spawnRaindrop(){
        Droplet droplet = new Droplet(MathUtils.random(Settings.DROPLET_SIZE/2,Settings.GAME_WIDTH-Settings.DROPLET_SIZE),Settings.GAME_HEIGHT,Settings.DROPLET_SIZE,Settings.DROPLET_SIZE, Settings.DROPLET_VELOCITY);
        droplets.add(droplet);
        stage.addActor(droplet);
        lastDropTime = TimeUtils.nanoTime();
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

    }

    @Override
    public void dispose() {

    }

    public Player getPlayer() {
        return player;
    }

    public Stage getStage() {
        return stage;
    }
}
