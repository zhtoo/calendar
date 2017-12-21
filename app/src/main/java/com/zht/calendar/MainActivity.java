package com.zht.calendar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.zht.calendar.calendar.CalendarPosition;
import com.zht.calendar.calendar.CalendarUtil;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.main_calendar);

        long startTime = System.currentTimeMillis();
        String currentDate = CalendarUtil.getCurrentDate();
        String[] split = currentDate.split("-");
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        List<String> position = CalendarPosition.getPosition(year, month);
        String dateText = "";
        for (int i = 0; i < position.size(); i++) {
            dateText += position.get(i) + "\t ";
            if ((i + 1) % 7 == 0) {
                dateText += "\n";
            }
        }
        long endTime = System.currentTimeMillis();
        Log.e("方法执行时间"," "+ (endTime - startTime));
        text.setText(dateText);

    }
}
