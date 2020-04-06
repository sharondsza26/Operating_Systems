package operatingsystems;

import java.util.*;
import java.util.Queue;
import java.util.HashMap;
import java.util.Scanner;

public class CacheReplacement {

    public static void main(String args[]) {
        System.out.println("*************************************************************************************************");
        System.out.println("*                            WELCOME TO MEMORY CACHING                                          *");
        System.out.println("*************************************************************************************************");
        System.out.println("");

        int id = 1;
        Scanner sc = new Scanner(System.in);
        HashMap<Integer, String> songMap = new HashMap<>();
        HashMap<Integer, Integer> lfuCache = new HashMap<>();
        LinkedList<Integer> lruCache = new LinkedList<>();
        ArrayList<Integer> randomCache = new ArrayList<>();
        Queue<Integer> fifoCache = new LinkedList<>();
        Stack<Integer> lifoCache = new Stack<>();
        populate(songMap);
        System.out.println("Select the song number of songs you want to play");
        System.out.println("Enter -1 to exit");
        id = sc.nextInt();

        //User input
        while (id != -1) {

            String fifofault = updateFifo(fifoCache, id);
            String lifofault = updateLifo(lifoCache, id);
            String lfufault = updateLFU(lfuCache, id);
            String lrufault = updateLRU(lruCache, id);
            String randomfault = updateRandom(randomCache, id);

            displayCache(fifoCache, "FIFO", fifofault);
            displayCache(lifoCache, "LIFO", lifofault);
            displayCache(lfuCache.keySet(), "LFU", lfufault);
            displayCache(lruCache, "LRU", lrufault);
            displayCache(randomCache, "RANDOM", randomfault);

            System.out.println("Select the song number of songs you want to play");
            System.out.println("Enter -1 to exit");
            id = sc.nextInt();
        }
        
        System.out.println("*************************************************************************************************");
        System.out.println("*                                         THANK YOU!                                            *");
        System.out.println("*************************************************************************************************");
        System.out.println("");

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
        System.out.println("\tSong PlayList :");
        System.out.println("\t--------------------------------");
        System.out.println("\t|    Song No. \t| Song Name\t|");
        System.out.println("\t--------------------------------");
        songMap.forEach((k, v) -> System.out.println("\t|\t" + k + "  \t|  " + v + "\t|"));
        System.out.println("\t--------------------------------");

        System.out.println("");
    }

    public static String updateFifo(Queue fifoCache, int id) {
        String fault = "MISS";

        //Updating Cache
        if (fifoCache.contains(id)) {
            fault = "HIT";
        } else {
            if (fifoCache.size() == 5) {
                fifoCache.remove();
            }
            fifoCache.add(id);
            fault = "MISS";
        }
        return fault;

    }

    public static String updateLifo(Stack lifoCache, int id) {
        String fault = "MISS";

        //Updating Cache
        if (lifoCache.contains(id)) {
            fault = "HIT";
        } else {
            if (lifoCache.size() == 5) {
                lifoCache.pop();
            }
            lifoCache.push(id);
            fault = "MISS";
        }
        return fault;

    }

    public static String updateLFU(HashMap<Integer, Integer> cacheMap, int id) {

        String fault = "MISS";
        //Updating cache if song present
        if (cacheMap.containsKey(id)) {
            int newVal = cacheMap.get(id) + 1;
            cacheMap.put(id, newVal);
            fault = "HIT";
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
        return fault;
    }

    public static String updateLRU(LinkedList<Integer> listCache, int id) {

        String fault = "MISS";
        //adding songs to Empty Cache
        if (listCache.isEmpty()) {
            listCache.add(id);
        } else {

            //Updating cache if song present
            if (listCache.contains(id)) {
                int temp = id;
                listCache.remove(new Integer(id));
                listCache.addFirst(temp);
                fault = "HIT";
            } else {

                //Updating cache after it's full
                if (!listCache.contains(id) && listCache.size() == 5) {
                    listCache.removeLast();
                    listCache.addFirst(id);
                } else {

                    //adding to Cache
                    listCache.addFirst(id);
                }
            }
        }
        return fault;
    }

    public static String updateRandom(ArrayList<Integer> arrayCache, int id) {

        String fault = "MISS";

        if (arrayCache.contains(id)) {
            fault = "HIT";
        } //Updating cache after it's full
        else {
            if (arrayCache.size() == 5) {
                Random random = new Random();
                int removeID = random.nextInt(arrayCache.size());
                arrayCache.remove(removeID);
            }
            //adding cache
            arrayCache.add(id);
            fault = "MISS";
        }
        return fault;
    }

    //Display Table
    public static void displayCache(Collection cache, String cacheName, String fault) {

        System.out.println(cacheName + "\t" + cache + "\t" + fault);
    }
}
