package longlevan2k.com.example.mqtt_esp32.model;

public class MqttObject {
    private double temperature;
    private double humidity;
    private double sound;
    private double light;

    public MqttObject(){}

    public MqttObject(double temperature, double humidity, double sound, double light) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.sound = sound;
        this.light = light;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getSound() {
        return sound;
    }

    public void setSound(double sound) {
        this.sound = sound;
    }

    public double getLight() {
        return light;
    }

    public void setLight(double light) {
        this.light = light;
    }

    @Override
    public String toString() {
        return "MqttObject{" +
                "temperature=" + temperature +
                ", humidity=" + humidity +
                ", sound=" + sound +
                ", light=" + light +
                '}';
    }
}
