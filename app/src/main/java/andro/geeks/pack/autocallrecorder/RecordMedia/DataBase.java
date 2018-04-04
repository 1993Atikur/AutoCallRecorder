package andro.geeks.pack.autocallrecorder.RecordMedia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pallob on 4/4/18.
 */

public class DataBase extends SQLiteOpenHelper {
    public static final String database="Dataset.db";
    public static final String table_name="TruthTable";
    public static final String Col="Value";

    public static final String SQLquerry="CREATE TABLE TruthTable (Value TEXT);";

    public DataBase(Context context) {
        super(context,database,null,1);
        SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLquerry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+table_name);
        onCreate ( db );

    }
    public boolean insertvalue(String myvalue){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Col,myvalue);
        long result=db.insert(table_name,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }
    public Cursor getall(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * FROM "+table_name,null);
        return res;

    }
    public void delete(){

        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DELETE FROM "+table_name);
    }
}
