package pro.gs.com.lessonappgspro;



/**
 * Created by takenogaku on 2016/06/20.
 */
public class Human {

    private int age;

    private int sex;//1:男 0:女


    public Human () {
        this.age = 0;

    }
    /**
     * 性別を設定する
     * @param sex
     */
    public void setSex (int sex) {
        this.sex = sex;
    }

    /**
     * 性別を取る
     * 1:男 0:女
     * @return
     */
    public int getSex () {
        return this.sex;
    }


    /**
     * 年齢を設定する
     * @param age
     */
    public void setAge (int age) {
        this.age = age;
    }

    /**
     * 年齢を取る
     * @return
     */
    public int getAge () {
        return this.age;
    }




}
