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
    TextView recommend;

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
        ratingBar=(RatingBar)findViewById(R.id.commentListRatingBar);
        timeView = (TextView)findViewById(R.id.writeTime);
        recommend = (TextView)findViewById(R.id.commentRecommended);
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

    public void setTime(String time) {
        /*
        String[] getDate = time.split(" ");
        Log.d("time", getDate[0] + " " + getDate[1]);
        String[] getDay = getDate[0].split("-");
        String[] getTime = getDate[1].split(":");

        String newDate = getDay[0]+getDay[1]+getDay[2]+getTime[0]+getTime[1];
        Log.d("newTime", newDate);
        Date curDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMMddHHmm");
        Date past;
        long pastTime = 0;
        try {
            past = dateFormat.parse(newDate);
            pastTime = past.getTime();
        } catch (Exception e) {}

        long curDateTime = 0;
        try {
            curDate = dateFormat.parse(dateFormat.format(curDate));
            curDateTime = curDate.getTime();
        } catch (Exception e) {}

        long minute = (curDateTime - pastTime) / 60000;
        Log.d("check date", time);
        Calendar temp = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = simpleDateFormat.parse(time, new ParsePosition(0));

        long startTime = date.getTime();
        Date now = temp.getTime();
        long endTime = now.getTime();

        long mills = endTime - startTime;
        long min = mills / 60000;
        Log.d("minute", String.valueOf(min));

        if(min > 60) {
            long hour = min / 60;
            if(hour > 24) {
                long day = hour / 24;
                timeView.setText(String.valueOf(day)+"일 전");
            } else {
                timeView.setText(String.valueOf(hour)+"시간 전");
            }
        } else {
            timeView.setText(String.valueOf(min) + "분 전");
        }
        */

        timeView.setText("작성일자: " + time);
    }

    public void setRecommend(int recommend) {
        this.recommend.setText(String.valueOf(recommend));
    }
}
