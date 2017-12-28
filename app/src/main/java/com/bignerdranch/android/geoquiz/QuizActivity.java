package com.bignerdranch.android.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueBurron;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;
    private int mCurrentIndex = 0;

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas,true),
            new Question(R.string.question_asia,true),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mQuestionTextView = findViewById(R.id.question_text_view);
        showQuestion();

        mTrueBurron = findViewById(R.id.true_button);
        mTrueBurron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
                nextQuestion();
            }
        });

        mFalseButton = findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
                nextQuestion();
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextQuestion();
            }
        });
    }

    public void showQuestion(){
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question); //mQuestionTextView.setText(question+""); чтобы вывести id ресурса
    }

    public void nextQuestion() {
         mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length; /* перебор по кругу всех индексов массива.
                                                                           (0+1)%5 = 1;
                                                                           (1+1)%5 = 2;
                                                                           (2+1)%5 = 3;
                                                                           (3+1)%5 = 4;
                                                                           (4+1)%5 = 0;
                                                                            и т.д.
                                                                      */
        showQuestion();
    }

    public void checkAnswer(boolean userAnswer){
        int messageResId;
        if (userAnswer == mQuestionBank[mCurrentIndex].isAnswerTrue()) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }
        Toast toast = Toast.makeText(QuizActivity.this, messageResId, Toast.LENGTH_SHORT);
        toast.show();
        toast.setGravity(Gravity.TOP, 0, 500);
    }
}
