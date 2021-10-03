package com.example.ej05_juan_buades;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class HourDayActivity extends AppCompatActivity {
    TextView tvHour, tvDate;
    int t1hour, t1minute;
    DatePickerDialog.OnDateSetListener setListener;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvHour = findViewById(R.id.tvHour);
        tvDate = findViewById(R.id.tvDate);

        sharedPreferences = getSharedPreferences("EVENT",MODE_PRIVATE);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        HourDayActivity.this, setListener, year, month, day);
                datePickerDialog.show();
            }
        });
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String dateSelected = day + "/" + month + "/" + year;
                tvDate.setText(dateSelected);
            }
        };
        tvHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        HourDayActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                t1hour = hourOfDay;
                                t1minute = minute;
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(0, 0, 0, t1hour, t1minute);
                                tvHour.setText(DateFormat.format("hh:mm", calendar));
                            }
                        }, 12, 0, true
                );
                timePickerDialog.updateTime(t1hour, t1minute);
                timePickerDialog.show();
            }
        });

    }
        public void sendInfoToMapsActivity (View view) {
            String selectedDate = tvDate.getText().toString();
            String selectedHour = String.valueOf(t1hour);
            String selectedMinute = String.valueOf(t1minute);


            Intent intent = new Intent(this, TitlePlaceActivity.class);
            intent.putExtra("hour", selectedHour);
            intent.putExtra("minute", selectedMinute);
            intent.putExtra("date", selectedDate);
            Log.d("dato de date a maps", "enviado "+selectedHour+selectedMinute+selectedDate);
            startActivityForResult(intent, 1);
            finish();
            }

}