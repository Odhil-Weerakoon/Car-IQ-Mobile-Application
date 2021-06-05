package com.example.mycarapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Hint extends AppCompatActivity {

    ImageView imageView;
    Button button;
    String image;
    String userInput;
    EditText textBox ;
    TextView dashLines;
    TextView result;
    TextView correctAnswer;
    TextView hintTimer;
    Random random=new Random();
    char[] dashes;
    char[] guessWord;
    String carModel;
    String text;
    int chances=0;
    boolean switchStatus;
    CountDownTimer timer;



    String[] carImages ={"audi_a1","audi_a3","audi_a7","audi_a8","audi_q8","audi_r8","benz_a_class","benz_amg_gt_roadster","benz_e300_amg","benz_e_class",
            "benz_sl_300gullwing","benz_suv","bmw_2","bmw_3","bmw_5","bmw_6","bmw_530d","bmw_x","ferrari_488_pista","ferrari_812_superfastyellow","ferrari_f8","ferrari_monza","ferrari_roma"
            ,"ferrari_sf90_spider","porsche_550","porsche_718_cayman_gt4","porsche_904","porsche_911","porsche_cayman_gt4","porsche_panamera"};
    //String Array Containing the names of the Images of the Car Models in the drawable folder.


    List<String> audi= Arrays.asList("audi_a1","audi_a3","audi_a7","audi_a8","audi_q8","audi_r8");
    List<String> mercedes_Benz= Arrays.asList("benz_a_class","benz_amg_gt_roadster","benz_e300_amg","benz_e_class", "benz_sl_300gullwing","benz_suv");
    List<String> bmw= Arrays.asList("bmw_2","bmw_3","bmw_5","bmw_6","bmw_530d","bmw_x");
    List<String> ferrari= Arrays.asList("ferrari_488_pista","ferrari_812_superfastyellow","ferrari_f8","ferrari_monza","ferrari_roma","ferrari_sf90_spider");
    List<String> porsche= Arrays.asList("porsche_550","porsche_718_cayman_gt4","porsche_904","porsche_911","porsche_cayman_gt4","porsche_panamera");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);

        dashLines=findViewById(R.id.GuessCar);
        textBox=findViewById(R.id.fill_in_theBlanks);
        imageView= findViewById(R.id.randomImage);
        button= findViewById(R.id.submit);
        result=findViewById(R.id.guess_result);
        correctAnswer=findViewById(R.id.show_answer);
        hintTimer=findViewById(R.id.hintTimer);

        Intent intent = getIntent();
        switchStatus = intent.getBooleanExtra(MainActivity.EXTRA_MESSAGE,false);//Passing the Status of the Switch and check whether the timer switch is on or off,
        // if the switch is On the switchStatus will be set to True ,or else it will be as default false.
        setImage();

        if(switchStatus){ //if the switchStatus is True then the Countdown timer starts by calling the setHintTimer Method.
            setHintTimer();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button.getText().equals("Submit")&&(chances<=2)){
                   if(TextUtils.isEmpty(textBox.getText().toString())){
                       alertDialog();
                   }
                    checkGuess();
                }
                else if(button.getText().equals("Next")){
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                    button.setText("Submit");
                    result.setText("");
                    correctAnswer.setText("");
                }
            }
        });



    }

    public void setImage(){
        int randomNo=random.nextInt(carImages.length); //choosing a random index number from the Array of CarImages
        image = carImages[randomNo]; //Assigning the Image name to the String Variable named Image
        int resourceId=getResources().getIdentifier(image,"drawable","com.example.mycarapp");
        imageView.setImageResource(resourceId);
        if(bmw.contains(image)){
            carModel="BMW";
            dashes=new char[3]; //Initializing the size of the dashes character array
            Arrays.fill(dashes,'-'); //filling the dashes array with - according to the size
            dashLines.setText(new String(dashes));
        }
        else if(mercedes_Benz.contains(image)){
            carModel="MERCEDES";
            dashes=new char[8];
            Arrays.fill(dashes,'-');
            dashLines.setText(new String(dashes));
        }else if(ferrari.contains(image)) {
            carModel="FERRARI";
            dashes=new char[7];
            Arrays.fill(dashes,'-');
            dashLines.setText(new String(dashes));
        }
        else if(audi.contains(image)) {
            carModel="AUDI";
            dashes=new char[4];
            Arrays.fill(dashes,'-');
            dashLines.setText(new String(dashes));
        }else if(porsche.contains(image)){
            carModel="PORSCHE";
            dashes=new char[7];
            Arrays.fill(dashes,'-');
            dashLines.setText(new String(dashes));
        }
    }

    public void checkGuess(){
        userInput=textBox.getText().toString(); //Getting the user input  string value from the edit text
        text=userInput.toUpperCase(); //converting the user input string value to uppercase
        guessWord=text.toCharArray(); //converting the string value into an character array

        for (char character : guessWord){                      //Taking each character one by one and from the guessWord character array
            int index = carModel.indexOf(character);//Assigning the index number of the letter in the carModel String
            System.out.println(index);
            while (index>= 0){
                dashes[index] = carModel.charAt(index);     //Adding the character to the respective position of the dashes character array according to the carModel word
                index = carModel.indexOf(character,index+1);//searching the same character in the other indexes of the String  Car model and Assigning the next index of the same character to the variable index
                dashLines.setText(new String(dashes));      //setting the Text view with the new amount of dashes and correct character entered by the user in the correct position
            }

        }

        if(!carModel.contains(text)){           //checks whether the user input string character is present in the car model name,and if not number of chances count increase by one each timer user make incorrect gues
            if(switchStatus){                   //If the timer is on then the timer is stopped and restarted again.
                timer.cancel();
                timer.start();
            }
            chances++;
        }
        if(chances>2){                 //IF the number of chances count exceeds 2 then the timer is made to stop and the correct car model answer is shown
            if(switchStatus){
                timer.cancel();
            }
            result.setText("WRONG!");
            correctAnswer.setText(carModel+" is the Correct Answer");
            button.setText("Next");
            result.setTextColor(Color.RED);
            correctAnswer.setTextColor(Color.rgb(204,204,0));
        }
        if (!(new String(dashes).contains("-"))) {          //If the dashes character array does not contain "-" then the button text is made to Next and CORRECT! is being Displayed
            button.setText("Next");
            result.setText("CORRECT!");
            result.setTextColor(Color.GREEN);
        }
        textBox.getText().clear();      //After all the checking the edit text is being cleared each time the user press the submit button
    }

    public void setHintTimer(){  //Setting the Timer for 20s
        timer=new CountDownTimer(20000, 1000) {
            public void onTick(long millisUntilFinished) {
                hintTimer.setText("Seconds Remaining: " + millisUntilFinished / 1000);
            }
            public void onFinish() {
                if(TextUtils.isEmpty(textBox.getText().toString())){ //Displays the alert message if the user hasnt enter any input before the timer runs out
                    alertDialog();
                }
                checkGuess();   //When the timer is finished ,Calling the checkGuess Method to see whether the entered word or letters are  correct or wrong
            }
        }.start();
    }

    private void alertDialog() {        //alertDialog method is used to show a Alert if the user press the  submit button without entering a character
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setMessage("Please Enter a Character before Submitting!");
        dialog.setTitle("Alert!");
        dialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                    }
                });
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }



}