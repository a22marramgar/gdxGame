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

import gdx.game.ScreenManager;
import gdx.game.helpers.AssetManager;
import gdx.game.helpers.InputHandler;
import gdx.game.objects.Droplet;
import gdx.game.objects.Enemy;
import gdx.game.objects.Life;
import gdx.game.objects.Player;
import gdx.game.objects.ScoreText;
import gdx.game.objects.Text;
import gdx.game.utils.Settings;

public class GameScreen implements Screen {

    private Stage stage;
    private Player player;
    private Preferences prefs;
    private ArrayList<Droplet> droplets;
    private Array<Enemy> enemies;
    private Array<Life> lives;
    private long lastDropTime;
    private long lastEnemyTime;

    private long nextDropTime;
    private long nextEnemyTime;

    private ScoreText scoreText;
    public GameScreen(){
        OrthographicCamera camera = new OrthographicCamera(Settings.GAME_WIDTH, Settings.GAME_HEIGHT);
        camera.setToOrtho(false, Settings.GAME_WIDTH,Settings.GAME_HEIGHT);
        stage = new Stage();
        droplets = new ArrayList<>();
        enemies = new Array<>();
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
        scoreText = new ScoreText(Settings.LIVE_SPACE_BETWEEN, Settings.GAME_HEIGHT-Settings.LIVE_SIZE,0);
        stage.addActor(scoreText);
        nextDropTime = MathUtils.random(Settings.DROPLET_MIN_COUNTER,Settings.DROPLET_MAX_COUNTER);
        nextEnemyTime = MathUtils.random(Settings.ENEMY_MIN_COUNTER,Settings.ENEMY_MAX_COUNTER);
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
                scoreText.increment();
                //to remove a life
                //lives.pop().remove();

            }else if(droplets.get(i).isOutOfScreen()){
                droplets.get(i).remove();
            }
        }
        for(int i = 0; i<enemies.size; i++){
            if(enemies.get(i).collides(player)){
                enemies.get(i).remove();
                enemies.removeIndex(i);
                lives.pop().remove();
            }else if(enemies.get(i).isOutOfScreen()){
                enemies.get(i).remove();
            }
        }

        if(lives.isEmpty()) ScreenManager.setScreen(new GameOverScreen());
        if(TimeUtils.nanoTime() - lastDropTime > nextDropTime) spawnRaindrop();
        if(TimeUtils.nanoTime() - lastEnemyTime > nextEnemyTime) spawnEnemy();
    }

    private void spawnEnemy() {
        Enemy enemy = new Enemy(MathUtils.random(Settings.DROPLET_SIZE/2,Settings.GAME_WIDTH-Settings.DROPLET_SIZE),Settings.GAME_HEIGHT,Settings.DROPLET_SIZE,Settings.DROPLET_SIZE,
            Settings.ENEMY_VELOCITY - scoreText.getScore()*5);
        enemies.add(enemy);
        stage.addActor(enemy);
        lastEnemyTime = TimeUtils.nanoTime();
    }

    public void spawnRaindrop(){
        Droplet droplet = new Droplet(MathUtils.random(Settings.DROPLET_SIZE/2,Settings.GAME_WIDTH-Settings.DROPLET_SIZE),Settings.GAME_HEIGHT,Settings.DROPLET_SIZE,Settings.DROPLET_SIZE, Settings.DROPLET_VELOCITY);
        droplets.add(droplet);
        stage.addActor(droplet);
        lastDropTime = TimeUtils.nanoTime();
        nextDropTime = MathUtils.random(Settings.DROPLET_MIN_COUNTER,Settings.DROPLET_MAX_COUNTER);
        nextEnemyTime = MathUtils.random(Settings.ENEMY_MIN_COUNTER,Settings.ENEMY_MAX_COUNTER);
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
