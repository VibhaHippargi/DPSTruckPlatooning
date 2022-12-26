import java.io.Serializable;

class Location implements Serializable {
    public double lat, lng;
    Location(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }
};

public class leader implements Serializable {

    /*1.distance -double
 * 2.speed -int
 * 3.signal strength -int
 * 4.location -string
 * 5.weather/road condition - string
 */
    private double distance;
    private int speed,signal_strength;
    private String weather;
    private Location location;


    


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


    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }


    public String getWeather() {
        return weather;
    }
    public void setWeather(String weather) {
        this.weather = weather;
    }

    

    public leader()
    {
        this.distance=50.0;
        this.speed=50;
        this.signal_strength=100;
        this.location=new Location(50, 60);
        this.weather="Hot";
    }

    public leader(double _distance,int _speed,int _signal_strength,Location _location,String _weather)
    {
        this.distance=_distance;
        this.speed=_speed;
        this.signal_strength=_signal_strength;
        this.location=_location;
        this.weather=_weather;
    }

}
