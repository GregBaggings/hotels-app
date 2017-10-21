package io.git.thsesis.hotelapp;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ResultListerActivity extends ListActivity {

    static final String[] FRUITS = new String[]{"Apple", "Avocado", "Banana",
            "Blueberry", "Coconut", "Durian", "Guava", "Kiwifruit",
            "Jackfruit", "Mango", "Olive", "Pear", "Sugar-apple"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_result_lister);

        setListAdapter(new ArrayAdapter<>(this, R.layout.activity_result_lister, FRUITS));

        ListView listView = getListView();
        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),
                        ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
