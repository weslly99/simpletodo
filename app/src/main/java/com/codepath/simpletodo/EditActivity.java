package com.codepath.simpletodo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by weslly on 21/02/17.
 */

public class EditActivity extends AppCompatActivity {
    EditText editTextTask;
    Button btnSave;
    int pos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        String oldTask = intent.getStringExtra(Constants.EXTRA_TASK);
        pos = intent.getIntExtra(Constants.EXTRA_POSITION,-1);
        editTextTask = (EditText) findViewById(R.id.editTextTask);
        btnSave = (Button) findViewById(R.id.btnSave);

        editTextTask.setText(oldTask);

        setupbtnListener();
    }

    private void setupbtnListener() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String newTesk = editTextTask.getText().toString();
                result(newTesk);
            }
        });
    }

    private void result(String data){
        Intent intent = new Intent();
        intent.putExtra(Constants.EXTRA_TASK,data);
        intent.putExtra(Constants.EXTRA_POSITION,pos);
        setResult(Activity.RESULT_OK,intent);

        finish();
    }
}
