package com.example.multithreading.factorial;

import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;

import com.example.multithreading.factorial.Factorial.List;

class ForkAndJoin extends RecursiveTask<List> {

    private long workLoad = 0;

    public ForkAndJoin(long workLoad) {
        this.workLoad = workLoad;
    }

    protected List compute() {

        //if work is above threshold, break tasks up into smaller tasks
        if(this.workLoad > 16) {
            System.out.println("Splitting workLoad : " + this.workLoad);

            ArrayList<ForkAndJoin> subtasks =
                new ArrayList<ForkAndJoin>();
            subtasks.addAll(createSubtasks());

            for(ForkAndJoin subtask : subtasks){
                subtask.fork();
            }

            List result = null;
            for(ForkAndJoin subtask : subtasks) {
                result = subtask.join();
            }
            return result;

        } else {
            System.out.println("Doing workLoad myself: " + this.workLoad);
            return null;// Factorial.multiply(firstNumber, secondNumber);
        }
    }

    private ArrayList<ForkAndJoin> createSubtasks() {
    	ArrayList<ForkAndJoin> subtasks =
        new ArrayList<ForkAndJoin>();

        ForkAndJoin subtask1 = new ForkAndJoin(this.workLoad / 2);
        ForkAndJoin subtask2 = new ForkAndJoin(this.workLoad / 2);

        subtasks.add(subtask1);
        subtasks.add(subtask2);

        return subtasks;
    }
}
