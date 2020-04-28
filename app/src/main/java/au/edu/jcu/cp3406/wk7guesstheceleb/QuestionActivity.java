package au.edu.jcu.cp3406.wk7guesstheceleb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import java.util.Objects;

import game.CelebrityManager;
import game.Game;
import game.GameBuilder;

public class QuestionActivity extends AppCompatActivity implements StateListener{
    StatusFragment statusFragment;
    QuestionFragment questionFragment;
    CelebrityManager celebrityManager;
    GameBuilder gameBuilder;
    Game currentGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        FragmentManager fragmentManager = getSupportFragmentManager();
        statusFragment = (StatusFragment) fragmentManager.findFragmentById(R.id.status);
        questionFragment = (QuestionFragment) fragmentManager.findFragmentById(R.id.question);
        currentGame = Objects.requireNonNull(getIntent().getExtras()).getParcelable("Game");

        celebrityManager = new CelebrityManager(this.getAssets(), "celebs");
        gameBuilder = new GameBuilder(celebrityManager);
        Difficulty level = (Difficulty) getIntent().getSerializableExtra("level");
        currentGame = gameBuilder.create(Objects.requireNonNull(level));

        questionFragment.setGame(currentGame, 0);
    }

    public void onUpdate(State state) {
        switch (state) {
            case CONTINUE_GAME:
                statusFragment.setScore(questionFragment.getScore());
                questionFragment.showNextQuestion();
                break;
            case GAME_OVER:
                statusFragment.setScore(questionFragment.getScore());
                statusFragment.setMessage("Game Over!");
                break;
        }
    }
}
