package com.example.assignment3mad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RecordsDB {
    private final String DATABASE_NAME="RecordsDB";
    private final String DATABASE_TABLE="RecordsTable";
    private final int DATABASE_VERSION=1;
    public static final String ROW_ID="_id";
    public static final String ROW_TITLE="_title";
    public static final String ROW_DESCRIPTION="_description";
    public Context activitycontext;
    public RecordsDB(Context context){
        activitycontext=context;
    }

    public String getAllRecords() {
        String []columns=new String[]{ROW_ID,ROW_TITLE,ROW_DESCRIPTION};
        Cursor cursor=sqlDB.query(DATABASE_TABLE,columns,null,null,null,null,null);

        int id=cursor.getColumnIndex(ROW_ID);
        int title=cursor.getColumnIndex(ROW_TITLE);
        int description=cursor.getColumnIndex(ROW_DESCRIPTION);

        String records="";
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            records = records + cursor.getString(id) + ") " + cursor.getString(title) + "\n"
            + cursor.getString(description) +"\n" + HorizontalLine() + "\n";;
        }
        cursor.close();

        return records;

    }
    private String HorizontalLine() {
        // Adjust the number of dashes based on your layout
        return "--------------------------------------------------------------";
    }

    private class DBHelper extends SQLiteOpenHelper{

        public DBHelper(Context context){
            super(context,DATABASE_NAME,null,DATABASE_VERSION,null);

        }
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            /*CREATE TABLE RecordsDB(
            _id INTEGER PRIMARY KEY AUTO INCREMENT,
            _username TEXT NOT NULL,
            _password TEXT NOT NULL);
            */
            String query="CREATE TABLE "+DATABASE_TABLE+"("
                    +ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    ROW_TITLE + " TEXT NOT NULL,"+
                    ROW_DESCRIPTION + " TEXT NOT NULL);";
            sqLiteDatabase.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
            onCreate(sqLiteDatabase);
        }
    }
    private DBHelper dbHelper;
    private SQLiteDatabase sqlDB;
    public void open() throws SQLException {
        dbHelper=new DBHelper(activitycontext);
        sqlDB=dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }
    public long addNewRecord(String title,String description){
        ContentValues cv=new ContentValues();
        cv.put(ROW_TITLE,title);
        cv.put(ROW_DESCRIPTION,description);
        return sqlDB.insert(DATABASE_TABLE,null,cv);
    }
    public int deleteRecord(String id){

        return sqlDB.delete(DATABASE_TABLE,ROW_ID+"=?",new String[]{id});
    }
    public int editRecord(String id,String title,String description){
        ContentValues cv=new ContentValues();
        cv.put(ROW_ID,id);
        cv.put(ROW_TITLE,title);
        cv.put(ROW_DESCRIPTION,description);
        return sqlDB.update(DATABASE_TABLE,cv,ROW_ID+"=?",new String[]{id});
    }

}
