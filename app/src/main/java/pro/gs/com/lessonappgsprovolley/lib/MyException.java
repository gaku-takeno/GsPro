package pro.gs.com.lessonappgsprovolley.lib;

/**
 * 例外クラス
 *
*/
public class MyException extends  Exception {

    private String message;
    MyException (String error) {
        super(error);
        this.message = error;
    }

    public String getErrorMes () {
        return this.message+"！！！！！！！！";
    }

}
