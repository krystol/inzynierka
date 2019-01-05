package ak.inzynierka.core;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ak.inzynierka.R;

public class Salka extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salka);


        ListView listaDanychSalki = findViewById(R.id.daneSalki);

        List<String> values = new ArrayList<String>();
        values.add("Salka 1");
        values.add("Zajeta");
        values.add("Wynajeta przez pokoj 924");
        values.add("Klucz w pokoju 214");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);

        listaDanychSalki.setAdapter(adapter);
    }

}
