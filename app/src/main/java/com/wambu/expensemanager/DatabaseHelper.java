package com.wambu.expensemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG ="Database Helper";
    private static final  String TABLE_NAME="income_table";
    private static final  String COL1="ID";
    private static final String COL2="amount";
    private static final  String COL3="name";
    private static final  String COL4="description";
    private static final String COL5= "date";





    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE " +TABLE_NAME+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"+COL2+" INTEGER,"+COL3+" TEXT,"+COL4+" TEXT,"+COL5+" TEXT)";
        db.execSQL(createTable);
    }

    public DatabaseHelper(Context context) {
        super(context,TABLE_NAME,null,1);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
    public boolean addData(int amount, String name, String desc, String date){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL2,amount);
        contentValues.put(COL3,name);
        contentValues.put(COL4,desc);
        contentValues.put(COL5,date);



        Log.d(TAG,"addData: Adding "+name+","+desc+", "+date+" ,to" +TABLE_NAME );
        long result=db.insert(TABLE_NAME,null,contentValues);

        if (result==-1){
            return false;
        }
        else {
            return true;
        }

    }

    public ArrayList<Transaction> getAllData() {
        ArrayList<Transaction> Transcationlist = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);

        while(res.moveToNext()) {
            //String id = res.getString(0);   //0 is the number of id column in your database table
            String amount = res.getString(1);
            String incomeType = res.getString(2);
            String desc = res.getString(3);
          String date = res.getString(4);

            Transaction newTransaction = new Transaction(incomeType,desc, date, amount);
            Transcationlist.add(newTransaction);
        }
        return Transcationlist;
    }

    public ArrayList<String> queryXData(){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ArrayList<String> xNewData = new ArrayList<String>();
        String query = "SELECT " + COL3 + " FROM " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            xNewData.add(cursor.getString(cursor.getColumnIndex(COL3)));
        }
        cursor.close();
        return xNewData;
    }

    public ArrayList<Float> queryYData(){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ArrayList<Float> yNewData = new ArrayList<Float>();
        String query = "SELECT " + COL2 + " FROM " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            yNewData.add(cursor.getFloat(cursor.getColumnIndex(COL2)));
        }
        cursor.close();
        return yNewData;
    }







}
