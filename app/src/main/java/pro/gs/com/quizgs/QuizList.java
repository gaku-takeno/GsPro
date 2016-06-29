package pro.gs.com.quizgs;

import java.io.Serializable;

/**
 * Created by takenogaku on 2016/06/29.
 */
public class QuizList implements Serializable {

    private String question;
    private String choice1;
    private String choice2;
    private String choice3;
    private int correctAnswer;

    private int yourAnswer;
    private int is_correct;


    public void setQuestion (String question) {
        this.question = question;
    }
    public String getQuestion () {
        return this.question;
    }

    public void setChoice1 (String choice1) {
        this.choice1 = choice1;
    }
    public String getChoice1 () {
        return this.choice1;
    }

    public void setChoice2 (String choice2) {
        this.choice2 = choice2;
    }
    public String getChoice2 () {
        return this.choice2;
    }

    public void setChoice3 (String choice3) {
        this.choice3 = choice3;
    }
    public String getChoice3 () {
        return this.choice3;
    }

    public void setCorrectAnswer (int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
    public int getCorrectAnswer () {
        return this.correctAnswer;
    }

    public void setYourAnswer (int yourAnswer) {
        this.yourAnswer = yourAnswer;
    }
    public int getYourAnswer () {
        return this.yourAnswer;
    }


    public void setIs_correct (int is_correct) {
        this.is_correct = is_correct;
    }
    public int getIs_correct () {
        return this.is_correct;
    }

}
