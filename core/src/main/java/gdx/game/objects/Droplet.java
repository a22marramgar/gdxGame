package gdx.game.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import gdx.game.helpers.AssetManager;

public class Droplet extends Actor {

    private Rectangle collisionRectangle;
    private Vector2 position;
    private float velocity;
    private float width;
    private float height;

    public Droplet(float x, float y, float width, float height, float velocity){
        position = new Vector2(x,y);
        this.width = width;
        this.height = height;
        this.velocity = velocity;
        collisionRectangle = new Rectangle();
    }

    public void act(float delta){
        position.y += velocity * delta;
        collisionRectangle.set(position.x , position.y, width , height );
    }

    public boolean collides(Player player){
        if (position.x <= player.getX() + player.getWidth()) {
// Comprovem si han col·lisionat sempre que l'asteroide estigui a la mateixa alçada que l'spacecraft
            return (Intersector.overlaps(collisionRectangle, player.getCollisionRect()));
        }
        return false;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(AssetManager.droplet, position.x, position.y, width, height);
    }
}
