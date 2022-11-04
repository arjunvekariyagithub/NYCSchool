package com.test.nycschools.data.model;

public class SchoolDetails {
    public String dbn;
    public String schoolName;
    public String numberOfTestTakers;
    public String criticalReadingAvgScore;
    public String mathAvgScore;
    public String writingAvgScore;

    public SchoolDetails(
            String dbn,
            String schoolName,
            String numberOfTestTakers,
            String criticalReadingAvgScore,
            String mathAvgScore,
            String writingAvgScore
    ) {
        this.dbn = dbn;
        this.schoolName = schoolName;
        this.numberOfTestTakers = numberOfTestTakers;
        this.criticalReadingAvgScore = criticalReadingAvgScore;
        this.mathAvgScore = mathAvgScore;
        this.writingAvgScore = writingAvgScore;
    }
}
