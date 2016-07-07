package pro.gs.com.lessonappgsprovolley.lib;

/**
 * throwsにより、例外クラスのMyExceptionをスローする。
 * 例外の条件の場合、例外ハンドラMyExceptionを投げる
 *
 */
public class MyErrorSample {


    public void methodB(int num) throws MyException {

        if (num <= 0) {
            throw new MyException("入力した数値は0かマイナスです。");
        }


    }

}
