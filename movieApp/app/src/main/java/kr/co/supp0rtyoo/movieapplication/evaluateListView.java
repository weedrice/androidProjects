package kr.co.supp0rtyoo.movieapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class evaluateListView extends LinearLayout {
    TextView writeName;
    TextView comments;

    public evaluateListView(Context context) {
        super(context);

        init(context);
    }

    public evaluateListView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.evaluate_listview, this, true);

        writeName = (TextView)findViewById(R.id.writer);
        comments=(TextView)findViewById(R.id.comments);
    }

    public void setName(String name) {
        writeName.setText(name);
    }

    public void setComments(String comments) {
        this.comments.setText(comments);
    }
}
