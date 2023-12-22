package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class OTPActivity extends AppCompatActivity {
    EditText OTP;
    Button VerfyButton;
    TextView Return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpactivity);
        OTP = findViewById(R.id.enterOTP);

        VerfyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enterOTP = OTP. getText().toString().trim();
                SendOTP(enterOTP);
            }
        });
    }

    private void SendOTP(String enterOTP) {
    }
    private void setTitleToolBar(){
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Enter OTP Code");
        }
    }
}