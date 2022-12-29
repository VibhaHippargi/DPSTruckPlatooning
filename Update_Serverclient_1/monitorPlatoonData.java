public class monitorPlatoonData {

   Velocity_equations vel_equ = new Velocity_equations();
   leader lead = new leader();
   private double std_distance = 30.0;
   private int std_speed = lead.getSpeed();
   private int std_signal_strength = lead.getSignal_strength() - 50;

   public String monitor_distance(double distance) {
      String s;
      double r;
      if (distance < std_distance) {
         r = std_distance - distance;
         s = "You are close by " + r;
      } else // need to add one more condition to check if distance is too far
      {
         s = "Distance okay";
      }
      return s;
   }

   public String monitor_signal_strength(int signalstrength) {
      String s;
      int r;
      if (signalstrength < std_signal_strength) {
         r = std_signal_strength - signalstrength;
         s = "You are " + r + " less than minimum required signal strength";
      } else // need to add one more condition to check if distance is in required lenght
      {
         s = "Signal strength okay";
      }
      return s;

   }

   public String monitor_speed(double distance, int speed) {
      // speed -> Current Speed of the Platoon
      // distance -> Distance between the Platoon and the Vehicle in front of it
      String s;
      int r;
      String acceleration;
      String time;
      double differnce_distance = distance - std_distance;
      // difference_distance -> the distance which needs to be maintained,
      // i.e. if the platoon is at 40m distance from Vehicle before it and Standard
      // distance is
      // 30m then 10m needs to be covered
      differnce_distance = (differnce_distance < 0) ? -differnce_distance : differnce_distance;
      if (speed < std_speed) {
         acceleration = vel_equ.vel_calculation(speed, std_speed, differnce_distance);
         time = vel_equ.time_calculation(speed, std_speed);
         r = std_speed - speed;
         s = "You are " + r + " slower than leader, accelerate by " + acceleration + "for the given time " + time;
      } else if (speed > std_speed) {
         acceleration = vel_equ.vel_calculation(speed, std_speed, differnce_distance);
         time = vel_equ.time_calculation(speed, std_speed);
         r = std_speed - speed;
         s = "You are " + r + " faster than leader.Slow down! decelerate by" + acceleration + "for the given time"
               + time;
      } else {
         s = "Speed okay";
      }
      return s;

   }

}
