package io.git.thsesis.hotelapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setTitle(R.string.app_name);

        Button button = (Button) findViewById(R.id.searchButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText textViewDestination = (EditText) findViewById(R.id.hotelNameTxtView);
                String hotelName = String.valueOf(textViewDestination.getText().toString());
                new MainActivity.HttpRequest(hotelName).execute();
            }
        });
    }

    private class HttpRequest extends AsyncTask<Void, Void, Hotel> {

        private String hotelName;

        public HttpRequest(String hotelName) {
            this.hotelName = hotelName;
        }

        @Override
        protected Hotel doInBackground(Void... voids) {
            HotelsRequest hotelsRequest = new HotelsRequest();
            return hotelsRequest.getHotel(hotelName);
        }

        @Override
        protected void onPostExecute(Hotel hotel) {
            if (hotel != null) {
                TextView hotelNameView = (TextView) findViewById(R.id.textViewHotelName);
                TextView hotelCityView = (TextView) findViewById(R.id.textViewCity);
                TextView hotelAddressView = (TextView) findViewById(R.id.textViewAddress);
                hotelNameView.setText(String.valueOf(hotel.getHotelName()));
                hotelCityView.setText(String.valueOf(hotel.getCity()));
                hotelAddressView.setText(String.valueOf(hotel.getAddress()));
                Intent in = new Intent(getApplicationContext(),ResultListerActivity.class);
                startActivity(in);
            } else {
                Toast.makeText(getApplicationContext(), "No result for that search.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
