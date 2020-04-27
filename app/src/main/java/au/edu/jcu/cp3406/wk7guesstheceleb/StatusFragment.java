package au.edu.jcu.cp3406.wk7guesstheceleb;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StatusFragment extends Fragment {
    private StateListener listener;
    private View view;
    private TextView message;
    private TextView score;

    public StatusFragment() {
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_status, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        view = getView();
    }

    public void setMessage(String text) {
        message = view.findViewById(R.id.message);
        message.setText(text);
    }

    public void setScore(String text) {
        score = view.findViewById(R.id.score);
        score.setText(text); }
}
