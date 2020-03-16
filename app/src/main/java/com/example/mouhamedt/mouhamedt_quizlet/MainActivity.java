package com.example.mouhamedt.mouhamedt_quizlet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
    int currentCardDisplayedIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        allFlashcards = flashcardDatabase.getAllCards();

        if (allFlashcards != null && allFlashcards.size() > 0) {
            ((TextView) findViewById(R.id.quizlet_question)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.quizlet_answer)).setText(allFlashcards.get(0).getAnswer());
        }

        findViewById(R.id.quizlet_question).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((TextView) findViewById(R.id.quizlet_question)).setVisibility(View.INVISIBLE);
                ((TextView) findViewById(R.id.quizlet_answer)).setVisibility(View.VISIBLE);
            }
        });
        findViewById(R.id.quizlet_answer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((TextView) findViewById(R.id.quizlet_answer)).setVisibility(View.INVISIBLE);
                ((TextView) findViewById(R.id.quizlet_question)).setVisibility(View.VISIBLE);
            }
        });
        findViewById(R.id.answer1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((TextView) (findViewById(R.id.answer1))).setBackgroundColor(getResources().getColor(R.color.real_red));
                ((TextView) (findViewById(R.id.answer3))).setBackgroundColor(getResources().getColor(R.color.real_green));
            }
        });
        findViewById(R.id.answer2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((TextView) (findViewById(R.id.answer2))).setBackgroundColor(getResources().getColor(R.color.real_red));
                ((TextView) (findViewById(R.id.answer3))).setBackgroundColor(getResources().getColor(R.color.real_green));
            }
        });
        findViewById(R.id.answer3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((TextView) (findViewById(R.id.answer3))).setBackgroundColor(getResources().getColor(R.color.real_green));
            }
        });
        findViewById(R.id.toggle_choices_visibility).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView txtView = ((TextView)findViewById(R.id.answer1));
                if (txtView.getVisibility() == View.VISIBLE) {
                    ((ImageView) findViewById(R.id.toggle_choices_visibility)).setImageResource(R.drawable.show_answer);
                    ((TextView) findViewById(R.id.answer1)).setVisibility(View.INVISIBLE);
                    ((TextView) findViewById(R.id.answer2)).setVisibility(View.INVISIBLE);
                    ((TextView) findViewById(R.id.answer3)).setVisibility(View.INVISIBLE);
                } else {
                    ((ImageView) findViewById(R.id.toggle_choices_visibility)).setImageResource(R.drawable.hide_answer);
                    ((TextView) findViewById(R.id.answer1)).setVisibility(View.VISIBLE);
                    ((TextView) findViewById(R.id.answer2)).setVisibility(View.VISIBLE);
                    ((TextView) findViewById(R.id.answer3)).setVisibility(View.VISIBLE);
                }

            }
        });
        findViewById(R.id.plus_icon_toggle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent1,100);
            }
        });
        findViewById(R.id.next_icon_toggle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentCardDisplayedIndex++;
                if (currentCardDisplayedIndex > allFlashcards.size() - 1) {
                    currentCardDisplayedIndex = 0;
                }
                ((TextView) findViewById(R.id.quizlet_question)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                ((TextView) findViewById(R.id.quizlet_answer)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                ((TextView) findViewById(R.id.quizlet_answer)).setVisibility(View.INVISIBLE);
                ((TextView) findViewById(R.id.quizlet_question)).setVisibility(View.VISIBLE);
            }
        });
        }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            String p_question = data.getExtras().getString("p_question");
            String p_answer = data.getExtras().getString("p_answer");
            ((TextView) findViewById(R.id.quizlet_question)).setText(p_question);
            ((TextView) findViewById(R.id.quizlet_answer)).setText(p_answer);
            flashcardDatabase.insertCard(new Flashcard(p_question, p_answer));
            allFlashcards = flashcardDatabase.getAllCards();
        }
    }
}
