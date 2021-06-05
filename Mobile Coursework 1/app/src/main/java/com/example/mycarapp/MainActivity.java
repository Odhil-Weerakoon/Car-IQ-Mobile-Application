package com.example.mycarapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;


public class MainActivity extends AppCompatActivity {
    public Switch timerSwitch;
    public static Boolean switchStatus=false;           //Initialize the switchStatus to False
    public static final String EXTRA_MESSAGE="com.example.android.mycarapp.extra.MESSAGE"; //public constant that define the key for the Intent extra

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button identifyTheCarMakeBtn = findViewById(R.id.CarMake);
        Button hintBtn = findViewById(R.id.hints);
        Button identifyTheCarImageBtn = findViewById(R.id.carImage);
        Button advanceLevelBtn = findViewById(R.id.advanceLevel);
        timerSwitch=findViewById(R.id.timerSwitch);



        identifyTheCarMakeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchStatus=timerSwitch.isChecked();       //checks whether the Timer Switch is On or Off
                Intent firstIntent = new Intent(MainActivity.this,IdentifyTheCarMake.class);
                firstIntent.putExtra(EXTRA_MESSAGE, switchStatus);//Adding the boolean value to the Intent as an extra with the EXTRA_MESSAGE constant as the key and the boolean as the value:
                startActivity(firstIntent);//Call the startActivity() method with the new Intent as the argument.
            }
        });

        hintBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchStatus=timerSwitch.isChecked();
                Intent secondIntent = new Intent(MainActivity.this,Hint.class);
                secondIntent.putExtra(EXTRA_MESSAGE, switchStatus);
                startActivity(secondIntent);
            }
        });

        identifyTheCarImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchStatus=timerSwitch.isChecked();
                Intent thirdIntent = new Intent(MainActivity.this,IdentifyTheCarImage.class);
                thirdIntent.putExtra(EXTRA_MESSAGE, switchStatus);
                startActivity(thirdIntent);
            }
        });

        advanceLevelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchStatus=timerSwitch.isChecked();
                Intent fourthIntent = new Intent(MainActivity.this,AdvanceLevel.class);
                fourthIntent.putExtra(EXTRA_MESSAGE, switchStatus);
                startActivity(fourthIntent);
            }
        });

    }

}