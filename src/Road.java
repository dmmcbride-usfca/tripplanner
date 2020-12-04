import java.util.Arrays;

public class Road {
    protected String [] locations = new String[2];
    protected int dist_s;
    protected int dist_t;

    public Road(String loc_1, String loc_2, int space, int time) {
        locations[0] = loc_1;
        locations[1] = loc_2;
        dist_s = space;
        dist_t = time;
    }

    @Override
    public String toString() {
        return "Road{" +
                "locations=" + Arrays.toString(locations) +
                ", dist_s=" + dist_s +
                ", dist_t=" + dist_t +
                '}';
    }

    public static void main(String [] args) {
        Road road1 = new Road("San Francisco", "New York", 1000, 8);
        System.out.println(road1);
    }
}
