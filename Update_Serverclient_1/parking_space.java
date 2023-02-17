import java.util.ArrayList;

public class parking_space {
    // used to suggest nearby parking locations
    // this is local data stored in each platoon and they can access them everytime
    // signal is lost from the server
    // number of parking spots here is minimum, it can be added as required
    public Location Parking_assitance(Location current) {
        Location l1 = new Location(50, 50);
        Location l2 = new Location(60, 80);
        Location l3 = new Location(70, 90);
        Location l4 = new Location(90, 100);
        ArrayList<Location> locations = new ArrayList<Location>();
        locations.add(l1);
        locations.add(l2);
        locations.add(l3);
        locations.add(l4);
        Location ret_fin = new Location(0, 0);
        for (int i = 0; i < locations.size(); i++) {

            if (current.lat <= locations.get(i).lat) {
                ret_fin = locations.get(i);
            } else {
                ret_fin.lat = 100;
                ret_fin.lng = 150;
            }
        }
        return ret_fin;
    }
}