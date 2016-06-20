package pro.gs.com.lib;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by takenogaku on 2016/06/13.
 */
public class Dictionary {

    ArrayList langArg = new ArrayList<>();
    public Dictionary() {

        HashMap eng = new HashMap<>();
        HashMap jp = new HashMap<>();

        langArg.add(eng);
        langArg.add(jp);

        eng.put("good_morning","Good Morning");
        eng.put("good_afternoon","Good AfterNoon");
        eng.put("good_evening","Good Evening");
        eng.put("good_night","Good Night");

        jp.put("good_morning","おはよう");
        jp.put("good_afternoon","こんにちは");
        jp.put("good_evening","こんばんは");
        jp.put("good_night","おやすみ");

    }




}


