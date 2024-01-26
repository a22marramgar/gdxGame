package gdx.game.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;

public class ScoreText extends Text{

    private int score;
    private BitmapFont font;
    private Vector2 position;

    public ScoreText(float x, float y, int score) {
        super(x, y, "Score: "+score);
        font = new BitmapFont();
        this.score = score;
        position = new Vector2(x,y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        font.draw(batch, "Score: "+score, position.x, position.y);
        //Also remember that an actor uses local coordinates for drawing within
        //itself!
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void increment(){
        this.score++;
    }

    public void decrement(){
        this.score--;
    }
}
