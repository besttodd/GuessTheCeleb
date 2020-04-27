package au.edu.jcu.cp3406.wk7guesstheceleb;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import java.util.Random;

import game.CelebrityManager;
import game.Game;
import game.Question;

public class QuestionFragment extends Fragment {
    private StateListener listener;
    private Game currentGame;
    View view;
    ImageView image;
    ViewGroup gridLayout;

    public QuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (StateListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_question, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        view = getView();
        image = view.findViewById(R.id.celebImg);
    }

    public void setGame(Game game, int question) {
        currentGame = game;
        Question firstQ = currentGame.getQuestion(question);
        image.setImageBitmap(firstQ.getCelebrityImage());
        addPossibleNames(firstQ);
    }

    public String getScore() { return currentGame.getScore(); }

    public void showNextQuestion() {
        Question nextQuestion = currentGame.next();
        if (!currentGame.isGameOver()) {
            image.setImageBitmap(nextQuestion.getCelebrityImage());
            addPossibleNames(nextQuestion);
        } else { listener.onUpdate(State.GAME_OVER); }
    }

    public void addPossibleNames(Question question) {
        gridLayout = view.findViewById(R.id.possibleNamesGrid);
        final Question currentQuestion = question;
        System.out.println("addPossibleNames"+currentGame.getRound()+"-------------------------------------");
        String[] possibilities = currentQuestion.getPossibleNames();

        for (String possibility : possibilities) {
            getLayoutInflater().inflate(R.layout.button, gridLayout);
            View lastChild = gridLayout.getChildAt(gridLayout.getChildCount()-1);
            final Button newButton = lastChild.findViewById(R.id.button);
            newButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentGame.updateScore(currentQuestion.check(newButton.getText().toString()));
                    if (!currentGame.isGameOver()) {
                        listener.onUpdate(State.CONTINUE_GAME);
                        System.out.println(newButton.getText() + "-----------------------------------------------------------");
                    } else { listener.onUpdate(State.GAME_OVER); }
                }
            });
            newButton.setText(possibility);
        }
    }
}
