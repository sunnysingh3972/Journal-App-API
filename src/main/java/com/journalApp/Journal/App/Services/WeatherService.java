package com.journalApp.Journal.App.Services;

import com.journalApp.Journal.App.Api.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class WeatherService {
    @Autowired
    private RestTemplate restTemplate;
    private static final String apikey="4a4007285a8f483c847f53bfd8185338";
    private static final String Api="https://api.weatherbit.io/v2.0/current?&city=CITY&country=IN&key=API_KEY";
    public WeatherResponse getWeather(String city){
        String finalApi=Api.replace("CITY",city).replace("API_KEY",apikey);
//        ResponseEntity<WeatherResponse> exchange = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
        ResponseEntity<WeatherResponse> exchange = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = exchange.getBody();
        if(body!=null)return body;
        return null;

    }

}
