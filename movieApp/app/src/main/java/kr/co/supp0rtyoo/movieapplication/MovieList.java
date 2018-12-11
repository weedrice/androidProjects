package kr.co.supp0rtyoo.movieapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;

import kr.co.supp0rtyoo.movieapplication.commentData.CommentList;
import kr.co.supp0rtyoo.movieapplication.movieData.MovieDetail;
import kr.co.supp0rtyoo.movieapplication.movieData.MovieDetailInfo;
import kr.co.supp0rtyoo.movieapplication.movieData.MovieListDetail;
import kr.co.supp0rtyoo.movieapplication.movieData.MovieListInfo;

public class MovieList extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ViewPager pager;
    MovieDetailFragment detailFragment;

    MovieListPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        if(AppHelper.requestQueue == null) {
            AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        pager = (ViewPager)findViewById(R.id.pager);
        adapter = new MovieListPagerAdapter(getSupportFragmentManager());
        loadMovieListDataFromAPI();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void loadMovieListDataFromAPI() {
        String url = AppHelper.getUrl();
        int port = AppHelper.getPort();
        String apiUrl = "/movie/readMovieList";
        int requestCode = 1;
        String requestURL = "http://" + url + ":" + port + apiUrl + "?type=" + requestCode;

        StringRequest request = new StringRequest(
                Request.Method.GET,
                requestURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        processMovieListResponse(response);
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

    private void processMovieListResponse(String response) {
        Gson gson = new Gson();
        MovieListInfo movieList = gson.fromJson(response, MovieListInfo.class);

        setPager(adapter, movieList);
    }

    private void setPager(MovieListPagerAdapter adapter, MovieListInfo movieListObj) {
        ArrayList<MovieListFragment> fragments = new ArrayList<MovieListFragment>();
        pager.setOffscreenPageLimit(movieListObj.getResult().size());

        for(int i=0;i<movieListObj.getResult().size();i++) {
            MovieListDetail movieListDetail = movieListObj.getResult().get(i);
            MovieListFragment fragment = new MovieListFragment();
            Bundle bundle = new Bundle(5);
            bundle.putInt("id", movieListDetail.getId());
            bundle.putString("title", movieListDetail.getTitle());
            bundle.putInt("grade", movieListDetail.getGrade());
            bundle.putString("image", movieListDetail.getImage());
            bundle.putFloat("reservation", movieListDetail.getReservation_rate());
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }

        for(int i=0;i<fragments.size();i++) {
            adapter.addItem(fragments.get(i));
        }

        pager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_movieList) {
            getSupportFragmentManager().beginTransaction().remove(detailFragment).commit();
        } else if (id == R.id.nav_movieAPI) {

        } else if (id == R.id.nav_reservation) {

        } else if (id == R.id.nav_setting) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    class MovieListPagerAdapter extends FragmentStatePagerAdapter {
        ArrayList<Fragment> items = new ArrayList<Fragment>();

        public MovieListPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addItem(Fragment item) {
            items.add(item);
        }

        @Override
        public Fragment getItem(int i) {
            return items.get(i);
        }

        @Override
        public int getCount() {
            return items.size();
        }
    }

    public void showMovieDetailActivity(int id) {
        if(detailFragment == null)
            detailFragment = new MovieDetailFragment();
        else {
            getSupportFragmentManager().beginTransaction().show(detailFragment).commit();
        }

        getMovieDetailFromAPI(id);
    }

    public void getMovieDetailFromAPI(int id) {
        String url = AppHelper.getUrl();
        int port = AppHelper.getPort();
        String apiUrl = "/movie/readMovie";
        int requestCode = id;
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

        setDetailBundle(movieDetail);
    }

    private void setDetailBundle(MovieDetail movieDetail) {
        MovieDetailInfo movieDetailInfo = movieDetail.getResult().get(0);
        Bundle bundle = new Bundle(17);
        bundle.putInt("id", movieDetailInfo.getId());
        bundle.putString("title", movieDetailInfo.getTitle());
        bundle.putString("date", movieDetailInfo.getDate());
        bundle.putFloat("user_rating", movieDetailInfo.getUser_rating());
        bundle.putFloat("audience_rating", movieDetailInfo.getAudience_rating());
        bundle.putFloat("reservation_rate", movieDetailInfo.getReservation_rate());
        bundle.putInt("reservation_grade", movieDetailInfo.getReservation_grade());
        bundle.putInt("grade", movieDetailInfo.getGrade());
        bundle.putString("thumb", movieDetailInfo.getThumb());
        bundle.putString("genre", movieDetailInfo.getGenre());
        bundle.putInt("duration", movieDetailInfo.getDuration());
        bundle.putInt("audience", movieDetailInfo.getAudience());
        bundle.putString("synopsis", movieDetailInfo.getSynopsis());
        bundle.putString("director", movieDetailInfo.getDirector());
        bundle.putString("actor", movieDetailInfo.getActor());
        bundle.putInt("like", movieDetailInfo.getLike());
        bundle.putInt("dislike", movieDetailInfo.getDislike());

        detailFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, detailFragment).commit();
    }

    public void showWriteComment(int movieID) {
        Intent intent = new Intent(getApplicationContext(), writeComment.class);
        intent.putExtra("id", movieID);
        startActivity(intent);

        //Toast.makeText(getApplicationContext(), "한줄평 작성 버튼이 눌렸습니다.", Toast.LENGTH_LONG).show();
    }

    public void showAllComment(int movieID) {
        Intent intent = new Intent(getApplicationContext(), seeAllComment.class);
        intent.putExtra("id", movieID);
        startActivity(intent);

        //Toast.makeText(getApplicationContext(), "한줄평 모두 보기 버튼이 눌렸습니다.", Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 101) {
            if(data != null) {
                String comment = data.getExtras().getString("comment");
                float rating = data.getExtras().getFloat("rating");

                Toast.makeText(this, "작성하기 화면에서 돌아왔습니다.\n" + "저장여부: " + comment + ", " + rating, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "작성하기가 취소되었습니다.", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void setMovieListImage(String url, ImageView imageView) {
        ImageLoadTask task = new ImageLoadTask(url, imageView);
        task.execute();
    }

    public void setMovieDetailImage(String url, ImageView imageView) {
        ImageLoadTask task = new ImageLoadTask(url, imageView);
        task.execute();
    }

    public void getCommentsFromAPI(int id) {
        String url = AppHelper.getUrl();
        int port = AppHelper.getPort();
        String apiUrl = "/movie/readCommentList";
        int movieID = id;
        int limit = 2;
        String requestURL = "http://" + url + ":" + port + apiUrl + "?id=" + movieID + "&limit=" + limit;

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

        detailFragment.setListView(commentList);
    }
}
