package longlevan2k.com.example.mqtt_esp32;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;

import longlevan2k.com.example.mqtt_esp32.model.MqttObject;


public class MainActivity extends AppCompatActivity {
    private Button btnConnect, btnDisconnect;
    private TextView tvTemperature, tvHumidity, tvSound, tvLight;
    static final String URL_MQTT = "tcp://mqtt.innoway.vn:1883";
    static final String USER_NAME = "longlevan2k@gmail.com";
    static final String PASSWORD = "5mFnKK3FjMmyHyxhweMHssKlUJdie4UU";
    static final String TOPIC_SUB = "messages/4dadc1cf-7cdd-476f-bb55-c512bfeeeed0/status";
    static final String TOPIC_PUB = "messages/4dadc1cf-7cdd-476f-bb55-c512bfeeeed0/attribute";
    static final String TAG = "DEBUG: ";
    MqttAndroidClient client;
    String SubMess = ""; // data co dang nhiet do, do am , cuong do am thanh, cuong do anh sang
                        // VD: SubMess = "25,70,30,5000"
    //String PubMess = "";
    String[] Data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tracking_screen);

        btnConnect = findViewById(R.id.btnConnect);
        btnDisconnect = findViewById(R.id.btnDisconnect);
        tvTemperature = findViewById(R.id.tvTemperature);
        tvHumidity = findViewById(R.id.tvHumidity);
        tvLight = findViewById(R.id.tvLight);
        tvSound = findViewById(R.id.tvSound);


        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Connect();
            }
        });

        btnDisconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Disconnect();
            }
        });

    }

    public void Connect() {
        String clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient(this.getApplicationContext(), URL_MQTT, clientId);
        MqttConnectOptions options = new MqttConnectOptions();
        options.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1);
        options.setCleanSession(false);
        options.setUserName(USER_NAME);
        options.setPassword(PASSWORD.toCharArray());

        // connect MQTT
        try{
            IMqttToken token = client.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    btnConnect.setText("CONNECTED");
                    btnConnect.setTextColor(getColor(R.color.red));
                    btnConnect.setBackgroundColor(getColor(R.color.green));
                    //Toast.makeText(MainActivity.this, "Connect MQTT success", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Connect MQTT success");
                    Subscribe();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(MainActivity.this, "Connect MQTT failure", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Connect MQTT failure");
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

        // callback nhan data
        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                Toast.makeText(MainActivity.this, "Connect MQTT lost", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Connect MQTT lost");
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                SubMess = new String(message.getPayload());
                Gson gson = new Gson();
                MqttObject mqttObject = gson.fromJson(SubMess, MqttObject.class);

                tvTemperature.setText(mqttObject.getTemperature() + "°C");
                tvHumidity.setText(mqttObject.getHumidity() + "%");
                tvSound.setText(mqttObject.getSound() + "db");
                tvLight.setText(mqttObject.getLight() + "lux");
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });

    }

    public void Disconnect() {
        try {
            IMqttToken disconToken = client.disconnect();
            disconToken.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    btnConnect.setText("CONNECT");
                    btnConnect.setTextColor(getColor(R.color.black));
                    btnConnect.setBackgroundColor(getColor(R.color.gray));
                    Toast.makeText(MainActivity.this, "Disconnect MQTT success", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Disconnect MQTT success");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken,
                                      Throwable exception) {
                    Toast.makeText(MainActivity.this, "Disconnect MQTT failure", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Disconnect MQTT failure");
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

//    public void Publish() {
//        String pubMess = edtPubMess.getText().toString();
//        byte[] encodedPayload = new byte[0];
//        try {
//            encodedPayload = pubMess.getBytes("UTF-8");
//            MqttMessage message = new MqttMessage(encodedPayload);
//            client.publish(TOPIC_PUB, message);
//        } catch (UnsupportedEncodingException | MqttException e) {
//            e.printStackTrace();
//        }
//    }

    public void Subscribe() {
        int qos = 0;
        try {
            IMqttToken subToken = client.subscribe(TOPIC_SUB, qos);
            subToken.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(MainActivity.this, "Subscribe MQTT success", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Subscribe MQTT success");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(MainActivity.this, "Subscribe MQTT failure", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Subscribe MQTT failure");
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

}