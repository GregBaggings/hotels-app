package io.git.thsesis.hotelapp;

import android.util.Log;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
          //  ResponseEntity<List<Hotel>> hotelListResponse = restTemplate.exchange(searchServiceURL + "?destination={destination}", HttpMethod.GET, null, new ParameterizedTypeReference<List<Hotel>>() {
         //   }, destination);  //TODO NOT WORKING... JACKSON PARSE ERROR...

            String response = restTemplate.getForObject(searchServiceURL + "?destination={destination}",String.class, destination); // AS PLAIN STRING IT WORKS (Change in Async to String from List<Hotel>)
           // Log.d("CONTENT LOG", "Content was: " + hotelListResponse.getBody());
            //List<Hotel> hotelList = hotelListResponse.getBody();
            return response;
        } catch (HttpClientErrorException e) {
            Log.e("Network Error ", e.getLocalizedMessage(), e);
            return null;
        }
    }
}
