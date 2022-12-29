import java.text.DecimalFormat;

public class Velocity_equations {

    // rounding off the final out come to 2 decimal places
    private static final DecimalFormat dec = new DecimalFormat("0.00");
    public double acceleration;
    public double acceleration_fin;
    public double time;

    public String vel_calculation(int ini_vel, int fin_vel, double distance) {
        // convert velocity to m/s, so we divide the given kmph by 3.6 to m/s
        // v^2 -u^2 = 2as, V-> Final Velocity, u -> initial Velocity a -> acceleration,
        // s -> distance
        // Standard distance is 40m, Platoon is at 50m, then it has to cover 10 m with
        // given acceratation
        double ini_vel_m = (ini_vel / 3.6);
        double fin_vel_m = (fin_vel / 3.6);
        acceleration = ((Math.pow(fin_vel_m, 2)) - (Math.pow(ini_vel_m, 2))) / (2 * distance);
        // convert accelration back to kmph^2
        acceleration_fin = acceleration * 3.6;

        return (dec.format(acceleration_fin));
    }

    public String time_calculation(int ini_vel, int fin_vel) {
        // calculate the time for which the acceleration needs to be maintained
        // v = u + at, a-> calculated from previous function, t -> time
        double ini_vel_m = (ini_vel / 3.6);
        double fin_vel_m = (fin_vel / 3.6);
        time = (fin_vel_m - ini_vel_m) / (acceleration);

        return (dec.format(time));
    }
}
