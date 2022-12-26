import java.io.Serializable;

class Location implements Serializable {
    public double lat, lng;
    Location(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }
};

public class platoon implements Serializable {

    private double distance;
    private int speed,signal_strength;
    private String weather;
    private Location location;
    private boolean quit;


    public boolean getQuit() {
        return quit;
    }
    public void setQuit(boolean quit) {
        this.quit = quit;
    }
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

    public platoon()
    {
        this.distance=0.0;
        this.speed=0;
        this.signal_strength=0;
        this.location=new Location(0, 0);
        this.weather="";
        this.quit=false;
    }

    public platoon(double _distance,int _speed,int _signal_strength,Location _location,String _weather,boolean _quit)
    {
        this.distance=_distance;
        this.speed=_speed;
        this.signal_strength=_signal_strength;
        this.location=_location;
        this.weather=_weather;
        this.quit=_quit;
    }

}
