package com.example.mycarapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AdvanceLevel extends AppCompatActivity {

    ImageView firstCar;
    ImageView secondCar;
    ImageView thirdCar;
    String firstRandImage;
    String secondRandImage;
    String thirdRandImage;
    EditText firstImgText;
    EditText secondImgText;
    EditText thirdImgText;
    TextView carModel;
    TextView score;
    TextView result;
    TextView AdvLevelTimer;
    TextView firstAns;
    TextView secAns;
    TextView thirdAns;
    Button submit;
    Random random=new Random();
    int firstRandNum;
    int secondRandNum;
    int thirdRandNum;
    int totalScore=0;
    int btnCount=0;
    Boolean firstAnswer=false;
    Boolean secondAnswer=false;
    Boolean thirdAnswer=false;
    Boolean firstCheck=false;
    Boolean secondCheck=false;
    Boolean thirdCheck=false;
    Boolean switchStatus;
    CountDownTimer timer;



    String[]firstImage={"audi_a1","audi_a3","audi_a7","audi_a8","audi_q8","audi_r8","benz_a_class","benz_amg_gt_roadster","benz_e300_amg","benz_e_class","benz_sl_300gullwing","benz_suv"};
    String[]secondImage={"bmw_2","bmw_3","bmw_5","bmw_6","bmw_530d","bmw_x","ferrari_488_pista","ferrari_812_superfastyellow","ferrari_f8","ferrari_monza","ferrari_roma","ferrari_sf90_spider"};
    String[]thirdImage={"porsche_550","porsche_718_cayman_gt4","porsche_904","porsche_911","porsche_cayman_gt4","porsche_panamera"};
    //String Array Containing the names of the Images of the 5 Car Models in separate 3 Arrays.


    List<String> firstSet= Arrays.asList("audi_a1","audi_a3","audi_a7","audi_a8","audi_q8","audi_r8");
    List<String> secondSet=Arrays.asList("bmw_2","bmw_3","bmw_5","bmw_6","bmw_530d","bmw_x");
    List<String> thirdSet=Arrays.asList("porsche_550","porsche_718_cayman_gt4","porsche_904","porsche_911","porsche_cayman_gt4","porsche_panamera");
    List<String> fourthSet= Arrays.asList("benz_a_class","benz_amg_gt_roadster","benz_e300_amg","benz_e_class","benz_sl_300gullwing","benz_suv");
    List<String> fifthSet= Arrays.asList("ferrari_488_pista","ferrari_812_superfastyellow","ferrari_f8","ferrari_monza","ferrari_roma","ferrari_sf90_spider");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance_level);

        result=findViewById(R.id.finalResult);
        submit=findViewById(R.id.submitBtn);
        AdvLevelTimer=findViewById(R.id.AdlTImer);

        randomImgModel(); //randomImgModel method is called to display three random car model images chosen from the three Arrays,firstImage,secondImage and thirdImage


        Intent intent = getIntent();
        switchStatus= intent.getBooleanExtra(MainActivity.EXTRA_MESSAGE,false);

        if(switchStatus){//if the switchStatus is True then the Countdown timer starts by calling the timer Method.
            timer();
        }
    }

    public void checkAnswer(){
        firstImgText=findViewById(R.id.image1);
        secondImgText=findViewById(R.id.image2);
        thirdImgText=findViewById(R.id.image3);
        score=findViewById(R.id.score);
        carModel = findViewById(R.id.car_model);
        firstAns = findViewById(R.id.firstAns);
        secAns = findViewById(R.id.secAns);
        thirdAns = findViewById(R.id.thirdAns);


        if(firstSet.contains(firstRandImage)){  //checks whether first random image is from the firstSet list
            firstAns.setText("AUDI");           //sets the firstAns TextView to "AUDI"
            if("AUDI".equalsIgnoreCase(firstImgText.getText().toString())){
                firstImgText.setBackgroundColor(Color.GREEN);   //If the entered user input is correct then the Edit text box background is made green
                firstImgText.setFocusable(false);               //The edit text box is set so it cannot be edited again
                firstAnswer=true;                               //firstAnswer variable is set to true
                if(!firstCheck){                                //total score is being updated and firstCheck variable is set to True
                    totalScore++;
                    firstCheck=true;
                }
            }
            else{
                firstImgText.setBackgroundColor(Color.RED);     //if the user input answer is wrong then the edit text background is made red
            }
        }
        if(fourthSet.contains(firstRandImage)){
            firstAns.setText("MERCEDES");
            if("MERCEDES".equalsIgnoreCase(firstImgText.getText().toString())){
                firstImgText.setBackgroundColor(Color.GREEN);
                firstImgText.setFocusable(false);
                firstAnswer=true;
                if(!firstCheck){
                    totalScore++;
                    firstCheck=true;
                }
            }
            else{
                firstImgText.setBackgroundColor(Color.RED);
            }
        }
        if(secondSet.contains(secondRandImage)){
            secAns.setText("BMW");
            if("BMW".equalsIgnoreCase(secondImgText.getText().toString())){
                secondImgText.setBackgroundColor(Color.GREEN);
                secondImgText.setFocusable(false);
                secondAnswer=true;
                if(!secondCheck){
                    totalScore++;
                    secondCheck=true;
                }
            }
            else{
               secondImgText.setBackgroundColor(Color.RED);
            }
        }
        if(fifthSet.contains(secondRandImage)){
            secAns.setText("FERRARI");
            if("FERRARI".equalsIgnoreCase(secondImgText.getText().toString())){
                secondImgText.setBackgroundColor(Color.GREEN);
                secondImgText.setFocusable(false);
                secondAnswer=true;
                if(!secondCheck){
                    totalScore++;
                    secondCheck=true;
                }
            }
            else{
                secondImgText.setBackgroundColor(Color.RED);
            }
        }
        if(thirdSet.contains(thirdRandImage)){
            thirdAns.setText("PORSCHE");
            if("PORSCHE".equalsIgnoreCase(thirdImgText.getText().toString())){
                thirdImgText.setBackgroundColor(Color.GREEN);
                thirdImgText.setFocusable(false);
                thirdAnswer=true;
                if(!thirdCheck){
                    totalScore++;
                    thirdCheck=true;
                }
            }
            else{
                thirdImgText.setBackgroundColor(Color.RED);
            }
        }
        btnCount++;
        score.setText("Your Score is :"+" "+totalScore);
    }




    public void randomImgModel(){           //method to generate three random car model images
        firstCar= findViewById(R.id.firstImage);
        secondCar= findViewById(R.id.secondImage);
        thirdCar= findViewById(R.id.thirdImage);

        firstRandNum=random.nextInt(firstImage.length);
        secondRandNum=random.nextInt(secondImage.length);
        thirdRandNum=random.nextInt(thirdImage.length);

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


    public void submitButtonClick(View view) {
        checkAnswer();
        if(submit.getText().equals("Next")){
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }else if(submit.getText().equals("Submit")){
            if(switchStatus){       //Every time when the user press the submit button while the timer is running the current timer is stopped and again the timer starts from 20s
                timer.cancel();
                timer.start();
                if(firstAnswer && secondAnswer && thirdAnswer) {   //checks whether all three answers entered by the user are correct or not
                    timer.cancel();
                    result.setText("CORRECT!");
                    result.setTextColor(Color.GREEN);
                    submit.setText("Next");
                }
                if(btnCount==3){
                    timer.cancel();
                }
            }
        }

        if(firstAnswer && secondAnswer && thirdAnswer){
            result.setText("CORRECT!");
            result.setTextColor(Color.GREEN);
            submit.setText("Next");
        }

        if(btnCount==3){            //checks whether the number of times the user press the submit button is equal to 3 or not and if he has pressed 3 times then validates all the answers entered by the user
            if(firstAnswer && secondAnswer && thirdAnswer){
                result.setText("CORRECT!");
                result.setTextColor(Color.GREEN);
                submit.setText("Next");
            }

            if(!firstAnswer){
                firstAns.setTextColor(Color.rgb(204,204,0));
                firstAns.setVisibility(View.VISIBLE);
                result.setText("WRONG!");
                result.setTextColor(Color.RED);
            }
            if(!secondAnswer){
                secAns.setTextColor(Color.rgb(204,204,0));
                secAns.setVisibility(View.VISIBLE);
                result.setText("WRONG!");
                result.setTextColor(Color.RED);
            }
            if(!thirdAnswer){
                thirdAns.setTextColor(Color.rgb(204,204,0));
                thirdAns.setVisibility(View.VISIBLE);
                result.setText("WRONG!");
                result.setTextColor(Color.RED);
            }
            submit.setText("Next");
        }
    }


    public void timer(){
        timer=new CountDownTimer(20000, 1000) {
            public void onTick(long millisUntilFinished) {
                AdvLevelTimer.setText("Seconds Remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                checkAnswer();
                start();        //starts the timer each time 20s has gone and runs for 3 times
                if(firstAnswer && secondAnswer && thirdAnswer){
                    timer.cancel();
                    result.setText("CORRECT!");
                    result.setTextColor(Color.GREEN);
                    submit.setText("Next");
                }

                if(btnCount==3){        //if the timer is on and  the number of submit button count is equal to 3 ,then the answers are validated and button count is set to zero and the timer is being stopped.
                    if(firstAnswer && secondAnswer && thirdAnswer){
                        result.setText("CORRECT");
                        result.setTextColor(Color.GREEN);
                        submit.setText("Next");
                    }

                    if(!firstAnswer){
                        firstAns.setTextColor(Color.rgb(204,204,0));
                        firstAns.setVisibility(View.VISIBLE);
                        result.setText("WRONG!");
                        result.setTextColor(Color.RED);
                    }
                    if(!secondAnswer){
                        secAns.setTextColor(Color.rgb(204,204,0));
                        secAns.setVisibility(View.VISIBLE);
                        result.setText("WRONG!");
                        result.setTextColor(Color.RED);
                    }
                    if(!thirdAnswer){
                        thirdAns.setTextColor(Color.rgb(204,204,0));
                        thirdAns.setVisibility(View.VISIBLE);
                        result.setText("WRONG!");
                        result.setTextColor(Color.RED);
                    }
                    btnCount=0;
                    timer.cancel();
                    submit.setText("Next");
                }
            }
        }.start();
    }
}