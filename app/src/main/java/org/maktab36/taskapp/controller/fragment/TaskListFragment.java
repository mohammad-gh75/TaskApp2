package org.maktab36.taskapp.controller.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.maktab36.taskapp.R;
import org.maktab36.taskapp.model.Task;
import org.maktab36.taskapp.model.TaskState;
import org.maktab36.taskapp.repository.TaskRepository;
import org.maktab36.taskapp.util.TaskListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class TaskListFragment extends Fragment {
    public static final String ARG_TASK_STATE = "taskState";
    private RecyclerView mRecyclerView;
    private TaskListAdapter mAdapter;
    private List<Task> mTasks;

    public TaskListFragment() {
        // Required empty public constructor
    }

    public static TaskListFragment newInstance(TaskState state) {
        TaskListFragment fragment = new TaskListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TASK_STATE,state.toString());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private List<Task> createTaskList() {
        List<Task> allTasks = TaskRepository.getInstance().getList();
        List<Task> tasks = new ArrayList<>();
        TaskState state=TaskState.valueOf(getArguments().getString(ARG_TASK_STATE));
        for (int i = 0; i < allTasks.size(); i++) {
            if(allTasks.get(i).getState()==state){
                tasks.add(allTasks.get(i));
            }
        }
        return tasks;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);
        findViews(view);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        } else {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        mTasks = createTaskList();
        updateUI();
//        setListener();
        return view;
    }

    private void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view_tasks);
    }

    /*private void setListener(){
    }*/

    private void updateUI() {
        if (mAdapter == null) {
            mAdapter=new TaskListAdapter(getActivity(),mTasks);
            mRecyclerView.setAdapter(mAdapter);
        } else {
//            mAdapter.notifyItemInserted(mTaskNumber);
        }
    }
}