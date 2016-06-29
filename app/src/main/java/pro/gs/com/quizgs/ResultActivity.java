package pro.gs.com.quizgs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView resultView = (TextView)findViewById(R.id.result);

        Intent intent = getIntent();
        ArrayList<QuizList> questionsArrayList = (ArrayList) intent.getSerializableExtra("questionsArrayList");

        String result = "";
        for (int c = 0; c <questionsArrayList.size();c++){
            QuizList quizList= questionsArrayList.get(c);

            result += quizList.getQuestion()+"\n";
            if (quizList.getYourAnswer() == 1) {
                result += "Your Answer:"+quizList.getChoice1()+"\n";
            } else if (quizList.getYourAnswer() == 2) {
                result += "Your Answer:"+quizList.getChoice2()+"\n";
            } else {
                result += "Your Answer:"+quizList.getChoice3()+"\n";
            }

            if (quizList.getIs_correct() == 1) {
                result += "正解!\n";
            } else {
                result += "不正解!\n";
            }

            result += "\n\n";


        }


        resultView.setText(result);

    }
}
