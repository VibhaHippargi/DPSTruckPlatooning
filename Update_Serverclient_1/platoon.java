import java.util.Enumeration;

public class platoon {

    /*1.distance -double
 * 2.speed -int
 * 3.signal strength -int
 * 4.location -string
 * 5.weather/road condition - string
 */
    private double distance;
    private int speed,signal_strength;
    private String location,weather;


    public double getDistance() {
        return distance;
    }
    public void setDistance(double distance) {
        this.distance = distance;
    }


    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public int getSignal_strength() {
        return signal_strength;
    }
    public void setSignal_strength(int signal_strength) {
        this.signal_strength = signal_strength;
    }


    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }


    public String getWeather() {
        return weather;
    }
    public void setWeather(String weather) {
        this.weather = weather;
    }

    

    public platoon()
    {
        this.distance=0.0;
        this.speed=0;
        this.signal_strength=0;
        this.location="";
        this.weather="";
    }

    public platoon(double _distance,int _speed,int _signal_strength,String _location,String _weather)
    {
        this.distance=_distance;
        this.speed=_speed;
        this.signal_strength=_signal_strength;
        this.location=_location;
        this.weather=_weather;
    }

}
