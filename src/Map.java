import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Map {
    protected int v;
    protected int [][] m;

    protected Hashtable<String, Integer> cities;
    HashMap<String, String> attractions = new HashMap<String, String>();

    Map() throws IOException {
        Hashtable<String, Integer> cities = new Hashtable<String, Integer>();
        Set<String> uniqueCities = new HashSet<String>();
        BufferedReader csvReader = new BufferedReader(new FileReader("roads.csv"));
        String row;
        while ((row = csvReader.readLine()) != null)
        {
            String[] data = row.split(",");
            uniqueCities.add(data[0]);
            uniqueCities.add(data[1]);
        }
        csvReader.close();

        int count=0;
        for (String city : uniqueCities) {
            cities.put(city, count++);
        }

        this.v = cities.size();
        m = new int[v][v];

        this.cities = cities;
        makeMap();
    }

    private void addEdge(int src, int dest, int weight)
    {
        m[src][dest] = weight;
        m[dest][src] = weight;
    }

    public void makeMap() throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader("roads.csv"));
        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            addEdge(cities.get(data[0]),cities.get(data[1]), Integer.parseInt(data[3]));
        }
        csvReader.close();

        BufferedReader csvReader1 = new BufferedReader(new FileReader("attractions.csv"));
        String row1;
        while ((row1 = csvReader1.readLine()) != null) {
            String[] data = row1.split(",");
            attractions.put(data[0], data[1]);
        }
        csvReader1.close();
    }
    public String findKey(int node)
    {
        Set<String> keys = cities.keySet();
        for(String key: keys){
            if (cities.get(key)==node)
                return key;
        }
        return null;
    }




    public static void main(String [] args) throws IOException {


        Map map1 = new Map();
        System.out.println("test");




    }
}