package com.example.nabi1_trialbook;

// main class for the app
public class Experiment {
    private String Experiment;
    private String dateCreated;
    public int passes;
    public int fails;
    public float successRate;
    public int totalTrials;

    // constructor to maake new experiment
    public Experiment(String Experiment, String dateCreated, int successRate, int totalTrials) {
        this.Experiment = Experiment;
        this.dateCreated = dateCreated;
        this.successRate = successRate;
        this.totalTrials = totalTrials;
    }

    // getters and setters
    public int getPasses() {
        return passes;
    }

    public int getTotalTrials() {
        return totalTrials;
    }

    public void setTotalTrials(int totalTrials) {
        this.totalTrials = totalTrials;
    }

    public void setPasses(int passes) {
        this.passes = passes;
    }

    public int getFails() {
        return fails;
    }

    public void setFails(int fails) {
        this.fails = fails;
    }

    public float getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(float successRate) {
        this.successRate = successRate;
    }

    public void setExperiment(String experiment) {
        this.Experiment = experiment;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getExperimentName() {
        return this.Experiment;
    }

    public String getDateCreated() {
        return this.dateCreated;
    }
}
