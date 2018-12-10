package kr.co.supp0rtyoo.movieapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button thumbsUpBtn;
    Button thumbsDownBtn;
    TextView thumbsUpTxt;
    TextView thumbsDownTxt;
    TextView evaluateTxt;
    LinearLayout allEvaluateTxt;
    int thumbsUpScore = 15;
    int thumbsDownScore = 1;
    boolean isThumbsUp = false;
    boolean isThumbsDown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thumbsUpBtn = (Button)findViewById(R.id.thumbsUp);
        thumbsDownBtn = (Button)findViewById(R.id.thumbsDown);
        thumbsUpTxt = (TextView)findViewById(R.id.thumbsUpTxt);
        thumbsDownTxt = (TextView)findViewById(R.id.thumbsDownTxt);
        evaluateTxt = (TextView)findViewById(R.id.writeEvaluate);
        allEvaluateTxt = (LinearLayout) findViewById(R.id.seeAllEvaluate);

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
                Intent intent = new Intent(getApplicationContext(), writeComment.class);
                startActivityForResult(intent, 101);

                //Toast.makeText(getApplicationContext(), "한줄평 작성 버튼이 눌렸습니다.", Toast.LENGTH_LONG).show();
            }
        });

        allEvaluateTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), seeAllComment.class);
                startActivity(intent);

                //Toast.makeText(getApplicationContext(), "한줄평 모두 보기 버튼이 눌렸습니다.", Toast.LENGTH_LONG).show();
            }
        });

        ListView evalListView = (ListView)findViewById(R.id.evaluateListview);
        EvaluateAdapter adapter = new EvaluateAdapter();

        adapter.addItem(new evaluateItems("kym71**", "적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요.", 5.0f, 10));
        adapter.addItem(new evaluateItems("kym71**", "적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요.", 4.8f, 10));

        evalListView.setAdapter(adapter);

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
