package ak.inzynierka.core.pralnia;

import ak.inzynierka.R;
import ak.inzynierka.core.MainActivity;
import ak.inzynierka.core.utility.EntityUtil;
import ak.inzynierka.model.LaundryRoom;
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
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


public class PralniaLista extends Activity {

    private List<LaundryRoom> laundryList = new ArrayList<>();
    private ListView laundryListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pralnia_lista);
        laundryListView = findViewById(R.id.pralniaListaView);

        GetLaundryRestTask task = new GetLaundryRestTask();
        task.execute(laundryList);

        laundryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int itemNumber, long id) {
                Intent pralniaKalendarzIntent = new Intent(view.getContext(), PralniaKalendarz.class);
                pralniaKalendarzIntent.putExtra("LaundryRoom", laundryList.get(itemNumber));
                startActivityForResult(pralniaKalendarzIntent, 1);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pralnia, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void populateList(ListView listaSalekListView) {
        List<String> nazwySalek = new ArrayList<String>();

        for (LaundryRoom r : laundryList) {
            nazwySalek.add(r.getRoomName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, nazwySalek);
        listaSalekListView.setAdapter(adapter);
    }


    private class GetLaundryRestTask extends AsyncTask<List, Void, List> {

        @Override
        protected List doInBackground(List... input) {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            List<LaundryRoom> listFromServer = new ArrayList<>();
            if(isNetworkAvailable()) {
                ResponseEntity<List<LaundryRoom>> response = restTemplate.exchange(
                        MainActivity.URL+"/getlaundry",
                        HttpMethod.GET,
                        EntityUtil.getAuthorizationEntity(),
                        new ParameterizedTypeReference<List<LaundryRoom>>(){});
                listFromServer = response.getBody();
            }
            return listFromServer;
        }

        @Override
        protected void onPostExecute(List list) {
            laundryList = list;
            populateList(laundryListView);
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
