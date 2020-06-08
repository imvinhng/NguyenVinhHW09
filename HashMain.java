/*
 * ==========================================
 * CS211, Spring 2020, 6/7
 * Vinh T. Nguyen
 * Homework 09 - Chap 18 EX 1,2, 3, 9, 11
 * Exercise 1 - addAll method that accepts another hashset as parameter and adds all the value 
 * from the other set to the current set
 * Exercise 2 - containAll method that accepts another hashset as parameter and return true
 * if your set contains every element from the other set, and false otherwise
 * Exercise 3 - equals method that accepts another hashset as parameter and return true
 * if the two sets contain the same exact elements
 * Exercise 9 - kthSmallest method that accepts a PriorityQueue of integers and an integer k as parameter
 * and return the fourth-smallest integer from PriorityQueue, or else
 * throw an IllegalArgumentException if k is equal or less than 0, or greater than the size of the queue
 * Exercise 11 - stutter method that accepts a PriorityQueue of integers as parameter and replace
 * every value with two occurence of that value.
 * ==========================================
 */
 
// This client program tests our hash set of integers
// by adding and removing several elements from it.

import java.util.*;

public class HashMain {
    public static void main(String[] args) {
        HashIntSet set = new HashIntSet();
        set.add(7);
        set.add(5);
        set.add(1);
        set.add(9);
        
        HashIntSet set2 = new HashIntSet();
        set2.add(-7);
        set2.add(5);
        set2.add(-1);
        set2.add(9);
        
        PriorityQueue pq = new PriorityQueue<>();
        pq.add(-7);
        pq.add(5);
        pq.add(-1);
        pq.add(9);
        
        // test adding duplicates
        set.add(7);
        set.add(7);
        set.add(5);
        
        // test collisions and linked lists
        set.add(45);
        set.add(91);
        set.add(71);
        
        // test rehashing
        set.add(810);   // re-hash should occur here (8/10, load = 0.8)
        set.add(62);
        set.add(1238);
        set.add(-99);
        set.add(-30);
        set.add(42);
        set.add(49857);
        set.add(1520);  // re-hash should occur here (15/20, load = 0.75)
        set.add(2);
        set.add(31);
        set.add(11);
        set.add(21);
        
        // test removal
        set.remove(7);
        set.remove(9);
        set.remove(1);
        set.remove(1);
        set.remove(21);
        set.remove(71);
        
        for (int n : new int[] {1520, 99, 2, 55, 42, 11, 45, 0, 5, -10, 810}) {
			System.out.println("contains " + n + "? " + set.contains(n));
		}
        
        System.out.println(set + ", size " + set.size());
        System.out.println(set2 + ", size " + set2.size());
        
      // Exercise 1 
        set.addAll(set2);
        System.out.println(set + ", size " + set.size());
        System.out.println(set2 + ", size " + set2.size() +"\n");
        
      // Exercise 2 
      System.out.println(set.containAll(set2) +"\n"); //true
      
       // Exercise 3
      System.out.println(set.equals(set2) +"\n"); //false
      
    // Exercise 9 
       System.out.println("The kth smallest number: "+kthSmallest(pq, 2) +"\n"); //-1
      
    // Exercise 11 
      System.out.println(pq);
      stutter(pq);
      System.out.println(pq);

    }
    
    
    // Exercise 9 method
    public static Object kthSmallest(PriorityQueue pq, int index) {
      Object[] arr = pq.toArray();
      
      Arrays.sort(arr);
      
      return arr[index-1];
    }
    
   // Exercise 11 method
    public static void stutter(PriorityQueue pq) {
      Object[] arr = pq.toArray();
      pq.clear();
      for (int j = 0; j < arr.length; j++) {
         pq.add(arr[j]);
         pq.add(arr[j]);
      }
    }
    
    
}
