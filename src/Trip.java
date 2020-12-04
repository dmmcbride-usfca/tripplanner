import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class Trip {
    private Map map;
    private int src;
    private int trg;
    private static final int NO_PARENT = -1;
    public ArrayList<String> path = new ArrayList<>();

    public Trip(String source, String target) throws IOException {
        map = new Map();
        src = map.cities.get(source);
        trg = map.cities.get(target);
    }


    public void dijkstra()
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

        findRoute(src, trg, fastestroads, parents);
    }

    private void findRoute(int src, int trg, int[] distances, int[] parents)
    {
        findRouteHelper(trg, parents);
        path.remove(0);
    }


    private void findRouteHelper(int trg, int[] parents)
    {
        path.add(0,map.findKey(trg));
        if (trg == NO_PARENT)
            return;
        findRouteHelper(parents[trg], parents);
        System.out.print(trg + " ");
    }


    public static void main(String [] args) throws IOException {
        Trip trip = new Trip("Denver CO", "Bentonville AR");
        trip.dijkstra();
        System.out.println("Route: ");
        for (String town : trip.path)
        {
            System.out.printf("-> " + town + " ");
        }

    }
}






