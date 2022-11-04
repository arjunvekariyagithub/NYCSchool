package com.test.nycschools.remote.model;

import com.squareup.moshi.Json;

public class SchoolResponse {
    @Json(name = "dbn") public String dbn;
    @Json(name = "school_name") public String schoolName;
    @Json(name = "location") public String schoolAddress;
    @Json(name = "phone_number") public String schoolPhoneNumber;
}
