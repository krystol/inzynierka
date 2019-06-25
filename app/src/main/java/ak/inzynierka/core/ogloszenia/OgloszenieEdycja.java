package ak.inzynierka.core.ogloszenia;

import ak.inzynierka.R;
import ak.inzynierka.core.MainActivity;
import ak.inzynierka.model.BoardMessage;
import ak.inzynierka.model.User;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class OgloszenieEdycja extends Activity {

    private boolean inEditMode = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ogloszenie_edycja);


        final TextView autorTextView = findViewById(R.id.autorOgloszeniaTextView);
        final TextView tytulEditText = findViewById(R.id.tytulEditText);
        final TextView trescOgloszenia = findViewById(R.id.trescOgloszeniaEditText);
        final Button zapiszOgloszenieButton = findViewById(R.id.zapiszOgloszenieButton);
        final Button anulujOgloszenieButton = findViewById(R.id.anulujEdycjeOgloszeniaButton);
        final TextView dataTextView = findViewById(R.id.dataOgloszeniaTextView);

        Serializable extra = getIntent().getSerializableExtra("Ogloszenie");
        if (extra != null) {
            zapiszOgloszenieButton.setText("Edytuj");
            BoardMessage ogloszenie = (BoardMessage)extra;
            String author = ogloszenie.getAuthor().getFirstName()+" "+ogloszenie.getAuthor().getLastName();
            autorTextView.setText(author);
            tytulEditText.setText(ogloszenie.getTitle());
            trescOgloszenia.setText(ogloszenie.getMessage());

            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss",Locale.US);
            String date = dateFormat.format(ogloszenie.getDate());

            dataTextView.setText(date);

            inEditMode = false;
            tytulEditText.setEnabled(false);
            trescOgloszenia.setEnabled(false);
        }

        autorTextView.setText("adminTest");
        tytulEditText.setHint("Wprowadz tytul...");
        trescOgloszenia.setHint("Napisz swoje ogloszenie...");


        zapiszOgloszenieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inEditMode){
                    //Zapisz notatke
                    BoardMessage ogloszenie = new BoardMessage();
                    ogloszenie.setAuthor(MainActivity.loggedUser);
                    ogloszenie.setTitle(tytulEditText.getText().toString());
                    ogloszenie.setMessage(trescOgloszenia.getText().toString());
                    ogloszenie.setDate(Calendar.getInstance().getTime());
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("Ogloszenie", ogloszenie);
                    setResult(RESULT_OK, returnIntent);
                    finish();
                } else {
                    //Edytuj notatke
                    inEditMode = true;
                    zapiszOgloszenieButton.setText("Zapisz");
                    trescOgloszenia.setEnabled(true);
                    tytulEditText.setEnabled(true);
                }
            }
        });

        anulujOgloszenieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED, new Intent());
                finish();
            }
        });

    }

}