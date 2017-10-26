package io.git.thsesis.hotelapp;

import android.util.Log;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import io.git.thsesis.hotelapp.pojos.FullDetails;
import io.git.thsesis.hotelapp.pojos.Hotel;
import io.git.thsesis.hotelapp.pojos.Price;
import io.git.thsesis.hotelapp.pojos.Room;

/**
 * Created by Geri on 2017.09.28..
 */

public class HotelRequests {

    String hotelServiceURL = "http://10.0.2.2:2221/v1/hotels/hotel";
    String searchServiceURL = "http://10.0.2.2:2222/v2/search";
    String roomsServiceURL = "http://10.0.2.2:2224/v1/hotels/hotel/{hotelId}/rooms";
    String pricesServiceURL = "http://10.0.2.2:2223/v1/hotels/prices/price";
    RestTemplate restTemplate = new RestTemplate();

    public FullDetails getDetails(String hotelName) {
        try {
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Hotel hotelDetails = restTemplate.getForObject(hotelServiceURL + "?name=" + hotelName, Hotel.class);
            ResponseEntity<List<Room>> roomResponse = restTemplate.exchange(roomsServiceURL.replace("{hotelId}", Integer.toString(hotelDetails.getId())), HttpMethod.GET, null, new ParameterizedTypeReference<List<Room>>() {
            });
            ResponseEntity<List<Price>> priceResponse = restTemplate.exchange(pricesServiceURL + "?id=" + Integer.toString(hotelDetails.getId()),HttpMethod.GET, null, new ParameterizedTypeReference<List<Price>>() {
            });

            List<Room> rooms = roomResponse.getBody();
            List<Price> prices = priceResponse.getBody();

            FullDetails details = new FullDetails();
            details.setHotel(hotelDetails);
            details.setPrice(prices);
            details.setRoom(rooms);
            return details;
        } catch (HttpClientErrorException e) {
            Log.e("Network Error ", e.getLocalizedMessage(), e);
            return null;
        }
    }

    public String getSearchResult(String destination) {
        try {
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Log.d("URL LOG", "URL was: " + destination);
            String response = restTemplate.getForObject(searchServiceURL + "?destination={destination}", String.class, destination);
            return response;
        } catch (HttpClientErrorException e) {
            Log.e("Network Error ", e.getLocalizedMessage(), e);
            return null;
        }
    }
}
