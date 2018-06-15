package ak.inzynierka;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import ak.inzynierka.Model.OgloszenieData;


public class OgloszeniaLista extends Activity {

    private List<OgloszenieData> listaOgloszen = new ArrayList<OgloszenieData>();
    private ListView listaOgloszenListView;
    private int idEdytowanegoOgloszenia = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ogloszenia_lista);

        listaOgloszenListView = findViewById(R.id.listaOgloszenListView);

        listaOgloszen.add(new OgloszenieData("Slawek", "Sprzedam Opla", "2005 rok, stoi za akademikiem", new Date()));
        listaOgloszen.add(new OgloszenieData("Tomek", "Sprzedam lodowke", "za czteropak", new Date()));
        listaOgloszen.add(new OgloszenieData("Aleksander", "Oddam szklanki i kubki", "Wyprowadzam sie wiec jak cos to w 523 sa szklanki i kubki do wziecia za darmo", new Date()));

        populateList(listaOgloszenListView);

        listaOgloszenListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int itemNumber, long id) {
                Intent edytujOgloszenieIntent = new Intent(view.getContext(), OgloszenieEdycja.class);
                edytujOgloszenieIntent.putExtra("Ogloszenie", listaOgloszen.get(itemNumber));
                idEdytowanegoOgloszenia = itemNumber;
                startActivityForResult(edytujOgloszenieIntent, 1);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ogloszenia_lista, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Dodaj ogloszenie
        Intent edycjaOgloszeniaIntent = new Intent(this, OgloszenieEdycja.class);
        startActivityForResult(edycjaOgloszeniaIntent, 1);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_CANCELED){
            return;
        }
        Serializable extra = data.getSerializableExtra("Ogloszenie");
        if (extra != null) {
            OgloszenieData ogloszenie = (OgloszenieData) extra;
            if(idEdytowanegoOgloszenia > -1){
                listaOgloszen.set(idEdytowanegoOgloszenia, ogloszenie);
                idEdytowanegoOgloszenia = -1;
            }else {
                listaOgloszen.add(ogloszenie);
            }
        }
        populateList(listaOgloszenListView);
    }

    private void populateList(ListView listaOgloszenListView) {
        List<String> tytulyOgloszen = new ArrayList<String>();
        for (OgloszenieData ogloszenie : listaOgloszen) {
            tytulyOgloszen.add(ogloszenie.getTytul());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, tytulyOgloszen);

        listaOgloszenListView.setAdapter(adapter);
    }
}
