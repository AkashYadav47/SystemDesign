package com.fk.JobScheduler;

import com.fk.JobScheduler.Entities.Job;
import com.fk.JobScheduler.Resources.Scheduler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main (String [] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\akashyad\\IdeaProjects\\MachineCoding\\src\\test\\SchedulerTest.txt"));
            String s;

            Scheduler scheduler;
            if((s = br.readLine()) != null) {
                int threadCount = Integer.parseInt(s);
                scheduler = new Scheduler(threadCount);

                while ((s = br.readLine()) != null) {
                    String[] jobArgs = s.split(" ");
                    String name = jobArgs[0];
                    int duration = Integer.parseInt(jobArgs[1]);
                    String priority = jobArgs[2];
                    int deadline = Integer.parseInt(jobArgs[3]);
                    String userType = jobArgs[4];
                    scheduler.addJobs(name, duration, priority, deadline, userType);
                }

                List<List<Job>> SJF = scheduler.getSJF();
                printJobs(SJF,"SJF");
                List<List<Job>> FCFS = scheduler.getFCFS();
                printJobs(FCFS,"FCFS");
                List<List<Job>> FPS = scheduler.getFPS();
                printJobs(FPS,"FPS");
                List<List<Job>> EDF = scheduler.getEDF();
                printJobs(EDF,"EDF");
            }

        } catch (IOException e) {
            System.out.println("Invalid Inputs : " + e.getMessage());
        }
    }
    public static void printJobs (List<List<Job>> jobList, String scheduleType) {
        System.out.println(scheduleType);
        for(int i=0; i< jobList.size(); i++) {
            System.out.print("Thread "+ i+" -");
            for (Job j : jobList.get(i)) {
                System.out.print(" " + j.getName());
            }
            System.out.println();
        }
        System.out.println();
    }
}
