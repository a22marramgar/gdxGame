package gdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import gdx.game.Drop;
import gdx.game.ScreenManager;

public class GameOverScreen implements Screen {

    private Stage stage;

    private Batch batch;
    long lastTime;
    OrthographicCamera camera;
    private BitmapFont font = new BitmapFont();

    public GameOverScreen() {

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        lastTime = TimeUtils.nanoTime();
        stage = new Stage();
        batch = stage.getBatch();
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        font.draw(batch, "HAS PERDIDO ", 100, 150);
        font.draw(batch, "Desinstalando sistema operativo...", 100, 100);
        batch.end();
        if(TimeUtils.nanoTime() - lastTime > 2000000000){
            ScreenManager.setScreen(new MainMenuScreen());
            dispose();
        }

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
}
