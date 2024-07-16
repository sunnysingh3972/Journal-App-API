package com.journalApp.Journal.App.Api;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class WeatherResponse{
    private int count;
    private List<Datum> data;
    @Getter
    @Setter
    public class Datum{

        private Weather weather;

    }
   @Getter
   @Setter
    public class Weather{
        private int code;
        private String icon;
        private String description;
    }
}
