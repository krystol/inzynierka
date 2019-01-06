package ak.inzynierka.core;

import ak.inzynierka.main;
import ak.inzynierka.model.BoardMessage;
import ak.inzynierka.model.Room;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ak.inzynierka.R;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class ListaSalek extends Activity {
    private List<Room> listaSalek = new ArrayList<>();
    private ListView listaSalekListView;
    private int idRezerwowanejSalki = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_salek);
        listaSalekListView = findViewById(R.id.listaSalekView);

        GetBoardMessagesRestTask task = new GetBoardMessagesRestTask();
        task.execute(listaSalek);
        listaSalekListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int ItemNumber, long id){
                Intent salkaIntent = new Intent(view.getContext(), Salka.class);
                startActivity(salkaIntent);
            }
        });

        listaSalekListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int itemNumber, long id) {
                Intent salkaRezerwacjaIntent = new Intent(view.getContext(), Salka.class);
                salkaRezerwacjaIntent.putExtra("Salka", listaSalek.get(itemNumber));
                //putExtra("Ogloszenie", listaOgloszen.get(itemNumber));
                idRezerwowanejSalki = itemNumber;
                startActivityForResult(salkaRezerwacjaIntent, 1);
            }
        });
    }


    private void populateList(ListView listaSalekListView) {
        List<String> nazwySalek = new ArrayList<String>();

        for (Room r : listaSalek) {
            nazwySalek.add(r.getRoomName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, nazwySalek);
        listaSalekListView.setAdapter(adapter);
    }

    private class GetBoardMessagesRestTask extends AsyncTask<List, Void, List> {

        @Override
        protected List doInBackground(List... input) {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            List<Room> listFromServer = new ArrayList<>();
            if(isNetworkAvailable()) {
                ResponseEntity<List<Room>> response = restTemplate.exchange(
                        main.URL+"/getrooms",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Room>>(){});
                listFromServer = response.getBody();
            }
            return listFromServer;
        }

        @Override
        protected void onPostExecute(List list) {
            listaSalek = list;
            populateList(listaSalekListView);
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

