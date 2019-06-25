package ak.inzynierka.core;

import ak.inzynierka.R;
import ak.inzynierka.core.ogloszenia.OgloszeniaLista;
import ak.inzynierka.core.pralnia.PralniaLista;
import ak.inzynierka.core.salki.SalkaLista;
import ak.inzynierka.model.User;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import java.io.Serializable;


public class MainActivity extends Activity {

    public static String URL = "http://97db8ecb.ngrok.io";
    public static final User loggedUser = new User("Krystian", "Adamczyk", 214);
    public static String TOKEN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout rootLayaut = findViewById(R.id.relativeMain);
        rootLayaut.setBackgroundResource(R.drawable.background);
        Serializable extra = getIntent().getSerializableExtra("Token");
        if (extra != null) {
            TOKEN = (String)extra;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*
        Handle action bar item clicks here. The action bar will
        automatically handle clicks on the Home/Up button, so long
        as you specify a parent activity in AndroidManifest.xml.
        */
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void goToSalki(View view) {
        Intent intentSalki = new Intent(this, SalkaLista.class);
        startActivity(intentSalki);
    }

    public void goToOgloszenia(View view) {
        Intent intentOgloszenia = new Intent(this, OgloszeniaLista.class);
        startActivity(intentOgloszenia);
    }

    public void goToPralnia(View view) {
        Intent intentPralnia = new Intent(this, PralniaLista.class);
        startActivity(intentPralnia);
    }

}
