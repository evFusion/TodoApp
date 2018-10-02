package fusion.learning_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "MyTask1";
    public static final String DB_TABLE_NAME = "Task";
    public static final String DB_COLUMN = "Title";

    private static final String TAG = "MyApp";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_tasks_table = String.format("CREATE TABLE %s (ID INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT NOT NULL);",DB_TABLE_NAME, DB_COLUMN);
        //String create_tasks_table = "CREATE TABLE " + DB_TABLE_NAME + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_TITLE + " TEXT)";
        db.execSQL(create_tasks_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_NAME);
        onCreate(db);
    }

    public void createTask(String task){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_COLUMN, task);
        db.insertWithOnConflict(DB_TABLE_NAME, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    public void deleteTask(String taskId){
        SQLiteDatabase db = this.getWritableDatabase();
        //db.execSQL("DELETE FROM " + DB_TABLE_NAME + " WHERE " + COL_ID + "=" + taskId + "");
        db.delete(DB_TABLE_NAME, DB_COLUMN + " = ?", new String[]{taskId});
        db.close();
    }

    public ArrayList<String> getTaskList(){
        ArrayList<String> taskList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DB_TABLE_NAME, new String[]{DB_COLUMN}, null, null, null, null, null);

        while(cursor.moveToNext()){
            int index = cursor.getColumnIndex(DB_COLUMN);
            taskList.add(cursor.getString(index));
            Log.i(TAG, String.valueOf(index));
        }
        cursor.close();
        db.close();
        return taskList;
    }
}