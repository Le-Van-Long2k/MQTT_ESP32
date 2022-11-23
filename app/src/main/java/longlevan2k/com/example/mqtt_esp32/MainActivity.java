package longlevan2k.com.example.mqtt_esp32;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.eclipse.paho.android.service.MqttAndroidClient;

public class MainActivity extends AppCompatActivity {
    private TextView tvSubMess;
    private EditText edtPubMess;
    private Button btnConnect, btnDisconnect, btnPublish, btnSubscribe;
    static final String URL_MQTT = "tcp://mqtt.innoway.vn:1883";
    static final String USER_NAME = "longlevan2k@gmail.com";
    static final String PASSWORD = "5mFnKK3FjMmyHyxhweMHssKlUJdie4UU";
    static final String TOPIC_SUB = "messages/4dadc1cf-7cdd-476f-bb55-c512bfeeeed0/attribute";
    static final String TOPIC_PUB = "messages/4dadc1cf-7cdd-476f-bb55-c512bfeeeed0/attribute";
    static final String TAG = "DEBUG: ";
    MqttAndroidClient client;
    String SubMess = "";
    String PubMess = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tracking_screen);

//        tvSubMess = findViewById(R.id.tvSubMess);
//        edtPubMess = findViewById(R.id.edtPubMess);
        btnConnect = findViewById(R.id.btnConnect);
        btnDisconnect = findViewById(R.id.btnDisconnect);
//        btnPublish = findViewById(R.id.btnPublish);
//        btnSubscribe = findViewById(R.id.btnSubscribe);

    }

//    public void Connect(View view) {
//        String clientId = MqttClient.generateClientId();
//        client = new MqttAndroidClient(this.getApplicationContext(), URL_MQTT, clientId);
//        MqttConnectOptions options = new MqttConnectOptions();
//        options.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1);
//        options.setCleanSession(false);
//        options.setUserName(USER_NAME);
//        options.setPassword(PASSWORD.toCharArray());
//
//        // connect MQTT
//        try{
//            IMqttToken token = client.connect(options);
//            token.setActionCallback(new IMqttActionListener() {
//                @Override
//                public void onSuccess(IMqttToken asyncActionToken) {
//                    Toast.makeText(MainActivity.this, "Connect MQTT success", Toast.LENGTH_SHORT).show();
//                    Log.d(TAG, "Connect MQTT success");
//                }
//
//                @Override
//                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
//                    Toast.makeText(MainActivity.this, "Connect MQTT failure", Toast.LENGTH_SHORT).show();
//                    Log.d(TAG, "Connect MQTT failure");
//                }
//            });
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        // callback nhan data
//        client.setCallback(new MqttCallback() {
//            @Override
//            public void connectionLost(Throwable cause) {
//                Toast.makeText(MainActivity.this, "Connect MQTT lost", Toast.LENGTH_SHORT).show();
//                Log.d(TAG, "Connect MQTT lost");
//            }
//
//            @Override
//            public void messageArrived(String topic, MqttMessage message) throws Exception {
//                SubMess = new String(message.getPayload());
//                tvSubMess.setText(SubMess);
//            }
//
//            @Override
//            public void deliveryComplete(IMqttDeliveryToken token) {
//
//            }
//        });
//
//        btnConnect.setTextColor(getResources().getColor(R.color.color_done));
//    }
//
//    public void Disconnect(View view) {
//        try {
//            IMqttToken disconToken = client.disconnect();
//            disconToken.setActionCallback(new IMqttActionListener() {
//                @Override
//                public void onSuccess(IMqttToken asyncActionToken) {
//                    Toast.makeText(MainActivity.this, "Disconnect MQTT success", Toast.LENGTH_SHORT).show();
//                    Log.d(TAG, "Disconnect MQTT success");
//                }
//
//                @Override
//                public void onFailure(IMqttToken asyncActionToken,
//                                      Throwable exception) {
//                    Toast.makeText(MainActivity.this, "Disconnect MQTT failure", Toast.LENGTH_SHORT).show();
//                    Log.d(TAG, "Disconnect MQTT failure");
//                }
//            });
//        } catch (MqttException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void Publish(View view) {
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
//
//    public void Subscribe(View view) {
//        int qos = 0;
//        try {
//            IMqttToken subToken = client.subscribe(TOPIC_SUB, qos);
//            subToken.setActionCallback(new IMqttActionListener() {
//                @Override
//                public void onSuccess(IMqttToken asyncActionToken) {
//                    Toast.makeText(MainActivity.this, "Subscribe MQTT success", Toast.LENGTH_SHORT).show();
//                    Log.d(TAG, "Subscribe MQTT success");
//                }
//
//                @Override
//                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
//                    Toast.makeText(MainActivity.this, "Subscribe MQTT failure", Toast.LENGTH_SHORT).show();
//                    Log.d(TAG, "Subscribe MQTT failure");
//                }
//            });
//        } catch (MqttException e) {
//            e.printStackTrace();
//        }
//    }
//

}