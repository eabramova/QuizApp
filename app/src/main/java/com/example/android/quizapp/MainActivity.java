package com.example.android.quizapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    int eyes = R.id.radio_eyes;
    int eggs = R.id.radio_eggs;
    int grp_1 = R.id.radio_group_1;
    int grp_2 = R.id.radio_group_2;
    int cbEgypt = R.id.checkbox_egypt;
    int cbMexico = R.id.checkbox_mexico;
    int cbJapan = R.id.checkbox_japan;
    int cbRussia = R.id.checkbox_russia;
    int edit_qs4 = R.id.edittext_qs4;

    int scoreBttn = R.id.score_button;

    private RadioGroup radioGrp1;
    private RadioGroup radioGrp2;

    private RadioButton radioBttnSelected1;
    private RadioButton radioBttnSelected2;

    private CheckBox checkBoxEgypt;
    private CheckBox checkBoxMexico;
    private CheckBox checkBoxRussia;
    private CheckBox checkBoxJapan;

    private EditText editText;

    private Button bttnScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();
    }

    /**
     * Adds the Button Listener when the button is clicked it will count all the results based on the right answers in RadioGroups/CheckBoxes/EditText views
     */
    private void addListenerOnButton() {
        radioGrp1 = findViewById(grp_1);
        radioGrp2 = findViewById(grp_2);
        bttnScore = findViewById(scoreBttn);

        bttnScore.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int totalScore = 0;
                // get selected radio buttons from radioGroups
                int selectedIdRG1 = radioGrp1.getCheckedRadioButtonId();
                int selectedIdRG2 = radioGrp2.getCheckedRadioButtonId();
                // find the radiobutton by returned id gr 1
                radioBttnSelected1 = findViewById(selectedIdRG1);
                // find the radiobutton by returned id gr 2
                radioBttnSelected2 = findViewById(selectedIdRG2);
                checkBoxEgypt = findViewById(cbEgypt);
                checkBoxMexico = findViewById(cbMexico);
                checkBoxJapan = findViewById(cbJapan);
                checkBoxRussia = findViewById(cbRussia);
                editText = findViewById(edit_qs4);

                try {
                    if (radioBttnSelected1.getId() == eyes) {
                        totalScore += 1;
                    }
                } catch (NullPointerException e) {
                    displayMessage("Warning!\n\nYou haven't selected any answer in the 1st question!");
                }

                try {
                    if (radioBttnSelected2.getId() == eggs) {
                        totalScore += 1;
                    }
                } catch (NullPointerException e) {
                    displayMessage("Warning!\n\nYou haven't selected any answer in the 2nd question!");
                }

                //find correct checkboxes checked ans increment the score to 1
                if ((checkBoxEgypt.isChecked() && checkBoxMexico.isChecked()) && (!checkBoxJapan.isChecked() && !checkBoxRussia.isChecked())) {
                    totalScore += 1;
                } else if (!checkBoxRussia.isChecked() && !checkBoxJapan.isChecked() && !checkBoxMexico.isChecked() && !checkBoxEgypt.isChecked()) {
                    displayMessage("Warning!\n\nYou haven't selected any answer in the 3rd question!");
                }

                //get the text from the edit field
                String qs_4_answer = String.valueOf(editText.getText());
                System.out.println(qs_4_answer);
                if (qs_4_answer.trim().equalsIgnoreCase("mouse")) {
                    totalScore += 1;
                }
                if (qs_4_answer.trim().equals("")) {
                    displayMessage("Warning!\n\nYou haven't entered any answer in the 4th question!");
                }

                displayMessage(createScoreSummary(totalScore));
            }
        });

    }

    /**
     * Displays given message on the screen in the Toast popup
     *
     * @param message
     */
    public void displayMessage(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        View toastView = toast.getView();
        toastView.setBackgroundColor(Color.rgb(180, 0, 78));
        TextView toastMessage = toastView.findViewById(android.R.id.message);
        toastMessage.setTextColor(Color.WHITE);
        toastMessage.setTextSize(24);
        toastMessage.setAllCaps(false);
        toastMessage.setGravity(1);
        toast.show();
    }

    /**
     * Create the string for the results summary
     *
     * @param score
     * @return string
     */
    public String createScoreSummary(int score) {
        String message;
        if (score < 4) {
            message = "You may do better! Your total score is " + score;
        } else {
            message = "Congrats! You won! Your total score is " + score;
        }
        return message;
    }
}