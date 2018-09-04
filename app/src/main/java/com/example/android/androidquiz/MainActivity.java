package com.example.android.androidquiz;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean running = false;
    int currentQuestion = 1;
    int score = 0;
    long startTime = 0;
    long hours = 0;
    long minutes = 0;
    long seconds = 0;
    long milliseconds = 0;
    Animation slideIn;
    Animation slideOut;
    String name;
    TextView welcomeTextView;
    EditText nameEditBox;
    Button startQuizButton;
    TextView startTextView;
    RelativeLayout timeView;
    TextView timeTextView;
    RelativeLayout loadingSpinnerView;
    RelativeLayout question1View;
    ProgressBar progressBar;
    RadioButton question1RadioButton;
    RelativeLayout question2View;
    RadioButton question2RadioButton;
    RelativeLayout question3View;
    RadioButton question3RadioButton;
    RelativeLayout question4View;
    CheckBox question4CheckBox1;
    CheckBox question4CheckBox2;
    CheckBox question4CheckBox3;
    CheckBox question4CheckBox4;
    RelativeLayout question5View;
    EditText question5EditText;
    RelativeLayout resultView;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        slideIn = AnimationUtils.loadAnimation(this, R.anim.slide_in);
        slideOut = AnimationUtils.loadAnimation(this, R.anim.slide_out);
        welcomeTextView = findViewById(R.id.welcome_text_view);
        nameEditBox = findViewById(R.id.name_edit_box);
        startQuizButton = findViewById(R.id.start_quiz_button);
        startTextView = findViewById(R.id.start_text_view);
        timeView = findViewById(R.id.time_view);
        timeTextView = findViewById(R.id.time_text_view);
        loadingSpinnerView = findViewById(R.id.loading_spinner_view);
        question1View = findViewById(R.id.question1_view);
        progressBar = findViewById(R.id.progress_bar);
        question1RadioButton = findViewById(R.id.question1_radio_button);
        question2View = findViewById(R.id.question2_view);
        question2RadioButton = findViewById(R.id.question2_radio_button);
        question3View = findViewById(R.id.question3_view);
        question3RadioButton = findViewById(R.id.question3_radio_button);
        question4View = findViewById(R.id.question4_view);
        question4CheckBox1 = findViewById(R.id.question4_option1);
        question4CheckBox2 = findViewById(R.id.question4_option2);
        question4CheckBox3 = findViewById(R.id.question4_option3);
        question4CheckBox4 = findViewById(R.id.question4_option4);
        question5View = findViewById(R.id.question5_view);
        question5EditText = findViewById(R.id.question5_edit_text);
        resultView = findViewById(R.id.result_view);
        ratingBar = findViewById(R.id.rating_bar);
    }

    /**
     * @param view it invokes by Start Quiz Button and starts Stop Watch along with relative changes on screen
     */

    public void startQuiz(View view) {
        String startMessage = getString(R.string.lets_start) + " ";
        name = nameEditBox.getText().toString();
        if (name.isEmpty()) {
            Toast.makeText(this, getString(R.string.enter_name), Toast.LENGTH_SHORT).show();
        } else {
            hideSoftKeyboard();
            welcomeTextView.setVisibility(View.GONE);
            nameEditBox.setVisibility(View.GONE);
            startQuizButton.setVisibility(View.GONE);
            startMessage += name;
            startMessage += "!";
            startTextView.setText(startMessage);
            startTextView.setVisibility(View.VISIBLE);
            timeView.setVisibility(View.VISIBLE);
            startTimer();
            loadingSpinnerView.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            question1();
        }
    }

    /**
     * Starts Stopwatch
     */

    public void startTimer() {
        startTime = System.currentTimeMillis();
        running = true;
        time();
    }

    /**
     * Stops Stopwatch
     */

    public void stopTimer() {
        running = false;
    }

    /**
     * Calculate time elapsed and set it in a String format
     *
     * @return time in String format
     */

    public String timeCalculate() {
        String time;
        if (running) {
            milliseconds = ((System.currentTimeMillis() - startTime) / 10) % 100;
            seconds = ((System.currentTimeMillis() - startTime) / 1000) % 60;
            minutes = (((System.currentTimeMillis() - startTime) / 1000) / 60) % 60;
            hours = ((((System.currentTimeMillis() - startTime) / 1000) / 60) / 60);
        }
        time = String.format("%02d:%02d:%02d:%02d", hours, minutes, seconds, milliseconds);
        return time;
    }

    /**
     * Updates the Stopwatch View regularly by using Handler and calls timeCalculate to calculate latest time
     */

    public void time() {

        final Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {
                timeTextView.setText(timeCalculate());

                handler.postDelayed(this, 100);
            }
        });

    }

    /**
     * Updates screen for Question 1
     */

    public void question1() {
        question1View.setVisibility(View.VISIBLE);
        question1View.startAnimation(slideIn);
        progressBar.setProgress(20);
    }

    /**
     * Updates screen for Question 2
     */

    public void question2() {
        question1View.setVisibility(View.GONE);
        question1View.startAnimation(slideOut);
        question2View.setVisibility(View.VISIBLE);
        question2View.startAnimation(slideIn);
        progressBar.setProgress(40);
    }

    /**
     * Updates screen for Question 3
     */

    public void question3() {
        question2View.setVisibility(View.GONE);
        question2View.startAnimation(slideOut);
        question3View.setVisibility(View.VISIBLE);
        question3View.startAnimation(slideIn);
        progressBar.setProgress(60);
    }

    /**
     * Updates screen for Question 4
     */

    public void question4() {
        question3View.setVisibility(View.GONE);
        question3View.startAnimation(slideOut);
        question4View.setVisibility(View.VISIBLE);
        question4View.startAnimation(slideIn);
        progressBar.setProgress(80);
    }

    /**
     * Updates screen for Question 5
     */

    public void question5() {
        question4View.setVisibility(View.GONE);
        question4View.startAnimation(slideOut);
        question5View.setVisibility(View.VISIBLE);
        question5View.startAnimation(slideIn);
        progressBar.setProgress(100);
    }

    /**
     * Checks responses for all the questions and calculates the final Score and stops the Stopwatch and updates the screen
     */

    public void checkResult() {
        hideSoftKeyboard();
        stopTimer();
        question5View.setVisibility(View.GONE);
        question5View.startAnimation(slideOut);
        resultView.setVisibility(View.VISIBLE);
        resultView.startAnimation(slideIn);
        String answer5 = question5EditText.getText().toString();
        if (question1RadioButton.isChecked())
            score++;
        if (question2RadioButton.isChecked())
            score++;
        if (question3RadioButton.isChecked())
            score++;
        if ((question4CheckBox1.isChecked()) && (question4CheckBox2.isChecked()) && (question4CheckBox3.isChecked()) && (!(question4CheckBox4.isChecked())))
            score++;
        if (answer5.equalsIgnoreCase(getString(R.string.oreo)))
            score++;
        Toast.makeText(this, name + getString(R.string.score_toast) + " " + score, Toast.LENGTH_LONG).show();
        ratingBar.setNumStars(5);
        ratingBar.setRating(score);
        startTextView.setText(getString(R.string.congratulation) + "\n" + name + "!");
    }

    /**
     * Keeps account of current question and accordingly calls appropriate question
     *
     * @param view gets invoked by Submit Button
     */

    public void submit(View view) {
        currentQuestion++;
        if (currentQuestion == 2) {
            question2();
        }
        if (currentQuestion == 3) {
            question3();
        }
        if (currentQuestion == 4) {
            question4();
        }
        if (currentQuestion == 5) {
            question5();
        }
        if (currentQuestion == 6) {
            checkResult();
        }
    }

    /**
     * Restarts the application for new user
     *
     * @param view invokes on Try Again Button
     */

    public void reset(View view) {
        Intent intent = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    /**
     * Asks for user feedback by redirecting to email app using Intents
     *
     * @param view invokes on Feedback Button
     */

    public void feedback(View view) {
        String[] email = {"sumankalyangiri786@gmail.com"};
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, email);
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.feedback_intent_message));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * Use to close keyboard when required to improve user experience
     */

    public void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

}
