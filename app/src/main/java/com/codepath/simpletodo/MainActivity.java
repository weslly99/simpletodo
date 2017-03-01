package com.codepath.simpletodo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String LOG = MainActivity.class.getName();
    private ListView listItens;
    private ArrayAdapter<String> itensAdapter;
    private List<String> itens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readItens();
        listItens = (ListView) findViewById(R.id.lvlItem);
        itensAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itens);
        listItens.setAdapter(itensAdapter);
        setupListListener();
        

    }

    private void setupListListener() {
        listItens.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                itens.remove(position);
                itensAdapter.notifyDataSetChanged();
                writerItens();
                return true;
            }
        });

        listItens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                intent.putExtra(Constants.EXTRA_POSITION, position);
                intent.putExtra(Constants.EXTRA_TASK, itens.get(position));
                startActivityForResult(intent, Constants.REQUEST_EDIT);
            }
        });
    }

    public void onAddItem(View view) {
        EditText editText = (EditText) findViewById(R.id.etEditText);
        String task = editText.getText().toString();
        itensAdapter.add(task);
        editText.setText("");
        writerItens();
    }

    private void readItens() {
        File todoFile = getFile();
        try {
            itens = new ArrayList<>(FileUtils.readLines(todoFile));
        } catch (IOException e) {
            itens = new ArrayList<>();
        }
    }

    private void writerItens() {
        File todoFile = getFile();
        try {
            FileUtils.writeLines(todoFile, itens);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File getFile() {
        File fileDir = getFilesDir();
        return new File(fileDir, "todo.text");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_CANCELED) {
            return;
        }
        int position = data.getIntExtra(Constants.EXTRA_POSITION, -1);
        if (position >= 0) {
            itens.set(position, data.getStringExtra(Constants.EXTRA_TASK));
            itensAdapter.notifyDataSetChanged();
            writerItens();
        }
    }


}
