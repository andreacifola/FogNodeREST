package fognoderest.tasksolver;

import fognoderest.entities.LightTask;
import fognoderest.utils.UtilityMD5;


public class LightTaskSolver {

    public String hash(LightTask task){
        String encrypted = UtilityMD5.stringByHashingPassword(task.getToEncrypt());
        return encrypted;
    }
}