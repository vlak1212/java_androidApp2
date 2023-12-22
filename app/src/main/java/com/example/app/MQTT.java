package com.example.app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MQTT extends AppCompatActivity {
    EditText Topic;
    EditText URI;
    Button Conect;
    Button Layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mqtt );
    }
}
