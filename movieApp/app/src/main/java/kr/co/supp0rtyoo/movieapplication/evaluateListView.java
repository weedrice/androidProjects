package kr.co.supp0rtyoo.movieapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

public class evaluateListView extends LinearLayout {
    TextView writeName;
    TextView comments;
    RatingBar ratingBar;
    TextView timeView;

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
        ratingBar=(RatingBar)findViewById(R.id.listRatingBar);
        timeView = (TextView)findViewById(R.id.writeTime);
    }

    public void setName(String name) {
        writeName.setText(name);
    }

    public void setComments(String comments) {
        this.comments.setText(comments);
    }

    public void setRatingBar(float rating) {
        ratingBar.setRating(rating);
    }

    public void setTime(int time) {
        timeView.setText(String.valueOf(time) + "분전");
    }
}
