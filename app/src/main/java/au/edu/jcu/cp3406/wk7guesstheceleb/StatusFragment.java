package au.edu.jcu.cp3406.wk7guesstheceleb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import game.Timer;

public class StatusFragment extends Fragment {
    private StateListener listener;
    private View view;
    private Timer timer;
    final Handler handler = new Handler();
    Runnable runnable;

    public StatusFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (StateListener) context;
        timer = new Timer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_status, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        view = getView();
    }

    public void setMessage(String text) {
        TextView message = view.findViewById(R.id.message);
        message.setText(text);
    }

    public void setScore(String text) {
        TextView score = view.findViewById(R.id.score);
        score.setText(text);
    }

    public void setTime(String text) {
        TextView time = view.findViewById(R.id.time);
        time.setText(text);
    }

    public void setTimer(String time) {
        timer.setTimer(time);
    }

    public void stopTimer() {
        timer.stopTimer();
    }

    public void startTimer() {
        timer.startTimer();

        handler.post(runnable = new Runnable() {
            @Override
            public void run() {
                timer.tick();
                setTime(String.format("Time: %s", timer.toString()));
                if (timer.isRunning()) {
                    System.out.println("TICK TICK TICK TICK");
                    handler.postDelayed(this, 1000);
                } else {
                    listener.onUpdate(State.GAME_OVER);
                }
                setMessage("");
            }
        });
    }
}
