package com.example.happyhealthyme;

public class HappyHealthyMeDbSchema {
    public static final class BiometricsTable {
        public static final String NAME = "biometrics";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String DATE = "date";
            public static final String WEIGHT = "weight";
            public static final String HEIGHT = "height";
            public static final String SLEEP = "sleep"; //hours of sleep during night prior to DATE
        }
    }

    public static final class ActivitiesTable {
        public static final String NAME = "activities";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String NAME = "name";
            public static final String DATE = "date";
            public static final String REPEAT_DAYS = "repeat_days";
            public static final String NOTES = "notes";
        }
    }
}
