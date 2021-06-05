package com.example.mycarapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class IdentifyTheCarImage extends AppCompatActivity {

    ImageView firstCar;
    ImageView secondCar;
    ImageView thirdCar;
    String firstRandImage;
    String secondRandImage;
    String thirdRandImage;
    TextView carModel;
    TextView result;
    TextView timer;
    Button next;
    Random random=new Random();
    int firstRandNum;
    int secondRandNum;
    int thirdRandNum;
    boolean switchStatus;
    CountDownTimer countDownTimer;





    ArrayList<String> carModels=new ArrayList<>(); //ArrayList Containing the Names of the Randomly Picked three car Models each time.


    String[]firstImage={"audi_a1","audi_a3","audi_a7","audi_a8","audi_q8","audi_r8","benz_a_class","benz_amg_gt_roadster","benz_e300_amg","benz_e_class","benz_sl_300gullwing","benz_suv"};
    String[]secondImage={"bmw_2","bmw_3","bmw_5","bmw_6","bmw_530d","bmw_x","ferrari_488_pista","ferrari_812_superfastyellow","ferrari_f8","ferrari_monza","ferrari_roma","ferrari_sf90_spider"};
    String[]thirdImage={"porsche_550","porsche_718_cayman_gt4","porsche_904","porsche_911","porsche_cayman_gt4","porsche_panamera"};
    //String Array Containing the names of the Images of the 5 Car Models in separate 3 Arrays.

    List<String> firstSet= Arrays.asList("audi_a1","audi_a3","audi_a7","audi_a8","audi_q8","audi_r8");
    List<String> secondSet=Arrays.asList("bmw_2","bmw_3","bmw_5","bmw_6","bmw_530d","bmw_x");
    List<String> thirdSet=Arrays.asList("porsche_550","porsche_718_cayman_gt4","porsche_904","porsche_911","porsche_cayman_gt4","porsche_panamera");
    List<String> fourthSet= Arrays.asList("benz_a_class","benz_amg_gt_roadster","benz_e300_amg","benz_e_class","benz_sl_300gullwing","benz_suv");
    List<String> fifthSet= Arrays.asList("ferrari_488_pista","ferrari_812_superfastyellow","ferrari_f8","ferrari_monza","ferrari_roma","ferrari_sf90_spider");
    // ArrayList Containing the names of the Images of the 5 Car Models in  5 separate ArrayList .





    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_the_car_image);
        result=findViewById(R.id.result);
        timer=findViewById(R.id.timer);


        Intent intent = getIntent();
        switchStatus = intent.getBooleanExtra(MainActivity.EXTRA_MESSAGE,false);//Passing the Status of the Switch and check whether the timer switch is on or off,
        // If the switch is On the switchStatus will be set to True ,or else it will be as default false.

        if(switchStatus){
            countDownTimer=new CountDownTimer(20000, 1000) {
                public void onTick(long millisUntilFinished) {
                    timer.setText("Seconds Remaining: " + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    result.setText("WRONG!");
                    result.setTextColor(Color.RED);
                    firstCar.setClickable(false);
                    secondCar.setClickable(false);
                    thirdCar.setClickable(false);
                }
            }.start();
        }


        randomImgModel(); //calling the randomImgModel method  to pick three random car model images out of the 3 Array list.
        randomModelTxt(); //Calling the randomModelTxt method to assign names of  the three  randomly picked car model images.


    }




    public void randomModelTxt() {  //randomModelTxt method to assign names of  the three  randomly picked car model images.
        carModel = findViewById(R.id.car_model);
        if(firstSet.contains(firstRandImage)){
            carModels.add("AUDI");
        }
        if(fourthSet.contains(firstRandImage)){
            carModels.add("MERCEDES");
        }
        if(secondSet.contains(secondRandImage)){
            carModels.add("BMW");
        }
        if(fifthSet.contains(secondRandImage)){
            carModels.add("FERRARI");
        }
        if(thirdSet.contains(thirdRandImage)){
            carModels.add("PORSCHE");
        }
        carModel.setText(carModels.get(random.nextInt(carModels.size())));
    }

    public void randomImgModel(){   //randomImgModel method  to pick three random car model images out of the 3 Array list.
        firstCar= findViewById(R.id.carImage1);
        secondCar= findViewById(R.id.carImage2);
        thirdCar= findViewById(R.id.carImage3);

         firstRandNum=random.nextInt(firstImage.length);        //choosing a random index number from the First Array of CarImages
         secondRandNum=random.nextInt(secondImage.length);      //choosing a random index number from the Second Array of CarImages
         thirdRandNum=random.nextInt(thirdImage.length);        //choosing a random index number from the Third Array of CarImages

        firstRandImage= firstImage[firstRandNum];
        secondRandImage= secondImage[secondRandNum];
        thirdRandImage= thirdImage[thirdRandNum];

        int firstResId=getResources().getIdentifier(firstRandImage,"drawable","com.example.mycarapp");
        int secondResId=getResources().getIdentifier(secondRandImage,"drawable","com.example.mycarapp");
        int thirdResId=getResources().getIdentifier(thirdRandImage,"drawable","com.example.mycarapp");

        firstCar.setImageResource(firstResId);
        secondCar.setImageResource(secondResId);
        thirdCar.setImageResource(thirdResId);

    }

    public void firstImageOnClick(View view) {
        if(switchStatus){           //when the switch is On,Once the First Image is pressed the Timer is being stopped.
            countDownTimer.cancel();
        }
        if(carModel.getText().equals("AUDI")){
            if(firstRandNum>=0 && firstRandNum<=5){ //If the random number chosen from the First Array is between 0 to 5 ,then the user identified image is AUDI and the answer is correct
                result.setText("CORRECT!");
                result.setTextColor(Color.GREEN);
            }

        }else if(carModel.getText().equals("MERCEDES")){   //If the random number chosen from the First Array is between 6 to 11 ,then the user identified image is MERCEDES and the answer is correct
            if(firstRandNum>=6 && firstRandNum<=11){
                result.setText("CORRECT!");
                result.setTextColor(Color.GREEN);
            }
        }
        else {
            result.setText("WRONG!");
            result.setTextColor(Color.RED);
        }
        firstCar.setClickable(false);       //Making the first image un-clickable
        secondCar.setClickable(false);      //Making the second image un-clickable
        thirdCar.setClickable(false);       //Making the third image un-clickable
    }


    public void secondImageOnClick(View view) {
        if(switchStatus){            //when the switch is On,Once the Second Image is pressed the Timer is being stopped.
            countDownTimer.cancel();
        }
        if(carModel.getText().equals("BMW")){   //checks whether the Car model is Bmw
            if(secondRandNum>=0 && secondRandNum<=5){ //If the random number chosen from the second Array is between 0 to 5 ,then the user identified image is BMW and the answer is correct
                result.setText("CORRECT!");
                result.setTextColor(Color.GREEN);
            }

        }else if(carModel.getText().equals("FERRARI")){ //If the random number chosen from the second Array is between 6 to 11 ,then the user identified image is FERRARI and the answer is correct
            if(secondRandNum>=6 && secondRandNum<=11){
                result.setText("CORRECT!");
                result.setTextColor(Color.GREEN);
            }
        }
        else {

            result.setText("WRONG!");
            result.setTextColor(Color.RED);

        }
        firstCar.setClickable(false);
        secondCar.setClickable(false);
        thirdCar.setClickable(false);
    }


    public void thirdImageOnClick(View view) {
        if(switchStatus){           //when the switch is On,Once the third Image is pressed the Timer is being stopped.
            countDownTimer.cancel();
        }
        if(carModel.getText().equals("PORSCHE")){   //If the random number chosen from the third Array is between 0 to 5 ,then the user identified image is Porsche and the answer is correct
            if(thirdRandNum>=0 && thirdRandNum<=5){
                result.setText("CORRECT!");
                result.setTextColor(Color.GREEN);
            }
        }
        else {
            result.setText("WRONG!");
            result.setTextColor(Color.RED);

        }
        firstCar.setClickable(false);
        secondCar.setClickable(false);
        thirdCar.setClickable(false);
    }

    public void nextButtonOnclick(View view) {      //When the button text is Next,then the current intent is finished and a new intent is being started
        next=findViewById(R.id.NextButton);
        if(next.getText().equals("Next")){
            Intent intent = getIntent();
            finish();
            startActivity(intent);
            result.setText("");
        }

    }
}