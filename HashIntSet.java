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
 
// Implements a set of integers using a hash table.
// The hash table uses separate chaining to resolve collisions.
public class HashIntSet {
    private static final double MAX_LOAD_FACTOR = 0.75;
    private HashEntry[] elementData;
    private int size;
    
    // Constructs an empty set.
    public HashIntSet() {
        elementData = new HashEntry[10];
        size = 0;
    }
    
    // Adds the given element to this set, if it was not already
    // contained in the set.
    public void add(int value) {
        if (!contains(value)) {
            if (loadFactor() >= MAX_LOAD_FACTOR) {
                rehash();
            }
            
            // insert new value at front of list
            int bucket = hashFunction(value);
            elementData[bucket] = new HashEntry(value, elementData[bucket]);
            size++;
        }
    }
    
   // addAll method that accepts another hashset as parameter and adds all the value 
   // from the other set to the current set
    public void addAll(HashIntSet other) {
      for (int i = 0; i < other.elementData.length; i++) {
         HashEntry current = other.elementData[i];
         while(current!= null) {
            if (!this.contains(current.data)) {
               this.add(current.data);
            }
            current = current.next;
         }
      }

    }
    
   // Exercise 2 - containAll method that accepts another hashset as parameter and return true
   // if your set contains every element from the other set, and false otherwise
    public boolean containAll(HashIntSet other) {
      for (int i = 0; i < other.elementData.length; i++) {
         HashEntry current = other.elementData[i];
         while(current!= null) {
            if (!this.contains(current.data)) {
               return false;
            }
            current = current.next;
         }
      }
      return true;

    }
    
   // Exercise 3 - equals method that accepts another hashset as parameter and return true
   // if the two sets contain the same exact elements
    public boolean equals(HashIntSet other) {
      for (int i = 0; i < other.elementData.length; i++) {
         HashEntry current = other.elementData[i];
         while(current!= null) {
            if (!this.contains(current.data)) {
               return false;
            }
            current = current.next;
         }
      }
      for (int i = 0; i < elementData.length; i++) {
         HashEntry current = elementData[i];
         while(current!= null) {
            if (!other.contains(current.data)) {
               return false;
            }
            current = current.next;
         }
      }
      return true;

    }
    
  
    // Removes all elements from the set.
    public void clear() {
        for (int i = 0; i < elementData.length; i++) {
            elementData[i] = null;
        }
        size = 0;
    }
    
    // Returns true if the given value is found in this set.
    public boolean contains(int value) {
        int bucket = hashFunction(value);
        HashEntry current = elementData[bucket];
        while (current != null) {
            if (current.data == value) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    
    // Returns true if there are no elements in this queue.
    public boolean isEmpty() {
        return size == 0;
    }
    
    // Removes the given value if it is contained in the set.
    // If the set does not contain the value, has no effect.
    public void remove(int value) {
        int bucket = hashFunction(value);
        if (elementData[bucket] != null) {
            // check front of list
            if (elementData[bucket].data == value) {
                elementData[bucket] = elementData[bucket].next;
                size--;
            } else {
                // check rest of list
                HashEntry current = elementData[bucket];
                while (current.next != null && current.next.data != value) {
                    current = current.next;
                }
                
                // if the element is found, remove it
                if (current.next != null && current.next.data == value) {
					current.next = current.next.next;
					size--;
				}
            }
        }
    }
    
    // Returns the number of elements in the queue.
    public int size() {
        return size;
    }
    
    // Returns a string representation of this queue, such as "[10, 20, 30]";
    // The elements are not guaranteed to be listed in sorted order.
    public String toString() {
        String result = "[";
        boolean first = true;
        if (!isEmpty()) {
            for (int i = 0; i < elementData.length; i++) {
                HashEntry current = elementData[i];
                while (current != null) {
                    if (!first) {
                        result += ", ";
                    }
                    result += current.data;
                    first = false;
                    current = current.next;
                }
            }
        }
        return result + "]";
    }
    
    
    // Returns the preferred hash bucket index for the given value.
    private int hashFunction(int value) {
        return Math.abs(value) % elementData.length;
    }
    
    private double loadFactor() {
        return (double) size / elementData.length;
    }
    
    // Resizes the hash table to twice its former size.
    private void rehash() {
        // replace element data array with a larger empty version
        HashEntry[] oldElementData = elementData;
        elementData = new HashEntry[2 * oldElementData.length];
        size = 0;

        // re-add all of the old data into the new array
        for (int i = 0; i < oldElementData.length; i++) {
            HashEntry current = oldElementData[i];
            while (current != null) {
                add(current.data);
                current = current.next;
            }
        }
    }
    
    // Represents a single value in a chain stored in one hash bucket.
    private class HashEntry {
        public int data;
        public HashEntry next;

        public HashEntry(int data) {
            this(data, null);
        }

        public HashEntry(int data, HashEntry next) {
            this.data = data;
            this.next = next;
        }
    }
}
