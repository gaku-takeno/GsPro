package pro.gs.com.quizgs;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class QuizActivity extends AppCompatActivity {

    private int step = 0;
    private final int REQUEST_CODE = 129;
    private ArrayList<HashMap<String,String>> arrayList;
    private TextView questionView;
    private TextView titleView;

    private RadioButton answer1;
    private RadioButton answer2;
    private RadioButton answer3;
    private RadioGroup radioGroup;
    private Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionView = (TextView)findViewById(R.id.question);
        titleView = (TextView)findViewById(R.id.title);

        arrayList = new ArrayList<HashMap<String,String>>();


        HashMap<String,String> hashMap = new HashMap<String, String>();
        hashMap.put("question","question1");
        hashMap.put("choice1","choice1");
        hashMap.put("choice2","choice2");
        hashMap.put("choice3","choice3");

        hashMap.put("answer","1");//この問題の正解を指定
        arrayList.add(hashMap);

        HashMap<String,String> hashMap2 = new HashMap<String, String>();
        hashMap2.put("question","question2");
        hashMap2.put("choice","question2");
        hashMap2.put("choice1","choice1_hashMap2");
        hashMap2.put("choice2","choice2_hashMap2");
        hashMap2.put("choice3","choice3_hashMap2");

        hashMap2.put("answer","2");//この問題の正解を指定
        arrayList.add(hashMap2);

        HashMap<String,String> hashMap3 = new HashMap<String, String>();
        hashMap3.put("question","question3");
        hashMap3.put("choice","question3");
        hashMap3.put("choice1","choice1_hashMap3");
        hashMap3.put("choice2","choice2_hashMap3");
        hashMap3.put("choice3","choice3_hashMap3");

        hashMap3.put("answer","3");//この問題の正解を指定
        arrayList.add(hashMap3);



        // ラジオグループのオブジェクトを取得
        radioGroup = (RadioGroup)findViewById(R.id.radiogroup_answer);
        submit = (Button)findViewById(R.id.submit);

        answer1 = (RadioButton)findViewById(R.id.answer1);
        answer2 = (RadioButton)findViewById(R.id.answer2);
        answer3 = (RadioButton)findViewById(R.id.answer3);



        setRadioAction();
        setQuestion();

    }


    private void setRadioAction () {


        submit.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {

                        int correctAnswer = Integer.parseInt(arrayList.get(step).get("answer"));
                        int yourAnswer = 0;
                        if (answer1.isChecked()) {
                            yourAnswer = 1;
                        } else if (answer2.isChecked()) {
                            yourAnswer = 2;
                        } else if (answer3.isChecked()) {
                            yourAnswer = 3;
                        }

                        //正解なのか、不正解なのかを指定
                        //0 不正解 1:正解
                        int correct = 0;
                        if (yourAnswer == correctAnswer) {
                            correct = 1;
                        }
                        setAnswer(correct);
                    }
                }
        );
    }


    private void setRadioAction2 () {

        if (radioGroup != null) {
            // ラジオグループのチェック状態が変更された時に呼び出されるコールバックリスナーを登録します
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                // ラジオグループのチェック状態が変更された時に呼び出されます
                // チェック状態が変更されたラジオボタンのIDが渡されます
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    RadioButton radioButton = (RadioButton) findViewById(checkedId);
                    int correctAnswer = Integer.parseInt(arrayList.get(step).get("answer"));
                    int yourAnswer = 0;
                    if (answer1.isChecked()) {
                        yourAnswer = 1;
                    } else if (answer2.isChecked()) {
                        yourAnswer = 2;
                    } else if (answer3.isChecked()) {
                        yourAnswer = 3;
                    }
                    int correct = 0;
                    if (yourAnswer == correctAnswer) {
                        correct = 1;
                    }

                    setAnswer(correct);

                }
            });
        }
    }

    private void setQuestion () {


        String title;
        if (step == 2 ) {
            int term = step +1;
            title = "第"+term+"問目\nいよいよ最後の問題です!";
        } else {
            int term = step +1;
            title = "第"+term+"問目";
        }
        titleView.setText(title);



        HashMap<String, String> map = arrayList.get(step);
        String question = map.get("question");
        String choice1 = map.get("choice1");
        String choice2 = map.get("choice2");
        String choice3 = map.get("choice3");

        questionView.setText(question);
        answer1.setText(choice1);
        answer2.setText(choice2);
        answer3.setText(choice3);


    }




    private void setAnswer (final int correct) {

        AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);

        // ダイアログの設定
        alertDialog.setIcon(R.drawable.my_icon);   //アイコン設定
//        alertDialog.setTitle("title");      //タイトル設定
        alertDialog.setMessage("ファイナルアンサー？");  //内容(メッセージ)設定

        // OK(肯定的な)ボタンの設定
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // OKボタン押下時の処理

                //ここに処理を書く
                Intent intent = new Intent(getApplicationContext(), AnswerActivity.class);
                intent.putExtra("result", correct);//0:正解 1:不正解
                intent.putExtra("step", step);
                startActivityForResult(intent, REQUEST_CODE);
                step++;


            }
        });

        // NG(否定的な)ボタンの設定
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // NGボタン押下時の処理
            }
        });

        // ダイアログの作成と描画
//        alertDialog.create();
        alertDialog.show();



    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle bundle = data.getExtras();

        switch (requestCode) {
            case REQUEST_CODE:
                if (resultCode == RESULT_OK) {

                    //radioボタンのチェッックを外す
                    radioGroup.clearCheck();
                    Log.d("hello_",step+":");
                    if (step > 2) {
                        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                        startActivity(intent);


                    } else {
                        setQuestion();
                    }



                }

                break;

            default:
                break;
        }
    }

}
