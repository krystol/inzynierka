package ak.inzynierka.core.pralnia;

import ak.inzynierka.R;
import ak.inzynierka.core.MainActivity;
import ak.inzynierka.core.utility.EntityUtil;
import ak.inzynierka.model.LaundryDay;
import ak.inzynierka.model.LaundryRoom;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PralniaGodzina extends Activity {
    private LaundryRoom room;
    private String data;
    private CheckBox cb8;
    private CheckBox cb9;
    private CheckBox cb10;
    private CheckBox cb11;
    private CheckBox cb12;
    private CheckBox cb13;
    private CheckBox cb14;
    private CheckBox cb15;
    private CheckBox cb16;
    private CheckBox cb17;
    private CheckBox cb18;
    private CheckBox cb19;
    private CheckBox cb20;
    private CheckBox cb21;
    private TextView gtv8;
    private TextView gtv9;
    private TextView gtv10;
    private TextView gtv11;
    private TextView gtv12;
    private TextView gtv13;
    private TextView gtv14;
    private TextView gtv15;
    private TextView gtv16;
    private TextView gtv17;
    private TextView gtv18;
    private TextView gtv19;
    private TextView gtv20;
    private TextView gtv21;
    private Button zarezerwujButton;
    LaundryDay dayInActivity;
    List<TextView> gtvList;
    List<CheckBox> cbList;
    private String firstAndLastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pralnia_godzina);
        firstAndLastName = MainActivity.loggedUser.getFirstName() + " " + MainActivity.loggedUser.getLastName();
        cbList = new ArrayList<>();
        cb8 = findViewById(R.id.checkBoxG8);
        cb8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if(isChecked){
                   dayInActivity.setHour8(firstAndLastName);
                   gtv8.setText(firstAndLastName);
               } else {
                   gtv8.setText("Wolne");
                   dayInActivity.setHour8(null);
               }

           }});
        cbList.add(cb8);
        cb9 = findViewById(R.id.checkBoxG9);
        cb9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    dayInActivity.setHour9(firstAndLastName);
                    gtv9.setText(firstAndLastName);
                } else {
                    gtv9.setText("Wolne");
                    dayInActivity.setHour9(null);
                }

            }});
        cbList.add(cb9);
        cb10 = findViewById(R.id.checkBoxG10);
        cb10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    dayInActivity.setHour10(firstAndLastName);
                    gtv10.setText(firstAndLastName);
                } else {
                    gtv10.setText("Wolne");
                    dayInActivity.setHour10(null);
                }

            }});
        cbList.add(cb10);
        cb11 = findViewById(R.id.checkBoxG11);
        cb11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    dayInActivity.setHour11(firstAndLastName);
                    gtv11.setText(firstAndLastName);
                } else {
                    gtv11.setText("Wolne");
                    dayInActivity.setHour11(null);
                }

            }});
        cbList.add(cb11);
        cb12 = findViewById(R.id.checkBoxG12);
        cb12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    dayInActivity.setHour12(firstAndLastName);
                    gtv12.setText(firstAndLastName);
                } else {
                    gtv12.setText("Wolne");
                    dayInActivity.setHour12(null);
                }

            }});
        cbList.add(cb12);
        cb13 = findViewById(R.id.checkBoxG13);
        cb13.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    dayInActivity.setHour13(firstAndLastName);
                    gtv13.setText(firstAndLastName);
                } else {
                    gtv13.setText("Wolne");
                    dayInActivity.setHour13(null);
                }

            }});
        cbList.add(cb13);
        cb14 = findViewById(R.id.checkBoxG14);
        cb14.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    dayInActivity.setHour14(firstAndLastName);
                    gtv14.setText(firstAndLastName);
                } else {
                    gtv14.setText("Wolne");
                    dayInActivity.setHour14(null);
                }

            }});
        cbList.add(cb14);
        cb15 = findViewById(R.id.checkBoxG15);
        cb15.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    dayInActivity.setHour15(firstAndLastName);
                    gtv15.setText(firstAndLastName);
                } else {
                    gtv15.setText("Wolne");
                    dayInActivity.setHour15(null);
                }

            }});
        cbList.add(cb15);
        cb16 = findViewById(R.id.checkBoxG16);
        cb16.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    dayInActivity.setHour16(firstAndLastName);
                    gtv16.setText(firstAndLastName);
                } else {
                    gtv16.setText("Wolne");
                    dayInActivity.setHour16(null);
                }

            }});
        cbList.add(cb16);
        cb17 = findViewById(R.id.checkBoxG17);
        cb17.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    dayInActivity.setHour17(firstAndLastName);
                    gtv17.setText(firstAndLastName);
                } else {
                    gtv17.setText("Wolne");
                    dayInActivity.setHour17(null);
                }

            }});
        cbList.add(cb17);
        cb18 = findViewById(R.id.checkBoxG18);
        cb18.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    dayInActivity.setHour18(firstAndLastName);
                    gtv18.setText(firstAndLastName);
                } else {
                    gtv18.setText("Wolne");
                    dayInActivity.setHour18(null);
                }

            }});
        cbList.add(cb18);
        cb19 = findViewById(R.id.checkBoxG19);
        cb19.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    dayInActivity.setHour19(firstAndLastName);
                    gtv19.setText(firstAndLastName);
                } else {
                    gtv19.setText("Wolne");
                    dayInActivity.setHour19(null);
                }

            }});
        cbList.add(cb19);
        cb20 = findViewById(R.id.checkBoxG20);
        cb20.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    dayInActivity.setHour20(firstAndLastName);
                    gtv20.setText(firstAndLastName);
                } else {
                    gtv20.setText("Wolne");
                    dayInActivity.setHour20(null);
                }

            }});
        cbList.add(cb20);
        cb21 = findViewById(R.id.checkBoxG21);
        cb21.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    dayInActivity.setHour21(firstAndLastName);
                    gtv21.setText(firstAndLastName);
                } else {
                    gtv21.setText("Wolne");
                    dayInActivity.setHour21(null);
                }

            }});
        cbList.add(cb21);


        gtvList = new ArrayList<>();
        gtv8 = findViewById(R.id.textWolneG8);
        gtvList.add(gtv8);
        gtv9 = findViewById(R.id.textWolneG9);
        gtvList.add(gtv9);
        gtv10 = findViewById(R.id.textWolneG10);
        gtvList.add(gtv10);
        gtv11 = findViewById(R.id.textWolneG11);
        gtvList.add(gtv11);
        gtv12 = findViewById(R.id.textWolneG12);
        gtvList.add(gtv12);
        gtv13 = findViewById(R.id.textWolneG13);
        gtvList.add(gtv13);
        gtv14 = findViewById(R.id.textWolneG14);
        gtvList.add(gtv14);
        gtv15 = findViewById(R.id.textWolneG15);
        gtvList.add(gtv15);
        gtv16 = findViewById(R.id.textWolneG16);
        gtvList.add(gtv16);
        gtv17 = findViewById(R.id.textWolneG17);
        gtvList.add(gtv17);
        gtv18 = findViewById(R.id.textWolneG18);
        gtvList.add(gtv18);
        gtv19 = findViewById(R.id.textWolneG19);
        gtvList.add(gtv19);
        gtv20 = findViewById(R.id.textWolneG20);
        gtvList.add(gtv20);
        gtv21 = findViewById(R.id.textWolneG21);
        gtvList.add(gtv21);


        zarezerwujButton = findViewById(R.id.zarezerwujPralnieButton);
        zarezerwujButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostRoomRestTask postTask = new PostRoomRestTask();
                postTask.execute(dayInActivity);
                finish();
            }
        });

        ConstraintLayout rootLayaut = findViewById(R.id.pralniaGLayaut);
        rootLayaut.setBackgroundResource(R.drawable.laundry);

        Serializable extra = getIntent().getSerializableExtra("Data");
        if (extra != null) {
            data = (String)extra;
        }
        Serializable extra2 = getIntent().getSerializableExtra("LaundryRoom");
        if (extra2 != null) {
            room = (LaundryRoom) extra2;
            setTitle(room.getRoomName());
        }

        GetLaundryDayRestTask task = new GetLaundryDayRestTask();
        task.execute(Integer.toString(room.getId()),data);

    }


    private class GetLaundryDayRestTask extends AsyncTask<String, Void, LaundryDay> {

        @Override
        protected LaundryDay doInBackground(String... input) {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            LaundryDay day = new LaundryDay();
            if(isNetworkAvailable()) {
                try {
                    ResponseEntity<LaundryDay> response = restTemplate.exchange(
                            MainActivity.URL + "/getlaundryday/" + input[0] + "/" + input[1],
                            HttpMethod.GET,
                            EntityUtil.getAuthorizationEntity(),
                            new ParameterizedTypeReference<LaundryDay>() {
                            });
                HttpStatus statusCode = response.getStatusCode();
                if(statusCode.equals(HttpStatus.OK)) day = response.getBody();
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
            return day;
        }

        @Override
        protected void onPostExecute(LaundryDay dayFromDb) {
            dayInActivity = dayFromDb;
            populateView(dayInActivity);
        }
    }

    private class PostRoomRestTask extends AsyncTask<LaundryDay, Void, ResponseEntity<LaundryDay>> {

        @Override
        protected ResponseEntity<LaundryDay> doInBackground(LaundryDay... input) {
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
            HttpEntity<LaundryDay> requestEntity = new HttpEntity<>(input[0],requestHeaders);
            ResponseEntity<LaundryDay> response = null;
            if(isNetworkAvailable()) {
                    response = restTemplate.exchange(
                            MainActivity.URL + "/saveLaundryDay",
                            HttpMethod.POST,
                            requestEntity,
                            new ParameterizedTypeReference<LaundryDay>() {
                            });
            }
            return response;
        }
        @Override
        protected void onPostExecute(ResponseEntity<LaundryDay> dayFromDb) {
            if (dayFromDb.getStatusCode().equals(HttpStatus.CREATED)) dayInActivity = dayFromDb.getBody();
            populateView(dayInActivity);
        }
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void populateView(LaundryDay laundryDay) {
        List<String> listaGodzin = new ArrayList<>();
        listaGodzin.add(laundryDay.getHour8());
        listaGodzin.add(laundryDay.getHour9());
        listaGodzin.add(laundryDay.getHour10());
        listaGodzin.add(laundryDay.getHour11());
        listaGodzin.add(laundryDay.getHour12());
        listaGodzin.add(laundryDay.getHour13());
        listaGodzin.add(laundryDay.getHour14());
        listaGodzin.add(laundryDay.getHour15());
        listaGodzin.add(laundryDay.getHour16());
        listaGodzin.add(laundryDay.getHour17());
        listaGodzin.add(laundryDay.getHour18());
        listaGodzin.add(laundryDay.getHour19());
        listaGodzin.add(laundryDay.getHour20());
        listaGodzin.add(laundryDay.getHour21());
        for (TextView tv : gtvList) {
            int index = gtvList.indexOf(tv);
            String a = listaGodzin.get(index);

            if (StringUtils.isEmpty(a)) {
                tv.setText("Wolne");
            } else {
                tv.setText(a);
                cbList.get(index).toggle();
                String firstName = a.trim().split("\\s+")[0];
                String lastName = a.trim().split("\\s+")[1];
                if(!bookedForLoggedUser(firstName,lastName)) cbList.get(index).setVisibility(View.INVISIBLE);
            }
        }
    }
    private boolean bookedForLoggedUser(String firstName, String lastName){
        return MainActivity.loggedUser.getFirstName().equals(firstName) && MainActivity.loggedUser.getLastName().equals(lastName);
    }
}