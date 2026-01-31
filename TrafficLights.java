import java.util.Scanner;

public class TrafficLights {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int l = sc.nextInt(); // distance A to B
        int d = sc.nextInt(); // distance A to traffic lights
        int v = sc.nextInt(); // car speed
        int g = sc.nextInt(); // green light duration
        int r = sc.nextInt(); // red light duration
        sc.close();
        
        // Time to reach traffic lights
        double timeToLights = (double) d / v;
        
        // Position in the traffic light cycle
        double cycleLength = g + r;
        double timeInCycle = timeToLights % cycleLength;
        
        double totalTime;
        
        if (timeInCycle < g) {
            // Green light - pass through immediately
            totalTime = (double) l / v;
        } else {
            // Red light - need to wait
            // Time into red phase
            double timeInRed = timeInCycle - g;
            // Time to wait until next green
            double waitTime = r - timeInRed;
            // Total time = time to reach lights + wait time + remaining distance
            totalTime = timeToLights + waitTime + (double) (l - d) / v;
        }
        
        System.out.println(totalTime);
    }
}