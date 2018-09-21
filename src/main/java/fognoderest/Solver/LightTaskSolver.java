package fognoderest.Solver;

import fognoderest.entities.LightTask;
import fognoderest.utils.UtilityMD5;


public class LightTaskSolver {

    public void hash(LightTask task){
        String encrypted = UtilityMD5.stringByHashingPassword(task.getToEncrypt());
        task.setEncrypted(encrypted);
    }
}