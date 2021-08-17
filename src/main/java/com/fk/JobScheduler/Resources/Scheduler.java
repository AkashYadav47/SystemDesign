package com.fk.JobScheduler.Resources;

import com.fk.JobScheduler.Entities.Job;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private final int threadCount;
    List<Job> jobs;

    public Scheduler(int threadCount) {
        this.threadCount = threadCount;
        this.jobs = new ArrayList<>();
    }

    public void addJobs (String name, int duration, String priority, int deadline, String userType) {
        int priorityInt = Integer.parseInt(priority.substring(1));
        priorityInt *= -1;
        Job j = new Job(name, duration, priorityInt, deadline, userType);
        jobs.add(j);
    }

    public List<List<Job>> getSJF() {
        List<Job> sortedJobs = new ArrayList<>(jobs);
        sortedJobs.sort((o1, o2) -> {
            if (o1.getDuration() < o2.getDuration()) {
                return -1;
            } else if (o1.getDuration() == o2.getDuration()) {
                return Integer.compare(o2.getPriority(), o1.getPriority());
            } else{
                return 1;
            }
        });
        return fcfs(sortedJobs,false);
    }

    public List<List<Job>> getFCFS() {
        return fcfs(this.jobs,false);
    }

    public List<List<Job>> getFPS() {
        List<Job> sortedJobs = new ArrayList<>(jobs);
        sortedJobs.sort((o1, o2) -> {
            if (o1.getPriority() > o2.getPriority()) {
                return -1;
            } else if (o1.getPriority() == o2.getPriority()) {
                if(o1.getUserType() < o2.getUserType()) {
                    return -1;
                } else if(o1.getUserType() == o2.getUserType()) {
                    return Integer.compare(o2.getDuration(), o1.getDuration());
                }  else {
                    return 1;
                }
            } else {
                return 1;
            }
        });
        return fcfs(sortedJobs,false);
    }

    public List<List<Job>> getEDF() {
        List<Job> sortedJobs = new ArrayList<>(jobs);
        sortedJobs.sort((o1, o2) -> {
            if (o1.getDeadline() < o2.getDeadline()) {
                return -1;
            } else if (o1.getDeadline() == o2.getDeadline()) {
                if(o1.getPriority() > o2.getPriority()) {
                    return -1;
                } else if(o1.getPriority() == o2.getPriority()) {
                    return Integer.compare(o1.getDuration(), o2.getDuration());
                }  else {
                    return 1;
                }
            } else {
                return 1;
            }
        });
        return fcfs(sortedJobs,true);
    }

    private List<List<Job>> fcfs (List<Job> jobs, Boolean checkExpiry) {
        List<List<Job>> FCFS = new ArrayList<>();
        for(int i=0; i<threadCount; i++) {
            FCFS.add(new ArrayList<>());
        }
        for(Job j : jobs) {
            int k=0;
            int leastFinish = Integer.MAX_VALUE;
            for(int i=0; i<threadCount; i++) {
                List<Job> l = FCFS.get(i);
                if(l.size() >0 ) {
                    Job lastJob = l.get(l.size()-1);
                    if(leastFinish > lastJob.getFinish()){
                        k = i;
                        leastFinish = lastJob.getFinish();
                    }
                } else {
                    k = i;
                    leastFinish = 0;
                    FCFS.set(k, new ArrayList<>());
                    break;
                }
            }
            int finish = leastFinish+j.getDuration();
            if(!checkExpiry || finish <= j.getDeadline()) {
                j.setStart(leastFinish);
                j.setFinish(leastFinish + j.getDuration());
                FCFS.get(k).add(j);
            }
        }
        return FCFS;
    }

}
