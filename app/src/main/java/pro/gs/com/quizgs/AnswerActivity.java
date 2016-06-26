package pro.gs.com.quizgs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AnswerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        final Intent intent = getIntent();
        int result = intent.getIntExtra("result",0);
        int step = intent.getIntExtra("step",0);

        TextView backView = (TextView) findViewById(R.id.back);
        TextView answerView = (TextView) findViewById(R.id.answer);
        TextView titleView = (TextView) findViewById(R.id.title);

        String answer;
        if (result == 0) {
            answer = "不正解!";
        } else {
            answer = "正解!";
        }
        answerView.setText(answer);

        if (step == 2) {
            backView.setText("結果画面へ行く");
        }

        if (backView != null) {
            backView.setOnClickListener(
                    new View.OnClickListener() {
                        public void onClick(View v) {
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                    }
            );
        }
    }
}
