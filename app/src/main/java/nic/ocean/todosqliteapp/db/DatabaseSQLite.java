package nic.ocean.todosqliteapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.recyclerview.widget.RecyclerView;

import nic.ocean.todosqliteapp.utility.Utility;

public class DatabaseSQLite {

    private final String DB_NAME = "todoappocean";
    private final String TABLE_NAME = "oceantodo";
    private final int DB_VERSION = 1;

    // TABLE Columns name
    private final String ROW_ID = "rowId";
    private final String TODO_TITTLE = "todotittle";
    private final String TODO_MSG = "todomsg";
    private final String DATE_TIME = "datetime";

    // CREATE TABLE STUDENTS (rowId Integer AUTOINCREAMENT PRIMARY KEY, todotittle text, todomsg text, datetime text);
    private String sqlQuery = "CREATE TABLE " + TABLE_NAME + " ("+ROW_ID+" "+"INTEGER PRIMARY KEY AUTOINCREMENT"+", "+TODO_TITTLE+" text"+", "+TODO_MSG+" text"+", "+DATE_TIME+" text"+")";

    //custom MyDbHelper class that extends SQLiteOpenHelper
    MyDbHelper myDbHelper;
    SQLiteDatabase sqLiteDatabase;

    public DatabaseSQLite(Context context){
        myDbHelper = new MyDbHelper(context);
    }

    //create class method to open database
    public DatabaseSQLite openDatabase(){
        sqLiteDatabase = myDbHelper.getWritableDatabase();
        return this;
    }

    //create class that extends SQLiteOpenHelper
    class MyDbHelper extends SQLiteOpenHelper {

        public MyDbHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(sqlQuery);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    //method to insert data
    public void insertData(Context context, String todotitlle, String todomsg, String datetime){

        ContentValues contentValues = new ContentValues();
        contentValues.put(TODO_TITTLE, todotitlle);
        contentValues.put(TODO_MSG, todomsg);
        contentValues.put(DATE_TIME, datetime);

        long insertedRow = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if (insertedRow > 0){
            Utility.showLongToast(context, insertedRow+" data is successfully inserted");
        }else {
            Utility.showLongToast(context, "Something went wrong");
        }

    }

    //through cursor get all data method
    public Cursor getAllData(){
        String[] colList = new String[]{ROW_ID,TODO_TITTLE,TODO_MSG,DATE_TIME};
        return sqLiteDatabase.query(TABLE_NAME,colList,null,null,null,null,null);
    }

    //method to delete a single record
    public void deleteSingleRecord(Context context, String rowId){
        // DELETE * FROM STUDENT Where RowDI = 101;
        int deletedItems = sqLiteDatabase.delete(TABLE_NAME, ROW_ID+" = "+rowId, null);
        if (deletedItems > 0 ){
            Utility.showLongToast(context, deletedItems + " Record deleted");
        }else {
            Utility.showLongToast(context, "Something went wrong. Please try later");
        }
    }

    //method to update a record in database
    public void updateRecord(Context context, String todotitlle, String todomsg, String datetime, String rowId){

        ContentValues contentValues = new ContentValues();
        contentValues.put(TODO_TITTLE, todotitlle);
        contentValues.put(TODO_MSG, todomsg);
        contentValues.put(DATE_TIME, datetime);

        int updatedRow = sqLiteDatabase.update(TABLE_NAME, contentValues, ROW_ID+" = "+rowId, null);
        if (updatedRow > 0){
            Utility.showLongToast(context, updatedRow+" data is successfully updated");
        }else {
            Utility.showLongToast(context, "Something went wrong");
        }
    }

    //method to delete all record in database
    public void deleteAllRecords(Context context){
        int deletedItems = sqLiteDatabase.delete(TABLE_NAME, null, null);
        if (deletedItems > 0 ){
            Utility.showLongToast(context, deletedItems+" All record deleted");
        }else {
            Utility.showLongToast(context, "Something went wrong. PLease try again later");
        }
    }

}
