package au.edu.jcu.cp3406.wk7guesstheceleb;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import game.Timer;

public class StatusFragment extends Fragment {
    private StateListener listener;
    private View view;
    private Timer timer;
    private final Handler handler = new Handler();

    public StatusFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
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

    void setMessage(String text) {
        TextView message = view.findViewById(R.id.message);
        message.setText(text);
    }

    void setScore(String text) {
        TextView score = view.findViewById(R.id.score);
        score.setText(text);
    }

    private void setTime(String text) {
        TextView time = view.findViewById(R.id.time);
        time.setText(text);
    }

    void setTimer(String time) {
        timer.setTimer(time);
    }

    void stopTimer() {
        timer.stopTimer();
    }

    void startTimer() {
        timer.startTimer();

        handler.post(new Runnable() {
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
