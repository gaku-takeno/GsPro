package pro.gs.com.lessonappgspro;

/**
 * Created by takenogaku on 2016/06/24.
 */
public class Human2 {


    private int age;

    private String name;


    /**
     * 名前を設定する
     * @param name
     */
    public void setName (String name) {
        this.name = name;
    }

    /**
     * 名前を取る
     * @return
     */
    public String getName () {
        return this.name;
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
