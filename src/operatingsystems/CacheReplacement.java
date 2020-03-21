package operatingsystems;

import java.util.*;
import java.util.Queue;
import java.util.HashMap;
import java.util.Scanner;

public class CacheReplacement {

    public static void main(String args[]) {
        int m = 1;
        Scanner sc = new Scanner(System.in);
        HashMap<Integer, String> songMap = new HashMap<Integer, String>();
        HashMap<Integer, Integer> cacheMap = new HashMap<Integer, Integer>();

        Queue<Integer> fifoCache = new LinkedList<>();
        Stack<Integer> lifoCache = new Stack<>();
        populate(songMap);

        //User input
        do {
            System.out.println("Select songs you want to play");
            m = sc.nextInt();
            updateLFU(cacheMap, m);
            updateFifo(fifoCache, m);
            updateLifo(lifoCache, m);
            System.out.println("Enter -1 to exit");
        } while (m != -1);
        display(fifoCache, lifoCache);

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
        if (fifoCache.size() > 5 && song == false) {
            fifoCache.remove();
            System.out.println("Updated : " + fifoCache);
        } else if (fifoCache.size() > 5 && song == true) {
            System.out.println("Updated : " + fifoCache + "HIT");

        }
    }

    public static void updateLifo(Stack lifoCache, int m) {
        //initially
        System.out.println("LIFO CACHE :" + lifoCache);

        //Adding songs to Cache
        Integer song = (Integer) lifoCache.search(m);
        lifoCache.push(m);
        System.out.println("LIFO CACHE : " + lifoCache);

        //Updating Cache
        if (lifoCache.size() < 5 && song == -1) {
            lifoCache.pop();
            System.out.println("Updated : " + lifoCache);
        } else if (lifoCache.size() < 5 && song != -1) {
            System.out.println("Updated : " + lifoCache + "HIT");
        }

    }

    public static void updateLFU(HashMap<Integer, Integer> cacheMap, int id) {

        if (cacheMap.containsKey(id)) {
            int newVal = cacheMap.get(id) + 1;
            cacheMap.put(id, newVal);
        } else {

            if (cacheMap.size() == 5) {
                int minVal = Integer.MAX_VALUE;
                int keyToRemove = 0;
                for (int key : cacheMap.keySet()) {
                    int value = cacheMap.get(key);
                    if(value <= minVal){
                        minVal = value;
                        keyToRemove = key;
                    }
                    
                }
                cacheMap.remove(keyToRemove);
                cacheMap.put(id, 1);

            } else {
                cacheMap.put(id, 1);
            }
        }
        System.out.println("Map: " + cacheMap);
    }

//Display Table
    public static void display(Queue fifoCache, Stack lifoCache) {

        System.out.println("FIFO\t LIFO\t");
        for (Object s : fifoCache) {
            System.out.println(s);
        }
    }

}
