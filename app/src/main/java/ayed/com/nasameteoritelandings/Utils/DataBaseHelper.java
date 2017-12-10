package ayed.com.nasameteoritelandings.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import ayed.com.nasameteoritelandings.Entities.Meteorite;

/**
 * Created by oussama on 10/12/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper

{

    public static final String DATABASE_NAME = "NASAMeteorites";
    //Meteorite Fields
    public static final String METEORITE_TABLE_NAME = "meteorites";
    public static final String METEORITE_COLUMN_ID = "id";
    public static final String METEORITE_COLUMN_NAME = "name";
    public static final String METEORITE_COLUMN_NAMETYPE = "nametype";
    public static final String METEORITE_COLUMN_RECCLASS = "recclass";
    public static final String METEORITE_COLUMN_MASS = "mass";
    public static final String METEORITE_COLUMN_FALL = "fall";
    public static final String METEORITE_COLUMN_YEAR = "year";
    public static final String METEORITE_COLUMN_RECLAT = "reclat";
    public static final String METEORITE_COLUMN_RECLONG = "reclong";
    public static final String METEORITE_COLUMN_LATITUDE = "latitude";
    public static final String METEORITE_COLUMN_LONGITUDE = "longitude";






    public DataBaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Meteorite creation
        db.execSQL("create table IF NOT EXISTS "+METEORITE_TABLE_NAME+
                "("+METEORITE_COLUMN_ID+" integer primary key,"+METEORITE_COLUMN_NAME+" text ,"+METEORITE_COLUMN_NAMETYPE+" text,"+METEORITE_COLUMN_RECCLASS+" text,"
                +METEORITE_COLUMN_MASS+" text,"+METEORITE_COLUMN_FALL+" text,"+METEORITE_COLUMN_YEAR+" text,"+METEORITE_COLUMN_RECLAT+" text,"
                +METEORITE_COLUMN_RECLONG+" text,"+METEORITE_COLUMN_LATITUDE+" text,"+METEORITE_COLUMN_LONGITUDE+" text)");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Meteorite Levels
        db.execSQL("DROP TABLE IF EXISTS "+METEORITE_TABLE_NAME);
        onCreate(db);
        db.close();

    }
    // Meteorites Methods Start
    public boolean insertMeteorites (ArrayList<Meteorite> meteorites)
    {
        ArrayList<Meteorite> listMeteorites = new ArrayList<Meteorite>();
        listMeteorites = meteorites ;
        SQLiteDatabase db = this.getWritableDatabase();
        for(Meteorite meteorite : listMeteorites ){
            ContentValues contentValues = new ContentValues();
            contentValues.put(METEORITE_COLUMN_ID, meteorite.getId());
            contentValues.put(METEORITE_COLUMN_NAME, meteorite.getName());
            contentValues.put(METEORITE_COLUMN_NAMETYPE, meteorite.getNametype());
            contentValues.put(METEORITE_COLUMN_FALL, meteorite.getFall());
            contentValues.put(METEORITE_COLUMN_RECCLASS, meteorite.getRecclass());
            contentValues.put(METEORITE_COLUMN_RECLAT, meteorite.getReclat());
            contentValues.put(METEORITE_COLUMN_RECLONG, meteorite.getReclong());
            contentValues.put(METEORITE_COLUMN_YEAR, meteorite.getYear());
            contentValues.put(METEORITE_COLUMN_MASS, meteorite.getMass());
            contentValues.put(METEORITE_COLUMN_LATITUDE, meteorite.getLatitude());
            contentValues.put(METEORITE_COLUMN_LONGITUDE, meteorite.getLongitude());

            db.insert(METEORITE_TABLE_NAME, null, contentValues);
        }
        return true ;
    }

    public ArrayList<Meteorite> getMeteorites()
    {
        ArrayList<Meteorite> listMeteorites = new ArrayList<Meteorite>();

        SQLiteDatabase  db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+METEORITE_TABLE_NAME ,null);
        res.moveToFirst();
        while (res.isAfterLast()== false)
        {
            Meteorite meteorite = new Meteorite();
            meteorite.setId(res.getInt(res.getColumnIndex(METEORITE_COLUMN_ID)));
            meteorite.setName(res.getString(res.getColumnIndex(METEORITE_COLUMN_NAME)));
            meteorite.setNametype(res.getString(res.getColumnIndex(METEORITE_COLUMN_NAMETYPE)));
            meteorite.setMass(res.getString(res.getColumnIndex(METEORITE_COLUMN_MASS)));
            meteorite.setRecclass(res.getString(res.getColumnIndex(METEORITE_COLUMN_RECCLASS)));
            meteorite.setReclat(res.getString(res.getColumnIndex(METEORITE_COLUMN_RECLAT)));
            meteorite.setReclong(res.getString(res.getColumnIndex(METEORITE_COLUMN_RECLONG)));
            meteorite.setYear(res.getString(res.getColumnIndex(METEORITE_COLUMN_YEAR)));
            meteorite.setFall(res.getString(res.getColumnIndex(METEORITE_COLUMN_FALL)));
            meteorite.setLongitude(res.getString(res.getColumnIndex(METEORITE_COLUMN_LONGITUDE)));
            meteorite.setLatitude(res.getString(res.getColumnIndex(METEORITE_COLUMN_LATITUDE)));

            listMeteorites.add(meteorite);
            res.moveToNext();
        }





        return listMeteorites ;

    }
    public Meteorite getMeteoriteById(int id)
    {
        Meteorite meteorite = new Meteorite();
        SQLiteDatabase  db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+METEORITE_TABLE_NAME+" where id="+id ,null);
        if (res != null)

            res.moveToFirst();
            meteorite.setId(res.getInt(res.getColumnIndex(METEORITE_COLUMN_ID)));
            meteorite.setName(res.getString(res.getColumnIndex(METEORITE_COLUMN_NAME)));
            meteorite.setNametype(res.getString(res.getColumnIndex(METEORITE_COLUMN_NAMETYPE)));
            meteorite.setMass(res.getString(res.getColumnIndex(METEORITE_COLUMN_MASS)));
            meteorite.setRecclass(res.getString(res.getColumnIndex(METEORITE_COLUMN_RECCLASS)));
            meteorite.setReclat(res.getString(res.getColumnIndex(METEORITE_COLUMN_RECLAT)));
            meteorite.setReclong(res.getString(res.getColumnIndex(METEORITE_COLUMN_RECLONG)));
            meteorite.setYear(res.getString(res.getColumnIndex(METEORITE_COLUMN_YEAR)));
            meteorite.setFall(res.getString(res.getColumnIndex(METEORITE_COLUMN_FALL)));
            meteorite.setLongitude(res.getString(res.getColumnIndex(METEORITE_COLUMN_LONGITUDE)));
            meteorite.setLatitude(res.getString(res.getColumnIndex(METEORITE_COLUMN_LATITUDE)));






        return meteorite ;

    }

    //Meteroites Method Ends




}
