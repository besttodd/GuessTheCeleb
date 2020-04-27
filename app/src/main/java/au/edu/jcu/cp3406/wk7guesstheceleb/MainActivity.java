package au.edu.jcu.cp3406.wk7guesstheceleb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Locale;

import game.CelebrityManager;
import game.Game;
import game.GameBuilder;
import game.Question;

public class MainActivity extends AppCompatActivity implements StateListener {
    CelebrityManager celebrityManager;
    GameBuilder gameBuilder;
    Game game;
    private GameFragment gameFragment;
    private StatusFragment statusFragment;
    private QuestionFragment questionFragment;
    private boolean isLargeScreen;

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
        game = gameBuilder.create(gameFragment.getLevel());
    }

    @Override
    public void onUpdate(State state) {
        Difficulty level = gameFragment.getLevel();
        String text = String.format(Locale.getDefault(), "state: %s level: %s", state, level);
        Log.i("MainActivity", text);

        if (isLargeScreen) {
            switch (state) {
                case START_GAME:
                    //Game game = gameBuilder.create(level);
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
        } else {}
    }

    public void playClicked(View view) {
        Intent intent = new Intent(this, QuestionActivity.class);
        //intent.putExtra("celebrity", celebrityManager.get(0));
        startActivity(intent);
    }
}
