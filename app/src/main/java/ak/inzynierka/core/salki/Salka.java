package ak.inzynierka.core.salki;

import ak.inzynierka.R;
import ak.inzynierka.core.MainActivity;
import ak.inzynierka.model.Room;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.Collections;

public class Salka extends Activity {
    private Room pokoj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salka);
        final ConstraintLayout rootLayaut = findViewById(R.id.SalkaLaylaut);
        final TextView roomName = findViewById(R.id.RoomName);
        final TextView numerPokoju = findViewById(R.id.NumerPokoju);
        final TextView kluczWPokoju = findViewById(R.id.KluczWPokoju);
        final TextView isOccupiedText = findViewById(R.id.CzyZajete);
        final TextView okupator = findViewById(R.id.WhoOccupies);
        Serializable extra = getIntent().getSerializableExtra("Salka");
        if (extra != null) {
            pokoj = (Room)extra;
            String nazwaPokoju = pokoj.getRoomName();
            switch(nazwaPokoju){
                case "Silownia":
                    rootLayaut.setBackgroundResource(R.drawable.gym);
                    break;
                case "Bilard":
                    rootLayaut.setBackgroundResource(R.drawable.bilard);
                    break;
                case "Joker":
                    rootLayaut.setBackgroundResource(R.drawable.party);
                    break;
                case "Pokoj spokojnej nauki":
                    rootLayaut.setBackgroundResource(R.drawable.study);
                    break;
                case "Ping-Pong":
                    rootLayaut.setBackgroundResource(R.drawable.ping2);
                    break;
            }
            roomName.setText(pokoj.getRoomName());
            numerPokoju.setText(pokoj.getRoomNumber().toString());
            kluczWPokoju.setText(pokoj.getKeyInRoomNumber().toString());
            if(pokoj.isOccupied()){
                isOccupiedText.setText("Zajete przez:");
                isOccupiedText.setTextColor(Color.parseColor("#ffcc0000"));
                if(pokoj.getOccupiedByUser()!=null) {
                    String username = pokoj.getOccupiedByUser().getFirstName() + " " + pokoj.getOccupiedByUser().getLastName();
                    okupator.setText(username);
                    okupator.setVisibility(View.VISIBLE);
                }
            }else{
                isOccupiedText.setText("Wolne");
                isOccupiedText.setTextColor(Color.parseColor("#ff669900"));
                okupator.setVisibility(View.INVISIBLE);
            }

            final Button zarezerwujSalke = findViewById(R.id.bookRoomButton);
            zarezerwujSalke.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isOccupiedText.setText("Zajete");
                    isOccupiedText.setTextColor(Color.parseColor("#ffcc0000"));
                    pokoj.setOccupied(true);
                    pokoj.setOccupiedByUser(MainActivity.loggedUser);
                    PostRoomRestTask saveRoomTask = new PostRoomRestTask();
                    saveRoomTask.execute(pokoj);
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("Pokoj", pokoj);
                    setResult(RESULT_OK, returnIntent);
                    finish();
                }
            });
        }
    }

    private class PostRoomRestTask extends AsyncTask<Room, Void, ResponseEntity<Room>> {

        @Override
        protected ResponseEntity<Room> doInBackground(Room... input) {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setAuthorization(new HttpAuthentication() {
                @Override
                public String getHeaderValue() {
                    return MainActivity.TOKEN;
                }
            });
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);
            requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<Room> requestEntity = new HttpEntity<>(input[0],requestHeaders);
            ResponseEntity<Room> response = null;
            if(isNetworkAvailable()) {
                response = restTemplate.exchange(
                        MainActivity.URL+"/saveRoom",
                        HttpMethod.POST,
                        requestEntity,
                        new ParameterizedTypeReference<Room>(){});
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

