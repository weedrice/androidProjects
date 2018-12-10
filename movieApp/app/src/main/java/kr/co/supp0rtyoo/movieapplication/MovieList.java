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
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MovieList extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ViewPager pager;
    MovieDetailFragment detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        pager = (ViewPager)findViewById(R.id.pager);
        pager.setOffscreenPageLimit(6);

        MovieListPagerAdapter adapter = new MovieListPagerAdapter(getSupportFragmentManager());

        setPager(adapter);

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

    private void setPager(MovieListPagerAdapter adapter) {
        MovieListFragment fragment = new MovieListFragment();
        Bundle bundle = new Bundle(2);
        bundle.putString("title", "군도");
        bundle.putInt("image", R.drawable.image1);
        fragment.setArguments(bundle);

        MovieListFragment fragment2 = new MovieListFragment();
        Bundle bundle2 = new Bundle(2);
        bundle2.putString("title", "공조");
        bundle2.putInt("image", R.drawable.image2);
        fragment2.setArguments(bundle2);

        MovieListFragment fragment3 = new MovieListFragment();
        Bundle bundle3 = new Bundle(2);
        bundle3.putString("title", "더킹");
        bundle3.putInt("image", R.drawable.image3);
        fragment3.setArguments(bundle3);

        MovieListFragment fragment4 = new MovieListFragment();
        Bundle bundle4 = new Bundle(2);
        bundle4.putString("title", "레지던트 이블");
        bundle4.putInt("image", R.drawable.image4);
        fragment4.setArguments(bundle4);

        MovieListFragment fragment5 = new MovieListFragment();
        Bundle bundle5 = new Bundle(2);
        bundle5.putString("title", "럭키");
        bundle5.putInt("image", R.drawable.image5);
        fragment5.setArguments(bundle5);

        MovieListFragment fragment6 = new MovieListFragment();
        Bundle bundle6 = new Bundle(2);
        bundle6.putString("title", "아수라");
        bundle6.putInt("image", R.drawable.image6);
        fragment6.setArguments(bundle6);

        adapter.addItem(fragment);
        adapter.addItem(fragment2);
        adapter.addItem(fragment3);
        adapter.addItem(fragment4);
        adapter.addItem(fragment5);
        adapter.addItem(fragment6);

        pager.setAdapter(adapter);
    }

    /*
    private void setMovieData(MovieListFragment fragment) {
        Bundle bundle = new Bundle(5);
        bundle.putString("title", "군도");
        bundle.putInt("index", 0);
        bundle.putString("reservation", "61.6%");
        bundle.putInt("limit", 15);
        bundle.putInt("day", 1);
        fragment.setArguments(bundle);
    } */

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
            getSupportFragmentManager().beginTransaction().hide(detailFragment).commit();
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

    public void showMovieDetailActivity(int image, String title) {
        detailFragment = new MovieDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("image", image);
        bundle.putString("title", title);
        detailFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, detailFragment).commit();
    }

    public void showWriteComment() {
        Intent intent = new Intent(getApplicationContext(), writeComment.class);
        startActivityForResult(intent, 101);

        //Toast.makeText(getApplicationContext(), "한줄평 작성 버튼이 눌렸습니다.", Toast.LENGTH_LONG).show();
    }

    public void showAllComment() {
        Intent intent = new Intent(getApplicationContext(), seeAllComment.class);
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


}
