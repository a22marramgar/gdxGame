package gdx.game.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Text extends Actor {

    private BitmapFont font;
    private String text;
    private Vector2 position;
     //I assumed you have some object
    //that you use to access score.
    //Remember to pass this in!
    public Text(float x, float y, String text){
        font = new BitmapFont();
        this.text = text;
        position = new Vector2(x,y);
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        font.draw(batch, text, position.x, position.y);
        //Also remember that an actor uses local coordinates for drawing within
        //itself!
    }

}
