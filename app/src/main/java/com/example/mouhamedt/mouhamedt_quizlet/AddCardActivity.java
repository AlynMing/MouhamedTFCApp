package com.example.mouhamedt.mouhamedt_quizlet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        findViewById(R.id.cancel_icon_toggle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(AddCardActivity.this, MainActivity.class);
                AddCardActivity.this.startActivity(intent2);
            }
        });
        findViewById(R.id.save_icon_toggle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra("p_question", ((EditText) findViewById(R.id.personal_Q)).getText().toString());
                data.putExtra("p_answer", ((EditText) findViewById(R.id.personal_A)).getText().toString());
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}
