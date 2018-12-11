package kr.co.supp0rtyoo.movieapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import kr.co.supp0rtyoo.movieapplication.commentData.CommentList;
import kr.co.supp0rtyoo.movieapplication.commentData.CommentListInfo;

public class MovieDetailFragment extends Fragment {
    Button thumbsUpBtn;
    Button thumbsDownBtn;
    TextView thumbsUpTxt;
    TextView thumbsDownTxt;
    TextView evaluateTxt;
    LinearLayout allEvaluateTxt;
    TextView movieTitle;
    ImageView poster;
    int thumbsUpScore;
    int thumbsDownScore;
    boolean isThumbsUp = false;
    boolean isThumbsDown = false;
    int movieID;

    ViewGroup rootView;
    MovieList movieList;
    EvaluateAdapter adapter;
    ListView evalListView;

    @Override
    public void onAttach(Context context) {
        movieList = (MovieList)getActivity();

        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();

        movieList=null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup)inflater.inflate(R.layout.fragment_movie_detail, container, false);

        thumbsUpBtn = (Button)rootView.findViewById(R.id.thumbsUp);
        thumbsDownBtn = (Button)rootView.findViewById(R.id.thumbsDown);
        thumbsUpTxt = (TextView)rootView.findViewById(R.id.thumbsUpTxt);
        thumbsDownTxt = (TextView)rootView.findViewById(R.id.thumbsDownTxt);
        evaluateTxt = (TextView)rootView.findViewById(R.id.writeEvaluate);
        allEvaluateTxt = (LinearLayout) rootView.findViewById(R.id.seeAllEvaluate);
        movieTitle = (TextView)rootView.findViewById(R.id.movieTitle);
        poster = (ImageView)rootView.findViewById(R.id.moviePoster);
        ImageView grade = (ImageView)rootView.findViewById(R.id.ageLimit);
        TextView opening = (TextView)rootView.findViewById(R.id.openingDay);
        TextView genre = (TextView)rootView.findViewById(R.id.genre);
        TextView duration = (TextView)rootView.findViewById(R.id.duration);
        TextView reservedRank = (TextView)rootView.findViewById(R.id.reservedRank);
        TextView reservedRatio = (TextView)rootView.findViewById(R.id.reservedRate);
        RatingBar scoreRatingBar = (RatingBar)rootView.findViewById(R.id.scoreRatingBar);
        TextView rating = (TextView)rootView.findViewById(R.id.scoreText);
        TextView audience = (TextView)rootView.findViewById(R.id.peopleNum);
        TextView synopsis = (TextView)rootView.findViewById(R.id.synopsis);
        TextView director = (TextView)rootView.findViewById(R.id.directorContent);
        TextView actor = (TextView)rootView.findViewById(R.id.actorsContent);

