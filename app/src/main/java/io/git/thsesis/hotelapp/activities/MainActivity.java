package io.git.thsesis.hotelapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import io.git.thsesis.hotelapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setTitle(R.string.app_name);

        Button button = (Button) findViewById(R.id.searchButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SearchResultActivity.class);
                EditText textViewDestination = (EditText) findViewById(R.id.hotelNameTxtView);
                intent.putExtra("destination", String.valueOf(textViewDestination.getText().toString()));
                startActivity(intent);
            }
        });
    }


}
