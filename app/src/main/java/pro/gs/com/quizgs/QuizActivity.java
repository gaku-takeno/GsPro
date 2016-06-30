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
import java.util.ArrayList;



public class QuizActivity extends AppCompatActivity {

    private int currentQuestionNumber = 0;
    private ArrayList<QuizList> questionsArrayList;
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

        // ラジオグループのオブジェクトを取得
        radioGroup = (RadioGroup)findViewById(R.id.radiogroup_answer);
        submit = (Button)findViewById(R.id.submit);

        answer1 = (RadioButton)findViewById(R.id.answer1);
        answer2 = (RadioButton)findViewById(R.id.answer2);
        answer3 = (RadioButton)findViewById(R.id.answer3);

        //questionリストを作成する
        createQuestions();

        //クイズを表示する。
        dispLayQuestion();

        //送信ボタンを押した時のイベント
        setSubmitAction();

    }



    /**
     * questionリストを作成する
     * 問題と解答の選択肢と答えを設定する
     */
    private void createQuestions() {

        questionsArrayList = new ArrayList<>();

        QuizList quizList = new QuizList();
        quizList.setQuestion("東京タワーの高さは？");
        quizList.setChoice1("100m");
        quizList.setChoice2("300m");
        quizList.setChoice3("400m");
        quizList.setCorrectAnswer(1);//この問題の正解を指定
        questionsArrayList.add(quizList);

        QuizList quizList2 = new QuizList();
        quizList2.setQuestion("東京の人口は？");
        quizList2.setChoice1("1億3000万人");
        quizList2.setChoice2("1億9000万人");
        quizList2.setChoice3("5000万人");
        quizList2.setCorrectAnswer(2);//この問題の正解を指定
        questionsArrayList.add(quizList2);

        QuizList quizList3 = new QuizList();
        quizList3.setQuestion("栗林さんの趣味は？");
        quizList3.setChoice1("プログラミング");
        quizList3.setChoice2("俳句");
        quizList3.setChoice3("音楽");
        quizList3.setCorrectAnswer(3);//この問題の正解を指定
        questionsArrayList.add(quizList3);


    }


    /**
     * 送信ボタンを押した時のイベント
     */
    private void setSubmitAction () {


        if (answer1.isChecked() || answer2.isChecked() || answer3.isChecked()) {

            //送信ボタンを押した時のイベント
            submit.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {

                        //現在のクイズのデータオブジェクトを取り出す。
                        QuizList quizList= questionsArrayList.get(currentQuestionNumber);

                        // ユーザーが選択した答えが正解なのか、不正解なのかをチェック
                        int yourAnswer = 0;
                        //ユーザーが選択した答えを取得
                        if (answer1.isChecked()) {//1番目の選択肢を選んだ場合
                            yourAnswer = 1;
                        } else if (answer2.isChecked()) {//2番目の選択肢を選んだ場合
                            yourAnswer = 2;
                        } else if (answer3.isChecked()) {//3番目の選択肢を選んだ場合
                            yourAnswer = 3;
                        }

                        //ユーザの解答をオブジェクトデータにセットする。
                        quizList.setYourAnswer(yourAnswer);

                        //ユーザーの解答と正解を比較して、一致していれば、正解とする。
                        //0の場合 不正解 1の場合:正解
                        if (yourAnswer == quizList.getCorrectAnswer()) {
                            quizList.setIs_correct(1);
                        } else {
                            quizList.setIs_correct(0);
                        }



//                        //結果画面へ遷移
//                        Intent intent = new Intent(getApplicationContext(), AnswerActivity.class);
//                        intent.putExtra("result", quizList.getIs_correct());//0:正解 1:不正解
//                        intent.putExtra("currentQuestionNumber", currentQuestionNumber);
//                        startActivityForResult(intent, Constants.REQUEST_CODE);
//                        currentQuestionNumber++;

                        setAnswer(quizList);
                    }
                }
            );


        }

    }


    /**
     * クイズを表示する。
     */
    private void dispLayQuestion() {

        String title;

        //現在のクイズが最後である場合
        if (currentQuestionNumber == Constants.LAST_QUESTION ) {
            int term = currentQuestionNumber +1;
            title = "第"+term+"問目\nいよいよ最後の問題です!";
        } else {
            int term = currentQuestionNumber +1;
            title = "第"+term+"問目";
        }
        titleView.setText(title);

        //現在のクイズのデータオブジェクトを取り出す。
        QuizList quizList = questionsArrayList.get(currentQuestionNumber);

        //現在のクイズの質問本文を表示する
        questionView.setText(quizList.getQuestion());

        //現在のクイズの答えの選択肢を表示する
        answer1.setText(quizList.getChoice1());
        answer2.setText(quizList.getChoice2());
        answer3.setText(quizList.getChoice3());


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle bundle = data.getExtras();

        switch (requestCode) {
            case Constants.REQUEST_CODE:
                if (resultCode == RESULT_OK) {

                    //radioボタンのチェッックを外す
                    radioGroup.clearCheck();
                    Log.d("hello_", currentQuestionNumber +":");


                    //最後の問題を解答した場合、結果一覧画面へ遷移する。
                    if (currentQuestionNumber > Constants.LAST_QUESTION) {
                        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                        intent.putExtra("questionsArrayList", questionsArrayList);
                        startActivity(intent);
                        finish();


                    } else {//最後のクイズでない場合、次のクイズを出力する。

                        dispLayQuestion();
                    }



                }

                break;

            default:
                break;
        }
    }





    private void setAnswer (final QuizList quizList) {

        AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);

        // ダイアログの設定
        alertDialog.setIcon(R.drawable.my_icon);   //アイコン設定
//        alertDialog.setTitle("title");      //タイトル設定
        alertDialog.setMessage("よろしいですか？");  //内容(メッセージ)設定

        // OK(肯定的な)ボタンの設定
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // OKボタン押下時の処理

                //結果画面へ遷移
                Intent intent = new Intent(getApplicationContext(), AnswerActivity.class);
                intent.putExtra("result", quizList.getIs_correct());//0:正解 1:不正解
                intent.putExtra("currentQuestionNumber", currentQuestionNumber);
                startActivityForResult(intent, Constants.REQUEST_CODE);
                currentQuestionNumber++;



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



}
