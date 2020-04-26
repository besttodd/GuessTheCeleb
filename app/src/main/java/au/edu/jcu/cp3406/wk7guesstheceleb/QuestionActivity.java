package au.edu.jcu.cp3406.wk7guesstheceleb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import game.CelebrityManager;
import game.Game;

public class QuestionActivity extends AppCompatActivity {
    CelebrityManager celebrityManager;
    GameFragment gameFragment;
    StatusFragment statusFragment;
    QuestionFragment questionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        FragmentManager fragmentManager = getSupportFragmentManager();
        gameFragment = (GameFragment) fragmentManager.findFragmentById(R.id.game);
        statusFragment = (StatusFragment) fragmentManager.findFragmentById(R.id.status);
        questionFragment = (QuestionFragment) fragmentManager.findFragmentById(R.id.question);

        /*celebrityManager = new CelebrityManager(this.getAssets(), "celebs");
        ImageView imageView = findViewById(R.id.image);
        imageView.setImageBitmap(celebrityManager.get(0));*/
    }

    public void onUpdate(State state) {
        switch (state) {
            case CONTINUE_GAME:
                statusFragment.setScore(questionFragment.getScore());
                break;
            case GAME_OVER:
                statusFragment.setScore(questionFragment.getScore());
                statusFragment.setMessage("Game Over!");
                break;
        }
    }
}
