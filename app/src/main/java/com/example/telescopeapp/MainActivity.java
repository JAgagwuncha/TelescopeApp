package com.example.telescopeapp;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;



public class MainActivity extends AppCompatActivity implements SensorEventListener, View.OnClickListener{

    //Declare Attributes here
    SensorManager sm;
    Sensor sensor;
    TextView textView1;
    Button button;
    List list;
    Boolean update;
    View v;

    SensorEventListener sel = new SensorEventListener(){
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            textView1.setText("x: "+values[0]+"\ny: "+values[1]+"\nz: "+values[2]);
        }
    };


    public MainActivity() {
//        button.setOnClickListener(new MainActivity());
    }


    //Determine what will be created when the
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        update = Boolean.FALSE;

        /* Get a SensorManager instance */
        sm = (SensorManager)getSystemService(SENSOR_SERVICE);

        textView1 = (TextView)findViewById(R.id.textView1);
        Button button = (Button) findViewById(R.id.update);
        button.setOnClickListener(this::onClick);


        list = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
        getPosition(list);

    }

    protected void onStop() {
        if(list.size()>0){
            sm.unregisterListener(sel);
        }
        super.onStop();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public List getPosition(List list){
        if(list.size()>0){
            sm.registerListener(sel, (Sensor) list.get(0), SensorManager.SENSOR_DELAY_NORMAL);
        }else{
            Toast.makeText(getBaseContext(), "Error: No Accelerometer.", Toast.LENGTH_LONG).show();
        }
        return list;
    }

//    button.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//
//            // Do something in response to button click
//        }
//    }

    @Override
    public void onClick(View v) {
//        System.out.println("Was pressed");
        list = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
        getPosition(list);
    }
}
