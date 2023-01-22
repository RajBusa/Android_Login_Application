package com.example.practical1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.Base64;
import java.util.Date;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "demodb";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "mydata";
    private static final String ID_COL = "id";
    private static final String NAME_COL = "name";
    private static final String EMAIL_COL = "email";
    private static final String PASS_COL = "password";
    private static final String TIME_STAMP = "timestamp";




    public DBHandler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + EMAIL_COL + " TEXT,"
                + PASS_COL + " TEXT,"
                + TIME_STAMP + " TEXT)";
        sqLiteDatabase.execSQL(query);
    }

    public void addNewRecord(User user){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String salt = "RajBusa";
        byte[] encryptPassword = Base64.getEncoder().encode((user.getPassword() + salt).getBytes());

        values.put(NAME_COL,user.getName());
        values.put(EMAIL_COL,user.getEmail());
        values.put(PASS_COL,encryptPassword);
        values.put(TIME_STAMP,new Date().toString());
        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public boolean checkusernamepassword(String email, String password){
        return true;
//        SQLiteDatabase db = this.getWritableDatabase();
//        byte[] encryptPassword = Base64.getEncoder().encode((password + "RajBusa").getBytes());
//        Cursor cursor = db.rawQuery("Select * from mydata where email = ? and password = ?",
//                new String[] {String.valueOf(email), String.valueOf(encryptPassword)});
//        if(cursor.getCount()>0)
//            return true;
//        else
//            return false;
    }

//    public boolean checkusernamepassword(String email, String password){
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery("Select * from mydata where email = ?",
//                new String[] {String.valueOf(email)});
//        String useremail, pass;
//        if(cursor.moveToFirst()){
//            do
//            {
//                useremail = cursor.getString(0);
//
//                if (useremail.equals(email))
//                {
//                    pass = cursor.getString(1);
//                    byte[] encryptBytePassword = Base64.getDecoder().decode(pass.getBytes());
//                    String encryptPassword = new String(encryptBytePassword);
//                    if(encryptPassword.equals(password + "RajBusa")){
//                        return true;
//                    }
//                    break;
//                }
//            }
//            while (cursor.moveToNext());
//        }
//        return false;
//    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
