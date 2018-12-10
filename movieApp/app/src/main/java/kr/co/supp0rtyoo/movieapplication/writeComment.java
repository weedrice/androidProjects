package kr.co.supp0rtyoo.movieapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class writeComment extends AppCompatActivity {

    RatingBar ratingBar;
    EditText commentContent;
    Button saveCommentBtn;
    Button cancelCommentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_comment);

        ratingBar = (RatingBar)findViewById(R.id.commentRatingbar);
        commentContent = (EditText)findViewById(R.id.commentContents);
        saveCommentBtn = (Button)findViewById(R.id.saveComment);
        cancelCommentBtn = (Button)findViewById(R.id.cancelComment);

        saveCommentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveComment();
            }
        });

        cancelCommentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelComment();
            }
        });

    }

    private void saveComment() {
        String comment = commentContent.getText().toString();
        float rating = ratingBar.getRating();

        Intent intent = new Intent();
        intent.putExtra("comment", comment);
        intent.putExtra("rating", rating);

        setResult(RESULT_OK, intent);
        finish();
    }

    private void cancelComment() {
        finish();
    }

}
