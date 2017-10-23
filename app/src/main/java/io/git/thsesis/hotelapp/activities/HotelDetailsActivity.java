package io.git.thsesis.hotelapp.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import io.git.thsesis.hotelapp.HotelRequests;
import io.git.thsesis.hotelapp.R;
import io.git.thsesis.hotelapp.pojos.Hotel;

public class HotelDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_details);
        String hotelName = getIntent().getStringExtra("hotelName");
        new HttpRequest(hotelName).execute();
    }

    private class HttpRequest extends AsyncTask<Void, Void, Hotel> {

        private String hotelName;

        public HttpRequest(String hotelName) {
            this.hotelName = hotelName;
        }

        @Override
        protected Hotel doInBackground(Void... voids) {
            HotelRequests hotelRequests = new HotelRequests();
            return hotelRequests.getHotel(hotelName);
        }

        @Override
        protected void onPostExecute(Hotel hotel) {
            if (hotel != null) {
                TextView hotelNameView = (TextView) findViewById(R.id.txtHotelName);
                TextView hotelCityView = (TextView) findViewById(R.id.txtCity);
                TextView hotelAddressView = (TextView) findViewById(R.id.txtAddress);
                TextView hotelCountryView = (TextView) findViewById(R.id.txtCountry);
                hotelNameView.setText(String.valueOf(hotel.getHotelName()));
                hotelCityView.setText(String.valueOf(hotel.getCity() + ", "));
                hotelAddressView.setText(String.valueOf(hotel.getAddress() + ", "));
                hotelCountryView.setText(String.valueOf(hotel.getCountry()));
            }
        }
    }
}