        movieID = getArguments().getInt("id");
        String imageInfo = getArguments().getString("thumb");
        movieList.setMovieDetailImage(imageInfo, poster);
        movieTitle.setText(getArguments().getString("title"));
        switch(getArguments().getInt("grade")) {
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
        String date = getArguments().getString("date");
        String[] dates = date.split("-");
        opening.setText(dates[0]+"."+dates[1]+"."+dates[2]+" 개봉");
        genre.setText(getArguments().getString("genre"));
        duration.setText(String.valueOf(getArguments().getInt("duration")) + " 분");
        thumbsUpScore = getArguments().getInt("like");
        thumbsUpTxt.setText(String.valueOf(thumbsUpScore));
        thumbsDownScore = getArguments().getInt("dislike");
        thumbsDownTxt.setText(String.valueOf(thumbsDownScore));
        reservedRank.setText(String.valueOf(getArguments().getInt("reservation_grade"))+"위  ");
        String reservedRatioFormat = String.format("%.2f", getArguments().getFloat("reservation_rate"));
        reservedRatio.setText(reservedRatioFormat + "%");
        float total_rating = (getArguments().getFloat("user_rating")+getArguments().getFloat("audience_rating"))/2.0f;
        scoreRatingBar.setRating(total_rating/2.0f);
        String ratingFormat = String.format("%.1f", total_rating);
        rating.setText(ratingFormat);
        String audienceNum = String.format("%,d", (getArguments().getInt("audience")));
        audience.setText(audienceNum + "명");
        synopsis.setText(getArguments().getString("synopsis"));
        director.setText(getArguments().getString("director"));
        actor.setText(getArguments().getString("actor"));


        thumbsUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isThumbsUp == true) {
                    isThumbsUp = !isThumbsUp;
                    thumbsUpScore--;
                    thumbsUpTxt.setText(String.valueOf(thumbsUpScore));
                    thumbsUpBtn.setBackgroundResource(R.drawable.ic_thumb_up);

                } else {
                    if(isThumbsDown == true) {
                        isThumbsDown = !isThumbsDown;
                        isThumbsUp = !isThumbsUp;
                        thumbsDownScore--;
                        thumbsUpScore++;
                        thumbsUpTxt.setText(String.valueOf(thumbsUpScore));
                        thumbsDownTxt.setText(String.valueOf(thumbsDownScore));
                        thumbsUpBtn.setBackgroundResource(R.drawable.ic_thumb_up_selected);
                        thumbsDownBtn.setBackgroundResource(R.drawable.ic_thumb_down);

                    } else {
                        isThumbsUp = !isThumbsUp;
                        thumbsUpScore++;
                        thumbsUpTxt.setText(String.valueOf(thumbsUpScore));
                        thumbsUpBtn.setBackgroundResource(R.drawable.ic_thumb_up_selected);

                    }
                }
            }
        });

        thumbsDownBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isThumbsDown == true) {
                    isThumbsDown = !isThumbsDown;
                    thumbsDownScore--;
                    thumbsDownTxt.setText(String.valueOf(thumbsDownScore));
                    thumbsDownBtn.setBackgroundResource(R.drawable.ic_thumb_down);

                } else {
                    if(isThumbsUp == true) {
                        isThumbsDown = !isThumbsDown;
                        isThumbsUp = !isThumbsUp;
                        thumbsDownScore++;
                        thumbsUpScore--;
                        thumbsUpTxt.setText(String.valueOf(thumbsUpScore));
                        thumbsDownTxt.setText(String.valueOf(thumbsDownScore));
                        thumbsUpBtn.setBackgroundResource(R.drawable.ic_thumb_up);
                        thumbsDownBtn.setBackgroundResource(R.drawable.ic_thumb_down_selected);

                    } else {
                        isThumbsDown = !isThumbsDown;
                        thumbsDownScore++;
                        thumbsDownTxt.setText(String.valueOf(thumbsDownScore));
                        thumbsDownBtn.setBackgroundResource(R.drawable.ic_thumb_down_selected);

                    }
                }
            }
        });

        evaluateTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movieList.showWriteComment(movieID);
            }
        });

        allEvaluateTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movieList.showAllComment(movieID);
            }
        });

        evalListView = (ListView)rootView.findViewById(R.id.evaluateListview);
        adapter = new EvaluateAdapter();
        movieList.getCommentsFromAPI(movieID);

        return rootView;
    }

    public void setListView(CommentList commentList) {
        //Log.d("setListView: ", commentList.getResult().get(0).toString());
        for(int i=0;i<commentList.getResult().size();i++) {
            if(i==2)
                break;
            CommentListInfo movieListDetail = commentList.getResult().get(i);
            adapter.addItem(new evaluateItems(movieListDetail.getWriter(), movieListDetail.getContents(),
                    movieListDetail.getRating(), movieListDetail.getTime(), movieListDetail.getRecommend()));
        }

        evalListView.setAdapter(adapter);
    }

    class EvaluateAdapter extends BaseAdapter {
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
                evalListView = new evaluateListView(movieList);
            } else {
                evalListView = (evaluateListView)view;
            }

            evaluateItems item = items.get(i);
            evalListView.setName(item.getWriterID());
            evalListView.setComments(item.getContents());
            evalListView.setRatingBar(item.getRating());
            evalListView.setTime(item.getWriteTime());
            evalListView.setRecommend(item.getRecommend());

            return evalListView;
        }

        public void addItem(evaluateItems item) {
            items.add(item);
        }
    }
}
