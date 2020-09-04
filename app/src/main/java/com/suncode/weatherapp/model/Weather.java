package com.suncode.weatherapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Weather {

    private Location location;

    @SerializedName("current_observation")
    private CurrentObservation currentObservation;

    private List<Forecasts> forecasts = new ArrayList<>();

    public Location getLocation() {
        return location;
    }

    public CurrentObservation getCurrentObservation() {
        return currentObservation;
    }

    public List<Forecasts> getForecasts() {
        return forecasts;
    }

    public class Location {
        private String city;
        private String region;
        private double woeid;
        private String country;

        @SerializedName("lat")
        private double latitude;

        @SerializedName("long")
        private double longitude;

        public String getCity() {
            return city;
        }

        public String getRegion() {
            return region;
        }

        public double getWoeid() {
            return woeid;
        }

        public String getCountry() {
            return country;
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }
    }

    public class CurrentObservation {
        //kelas ini direalisasi current_observation

        private Wind wind;
        private Atmosphere atmosphere;
        private Astronomy astronomy;
        private Condition condition;

        @SerializedName("pubDate")
        private double pubDate;

        public Wind getWind() {
            return wind;
        }

        public Atmosphere getAtmosphere() {
            return atmosphere;
        }

        public Astronomy getAstronomy() {
            return astronomy;
        }

        public Condition getCondition() {
            return condition;
        }

        public double getPubDate() {
            return pubDate;
        }
    }

    public class Wind {
        private int wind;
        private int direction;
        private double speed;

        public int getWind() {
            return wind;
        }

        public int getDirection() {
            return direction;
        }

        public double getSpeed() {
            return speed;
        }
    }

    public class Atmosphere {
        private int humidity;
        private double visibility;
        private double pressure;
        private int rising;

        public int getHumidity() {
            return humidity;
        }

        public double getVisibility() {
            return visibility;
        }

        public double getPressure() {
            return pressure;
        }

        public int getRising() {
            return rising;
        }
    }

    public class Astronomy{
        private String sunrise;
        private String sunset;

        public String getSunrise() {
            return sunrise;
        }

        public String getSunset() {
            return sunset;
        }
    }

    public class Condition {
        private String text;
        private int code;
        private int temperature;

        public String getText() {
            return text;
        }

        public int getCode() {
            return code;
        }

        public int getTemperature() {
            return temperature;
        }
    }

    public class Forecasts {
        private String day;
        private double date;
        private int low;
        private int high;
        private String text;
        private int code;

        public String getDay() {
            return day;
        }

        public double getDate() {
            return date;
        }

        public int getLow() {
            return low;
        }

        public int getHigh() {
            return high;
        }

        public String getText() {
            return text;
        }

        public int getCode() {
            return code;
        }
    }
}
