package au.edu.jcu.cp3406.wk7guesstheceleb;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.Random;

import game.Game;
import game.Question;

public class QuestionFragment extends Fragment {
    private StateListener listener;
    private Game currentGame;
    private Context context;
    private View view;
    private ImageView image;

    public QuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (StateListener) context;
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_question, container, false);
        image = view.findViewById(R.id.celebImg);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    void setGame(Game game) {
        currentGame = game;
        Question firstQ = currentGame.getQuestion(0);
        image.setImageBitmap(firstQ.getCelebrityImage());
        addPossibleNames(firstQ);
    }

    String getScore() { return currentGame.getScore(); }

    void showNextQuestion() {
        Question nextQuestion = currentGame.next();
        if (!currentGame.isGameOver()) {
            image.setImageBitmap(nextQuestion.getCelebrityImage());
            addPossibleNames(nextQuestion);
        } else {
            listener.onUpdate(State.GAME_OVER);
        }
    }

    private void addPossibleNames(Question question) {
        GridView gridLayout = view.findViewById(R.id.possibleNamesGrid);
        final Question currentQuestion = question;
        final String[] possibilities = currentQuestion.getPossibleNames();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, R.layout.button, R.id.button, possibilities);
        gridLayout.setAdapter(arrayAdapter);

        gridLayout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentGame.updateScore(currentQuestion.check(possibilities[position]));
                if (!currentGame.isGameOver()) {
                    listener.onUpdate(State.CONTINUE_GAME);
                } else {
                    listener.onUpdate(State.GAME_OVER);
                }
            }
        });
    }
}
