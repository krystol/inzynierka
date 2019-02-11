package ak.inzynierka.core;

import ak.inzynierka.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

public class PralniaKalendarz extends Activity {

    CalendarView calendarView;
    Button goToPralniaGodzina;
    private String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pralnia_kalendarz);

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        goToPralniaGodzina = (Button) findViewById(R.id.dalejPralniaKalendarzButton);
        calendarView.getMinDate();
        goToPralniaGodzina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pralniaGodzinaIntent = new Intent(v.getContext(), PralniaGodzina.class);
                pralniaGodzinaIntent.putExtra("Data",data);
                startActivity(pralniaGodzinaIntent);
            }
        });
    }

}