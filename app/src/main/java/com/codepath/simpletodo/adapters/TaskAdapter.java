package com.codepath.simpletodo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.simpletodo.R;
import com.codepath.simpletodo.model.Task;
import com.codepath.simpletodo.util.Formatter;

import java.util.List;

/**
 * Created by weslly on 16/04/17.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {

    private List<Task> mTasks;
    private Context mContext;

    public TaskAdapter(Context context, List<Task> tasks) {
        mContext = context;
        mTasks = tasks;
    }

    @Override
    public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_task, parent, false);
        return new TaskHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskHolder holder, int position) {
        holder.bindView(mTasks.get(position));
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    class TaskHolder extends RecyclerView.ViewHolder {
        private final int[] PRIORITYS = {
                R.drawable.ic_low,
                R.drawable.ic_medium,
                R.drawable.ic_hight,
        };

        private final int ARCHIVED = R.drawable.ic_archive;

        private CheckBox mIsDoneCb;
        private TextView mDateTv;
        private TextView mTitleTv;
        private ImageView mIsAchivedIv;
        private ImageView mPriorityIv;

        public TaskHolder(View itemView) {
            super(itemView);
            mIsDoneCb = (CheckBox) itemView.findViewById(R.id.is_done_cb);
            mTitleTv= (TextView) itemView.findViewById(R.id.title_tv);
            mDateTv = (TextView) itemView.findViewById(R.id.date_tv);
            mIsAchivedIv = (ImageView) itemView.findViewById(R.id.is_achived_iv);
            mPriorityIv = (ImageView) itemView.findViewById(R.id.priority_iv);
        }

        void bindView(Task task) {
            mTitleTv.setText(task.getTaskName());
            mDateTv.setText(Formatter.formatterDate(task.getTimestamp().getTime()));
            mIsDoneCb.setChecked(task.isStatus());
            mPriorityIv.setImageResource(PRIORITYS[task.getPriority()]);
            if (task.isArchived()) {
                mIsAchivedIv.setImageResource(ARCHIVED);
            }
        }
    }
}
