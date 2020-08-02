package org.maktab36.taskapp.controller.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.maktab36.taskapp.R;
import org.maktab36.taskapp.model.Task;
import org.maktab36.taskapp.model.TaskState;
import org.maktab36.taskapp.repository.TaskRepository;

import java.util.List;
import java.util.Random;


public class TaskTabFragment extends Fragment {
    private FloatingActionButton mActionButton;
    private List<Task> mTasks;
    private TabLayout mTabLayout;
    private ViewPager2 mTabViewPager;
    private FragmentStateAdapter mAdapter;

    public TaskTabFragment() {
        // Required empty public constructor
    }

    public static TaskTabFragment newInstance() {
        TaskTabFragment fragment = new TaskTabFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_tab, container, false);
        findViews(view);

        /*mTasks = createTaskList();*/
        updateUI();
        setListener();

        new TabLayoutMediator(mTabLayout, mTabViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 1:
                        tab.setText(TaskState.DOING.toString());
                        break;
                    case 2:
                        tab.setText(TaskState.DONE.toString());
                        break;
                    default:
                        tab.setText(TaskState.TODO.toString());
                        break;
                }
            }
        }).attach();
        return view;
    }

    private void findViews(View view) {
        mActionButton = view.findViewById(R.id.floating_action_button_add_task);
        mTabLayout = view.findViewById(R.id.tab_layout);
        mTabViewPager = view.findViewById(R.id.view_pager_tabs);
    }

    private void setListener() {
        /*mActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int taskNumber = TaskRepository.getInstance().getList().size() + 1;
                String taskName = TaskRepository.getInstance().getList().get(0)
                        .getName().split("#")[0];
                Random random = new Random();
                TaskState[] taskStates = TaskState.values();
                int r = random.nextInt(taskStates.length);
                Task task = new Task(taskName + "#" + taskNumber, taskStates[r]);
                TaskRepository.getInstance().insert(task);
            }
        });*/
    }

    private void updateUI() {
        mAdapter = new TaskViewPagerAdapter(this);
        mTabViewPager.setAdapter(mAdapter);
    }

    private class TaskViewPagerAdapter extends FragmentStateAdapter {

        public TaskViewPagerAdapter(@NonNull Fragment fragment) {
            super(fragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 1:
                    return TaskListFragment.newInstance(TaskState.DOING);
                case 2:
                    return TaskListFragment.newInstance(TaskState.DONE);
                default:
                    return TaskListFragment.newInstance(TaskState.TODO);
            }
        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }
}