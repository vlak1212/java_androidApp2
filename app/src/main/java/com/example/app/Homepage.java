package com.example.app;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Homepage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        Button backButton = (Button) findViewById(R.id.buttonGoBack);
        Button ledButton = (Button) findViewById(R.id.buttonToggleLED);
        Button fingerButton = (Button) findViewById(R.id.buttonCountFingers);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        ledButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this, ToggleLed.class);
                startActivity(intent);
            }
        });
        fingerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this, FingerCounter.class);
                startActivity(intent);
            }
        });
    }
}
