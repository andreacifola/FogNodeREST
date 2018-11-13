package fognoderest.handler;

import fognoderest.entities.Task;

import java.util.ArrayList;

public class InterruptionHandler {

    private static InterruptionHandler ourIstance = new InterruptionHandler();
    private ArrayList<TaskFlag> taskList = new ArrayList<>();

    public static InterruptionHandler getInstance(){
        return ourIstance;
    }

    public void addTaskToList(Task task){
        TaskFlag taskFlag = new TaskFlag(task);
        taskList.add(taskFlag);
    }

    public boolean getFlagByTask(int id){
        for (int i = 0; i < taskList.size(); i++) {
            if(taskList.get(i).getTask().getID() ==id){
                return taskList.get(i).isFlag();
            }
        }
        return false;
    }

    /**
     * This method implements the interruption request.
     * Once found, the task's flag is set to true.
     * @param id : identifies the task
     * @return true/false
     */
    public boolean interruptTask(int id){
        for (int i = 0; i < taskList.size() ; i++) {

            if(taskList.get(i).getTask().getID() == id){
                taskList.get(i).setFlag(true);
                //ho cambiato il flag con successo
                System.out.println("flag cambiato con successo");
                return true;
            }
        }
        return false;
    }

    public void removeTask(int id){
        for (int i = 0; i < taskList.size(); i++) {
            if(taskList.get(i).getTask().getID() == id){
                taskList.remove(i);
            }
        }
    }
}

/**
 * This class associates a task to a flag.
 * The flag is set by default to false, changed to true when the job needs to be interrupted.
 */
class TaskFlag{
    private Task task;
    private boolean flag;
    private Thread taskThread;

    public TaskFlag(Task task){
        this.task=task;
        this.flag=false;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Thread getTaskThread() {
        return taskThread;
    }

    public void setTaskThread(Thread taskThread) {
        this.taskThread = taskThread;
    }
}