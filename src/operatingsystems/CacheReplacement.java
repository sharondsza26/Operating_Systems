package operatingsystems;
import java.util.*;  
import java.util.Queue;
import java.util.HashMap;

public class CacheReplacement {
    public static void main(String args[]) {
        HashMap<Integer,String> songMap = new HashMap<Integer,String>();
        Queue<Integer> fifoCache = new LinkedList<>();
        populate(songMap);
        updateFifo(fifoCache,4);


    }

    public static void populate(HashMap<Integer,String> songMap) {
        int index=1;
        songMap.put(index++,"Song1");
        songMap.put(index++,"Song2");
        songMap.put(index++,"Song3");
        songMap.put(index++,"Song4");
        songMap.put(index++,"Song5");
        songMap.put(index++,"Song6");
        songMap.put(index++,"Song7");
        songMap.put(index++,"Song8");
        songMap.put(index++,"Song9");
        songMap.put(index++,"Song10");
        for(Integer i:songMap.keySet()){
        System.out.println(i);
        }
    }

   


   public static void updateFifo(Queue fifoCache,int m){
       for(int i=0;i<5;i++){
        fifoCache.add(i);
   }
        System.out.println("FIFO CACHE \n" + fifoCache);
        
        if (fifoCache.size()==5){
            fifoCache.remove();
            fifoCache.add(m);
            System.out.println("Updated"+ fifoCache);  
        }
    }

}