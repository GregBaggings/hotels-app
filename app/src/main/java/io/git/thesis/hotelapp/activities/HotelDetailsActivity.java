package io.git.thesis.hotelapp.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import io.git.thesis.hotelapp.HotelRequests;
import io.git.thesis.hotelapp.R;
import io.git.thesis.hotelapp.pojos.FullDetails;

public class HotelDetailsActivity extends AppCompatActivity {
    ListView roomNameList;
    ListView roomPriceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_details);

        roomNameList = (ListView) findViewById(R.id.listRooms);
        roomNameList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_gallery_item));
        roomPriceList = (ListView) findViewById(R.id.listPrices);
        roomPriceList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_gallery_item));

        String hotelName = getIntent().getStringExtra("hotelName");
        new LoadDetails(hotelName).execute();
        Toast.makeText(getBaseContext(), "Loading details...", Toast.LENGTH_SHORT).show();

    }

    private class LoadDetails extends AsyncTask<Void, Void, FullDetails> {

        ArrayAdapter<String> roomNameAdapter;
        ArrayAdapter<String> roomPricesAdapter;
        private String hotelName;

        public LoadDetails(String hotelName) {
            this.hotelName = hotelName;
        }

        @Override
        protected void onPreExecute() {
            roomNameAdapter = (ArrayAdapter) roomNameList.getAdapter();
            roomPricesAdapter = (ArrayAdapter) roomPriceList.getAdapter();
        }

        @Override
        protected FullDetails doInBackground(Void... voids) {
            HotelRequests hotelRequests = new HotelRequests();
            return hotelRequests.getDetails(hotelName);
        }

        @Override
        protected void onPostExecute(FullDetails hotelDetails) {
            if (hotelDetails != null) {
                TextView hotelNameView = (TextView) findViewById(R.id.txtHotelName);
                TextView hotelCityView = (TextView) findViewById(R.id.txtCity);
                TextView hotelAddressView = (TextView) findViewById(R.id.txtAddress);
                TextView hotelCountryView = (TextView) findViewById(R.id.txtCountry);
                hotelNameView.setText(String.valueOf(hotelDetails.getHotel().getHotelName()));
                hotelCityView.setText(String.valueOf(hotelDetails.getHotel().getCity() + ", "));
                hotelAddressView.setText(String.valueOf(hotelDetails.getHotel().getAddress() + ", "));
                hotelCountryView.setText(String.valueOf(hotelDetails.getHotel().getCountry()));

                for (int i = 0; i < hotelDetails.getRoom().size(); i++) {
                    String roomName = hotelDetails.getRoom().get(i).getRoomname();
                    roomNameAdapter.add((i + 1) + ". room: " + roomName);
                }

                for (int i = 0; i < hotelDetails.getRoom().size(); i++) {
                    int price = hotelDetails.getPrice().get(i).getPrice();
                    roomPricesAdapter.add(Integer.toString(price) + "$");
                }
            }
        }
    }
}
