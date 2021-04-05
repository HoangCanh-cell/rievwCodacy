package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import User.User;

/**
 * Created by Created by canh 2020.

 */
public class DatabaseHandler extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "User";
    public static final int DATABASE_VERSION = 1;
    
    public void doTableDangKy(SQLiteDatabase db) {
        String sSQL = "CREATE  TABLE tbUser (username TEXT PRIMARY KEY  NOT NULL , password TEXT,rePassword TEXT)";
        db.execSQL(sSQL);
    }
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        doTableDangKy(db);

    }

    public ArrayList<User> getAllUser() {
        ArrayList<User> listDangKy = new ArrayList<User>();
        // Select All Query
        String sSQL = "SELECT  * FROM tbUser";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sSQL, null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setUsername(cursor.getString(0));
                user.setPassword(cursor.getString(1));
                // Adding contact to list
                user.setRePassword(cursor.getString(2));
                listDangKy.add(user);
            } while (cursor.moveToNext());
        }
        return listDangKy;
    }

    public void addData_DangKy(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", user.getUsername());
        values.put("password", user.getPassword());
        values.put("rePassword", user.getRePassword());
        // Inserting Row
        db.insert("tbUser", null, values);
        db.close(); // Closing database connection
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub

    }
}

