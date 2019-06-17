package com.example.berksavac.myapplication.IlkYer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(Context context) {
        super(context, DataBaseOptions.DB_NAME, null, DataBaseOptions.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DataBaseOptions.CREATE_USERS_TABLE_);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseOptions.USERS_TABLE);
        onCreate(db);
    }

    public User queryUser(String email, String password) {

        SQLiteDatabase db = this.getReadableDatabase();
        User user = null;

        Cursor cursor = db.query(DataBaseOptions.USERS_TABLE, new String[]{DataBaseOptions.ID,
                        DataBaseOptions.EMAIL, DataBaseOptions.PASSWORD}, DataBaseOptions.EMAIL + "=? and " + DataBaseOptions.PASSWORD + "=?",
                new String[]{email, password}, null, null, null, "1");
        if (cursor != null)
            cursor.moveToFirst();
        if (cursor != null && cursor.getCount() > 0) {
            user = new User(cursor.getString(1), cursor.getString(2));
        }
        return user;
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DataBaseOptions.EMAIL, user.getEmail());
        values.put(DataBaseOptions.PASSWORD, user.getPassword());

        db.insert(DataBaseOptions.USERS_TABLE, null, values);
        db.close();

    }

}