package au.edu.jcu.cp3406.wk7guesstheceleb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.Locale;

import game.CelebrityManager;
import game.Game;
import game.GameBuilder;

public class MainActivity extends AppCompatActivity implements StateListener {
    CelebrityManager celebrityManager;
    GameBuilder gameBuilder;
    Game game;
    private GameFragment gameFragment;
    private StatusFragment statusFragment;
    private QuestionFragment questionFragment;
    private boolean isLargeScreen;
    Difficulty level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        gameFragment = (GameFragment) fragmentManager.findFragmentById(R.id.game);
        statusFragment = (StatusFragment) fragmentManager.findFragmentById(R.id.status);
        questionFragment = (QuestionFragment) fragmentManager.findFragmentById(R.id.question);
        isLargeScreen = statusFragment != null;

        celebrityManager = new CelebrityManager(this.getAssets(), "celebs");
        gameBuilder = new GameBuilder(celebrityManager);

    }

    @Override
    public void onUpdate(State state) {
        level = gameFragment.getLevel();
        String text = String.format(Locale.getDefault(), "state: %s level: %s", state, level);
        Log.i("MainActivity", text);

        if (isLargeScreen) {
            switch (state) {
                case START_GAME:
                    game = gameBuilder.create(level);
                    questionFragment.setGame(game, 0);
                    break;
                case CONTINUE_GAME:
                    statusFragment.setScore(questionFragment.getScore());
                    questionFragment.showNextQuestion();
                    break;
                case GAME_OVER:
                    statusFragment.setScore(questionFragment.getScore());
                    statusFragment.setMessage("Game Over!");
                    break;
            }
        } else {
            Intent intent = new Intent(this, QuestionActivity.class);
            intent.putExtra("level", level);
            startActivity(intent);
        }
    }
}
