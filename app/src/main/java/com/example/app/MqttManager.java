package com.example.app;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttManager {

    private static final String TAG = "MQTT";

    private MqttAndroidClient mqttAndroidClient;
    private String serverURI;
    private String clientId;
    private String topic;
    private int STT;

    public MqttManager(Context context) {
        mqttAndroidClient = new MqttAndroidClient(context.getApplicationContext(), serverURI, clientId);

        mqttAndroidClient.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                Log.e(TAG, "connectionLost: ");
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                Log.e(TAG, "messageArrived: " + topic + ":" + message.toString());
                // message.toString(); -> subscribe
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                Log.e(TAG, "deliveryComplete: ");
            }
        });
    }

    public void connect(String serverURI, String clientId, String topic, final Context context) {
        this.serverURI = serverURI;
        this.clientId = clientId;
        this.topic = topic;

        MqttConnectOptions connectOptions = new MqttConnectOptions();
        connectOptions.setAutomaticReconnect(true);

        try {
            mqttAndroidClient.connect(connectOptions, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(context, "connect onSuccess", Toast.LENGTH_SHORT).show();
                    STT = 1;
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(context, "connect onFailure", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "connect onFailure: ");
                    STT = 0;
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void publish(String msg) {
        MqttMessage message = new MqttMessage();
        message.setQos(0);
        message.setRetained(false);
        message.setPayload((msg).getBytes());
        try {
            mqttAndroidClient.publish(topic, message, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.e(TAG, "onSuccess: ");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.e(TAG, "onFailure: ");
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void subscribe() {
        try {
            mqttAndroidClient.subscribe(topic, 0, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.e(TAG, "onSuccess: " + asyncActionToken.getClient().getClientId());
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.e(TAG, "onFailure: ");
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public int getSTT() {
        return STT;
    }
}
