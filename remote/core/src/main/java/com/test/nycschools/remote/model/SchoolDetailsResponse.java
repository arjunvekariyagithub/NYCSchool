package com.test.nycschools.remote.model;

import com.squareup.moshi.Json;

public class SchoolDetailsResponse {
    @Json(name = "dbn") public String dbn;
    @Json(name = "school_name") public String schoolName;
    @Json(name = "num_of_sat_test_takers") public String numberOfTestTakers;
    @Json(name = "sat_critical_reading_avg_score") public String criticalReadingAvgScore;
    @Json(name = "sat_math_avg_score") public String mathAvgScore;
    @Json(name = "sat_writing_avg_score") public String writingAvgScore;

    public SchoolDetailsResponse(String dbn, String schoolName, String numberOfTestTakers, String criticalReadingAvgScore, String mathAvgScore, String writingAvgScore) {
        this.dbn = dbn;
        this.schoolName = schoolName;
        this.numberOfTestTakers = numberOfTestTakers;
        this.criticalReadingAvgScore = criticalReadingAvgScore;
        this.mathAvgScore = mathAvgScore;
        this.writingAvgScore = writingAvgScore;
    }
}
