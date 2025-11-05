package no.ntnu.idata2302.lab04;

import java.util.ArrayList;

public class Heap {

  public static Heap fromValues(int... values) {
    var heap = new Heap();
    for (var each : values) {
      heap.insert(each);
    }
    return heap;
  }

  private ArrayList<Integer> array;

  public Heap() {
    array = new ArrayList<Integer>();
    array.add(0);
  }

  public void insert(Integer k) {
    array.add(k);
    int index = array.size() - 1;
    while (index > 1 && array.get(parentOf(index)) > array.get(index)) {
      swap(parentOf(index), index);
      index = parentOf(index);
    }
  }

  /**
   * Returns the minimum value in the heap and removes it from the heap.
   *
   * @return the minimum value
   */
  public int takeMinimum() {
    int min = array.get(1);
    array.set(1, array.get(array.size() - 1));
    array.remove(array.size() - 1);
    int currentIndex = 1;
    while (leftChildOf(currentIndex) < array.size()) {
      int smallestChildIndex = leftChildOf(currentIndex);
      // Check if right child exists AND is smaller
      if (rightChildOf(currentIndex) < array.size()
          && array.get(rightChildOf(currentIndex)) < array.get(leftChildOf(currentIndex))) {
        smallestChildIndex = rightChildOf(currentIndex);
      }
      // If currentIndex is smaller than smallest child
      if (array.get(currentIndex) <= array.get(smallestChildIndex)) {
        break;
      }
      // Otherwise, swap and continue
      swap(currentIndex, smallestChildIndex);
      currentIndex = smallestChildIndex;
    }
    return min;
  }

  public void decreaseKey(int i, int k) {
    if (k >= array.get(i)) {
      throw new IllegalArgumentException("New key is larger or equal to current key");
    }
    array.set(i, k);
    while (i > 1 && array.get(parentOf(i)) > array.get(i)) {
      swap(parentOf(i), i);
      i = parentOf(i);
    }
  }

  private int parentOf(int index) {
    return index / 2;
  }

  private int leftChildOf(int index) {
    return index * 2;
  }

  private int rightChildOf(int index) {
    return index * 2 + 1;
  }

  void swap(int pos1, int pos2) {
    int temp = array.get(pos1);
    array.set(pos1, array.get(pos2));
    array.set(pos2, temp);
  }
}
