package gdx.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class AssetManager {

    public static Texture player, playerLeft, playerRight, droplet, life;

    public static void load(){

        player = new Texture(Gdx.files.internal("bucket.png"));

        playerLeft = new Texture(Gdx.files.internal("bucket.png"));

        playerRight = new Texture(Gdx.files.internal("bucket.png"));
        droplet = new Texture(Gdx.files.internal("droplet.png"));
        life = new Texture(Gdx.files.internal("life.png"));
    }

    public static void dispose(){
        player.dispose();
        playerRight.dispose();
        playerLeft.dispose();
        droplet.dispose();
        life.dispose();
    }
}
