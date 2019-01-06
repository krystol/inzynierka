package ak.inzynierka.core;

import ak.inzynierka.R;
import ak.inzynierka.main;
import ak.inzynierka.model.BoardMessage;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class OgloszeniaLista extends Activity {

    private List<BoardMessage> listaOgloszen = new ArrayList<>();
    private ListView listaOgloszenListView;
    private int idEdytowanegoOgloszenia = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ogloszenia_lista);
        GetBoardMessagesRestTask task = new GetBoardMessagesRestTask();
        task.execute(listaOgloszen);
        listaOgloszenListView = findViewById(R.id.listaOgloszenListView);

        listaOgloszenListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int itemNumber, long id) {
                Intent edytujOgloszenieIntent = new Intent(view.getContext(), OgloszenieEdycja.class);
                edytujOgloszenieIntent.putExtra("Ogloszenie", listaOgloszen.get(itemNumber));
                        //putExtra("Ogloszenie", listaOgloszen.get(itemNumber));
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
            BoardMessage ogloszenie = (BoardMessage) extra;
            if(idEdytowanegoOgloszenia > -1){
                listaOgloszen.set(idEdytowanegoOgloszenia, ogloszenie);
                idEdytowanegoOgloszenia = -1;
            }else {
                listaOgloszen.add(ogloszenie);
                PostBoardMessagesRestTask task = new PostBoardMessagesRestTask();
                task.execute(ogloszenie);
            }
        }
        populateList(listaOgloszenListView);
    }

    private void populateList(ListView listaOgloszenListView) {
        List<String> tytulyOgloszen = new ArrayList<String>();

        for (BoardMessage ogloszenie : listaOgloszen) {
            tytulyOgloszen.add(ogloszenie.getTitle());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, tytulyOgloszen);

        listaOgloszenListView.setAdapter(adapter);
    }

    private class GetBoardMessagesRestTask extends AsyncTask<List, Void, List> {

        @Override
        protected List doInBackground(List... input) {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            List<BoardMessage> listFromServer = new ArrayList<>();
            if(isNetworkAvailable()) {
                ResponseEntity<List<BoardMessage>> response = restTemplate.exchange(
                        main.URL+"/boardMessages",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<BoardMessage>>(){});
                listFromServer = response.getBody();
            }
            return listFromServer;
        }

        @Override
        protected void onPostExecute(List list) {
            listaOgloszen = list;
            listaOgloszenListView = findViewById(R.id.listaOgloszenListView);
            populateList(listaOgloszenListView);
        }
    }

    private class PostBoardMessagesRestTask extends AsyncTask<BoardMessage, Void, ResponseEntity<BoardMessage>> {

        @Override
        protected ResponseEntity<BoardMessage> doInBackground(BoardMessage... input) {
            RestTemplate restTemplate = new RestTemplate();
            //request entity is created with request body and headers

            //setting up the request headers
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);
            requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<BoardMessage> requestEntity = new HttpEntity<>(input[0],requestHeaders);
            ResponseEntity<BoardMessage> response = null;
            if(isNetworkAvailable()) {
                response = restTemplate.exchange(
                        main.URL+"/saveBoardMessage",
                        HttpMethod.POST,
                        requestEntity,
                        new ParameterizedTypeReference<BoardMessage>(){});
            }
            return response;
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
