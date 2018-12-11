package kr.co.supp0rtyoo.movieapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MovieListFragment extends Fragment {
    ImageView poster;
    TextView title;
    TextView reservation;
    TextView limit;
    //TextView dDay;
    int movieID;

    MovieList movieListActivity;
    LinearLayout detailView;

    @Override
    public void onAttach(Context context) {
        movieListActivity = (MovieList)getActivity();

        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();

        movieListActivity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.movie_list_fragment, container, false);

        movieID = getArguments().getInt("id");
        String imageInfo = getArguments().getString("image");
        String titleInfo = getArguments().getString("title");
        float reserveInfo = getArguments().getFloat("reservation");
        int gradeInfo = getArguments().getInt("grade");
        //int dayInfo = getArguments().getInt("day");

        poster = (ImageView)rootView.findViewById(R.id.movieImage);
        title = (TextView)rootView.findViewById(R.id.movieTitle);
        reservation = (TextView) rootView.findViewById(R.id.reservedRate);
        limit = (TextView) rootView.findViewById(R.id.ageLimit);
        //dDay = (TextView) rootView.findViewById(R.id.dDay);

        //poster.setImageResource(image);
        movieListActivity.setMovieListImage(imageInfo, poster);
        title.setText(titleInfo);
        reservation.setText("예매율 "+reserveInfo+"%");
        limit.setText(String.valueOf(gradeInfo)+"세 관람가");
        //dDay.setText("D-"+String.valueOf(dayInfo));

        detailView = (LinearLayout)rootView.findViewById(R.id.detailViewBtn);
        detailView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movieListActivity.showMovieDetailActivity(movieID);
            }
        });

        return rootView;
    }
}
