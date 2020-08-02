package org.maktab36.taskapp.repository;

import org.maktab36.taskapp.model.Task;

import java.util.List;
import java.util.UUID;

public class TaskRepository implements IRepository<Task> {
    private static TaskRepository sTaskRepository;
    private List<Task> mTasks;

    public static TaskRepository getInstance() {
        if(sTaskRepository==null){
            sTaskRepository=new TaskRepository();
        }
        return sTaskRepository;
    }

    private TaskRepository(){
    }


    @Override
    public List<Task> getList() {
        return mTasks;
    }

    @Override
    public void setList(List<Task> list) {
        mTasks=list;
    }

    @Override
    public Task get(UUID uuid) {
        for (Task task:mTasks) {
            if(task.getId().equals(uuid)){
                return task;
            }
        }
        return null;
    }



    @Override
    public void update(Task task) {

    }

    @Override
    public void delete(Task task) {

    }

    @Override
    public void insert(Task task) {
        mTasks.add(task);
    }

    @Override
    public void insertList(List<Task> list) {

    }

    @Override
    public int getPosition(UUID uuid) {
        return 0;
    }
}
