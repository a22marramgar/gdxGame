package gdx.game.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import gdx.game.helpers.AssetManager;

public class Life extends Actor {

    private Vector2 position;
    private float width;
    private float height;

    public Life(float x, float y, int width, int height) {
        position = new Vector2(x,y);
        this.width = width;
        this.height = height;
    }

    @Override
    public void act(float delta) {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(AssetManager.life, position.x, position.y, width, height);
    }
}
