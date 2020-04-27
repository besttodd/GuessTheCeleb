package au.edu.jcu.cp3406.wk7guesstheceleb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

public class gridAdapter extends BaseAdapter {
    private Context mContext;

    public gridAdapter(Context c) { mContext = c; }

    public int getCount() { return 3; }

    public Object getItem(int position) { return null; }

    public long getItemId(int position) { return 0; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridLayout = inflater.inflate(R.layout.button, null);
        Button guess = (Button) gridLayout.findViewById(R.id.button);
        guess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {}
        });
        return gridLayout;
    }
}
