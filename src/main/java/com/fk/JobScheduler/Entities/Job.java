package com.fk.JobScheduler.Entities;

public class Job {
    private final String name;
    private final int duration;
    private final int priority;
    private final int deadline;
    private final int userType;
    private int start;
    private int finish;

    public Job(String name, int duration, int priority, int deadline, String userType) {
        this.name = name;
        this.duration = duration;
        this.priority = priority;
        this.deadline = deadline;
        switch (userType) {
            case "Root":
                this.userType = 0;
                break;
            case "Admin":
                this.userType = 1;
                break;
            case "User":
                this.userType = 2;
                break;
            default:
                //throw error
                this.userType = 3;
                break;
        }

    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public int getPriority() {
        return priority;
    }

    public int getDeadline() {
        return deadline;
    }

    public int getUserType() {
        return userType;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }

    public int getStart() {
        return start;
    }

    public int getFinish() {
        return finish;
    }
}
