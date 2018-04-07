package utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jiao.cityapplication.IndexBar.citybean.CityColumns;


public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "citys.db";
    private final static int DATABASE_VERSION = 1;
    private final static String TABLE_NAME = "Book";

    //构造函数，创建数据库
    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //建表
    public void onCreate(SQLiteDatabase db) {
        createChinaCitysTables(db);
        createInternationalTables(db);
        String sql = "CREATE TABLE " + TABLE_NAME
                + "(_id INTEGER PRIMARY KEY,"
                + " BookName VARCHAR(30)  NOT NULL,"
                + " Author VARCHAR(20),"
                + " Publisher VARCHAR(30))";
        db.execSQL(sql);
    }

    private void createInternationalTables(SQLiteDatabase db) {
        StringBuilder sbSql = new StringBuilder();
        sbSql.append("CREATE TABLE IF NOT EXISTS ")
                .append(CityColumns.INTERNATIONAL_CITY_TABLE + "(")
                .append(CityColumns.IN_ID + " TEXT PRIMARY KEY,")
                .append(CityColumns.IN_CODE + " TEXT ,")
                .append(CityColumns.IN_PARENT + " TEXT ,")
                .append(CityColumns.IN_NAME + " TEXT ,")
                .append(CityColumns.IN_TYPE + " TEXT ,")
                .append(CityColumns.IN_LATITUDE + " TEXT ,")
                .append(CityColumns.IN_LONGITUDE + " TEXT ,")
                .append(CityColumns.IN_DELTIME + " TEXT " + ")");
        db.execSQL(sbSql.toString());
    }

    private void createChinaCitysTables(SQLiteDatabase db) {
        StringBuilder sbSql = new StringBuilder();
        sbSql.append("CREATE TABLE IF NOT EXISTS ")
                .append(CityColumns.CHINA_CITY_TABLE + "(")
                .append(CityColumns.CN_ID + " TEXT PRIMARY KEY,")
                .append(CityColumns.CN_CODE + " TEXT ,")
                .append(CityColumns.CN_PARENT + " TEXT ,")
                .append(CityColumns.CN_NAME + " TEXT ,")
                .append(CityColumns.CN_TYPE + " TEXT ,")
                .append(CityColumns.CN_LATITUDE + " TEXT ,")
                .append(CityColumns.CN_LONGITUDE + " TEXT ,")
                .append(CityColumns.CN_DELTIME + " TEXT " + ")");
        db.execSQL(sbSql.toString());
    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }

    //获取游标
    public Cursor select() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        return cursor;
    }

    //插入一条记录
    public long insert(String bookName, String author, String publisher) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("BookName", bookName);
        cv.put("Author", author);
        cv.put("Publisher", publisher);
        long row = db.insert(TABLE_NAME, null, cv);
        return row;
    }

    //根据条件查询
//    public Cursor query(String[] args) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE BookName LIKE  ?", args);
//        return cursor;
//    }

    //删除记录
    public void delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = "_id = ?";
        String[] whereValue = {Integer.toString(id)};
        db.delete(TABLE_NAME, where, whereValue);
    }

    //更新记录
    public void update(int id, String bookName, String author, String publisher) {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = "_id = ?";
        String[] whereValue = {Integer.toString(id)};
        ContentValues cv = new ContentValues();
        cv.put("BookName", bookName);
        cv.put("Author", author);
        cv.put("Publisher", publisher);
        db.update(TABLE_NAME, cv, where, whereValue);
    }
}
