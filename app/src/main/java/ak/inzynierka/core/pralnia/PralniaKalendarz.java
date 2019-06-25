package ak.inzynierka.core.pralnia;

import ak.inzynierka.R;
import ak.inzynierka.model.LaundryRoom;
import ak.inzynierka.model.Room;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PralniaKalendarz extends Activity {

    CalendarView calendarView;
    Button goToPralniaGodzina;
    private String data;
    LaundryRoom room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pralnia_kalendarz);

        Serializable extra = getIntent().getSerializableExtra("LaundryRoom");
        if (extra != null) {
            room = (LaundryRoom)extra;
        }
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        goToPralniaGodzina = (Button) findViewById(R.id.dalejPralniaKalendarzButton);
        goToPralniaGodzina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
                String selectedDate = sdf.format(new Date(calendarView.getDate()));
                Intent pralniaGodzinaIntent = new Intent(v.getContext(), PralniaGodzina.class);
                pralniaGodzinaIntent.putExtra("Data",selectedDate);
                pralniaGodzinaIntent.putExtra("LaundryRoom",room);
                startActivity(pralniaGodzinaIntent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        Serializable extra = data.getSerializableExtra("Pokoj");
        if (extra != null) {
            if (resultCode == RESULT_OK) {
                /*Room room = (Room) extra;
                listaSalek.set(idRezerwowanejSalki,room);
                idRezerwowanejSalki = -1;
                toast = new Toast(getApplicationContext());
                String text = "Zarezerwowane przez: "+room.getOccupiedByUser().getFirstName()+" "+room.getOccupiedByUser().getLastName();
                toast.makeText(this,text, Toast.LENGTH_SHORT).show();*/
            }
        }
    }

}
/*Toast toast=Toast.makeText(getApplicationContext(),"Hello Javatpoint",Toast.LENGTH_SHORT);
toast.setMargin(50,50);
toast.show();*/