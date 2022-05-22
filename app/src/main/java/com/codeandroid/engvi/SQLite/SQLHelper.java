package com.codeandroid.engvi.SQLite;

import static com.codeandroid.engvi.MainActivity.tuDaXemAdapter;
import static com.codeandroid.engvi.MainActivity.tuListDX;
import static com.codeandroid.engvi.MainActivity.tuListYT;
import static com.codeandroid.engvi.MainActivity.tuYeuThichAdapter;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.codeandroid.engvi.LichSuFragment;
import com.codeandroid.engvi.Tu.Tu;
import com.codeandroid.engvi.YeuThichFragment;

import java.util.ArrayList;
import java.util.List;

public class SQLHelper extends SQLiteOpenHelper {
    static final String DB_NAME = "TUDIEN";
    static final int DB_VERSION = 6;
    static final String DB_TABLE_TUYT = "TuYT";
    static final String DB_TABLE_TUDX = "TuDX";

    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;
    Context context;

    public SQLHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + DB_TABLE_TUYT + "(ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "tuAnh TEXT, phienAmAnh TEXT, loai TEXT, cacTuDongNghiaAnh TEXT, " +
                "tuViet TEXT, cacTuDongNghiaViet TEXT, " +
                "vdAnh TEXT, vdViet TEXT)";
        String query1 = "CREATE TABLE " + DB_TABLE_TUDX + "(ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "tuAnh TEXT, phienAmAnh TEXT, loai TEXT, cacTuDongNghiaAnh TEXT, " +
                "tuViet TEXT, cacTuDongNghiaViet TEXT, " +
                "vdAnh TEXT, vdViet TEXT)";
        db.execSQL(query);
        db.execSQL(query1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_TUYT);
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_TUDX);
            onCreate(db);
        }
    }

    public boolean daYeuThichTu(String tuAnh) {
        sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_TABLE_TUYT + " WHERE tuAnh=?", new String[]{tuAnh + ""});
        if (cursor.getCount() >= 1) {
            sqLiteDatabase.close();
            return true;
        }
        Log.d("sotu", "so tu " + cursor.getCount());
        sqLiteDatabase.close();
        return false;
    }

    public boolean daXemTu(String tuAnh) {
        sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_TABLE_TUDX + " WHERE tuAnh=?", new String[]{tuAnh + ""});
        if (cursor.getCount() >= 1) {
            sqLiteDatabase.close();
            return true;
        }
        sqLiteDatabase.close();
        return false;
    }


    public void yeuThichVaBoYeuThichTu(Tu tu) {
        if (!daYeuThichTu(tu.getTuAnh())) {
            tuListYT.add(tu);
            tuYeuThichAdapter.setList(tuListYT);

            new YeuThichFragment();
            sqLiteDatabase = getWritableDatabase();
            contentValues = new ContentValues();
            contentValues.put("tuAnh", tu.getTuAnh());
            contentValues.put("phienAmAnh", tu.getPhienAmAnh());
            contentValues.put("loai", tu.getLoai());
            contentValues.put("cacTuDongNghiaAnh", tu.getCacTuDongNghiaAnh());
            contentValues.put("tuViet", tu.getTuViet());
            contentValues.put("cacTuDongNghiaViet", tu.getCacTuDongNghiaViet());
            contentValues.put("vdAnh", tu.getVdAnh());
            contentValues.put("vdViet", tu.getVdViet());
            sqLiteDatabase.insert(DB_TABLE_TUYT, null, contentValues);
            sqLiteDatabase.close();
            Toast.makeText(context, "đã thêm vào yeu thich", Toast.LENGTH_SHORT).show();
        } else {
//            tuListYT.remove(tu);
            for (int i=0; i<tuListYT.size(); i++){
                if (tuListYT.get(i).getTuAnh().compareTo(tu.getTuAnh())==0){
                    tuListYT.remove(i);
                    break;
                }
            }
            tuYeuThichAdapter.setList(tuListYT);
            new YeuThichFragment();
            sqLiteDatabase = getWritableDatabase();
            sqLiteDatabase.delete(DB_TABLE_TUYT, "tuAnh=?", new String[]{tu.getTuAnh() + ""});
            sqLiteDatabase.close();
            Toast.makeText(context, "đã xóa khỏi vào db", Toast.LENGTH_SHORT).show();
        }
    }

    public void xemTu(Tu tu) {
        if (!daXemTu(tu.getTuAnh())) {
            tuListDX.add(tu);
            tuDaXemAdapter.setList(tuListDX);
            new LichSuFragment();
            sqLiteDatabase = getWritableDatabase();
            contentValues = new ContentValues();
            contentValues.put("tuAnh", tu.getTuAnh());
            contentValues.put("phienAmAnh", tu.getPhienAmAnh());
            contentValues.put("loai", tu.getLoai());
            contentValues.put("cacTuDongNghiaAnh", tu.getCacTuDongNghiaAnh());
            contentValues.put("tuViet", tu.getTuViet());
            contentValues.put("cacTuDongNghiaViet", tu.getCacTuDongNghiaViet());
            contentValues.put("vdAnh", tu.getVdAnh());
            contentValues.put("vdViet", tu.getVdViet());
            sqLiteDatabase.insert(DB_TABLE_TUDX, null, contentValues);
            sqLiteDatabase.close();
            }
    }

    public List<Tu> getAllTuYeuThich() {
        List<Tu> list = new ArrayList<>();
        sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_TABLE_TUYT , new String[]{});
        while (cursor.moveToNext()) {
            @SuppressLint("Range") String tuAnh = cursor.getString(cursor.getColumnIndex("tuAnh"));
            @SuppressLint("Range") String phienAmAnh = cursor.getString(cursor.getColumnIndex("phienAmAnh"));
            @SuppressLint("Range") String loai = cursor.getString(cursor.getColumnIndex("loai"));
            @SuppressLint("Range") String cacTuDongNghiaAnh = cursor.getString(cursor.getColumnIndex("cacTuDongNghiaAnh"));
            @SuppressLint("Range") String tuViet = cursor.getString(cursor.getColumnIndex("tuViet"));
            @SuppressLint("Range") String cacTuDongNghiaViet = cursor.getString(cursor.getColumnIndex("cacTuDongNghiaViet"));
            @SuppressLint("Range") String vdAnh = cursor.getString(cursor.getColumnIndex("vdAnh"));
            @SuppressLint("Range") String vdViet = cursor.getString(cursor.getColumnIndex("vdViet"));
            list.add(new Tu(tuAnh, phienAmAnh, loai, cacTuDongNghiaAnh, tuViet, cacTuDongNghiaViet, vdAnh, vdViet));
        }
        sqLiteDatabase.close();

        return list;
    }

    public List<Tu> getAllTuDaXem() {
        List<Tu> list = new ArrayList<>();
        sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_TABLE_TUDX, new String[]{});
        while (cursor.moveToNext()) {
            @SuppressLint("Range") String tuAnh = cursor.getString(cursor.getColumnIndex("tuAnh"));
            @SuppressLint("Range") String phienAmAnh = cursor.getString(cursor.getColumnIndex("phienAmAnh"));
            @SuppressLint("Range") String loai = cursor.getString(cursor.getColumnIndex("loai"));
            @SuppressLint("Range") String cacTuDongNghiaAnh = cursor.getString(cursor.getColumnIndex("cacTuDongNghiaAnh"));
            @SuppressLint("Range") String tuViet = cursor.getString(cursor.getColumnIndex("tuViet"));
            @SuppressLint("Range") String cacTuDongNghiaViet = cursor.getString(cursor.getColumnIndex("cacTuDongNghiaViet"));
            @SuppressLint("Range") String vdAnh = cursor.getString(cursor.getColumnIndex("vdAnh"));
            @SuppressLint("Range") String vdViet = cursor.getString(cursor.getColumnIndex("vdViet"));
            list.add(new Tu(tuAnh, phienAmAnh, loai, cacTuDongNghiaAnh, tuViet, cacTuDongNghiaViet, vdAnh, vdViet));
        }
        sqLiteDatabase.close();
        return list;
    }

    public void deleteTuDX(String tuAnh) {
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(DB_TABLE_TUDX, "tuAnh=?", new String[]{tuAnh + ""});
        sqLiteDatabase.close();
    }
}