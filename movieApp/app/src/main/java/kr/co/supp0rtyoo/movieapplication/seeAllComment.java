package kr.co.supp0rtyoo.movieapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class seeAllComment extends AppCompatActivity {

    TextView writeComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all_comment);

        ListView allCommentListView = (ListView)findViewById(R.id.seeAllCommentsListview);
        seeAllComment.AllCommentAdapter adapter = new seeAllComment.AllCommentAdapter();

        setComments(adapter);
        allCommentListView.setAdapter(adapter);

        writeComment = (TextView)findViewById(R.id.writeCommentInTotalList);

        writeComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), writeComment.class);
                startActivity(intent);

                finish();
            }
        });
    }

    private void setComments(seeAllComment.AllCommentAdapter adapter) {
        adapter.addItem(new evaluateItems("kym71**", "적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요.", 5.0f, 10));
        adapter.addItem(new evaluateItems("angel**", "웃긴 내용보다는 좀 더 진지한 영화.", 4.8f, 15));
        adapter.addItem(new evaluateItems("beaut**", "연기가 부족한 느낌이 드는 배우도 있다. 그래도 전체적으로는 재밌다.", 4.5f, 16));
        adapter.addItem(new evaluateItems("sales**", "적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요.", 5.0f, 18));
        adapter.addItem(new evaluateItems("supp0**", "하정우의 연기력은 명불허전!.", 4.2f, 20));
        adapter.addItem(new evaluateItems("rimel**", "약간 잔인할 수 있어요.", 4.5f, 22));
        adapter.addItem(new evaluateItems("uz48**", "조선시대 백성들의 아픔을 고스란히 전달하는 영화.", 4.3f, 25));
        adapter.addItem(new evaluateItems("saint**", "더이상 쓸말이 없을것 같아요.", 4.2f, 28));
        adapter.addItem(new evaluateItems("ghkrw**", "안드로이드 부스트코스 화이팅!", 4.9f, 32));
        adapter.addItem(new evaluateItems("xoxo2**", "뛰어난 개발자로 성장합시다!", 4.5f, 34));
        adapter.addItem(new evaluateItems("kym71**", "적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요.", 5.0f, 10));
        adapter.addItem(new evaluateItems("angel**", "웃긴 내용보다는 좀 더 진지한 영화.", 4.8f, 15));
        adapter.addItem(new evaluateItems("beaut**", "연기가 부족한 느낌이 드는 배우도 있다. 그래도 전체적으로는 재밌다.", 4.5f, 16));
        adapter.addItem(new evaluateItems("sales**", "적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요.", 5.0f, 18));
        adapter.addItem(new evaluateItems("supp0**", "하정우의 연기력은 명불허전!.", 4.2f, 20));
        adapter.addItem(new evaluateItems("rimel**", "약간 잔인할 수 있어요.", 4.5f, 22));
        adapter.addItem(new evaluateItems("uz48**", "조선시대 백성들의 아픔을 고스란히 전달하는 영화.", 4.3f, 25));
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
