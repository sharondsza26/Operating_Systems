package operatingsystems;
import java.util.*;
import java.util.Queue;
import java.util.HashMap;
import java.util.Scanner;

public class CacheReplacement1 {

    public static void main(String args[]) {
        int ch = 1;
        Scanner sc = new Scanner(System.in);
        HashMap<Integer, String> songMap = new HashMap<Integer, String>();
        Queue<Integer> fifoCache = new LinkedList<>();
        populate(songMap);

        //User input
        do {
            System.out.println("Select songs you want to play");
            int m = sc.nextInt();
            updateFifo(fifoCache, m);
            System.out.println("Enter -1 to exit");
            ch = sc.nextInt();
        } while (ch != -1);

    }

    public static void populate(HashMap<Integer, String> songMap) {
        int index = 1;
        songMap.put(index++, "Song1");
        songMap.put(index++, "Song2");
        songMap.put(index++, "Song3");
        songMap.put(index++, "Song4");
        songMap.put(index++, "Song5");
        songMap.put(index++, "Song6");
        songMap.put(index++, "Song7");
        songMap.put(index++, "Song8");
        songMap.put(index++, "Song9");
        songMap.put(index++, "Song10");

        //Printing
        songMap.forEach((k, v) -> System.out.println(k + "  :  " + v));
    }

    public static void updateFifo(Queue fifoCache, int m) {

        //Initially
        System.out.println("FIFO CACHE : " + fifoCache);

        //Adding songs to Cache
        boolean song = (boolean) fifoCache.contains(m);        
        fifoCache.add(m);
        System.out.println("FIFO CACHE : " + fifoCache);

        //Updating Cache
        if (fifoCache.size() < 5 && song == false) {
            fifoCache.remove();
            System.out.println("Updated : " + fifoCache);
        }
        else if (fifoCache.size() < 5 && song == true){
            System.out.println("Updated : " + fifoCache + "HIT");

        }
    }
}
