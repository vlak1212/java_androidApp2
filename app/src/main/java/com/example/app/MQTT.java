package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MQTT extends AppCompatActivity implements View.OnClickListener {

    private TextView tvStatus;
    private EditText inputtopic;
    private EditText inputSURI;
    private MqttManager mqttManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mqtt);

        // Khởi tạo các thành phần UI và thiết lập sự kiện onClick
        tvStatus = findViewById(R.id.tv_status);
        inputtopic = findViewById(R.id.input_topic);
        inputSURI = findViewById(R.id.input_suri);

        mqttManager = new MqttManager(this);

        findViewById(R.id.btn_connect).setOnClickListener(this);
        findViewById(R.id.btn_change).setOnClickListener(this);
    }
    private String generateClientId() {
        // Trong trường hợp này, ví dụ đơn giản là sử dụng ANDROID_ID
        return "android-" + android.provider.Settings.Secure.getString(
                getContentResolver(),
                android.provider.Settings.Secure.ANDROID_ID
        );
    }
    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        if (viewId == R.id.btn_connect) {
            /// Lấy dữ liệu từ trường nhập liệu và thực hiện kết nối
            String topic = inputtopic.getText().toString();
            String serverURI = inputSURI.getText().toString();

            // Kiểm tra xem topic và serverURI có giá trị không trống
            if (!topic.isEmpty() && !serverURI.isEmpty()) {
                tvStatus.setText("connect...");
                String clientId = generateClientId();  // Đặt client id của bạn
                mqttManager.connect(serverURI, clientId, topic, this);
            } else {
                // Hiển thị thông báo lỗi nếu topic hoặc serverURI trống
                Toast.makeText(MQTT.this, "Please enter Topic and Server URI", Toast.LENGTH_SHORT).show();
            }
        } else if (viewId == R.id.btn_change) {
            // Xử lý khi nút "Change" được click
            if (mqttManager.getSTT() == 0) {
                Toast.makeText(MQTT.this, "Please Connect MQTT", Toast.LENGTH_SHORT).show();
            } else  {
                Intent intent = new Intent(MQTT.this, Homepage.class);
                startActivity(intent);}
        }
    }
}
