package io.git.thsesis.hotelapp.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import io.git.thsesis.hotelapp.HotelRequests;
import io.git.thsesis.hotelapp.R;

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
        new HttpRequest(destination).execute();
    }

    class HttpRequest extends AsyncTask<Void, Void, String> {

        ArrayAdapter<String> adapter;
        private String destination;

        public HttpRequest(String destination) {
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
            String hotelName = "";
            try {
                JSONObject obj = new JSONObject(result);
                JSONObject hotelDetails = obj.getJSONObject("hotelDetails");
                for (int i = 0; i < hotelDetails.length(); i++) {
                    JSONObject hotel = hotelDetails.getJSONObject("hotel" + i);
                    hotelName = hotel.getString("hotelName");
                    adapter.add(hotelName);
                }
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        }
    }
}
