package com.example.a4thyearproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class UserRepo extends SQLiteOpenHelper {


    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "SportsApp.db";

    // User table name
    private static final String TABLE_USERS = "user";

    // User Table Columns names
    private static final String USER_ID = "user_id";
    private static final String USER_EMAIL = "user_email";
    private static final String USER_PASSWORD = "user_password";
    private static final String IS_ADMIN = "is_admin";



    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USERS + "("
            + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + USER_EMAIL +
            " TEXT," + USER_PASSWORD + " TEXT," + IS_ADMIN + " INT" + ")";

    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USERS;


    public UserRepo(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);

        // Create tables again
        onCreate(db);
    }

    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues userValues = new ContentValues();
        userValues.put(USER_EMAIL, user.getEmail());
        userValues.put(USER_PASSWORD, user.getPassword());
        userValues.put(IS_ADMIN, user.isAdmin());

        db.insert(TABLE_USERS, null, userValues);
        db.close();
    }

    public void getAllUsers(){

        ArrayList<User> userList = loadUsers();

        for(User elem : userList) {
            System.out.println(elem.toString());
        }
    }




    public ArrayList loadUsers(){

        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<User> userList = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_USERS, null);

        if(c.moveToFirst()){
            do{
                int id = c.getInt(c.getColumnIndex(USER_ID));
                String email = c.getString(c.getColumnIndex(USER_EMAIL));
                String password = c.getString(c.getColumnIndex(USER_PASSWORD));
                int admin = c.getInt(c.getColumnIndex(IS_ADMIN));

                User newUser = new User(id,email,password,admin);
                userList.add(newUser);
            } while (c.moveToNext());
        }
        return userList;
    }

    public boolean loginCheck(String email, String password) {
        boolean match = false;
        ArrayList<User> userList = loadUsers();

        for(User elem : userList) {
            if((elem.getEmail().matches(email)) && (elem.getPassword().matches(password))){
                match = true;
            } else{
                match = false;
            }
        }
        return match;
    }

    public boolean registerCheck(String email){
        ArrayList<User> userList = loadUsers();
        boolean accountExists = false;
        for(User elem : userList) {
            if((elem.getEmail().matches(email))){
                accountExists = true;
            } else{
                accountExists = false;
            }
        }
        return accountExists;
    }

}
