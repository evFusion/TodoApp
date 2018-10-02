package fusion.learning_db;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;
    ArrayAdapter<String> mAdapter;
    ListView listOfTask;
    AlertDialog.Builder build;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listOfTask = (ListView)findViewById(R.id.listOfTask);
        dbHelper = new DBHelper(this);
        loadTaskList();

       /* lstTask.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, final int arg2, long arg3) {

                TextView taskTextView = (TextView)findViewById(R.id.taskTitle);
                build = new AlertDialog.Builder(MainActivity.this);
                build.setTitle("Delete " + listItems.get(deleteItem).Title);
                build.setMessage("Do you want to delete ?");
                build.setPositiveButton("Yes",new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        //String task = String.valueOf(textView.getText());
                        //Toast.makeText( getApplicationContext(),
                          //      task.get(arg2)
                            //            + " is deleted.", 3000).show();

                       // TextView taskTextView = (TextView)findViewById(R.id.taskTitle);
                       // String task = String.valueOf(taskTextView.getText());
                        //dbHelper.deleteTask(arg2);

                        //Cursor cursor2 = dataBase.rawQuery("SELECT * FROM task;", null);
                        //cursor2.moveToPosition(arg2);
                        //dataBase.delete(DB_TABLE_NAME, DB_COLUMN + " =?", cursor2.getInt(cursor2.getColumnIndex(DBHelper.KEY_ID)));



                        //loadTaskList();
                        //dialog.cancel();
                    }
                });

                build.setNegativeButton("No", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = build.create();
                alert.show();

                return true;
            }
        });*/
    }

    public void loadTaskList() {
        ArrayList<String> taskList = dbHelper.getTaskList();
        if(mAdapter == null) {
            mAdapter = new ArrayAdapter<>(this, R.layout.row, R.id.taskTitle, taskList);
            listOfTask.setAdapter(mAdapter);
        } else {
            mAdapter.clear();
            mAdapter.addAll(taskList);
            mAdapter.notifyDataSetChanged();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent iExp = null;
        switch (item.getItemId()){
            case R.id.action_add_task:
                iExp = new Intent(new Intent(MainActivity.this, AddTask.class));
                /*final EditText taskEditText = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Добавить новую задачу")
                        .setMessage("Что будем делать дальше?")
                        .setView(taskEditText)
                        .setPositiveButton("Добавить", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String task = String.valueOf(taskEditText.getText());
                                dbHelper.addNewTask(task);
                                loadTaskList();
                            }
                        })
                        .setNegativeButton("Отмена", null)
                        .create();
                dialog.show();
                return true;*/
        }
        startActivity(iExp);
        return super.onOptionsItemSelected(item);
    }

    public void deleteTask(View view){
        View parent = (View)view.getParent();
        TextView taskTextView = parent.findViewById(R.id.taskTitle);
        String task = String.valueOf(taskTextView.getText());
        dbHelper.deleteTask(task);
        loadTaskList();
    }
}