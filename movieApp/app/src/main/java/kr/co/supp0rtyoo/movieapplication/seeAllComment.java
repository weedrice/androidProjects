package kr.co.supp0rtyoo.movieapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.util.ArrayList;

import kr.co.supp0rtyoo.movieapplication.commentData.CommentList;
import kr.co.supp0rtyoo.movieapplication.commentData.CommentListInfo;
import kr.co.supp0rtyoo.movieapplication.movieData.MovieDetail;
import kr.co.supp0rtyoo.movieapplication.movieData.MovieDetailInfo;

public class seeAllComment extends AppCompatActivity {
    TextView movieTitle;
    ImageView grade;
    RatingBar ratingBar;
    TextView rating;
    TextView participants;
    LinearLayout writeComment;
    int movieID;

    ListView allCommentListView;
    AllCommentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all_comment);

        Intent intent = getIntent();
        movieID = intent.getExtras().getInt("id");
        movieTitle = (TextView)findViewById(R.id.allCommentsMovieTitle);
        grade = (ImageView)findViewById(R.id.allCommentsGrade);
        ratingBar = (RatingBar)findViewById(R.id.allCommentsRatingBar);
        rating = (TextView)findViewById(R.id.allCommentsRating);
        participants = (TextView)findViewById(R.id.allCommentsParticipants);

        getMovieInfoFromAPI();

        allCommentListView = (ListView)findViewById(R.id.seeAllCommentsListview);
        adapter = new AllCommentAdapter();

        setComments();

        writeComment = (LinearLayout) findViewById(R.id.allCommentsWriteComment);

        writeComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), writeComment.class);
                startActivity(intent);

                finish();
            }
        });
    }

    public void getMovieInfoFromAPI() {
        String url = AppHelper.getUrl();
        int port = AppHelper.getPort();
        String apiUrl = "/movie/readMovie";
        int requestCode = movieID;
        String requestURL = "http://" + url + ":" + port + apiUrl + "?id=" + requestCode;

        StringRequest request = new StringRequest(
                Request.Method.GET,
                requestURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        processMovieDetailResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Log.d("Error: ", String.valueOf(error));
                    }
                }
        );

        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);
    }

    private void processMovieDetailResponse(String response) {
        Gson gson = new Gson();
        MovieDetail movieDetail = gson.fromJson(response, MovieDetail.class);
        MovieDetailInfo detailInfo = movieDetail.getResult().get(0);

        movieTitle.setText(detailInfo.getTitle());
        switch(detailInfo.getGrade()) {
            case 12:
                grade.setImageResource(R.drawable.ic_12);
                break;
            case 15:
                grade.setImageResource(R.drawable.ic_15);
                break;
            case 19:
                grade.setImageResource(R.drawable.ic_19);
                break;
            default:
                grade.setImageResource(R.drawable.ic_all);
                break;
        }
        float total_rating = (detailInfo.getUser_rating()+detailInfo.getAudience_rating())/2.0f;
        ratingBar.setRating(total_rating);
        String ratingFormat = String.format("%.1f", total_rating);
        rating.setText(ratingFormat);
    }

    private void setComments() {
        String url = AppHelper.getUrl();
        int port = AppHelper.getPort();
        String apiUrl = "/movie/readCommentList";
        String requestURL = "http://" + url + ":" + port + apiUrl + "?id=" + movieID + "&limit=all";

        StringRequest request = new StringRequest(
                Request.Method.GET,
                requestURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        processCommentsResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Log.d("Error: ", String.valueOf(error));
                    }
                }
        );

        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);
    }

    private void processCommentsResponse(String response) {
        Gson gson = new Gson();
        CommentList commentList = gson.fromJson(response, CommentList.class);

        setListView(commentList);
    }

    public void setListView(CommentList commentList) {
        int participantsInfo = commentList.getResult().size();
        participants.setText("("+String.valueOf(participantsInfo)+"명 참여)");
        for(int i=0;i<participantsInfo;i++) {
            CommentListInfo movieListDetail = commentList.getResult().get(i);
            adapter.addItem(new evaluateItems(movieListDetail.getWriter(), movieListDetail.getContents(),
                    movieListDetail.getRating(), movieListDetail.getTimestamp(), movieListDetail.getRecommend()));
        }

        allCommentListView.setAdapter(adapter);
    }

    class AllCommentAdapter extends BaseAdapter {
        ArrayList<evaluateItems> items = new ArrayList<evaluateItems>();

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            evaluateListView evalListView = null;

            if(view == null) {
                evalListView = new evaluateListView(getApplicationContext());
            } else {
                evalListView = (evaluateListView)view;
            }

            evaluateItems item = items.get(i);
            evalListView.setName(item.getWriterID());
            evalListView.setComments(item.getContents());
            evalListView.setRatingBar(item.getRating());
            evalListView.setTime(item.getWriteTime());

            return evalListView;
        }

        public void addItem(evaluateItems item) {
            items.add(item);
        }
    }
}
