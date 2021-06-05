package com.example.mycarapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class IdentifyTheCarMake extends AppCompatActivity {

    ImageView imageView;
    Button button;
    String image;
    TextView result;
    TextView answer;
    TextView showTimer;
    Spinner spinner;
    boolean switchStatus;
    CountDownTimer timer;
    Random random=new Random();



    String[] CarImages ={"audi_a1","audi_a3","audi_a7","audi_a8","audi_q8","audi_r8","benz_a_class","benz_amg_gt_roadster","benz_e300_amg","benz_e_class",
            "benz_sl_300gullwing","benz_suv","bmw_2","bmw_3","bmw_5","bmw_6","bmw_530d","bmw_x","ferrari_488_pista","ferrari_812_superfastyellow","ferrari_f8","ferrari_monza","ferrari_roma"
            ,"ferrari_sf90_spider","porsche_550","porsche_718_cayman_gt4","porsche_904","porsche_911","porsche_cayman_gt4","porsche_panamera"};
    //String Array Containing the names of the Images of the Car Models in the drawable folder.



    List<String> audi= Arrays.asList("audi_a1","audi_a3","audi_a7","audi_a8","audi_q8","audi_r8");//ArrayList containing the Names of the Audi Cars
    List<String> mercedes_Benz= Arrays.asList("benz_a_class","benz_amg_gt_roadster","benz_e300_amg","benz_e_class", "benz_sl_300gullwing","benz_suv");//ArrayList containing the Names of the Mercedes Benz Cars
    List<String> bmw= Arrays.asList("bmw_2","bmw_3","bmw_5","bmw_6","bmw_530d","bmw_x");    //ArrayList containing the Names of the Bmw Cars
    List<String> ferrari= Arrays.asList("ferrari_488_pista","ferrari_812_superfastyellow","ferrari_f8","ferrari_monza","ferrari_roma","ferrari_sf90_spider");//ArrayList containing the Names of the Ferrari Cars
    List<String> porsche= Arrays.asList("porsche_550","porsche_718_cayman_gt4","porsche_904","porsche_911","porsche_cayman_gt4","porsche_panamera");//ArrayList containing the Names of the Porsche Cars

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_the_car_make);

        result=findViewById(R.id.result);
        answer=findViewById(R.id.correct_answer);
        imageView= findViewById(R.id.imageView);
        button= findViewById(R.id.button);
        showTimer=findViewById(R.id.show_timer);


        Intent intent = getIntent();
        switchStatus = intent.getBooleanExtra(MainActivity.EXTRA_MESSAGE,false);  //Passing the Status of the Switch and check whether the timer switch is on or off,
        // if the switch is On the switchStatus will be set to True ,or else it will be as default false.



        spinner();//Calling the Spinner method
        setImage();//Calling the setImage method to display a random car model image
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSpinnerAnswer(); //Calling the checkSpinnerAnswer Method to see whether the spinner selection is correct or wrong

            }
        });

        if(switchStatus){ //if the switchStatus is True then the Countdown timer starts
            timer=new CountDownTimer(20000, 1000) {   //Setting the Timer for 20s

                public void onTick(long millisUntilFinished) {
                    showTimer.setText("Seconds Remaining: " + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    checkSpinnerAnswer(); //When the timer is finished ,Calling the checkSpinnerAnswer Method to see whether the spinner selection is correct or wrong
                }
            }.start();
        }
    }
    public void setImage(){

        int randomNo=random.nextInt(CarImages.length); //choosing a random index number from the Array of CarImages
        image = CarImages[randomNo]; //Assigning the Image name to the String Variable named Image
        int resourceId=getResources().getIdentifier(image,"drawable","com.example.mycarapp");
        imageView.setImageResource(resourceId);
    }

    public void spinner(){
        spinner = findViewById(R.id.selectChoice);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(    // Create ArrayAdapter using the string array and default spinner layout.
                this, R.array.spinner_choices,
                android.R.layout.simple_spinner_item);adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void showAnswer(){  //This method is use to display the correct car model everytime the user selects the wrong selection for the car model.
        if(bmw.contains(image)){
            answer.setText("Bmw is the Correct Answer");
        }
        else if(mercedes_Benz.contains(image)){
            answer.setText("Mercedes Benz is the Correct Answer");
        }else if(ferrari.contains(image)) {
            answer.setText("Ferrari is the Correct Answer");
        }
        else if(audi.contains(image)) {
            answer.setText("Audi is the Correct Answer");
        }else{
            answer.setText("Porsche is the Correct Answer");
        }
        result.setText("WRONG!");
        result.setTextColor(Color.RED);
        answer.setTextColor(Color.rgb(204,204,0));
    }

    public void checkSpinnerAnswer(){
        if(button.getText().equals("Identify")){
            if(switchStatus){       //when the switch is On ,Once the Identify button is pressed the Timer is being stopped
                timer.cancel();
            }
            button.setText("Next");
            if(spinner.getSelectedItem().toString().equals("Bmw")){     //checks whether the spinner selection is Bmw
                if(bmw.contains(image)){        //Checks whether the Array list contains the Image of Bmw car Model
                    result.setText("CORRECT!");
                    result.setTextColor(Color.GREEN);
                }
                else{                           //If the image is not a bmw car model then showAnswer method is called and the Correct car Model name is shown.
                    showAnswer();
                }
            }
            else if(spinner.getSelectedItem().toString().equals("Mercedes Benz")){
                if(mercedes_Benz.contains(image)){
                    result.setText("CORRECT!");
                    result.setTextColor(Color.GREEN);
                }
                else {
                    showAnswer();

                }
            }
            else if(spinner.getSelectedItem().toString().equals("Ferrari")){
                if(ferrari.contains(image)){
                    result.setText("CORRECT!");
                    result.setTextColor(Color.GREEN);
                }
                else {
                    showAnswer();
                }
            }
            else if(spinner.getSelectedItem().toString().equals("Audi")){
                if(audi.contains(image)){
                    result.setText("CORRECT!");
                    result.setTextColor(Color.GREEN);
                }
                else {
                    showAnswer();
                }
            }
            else if(spinner.getSelectedItem().toString().equals("Porsche")){
                if(porsche.contains(image)){
                    result.setText("CORRECT!");
                    result.setTextColor(Color.GREEN);
                }
                else {
                    showAnswer();
                }
            }
        }
        else if(button.getText().equals("Next")){   //When the button text is Next,then the current intent is finished and a new intent is being started
            Intent intent = getIntent();
            finish();
            startActivity(intent);
            button.setText("Identify!");
            result.setText("");
            answer.setText("");
        }

    }


}