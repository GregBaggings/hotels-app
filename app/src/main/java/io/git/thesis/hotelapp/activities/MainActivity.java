package io.git.thesis.hotelapp.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.git.thesis.hotelapp.HotelRequests;
import io.git.thesis.hotelapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setTitle(R.string.app_name);

        Button getLocationButton = (Button) findViewById(R.id.getLocationButton);
        getLocationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new MainActivity.GetLocationRequest().execute();
                Toast.makeText(getBaseContext(), "Getting your current location, please wait.", Toast.LENGTH_LONG).show();
            }
        });

        Button button = (Button) findViewById(R.id.searchButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchResultActivity.class);
                EditText textViewDestination = (EditText) findViewById(R.id.destinationTxtView);
                intent.putExtra("destination", String.valueOf(textViewDestination.getText().toString()));
                startActivity(intent);
            }
        });
    }

    private class GetLocationRequest extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            HotelRequests hotelRequests = new HotelRequests();
            return hotelRequests.getCurrentCity();
        }

        @Override
        protected void onPostExecute(String city) {
            EditText textViewDestination = (EditText) findViewById(R.id.destinationTxtView);
            textViewDestination.setText(city);
        }
    }
}
