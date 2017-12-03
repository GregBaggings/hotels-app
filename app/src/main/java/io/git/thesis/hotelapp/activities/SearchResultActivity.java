package io.git.thesis.hotelapp.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import io.git.thesis.hotelapp.HotelRequests;
import io.git.thesis.hotelapp.R;

public class SearchResultActivity extends AppCompatActivity {
    ListView hotelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        hotelList = (ListView) findViewById(R.id.list);
        hotelList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1));
        hotelList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(getApplicationContext(), HotelDetailsActivity.class);
                intent.putExtra("hotelName", ((TextView) view).getText().toString());
                startActivity(intent);
            }
        });

        String destination = getIntent().getStringExtra("destination");
        new GetResults(destination).execute();
        Toast.makeText(getBaseContext(), "Getting the result for your query, please wait.", Toast.LENGTH_SHORT).show();
    }

    private class GetResults extends AsyncTask<Void, Void, String> {

        ArrayAdapter<String> adapter;
        private String destination;

        public GetResults(String destination) {
            this.destination = destination;
        }

        @Override
        protected void onPreExecute() {
            adapter = (ArrayAdapter) hotelList.getAdapter();
        }

        @Override
        protected String doInBackground(Void... voids) {
            HotelRequests hotelRequests = new HotelRequests();
            return hotelRequests.getSearchResult(getIntent().getStringExtra("destination"));
        }

        @Override
        protected void onPostExecute(String result) {
            if (result == null) {
                Toast.makeText(getBaseContext(), "No hotel found for query. You are moved back to the main screen.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            } else {
                String hotelName = "";
                try {
                    JSONObject obj = new JSONObject(result);
                    JSONObject hotelDetails = obj.getJSONObject("hotelDetails");
                    for (int i = 0; i < hotelDetails.length(); i++) {
                        JSONObject hotel = hotelDetails.getJSONObject("hotel" + i);
                        hotelName = hotel.getString("hotelName");
                        adapter.add(hotelName);
                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
