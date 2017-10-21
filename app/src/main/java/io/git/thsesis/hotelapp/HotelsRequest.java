package io.git.thsesis.hotelapp;

import android.util.Log;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Geri on 2017.09.28..
 */

public class HotelsRequest {

    String url = "http://10.0.2.2:2221/v1/hotels/hotel";
    RestTemplate restTemplate = new RestTemplate();

    public Hotel getHotel(String hotelName) {
        try {
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Hotel hotel = restTemplate.getForObject(url + "?name=" + hotelName, Hotel.class);
            return hotel;
        } catch (HttpClientErrorException e) {
            Log.e("Network Error ", e.getLocalizedMessage(), e);
            return null;
        }
    }
}
