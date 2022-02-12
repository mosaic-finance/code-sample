package com.school.app.university;

import java.util.Map;

public class School {

    private String abbreviatedName;
    private String TYPE = "State University";
    private Map<String, String> locations = Map.of(
            "0","Oregon",
            "P", "Portland",
            "SF", "San Francisco"
    );

    public School(String name) {
        this.abbreviatedName = name;
    }

    public String getFullName() {
        String fullName;
        if (this.abbreviatedName.substring(0,1) == "0") {
            fullName = this.locations.get("O") + " " + TYPE;
        } else if (this.abbreviatedName.substring(0,1) == "P") {
            fullName = this.locations.get("P") + " " + TYPE;
        } else {
            fullName = this.locations.get("SF") + " " + TYPE;
        }
        return fullName;
    }
}
