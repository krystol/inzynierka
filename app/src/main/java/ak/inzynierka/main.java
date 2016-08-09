package ak.inzynierka;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class main extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        Intent intentSalki = new Intent(this, Salki.class);
        startActivity(intentSalki);
    }

    public void goToOgloszenia(View view) {
        Intent intentOgloszenia = new Intent(this, Ogloszenia.class);
        startActivity(intentOgloszenia);
    }

    public void goToPralnia(View view) {
        Intent intentPralnia = new Intent(this, Pralnia.class);
        startActivity(intentPralnia);
    }

    public void goToZakupy(View view) {
        Intent intentZakupy = new Intent(this, Zakupy.class);
        startActivity(intentZakupy);
    }

    public void goToImprezy(View view) {
        Intent intentImprezy = new Intent(this, Imprezy.class);
        startActivity(intentImprezy);
    }
}
