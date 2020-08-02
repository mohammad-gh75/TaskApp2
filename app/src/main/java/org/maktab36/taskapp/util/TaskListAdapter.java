package org.maktab36.taskapp.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.maktab36.taskapp.R;
import org.maktab36.taskapp.model.Task;

import java.util.List;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskHolder> {
    private List<Task> mTaskList;
    private Context mContext;

    public TaskListAdapter(Context context,List<Task> taskList) {
        mContext=context;
        mTaskList = taskList;
    }

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view;

        if (viewType == 0) {
            view = inflater.inflate(R.layout.list_row_task_even, parent, false);
        } else {
            view = inflater.inflate(R.layout.list_row_task_odd, parent, false);
        }
        return new TaskHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
        Task task = mTaskList.get(position);
        holder.bindTask(task);
    }

    @Override
    public int getItemCount() {
        return mTaskList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public class TaskHolder extends RecyclerView.ViewHolder{
        private TextView mTextViewTaskName;
        private TextView mTextViewTaskState;

        public TaskHolder(@NonNull View itemView) {
            super(itemView);

            mTextViewTaskName = itemView.findViewById(R.id.text_view_task_name);
            mTextViewTaskState = itemView.findViewById(R.id.text_view_task_state);
        }

        public void bindTask(Task task) {
            mTextViewTaskName.setText(task.getName());
            mTextViewTaskState.setText(task.getState().toString());
        }
    }
}
