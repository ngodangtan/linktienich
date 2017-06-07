package linktienich.com.pm1703.nguyenthinga.dev.linktienich;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import linktienich.com.pm1703.nguyenthinga.dev.linktienich.item.ItemUrl;


/**
 * Created by Hoang on 5/21/2016.
 */
public class DatabaseManager {
    private static final String DATA_PATH = Environment.getDataDirectory().getPath()
            + "/data/" + "linktienich.com.pm1703.nguyenthinga.dev.linktienich/databases";
    private static final String DATA_NAME = "APP_DOC_BAO.db";
    private static final String TAG = "DatabaseManager";

    private Context context;
    private SQLiteDatabase mSqLiteDatabase;

    public DatabaseManager(Context context) {
        this.context = context;
        copyDatabase();
    }

    private void copyDatabase() {
        new File(DATA_PATH).mkdir();
        File file = new File(DATA_PATH + "/" + DATA_NAME);
        if (file.exists()) {
            return;
        } else {
            try {
                file.createNewFile();
                InputStream input = context.getAssets().open(DATA_NAME);
                FileOutputStream output = new FileOutputStream(file);

                byte data[] = new byte[1024];
                int len = 0;

                while ((len = input.read(data)) > 0) {
                    output.write(data, 0, len);
                }

                input.close();
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void opendDatabase() {
        if (mSqLiteDatabase == null || mSqLiteDatabase.isOpen() == false) {
            mSqLiteDatabase = SQLiteDatabase.openDatabase(DATA_PATH + "/" + DATA_NAME, null, SQLiteDatabase.OPEN_READWRITE);
        }
    }

    public void closeDatabase() {
        if (mSqLiteDatabase != null && mSqLiteDatabase.isOpen() == true) {
            mSqLiteDatabase.close();
        }
    }


    public ArrayList<ItemUrl> getAllItemUrl(String fieldName, int value) {
        ArrayList<ItemUrl> arrItemUrl = new ArrayList<>();
        opendDatabase();
        String sql = "SELECT * FROM DOC_BAO WHERE " + fieldName + " = " + value + " ORDER BY ID ASC";
        Cursor cursor = mSqLiteDatabase.rawQuery(sql, null);
        if (cursor == null) {
            Toast.makeText(context, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
        } else if (cursor != null) {
            cursor.moveToFirst();

            while (cursor.isAfterLast() == false) {
                int idUrl = cursor.getInt(cursor.getColumnIndex("ID"));
                String titleUrl = cursor.getString(cursor.getColumnIndex("TitleLink"));
                String url = cursor.getString(cursor.getColumnIndex("Url"));
                int type = cursor.getInt(cursor.getColumnIndex("Type"));
                int yeuThich = cursor.getInt(cursor.getColumnIndex("YeuThich"));
                int choPhepXoa = cursor.getInt(cursor.getColumnIndex("ChoPhepXoa"));
                String urlIcon = cursor.getString(cursor.getColumnIndex("UrlIcon"));
                arrItemUrl.add(new ItemUrl(idUrl, titleUrl, url, type, yeuThich, choPhepXoa, urlIcon));
                cursor.moveToNext();
            }

        }
        return arrItemUrl;
    }

    public ArrayList<ItemUrl> getAllItem() {
        ArrayList<ItemUrl> arrItemUrl = new ArrayList<>();
        opendDatabase();
        String sql = "SELECT * FROM DOC_BAO";
        Cursor cursor = mSqLiteDatabase.rawQuery(sql, null);
        if (cursor == null) {
            Toast.makeText(context, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
        } else if (cursor != null) {
            cursor.moveToFirst();

            while (cursor.isAfterLast() == false) {
                int idUrl = cursor.getInt(cursor.getColumnIndex("ID"));
                String titleUrl = cursor.getString(cursor.getColumnIndex("TitleLink"));
                String url = cursor.getString(cursor.getColumnIndex("Url"));
                int type = cursor.getInt(cursor.getColumnIndex("Type"));
                int yeuThich = cursor.getInt(cursor.getColumnIndex("YeuThich"));
                int choPhepXoa = cursor.getInt(cursor.getColumnIndex("ChoPhepXoa"));
                String urlIcon = cursor.getString(cursor.getColumnIndex("UrlIcon"));
                arrItemUrl.add(new ItemUrl(idUrl, titleUrl, url, type, yeuThich, choPhepXoa, urlIcon));
                cursor.moveToNext();
            }

        }
        return arrItemUrl;
    }

    public boolean insertData(int id, String titleUrl, String url, int type, int favorite, int enableDelete) {

        opendDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", id);
        contentValues.put("TitleLink", titleUrl);
        contentValues.put("Url", url);
        contentValues.put("Type", type);
        contentValues.put("YeuThich", favorite);
        contentValues.put("ChoPhepXoa", enableDelete);
        long valueReturn = mSqLiteDatabase.insert("DOC_BAO", null, contentValues);
        if (valueReturn == -1) {
            return false;
        }
        return true;
    }

    public int updateURL(int id, String titleUrl, String url, int type, int favorite, int enableDelete) {
        opendDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", id);
        contentValues.put("TitleLink", titleUrl);
        contentValues.put("Url", url);
        contentValues.put("Type", type);
        contentValues.put("YeuThich", favorite);
        contentValues.put("ChoPhepXoa", enableDelete);
        return mSqLiteDatabase.update("DOC_BAO", contentValues, "ID=" + id, null);
    }

    public int updateFavoritesURL(int id, int favorite) {
        opendDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("YeuThich", favorite);
        return mSqLiteDatabase.update("DOC_BAO", contentValues, "ID=" + id, null);
    }


    public int deleteUrl(int id) {
        opendDatabase();
        return mSqLiteDatabase.delete("DOC_BAO", "ID = " + id, null);
    }


}
