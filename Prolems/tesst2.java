import java.io.*;
import java.util.*;


public class tesst2 {


    public static void swap(int[] array, int a , int b) {
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp ;
    }

    public static int partition(int[] a , int l , int r) {
        int pivot = a[(l + r) / 2]; // pivot ở giữa
        int i = l;
        int j = r;
        while (i <= j) {
            while (a[i] < pivot) {
                i++;
            }
            while (a[j] > pivot) {
                j--;
            }
            if (i <= j) {
                swap(a, i, j);
                i++;
                j--;
            }
        }
        return i; // vị trí chia mảng
    }

    public static void quickSort(int[] a , int l , int r ) {
        if (l >= r) return;

        int index = partition(a, l, r);

        quickSort(a, l, index - 1);
        quickSort(a, index, r);
    }

    public static void main(String[] args) {
        int[] a = new int[10];
        Random r = new Random();
        for (int i = 0 ; i < a.length ; i++) {
            a[i] = r.nextInt(10);
        }
        for (int x : a) {
            System.out.print(x + " ");
        }
        System.out.println(" ");
        quickSort(a, 0 , a.length - 1);
        for (int x : a) {
            System.out.print(x + " ");
        }
    }
}
