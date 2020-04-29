package au.edu.jcu.cp3406.wk7guesstheceleb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;

public class ResultsActivity extends AppCompatActivity {
    String score;
    Difficulty difficulty;
    Boolean isLarge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        score = Objects.requireNonNull(getIntent().getExtras()).getString("score");
        difficulty = (Difficulty) getIntent().getSerializableExtra("difficulty");
        isLarge = getIntent().getExtras().getBoolean("largeScreen");

        TextView scoreDisplay = findViewById(R.id.finalScore);
        String finalDisplay = "Well Done!\n\nYou scored: " + score + "\nOn " + difficulty + " mode!";
        scoreDisplay.setText(finalDisplay);
    }

    public void replayClicked(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
