package com.codepath.simpletodo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.codepath.simpletodo.adapters.TaskAdapter;
import com.codepath.simpletodo.database.TaskDao;
import com.codepath.simpletodo.listeners.ItemListener;
import com.codepath.simpletodo.model.Task;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String LOG = MainActivity.class.getName();
    private RecyclerView mRecyclerView;
    private TaskAdapter mTasksAdapter;
    private List<Task> mTasks;
    private TaskDao mDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDao = new TaskDao(this);
        readItens();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_task);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mTasksAdapter = new TaskAdapter(this, mTasks);
        mRecyclerView.setAdapter(mTasksAdapter);
        setupListListener();
        // new SimpleAsync().execute();

    }

    private void setupListListener() {
        findViewById(R.id.btnAddItem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddItem(v);
            }
        });
        mRecyclerView.addOnItemTouchListener(new ItemListener(mRecyclerView, this, new ItemListener.OnItemListener() {
            @Override
            public void onSingleTouch(View view) {

            }

            @Override
            public void onLongTouch(View view) {

            }
        }));

//        listItens.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                mTasks.remove(position);
//                itensAdapter.notifyDataSetChanged();
//                writerItens();
//                return true;
//            }
//        });
//
//        listItens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                Intent intent = new Intent(MainActivity.this, EditActivity.class);
//                intent.putExtra(Constants.EXTRA_POSITION, position);
//                intent.putExtra(Constants.EXTRA_TASK, mTasks.get(position));
//                startActivityForResult(intent, Constants.REQUEST_EDIT);
//            }
//        });
    }

    public void onAddItem(View view) {
        EditText editText = (EditText) findViewById(R.id.etEditText);
        String taskTitle = editText.getText().toString();
        Task task = new Task(taskTitle);
        mTasks.add(0, task);
        mTasksAdapter.notifyItemChanged(0);
        editText.setText("");
        writerItens(task);
    }

    private void readItens() {
        if (mTasks == null)
            mTasks = new ArrayList<>();
        mTasks = mDao.getAllTask();
    }


    private void writerItens(Task task) {
        mDao.addTask(task);
    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode == Activity.RESULT_CANCELED) {
//            return;
//        }
//        int position = data.getIntExtra(Constants.EXTRA_POSITION, -1);
//        if (position >= 0) {
//
//            mTasks.set(position, data.getStringExtra(Constants.EXTRA_TASK));
//            itensAdapter.notifyDataSetChanged();
//            writerItens();
//        }
//    }
//
//    class SimpleAsync extends AsyncTask<Void, Void, Void> {
//
//        @Override
//        protected Void doInBackground(Void... params) {
//
//            TaskDao dao = new TaskDao(MainActivity.this);
//            Calendar calendar = Calendar.getInstance();
//            Date date = new Date();
//            calendar.setTime(date);
//            dao.addTask(new Task(1, "name", "descritpion", false, false, calendar, Constants.PRIORITY_HIGHT));
//
//            return null;
//        }
//    }


}
