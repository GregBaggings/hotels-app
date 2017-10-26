package io.git.thsesis.hotelapp.pojos;

import java.util.List;

/**
 * Created by Gergely_Agnecz on 10/26/2017.
 */

public class FullDetails {
    Hotel hotel;
    List<Price> price;
    List<Room> room;

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public List<Price> getPrice() {
        return price;
    }

    public void setPrice(List<Price> price) {
        this.price = price;
    }

    public List<Room> getRoom() {
        return room;
    }

    public void setRoom(List<Room> room) {
        this.room = room;
    }
}
