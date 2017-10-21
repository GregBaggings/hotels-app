package io.git.thsesis.hotelapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class Test extends AppCompatActivity {
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        listview = (ListView) findViewById(R.id.list);
        listview.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1));

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
            adapter = (ArrayAdapter) listview.getAdapter();
        }

        @Override
        protected String doInBackground(Void... voids) {
            HotelRequests hotelRequests = new HotelRequests();
            return hotelRequests.getSearchResult(getIntent().getStringExtra("destination"));
        }

        @Override
        protected void onPostExecute(String result) {
         //   for(Hotel h : list){
                adapter.add(result);
         //   }
        }
    }
}
