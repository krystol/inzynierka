package ak.inzynierka.salki;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ak.inzynierka.R;

public class ListaSalek extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_salek);

        ListView listaSalekView = findViewById(R.id.listaSalekView);

        List<String> values = new ArrayList<String>();
        values.add("Bilard");
        values.add("Silownia");
        values.add("Pralnia?");
        values.add("Pokoj spokojnej nauki");
        values.add("Joker");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);

        listaSalekView.setAdapter(adapter);
        listaSalekView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int ItemNumber, long id){
                Intent salkaIntent = new Intent(view.getContext(), Salka.class);
                startActivity(salkaIntent);
            }
        });
    }

}

