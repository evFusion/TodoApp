package fusion.learning_db;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTask extends AppCompatActivity {

    Button btnAdd;
    EditText editText;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        editText = (EditText) findViewById(R.id.editTaskText);
        dbHelper = new DBHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            Intent iExp = null;
            public void onClick(View v) {
                iExp = new Intent(new Intent(AddTask.this, MainActivity.class));
                String task = String.valueOf(editText.getText());
                dbHelper.createTask(task);
                showToast("Запись создана");
                startActivity(iExp);
            }
        });
    }

    public void showToast(String text) {
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.show();
    }
}