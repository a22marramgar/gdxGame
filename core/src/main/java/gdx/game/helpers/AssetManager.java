package gdx.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class AssetManager {

    public static Texture player, playerLeft, playerRight, droplet, life, enemy;

    public static void load(){

        player = new Texture(Gdx.files.internal("dish.png"));

        playerLeft = new Texture(Gdx.files.internal("bowl.png"));

        playerRight = new Texture(Gdx.files.internal("bowl.png"));
        droplet = new Texture(Gdx.files.internal("sandwich.png"));
        life = new Texture(Gdx.files.internal("life.png"));
        enemy = new Texture(Gdx.files.internal("bucket.png"));
    }

    public static void dispose(){
        player.dispose();
        playerRight.dispose();
        playerLeft.dispose();
        droplet.dispose();
        life.dispose();
        enemy.dispose();
    }
}
