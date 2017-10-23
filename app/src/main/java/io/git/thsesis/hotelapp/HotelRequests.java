package io.git.thsesis.hotelapp;

import android.util.Log;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import io.git.thsesis.hotelapp.pojos.Hotel;

/**
 * Created by Geri on 2017.09.28..
 */

public class HotelRequests {

    String hotelServiceURL = "http://10.0.2.2:2221/v1/hotels/hotel";
    String searchServiceURL = "http://10.0.2.2:2222/v2/search";
    RestTemplate restTemplate = new RestTemplate();

    public Hotel getHotel(String hotelName) {
        try {
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Hotel hotel = restTemplate.getForObject(hotelServiceURL + "?name=" + hotelName, Hotel.class);
            return hotel;
        } catch (HttpClientErrorException e) {
            Log.e("Network Error ", e.getLocalizedMessage(), e);
            return null;
        }
    }

    public String getSearchResult(String destination) {
        try {
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Log.d("URL LOG", "URL was: " + destination);
            String response = restTemplate.getForObject(searchServiceURL + "?destination={destination}",String.class, destination); // AS PLAIN STRING IT WORKS (Change in Async to String from List<Hotel>)
            return response;
        } catch (HttpClientErrorException e) {
            Log.e("Network Error ", e.getLocalizedMessage(), e);
            return null;
        }
    }
}
