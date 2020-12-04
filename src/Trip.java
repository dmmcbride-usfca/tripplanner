import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Trip {
    private Map map;
    private static final int NO_PARENT = -1;


    public Trip() throws IOException {
        map = new Map();
//        src = map.cities.get(source);
//        trg = map.cities.get(target);

    }

    public List<String> route(String starting_city, String ending_city, List<String> attractions)
    {
        int src = map.cities.get(starting_city);
        int trg = map.cities.get(ending_city);

        ArrayList<String> path = dijkstra(src, trg);

        return path;
    }


    private ArrayList<String> dijkstra(int src, int trg)
    {
        int n = map.v;
        int[] fastestroads = new int[n];
        boolean[] added = new boolean[n];

        for (int vIdx = 0; vIdx < n; vIdx++)
        {
            fastestroads[vIdx] = Integer.MAX_VALUE;
            added[vIdx] = false;
        }

        fastestroads[src] = 0;
        int[] parents = new int[n];
        parents[src] = NO_PARENT;

        for (int i = 1; i < n; i++)
        {
            int closesttown = -1;
            int fastestroad = Integer.MAX_VALUE;
            for (int vIdx = 0; vIdx < n; vIdx++)
            {
                if (!added[vIdx] && fastestroads[vIdx] < fastestroad)
                {
                    closesttown = vIdx;
                    fastestroad = fastestroads[vIdx];
                }
            }

            added[closesttown] = true;

            for (int vIdx = 0; vIdx < n; vIdx++)
            {
                int traveltime = map.m[closesttown][vIdx];
                if (traveltime > 0 && ((fastestroad + traveltime) < fastestroads[vIdx]))
                {
                    parents[vIdx] = closesttown;
                    fastestroads[vIdx] = fastestroad + traveltime;
                }
            }
        }

        ArrayList<String> path = findRouteSegment(src, trg, fastestroads, parents);
        return path;
    }


    private ArrayList<String> findRouteSegment(int src, int trg, int[] distances, int[] parents)
    {
        ArrayList<String> path = new ArrayList<>();
        path = findRouteSegmentHelper(trg, parents, path);
        path.remove(0);
        return path;

        int n = distances.length;
        System.out.print("Vertex\t Distance\tPath");

        for (int vIdx = 0;
             vIdx < n;
             vIdx++)
        {
            if (vIdx != src)
            {
                System.out.print("\n" + src + " -> ");
                System.out.print(vIdx + " \t\t ");
                System.out.print(distances[vIdx] + "\t\t");
                printPath(vIdx, parents);
            }
        }
    }


    private ArrayList<String> findRouteSegmentHelper(int trg, int[] parents, ArrayList<String> path)
    {
        path.add(0,map.findKey(trg));
        if (trg == NO_PARENT)
            return path;
        findRouteSegmentHelper(parents[trg], parents, path);
        return path;
    }


    public static void main(String [] args) throws IOException {

        List<String> attractions = new ArrayList<>();
        attractions.add("Statue of Liberty");
        attractions.add("Mystic Seaport");

        Trip trip = new Trip();
        List<String> route = trip.route("Denver CO", "Bentonville AR", attractions);
        System.out.println("done ");
//        for (String town : trip.path)
//        {
//            System.out.printf("-> " + town + " ");
//        }

    }
}






