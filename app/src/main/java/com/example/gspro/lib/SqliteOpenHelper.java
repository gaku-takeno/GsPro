package com.example.gspro.lib;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;


/**
 * SQLiteデータベースを作成するときは、SQLiteOpenHelperクラスを使います。
 *データベースの作成やバージョン管理に役立つヘルパークラスです。
 * データベースの作成がまだ行われていなければ作成を行い、作成されていればそれを開きます。
 * また、必要とあればデータベースのアップグレードを行う。
 *
 */
public class SqliteOpenHelper extends SQLiteOpenHelper {

	final static private int DB_VERSION = 1;
	//DBとなるファイル名を定義する。
	private final static String DB_NAME_ASSET = Environment.getExternalStorageDirectory() + "/sqlite_gs.db";
	private final Context mContext;

	public SqliteOpenHelper(Context context) {
		super(context, DB_NAME_ASSET, null, DB_VERSION);
		mContext = context;
	}

	/**
	 * DBが作成されるタイミングで実行される。
	 * ここでは、profile_tableというテーブルを作成する。
	 * @param db
     */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO 自動生成されたメソッド・スタブ

		// ユーザーテーブル create
		db.execSQL(
		        "create table profile_table("+
		        "	id INTEGER PRIMARY KEY,"+
                "   icon text null,"+
                "   name text null,"+
		        "   email text null,"+
                "   sex int null,"+
                "   nation text null,"+
                "   address text null,"+
                "   icon_blob blob null,"+
                "   message text null"+
				");"
		    );


	}

	@Override
	public void onUpgrade(SQLiteDatabase upDb, int oldVersion, int newVersion) {
		// TODO 自動生成されたメソッド・スタブ

		Log.d("TalkBox_upgrade",oldVersion+";"+newVersion);
		if (oldVersion == 1 && newVersion == 2) {

		}

	}

}