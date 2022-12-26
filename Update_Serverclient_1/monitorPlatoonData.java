public class monitorPlatoonData {

    leader lead = new leader();
    private double std_distance=30.0;
    private int std_speed = lead.getSpeed(); 
    private int std_signal_strength = lead.getSignal_strength()-50;
   
    public String monitor_distance(double distance)
    {  String s;
       double r;
       if(distance<std_distance)
       {
        r=std_distance-distance;
        s= "You are close by "+r;
       }
       else
       {
        s= "Distance okay";
       }
       return s;  
    }

    public String monitor_signal_strength(int signalstrength)
    {  String s;
       int r;
       if(signalstrength<std_signal_strength)
       {
        r=std_signal_strength-signalstrength;
        s= "You are "+r+" less than minimum required signal strength";
       }
       else
       {
        s= "Signal strength okay";
       }
       return s;
      
    }

    public String monitor_speed(int speed)
    {  String s;
       int r;
       if(speed<std_speed)
       {
        r=std_speed-speed;
        s= "You are "+r+" slower than leader";
       }
       else if(speed>std_distance)
       {
         r=std_speed-speed;
        s= "You are "+r+" faster than leader.Slow down!";
       }
       else{
         s= "Speed okay";
       }
       return s;
      
    }

   



}
