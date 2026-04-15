import java.util.*;

public class Quicksortq1 {

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        int[] arr = new int[n];
        Random rand = new Random();

        // Generate random inputs
        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(); // random numbers
        }

        long start = System.currentTimeMillis();

        quickSort(arr, 0, n - 1);

        long end = System.currentTimeMillis();

        System.out.println("Execution Time: " + (end - start) + " ms");

        sc.close();
    }
}