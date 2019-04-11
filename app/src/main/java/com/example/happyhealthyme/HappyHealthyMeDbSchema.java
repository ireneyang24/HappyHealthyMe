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
}
