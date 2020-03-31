package operatingsystems;

import java.util.*;
import java.util.Queue;
import java.util.HashMap;
import java.util.Scanner;

public class CacheReplacement {

    public static void main(String args[]) {
        int id = 1;
        Scanner sc = new Scanner(System.in);
        HashMap<Integer, String> songMap = new HashMap<>();
        HashMap<Integer, Integer> cacheMap = new HashMap<>();
        LinkedList<Integer> listCache = new LinkedList<>();
        Queue<Integer> fifoCache = new LinkedList<>();
        Stack<Integer> lifoCache = new Stack<>();
        populate(songMap);

        //User input
        do {
            System.out.println("Select songs you want to play");
            id = sc.nextInt();
//            updateFifo(fifoCache, id);
//            updateLifo(lifoCache, id);
//            updateLFU(cacheMap, id);
            updateLRU(listCache, id);
            System.out.println("Enter -1 to exit");
        } while (id != -1);
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

    public static void updateFifo(Queue fifoCache, int id) {

        //Initially
        System.out.println("FIFO CACHE : " + fifoCache);

        //Adding songs to Cache
        boolean song = (boolean) fifoCache.contains(id);
        fifoCache.add(id);
        System.out.println("FIFO CACHE : " + fifoCache);

        //Updating Cache
        if (fifoCache.size() > 5 && song == false) {
            fifoCache.remove();
            System.out.println("Updated : " + fifoCache);
        } else if (fifoCache.size() > 5 && song == true) {
            System.out.println("Updated : " + fifoCache + "HIT");

        }
    }

    public static void updateLifo(Stack lifoCache, int id) {
        //initially
        System.out.println("LIFO CACHE :" + lifoCache);

        //Adding songs to Cache
        Integer song = (Integer) lifoCache.search(id);
        lifoCache.push(id);
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

        //Updating cache if song present
        if (cacheMap.containsKey(id)) {
            int newVal = cacheMap.get(id) + 1;
            cacheMap.put(id, newVal);
        } else {
            
            //Updating Cache after it's full
            if (cacheMap.size() == 5) {
                int minVal = Integer.MAX_VALUE;
                int keyToRemove = 0;
                for (int key : cacheMap.keySet()) {
                    int value = cacheMap.get(key);
                    if (value <= minVal) {
                        minVal = value;
                        keyToRemove = key;
                    }

                }
                cacheMap.remove(keyToRemove);
                cacheMap.put(id, 1);

            } else {
                //adding song to cache
                cacheMap.put(id, 1);
            }
        }
        System.out.println("Map: " + cacheMap);
    }
    
    public static void updateLRU(LinkedList<Integer> listCache, int id){
        
        //adding songs to Empty Cache
        if(listCache.isEmpty()){
            listCache.add(id);
        } else {
            
            //Updating cache if song present
            if(listCache.contains(id)){
                int temp = id;
                listCache.remove(new Integer(id));
                listCache.addFirst(temp);
            } else{
                
                //Updating cache after it's full
                if (!listCache.contains(id) && listCache.size() == 5){
                    listCache.removeLast();
                    listCache.addFirst(id);
                } else{
                    
                    //adding to Cache
                    listCache.addFirst(id);
                }
            }
        }
        System.out.println("List: "+ listCache);
        
    }

//Display Table
    public static void display(Queue fifoCache, Stack lifoCache) {

        System.out.println("FIFO\t LIFO\t");
        for (Object s : fifoCache) {
            System.out.println(s);
        }
    }

}
