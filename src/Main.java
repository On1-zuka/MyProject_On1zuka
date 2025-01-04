import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Введите размерность массива:");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println("Размер массива = " + n);

        int[] array = GenerateRandomArray(n);
        System.out.println("Массив случайных чисел:");
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();

        System.out.println("Массив после сортировки выбором (по возрастанию):");
        int[] selectionSortedArray = SelectionMethodSortedArray(array.clone());
        for (int num : selectionSortedArray) {
            System.out.print(num + " ");
        }
        System.out.println();

        System.out.println("Массив после сортировки выбором (по убыванию):");
        int[] selectionSortedArrayReverse = SelectionMethodSortedArrayReverse(array.clone());
        for (int num : selectionSortedArrayReverse) {
            System.out.print(num + " ");
        }
        System.out.println();

        System.out.println("Массив после пирамидальной сортировки (по возрастанию):");
        int[] heapSortedArray = HeapSortAscending(array.clone());
        for (int num : heapSortedArray) {
            System.out.print(num + " ");
        }
        System.out.println();

        System.out.println("Массив после пирамидальной сортировки (по убыванию):");
        int[] heapSortedArrayReverse = HeapSortDescending(array.clone());
        for (int num : heapSortedArrayReverse) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    static int[] GenerateRandomArray(int n) {
        int[] arr = new int[n];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100);
        }
        return arr;
    }

    static int[] SelectionMethodSortedArray(int[] arr) {
        int comparisons = 0;
        int swaps = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                comparisons++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = temp;
                swaps++;
            }
        }

        System.out.println("Количество сравнений (по возрастанию): " + comparisons);
        System.out.println("Количество перестановок (по возрастанию): " + swaps);
        return arr;
    }

    public static int[] SelectionMethodSortedArrayReverse(int[] arr) {
        int comparisons = 0;
        int swaps = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                comparisons++;
                if (arr[j] > arr[maxIndex]) {
                    maxIndex = j;
                }
            }
            if (maxIndex != i) {
                int temp = arr[maxIndex];
                arr[maxIndex] = arr[i];
                arr[i] = temp;
                swaps++;
            }
        }

        System.out.println("Количество сравнений (по убыванию): " + comparisons);
        System.out.println("Количество перестановок (по убыванию): " + swaps);
        return arr;
    }

    public static int[] HeapSortAscending(int[] arr) {
        int n = arr.length;
        int swaps = 0;
        int comparisons = 0;


        for (int i = n / 2 - 1; i >= 0; i--) {
            swaps += heapify(arr, n, i, true);
            comparisons += n;
        }

        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            swaps++;

            swaps += heapify(arr, i, 0, true);
            comparisons += i;
        }

        System.out.println("Количество сравнений (по возрастанию): " + comparisons);
        System.out.println("Количество перестановок (по возрастанию): " + swaps);
        return arr;
    }

    public static int[] HeapSortDescending(int[] arr) {
        int n = arr.length;
        int swaps = 0;
        int comparisons = 0;

        for (int i = n / 2 - 1; i >= 0; i--) {
            swaps += heapify(arr, n, i, false);
            comparisons += n;
        }

        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            swaps++;

            swaps += heapify(arr, i, 0, false);
            comparisons += i;
        }

        System.out.println("Количество сравнений (по убыванию): " + comparisons);
        System.out.println("Количество перестановок (по убыванию): " + swaps);
        return arr;
    }

    private static int heapify(int[] arr, int n, int i, boolean ascending) {
        int swaps = 0;
        int largestOrSmallest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (ascending) {
            if (left < n && arr[left] > arr[largestOrSmallest]) {
                largestOrSmallest = left;
            }
            if (right < n && arr[right] > arr[largestOrSmallest]) {
                largestOrSmallest = right;
            }
        } else {
            if (left < n && arr[left] < arr[largestOrSmallest]) {
                largestOrSmallest = left;
            }
            if (right < n && arr[right] < arr[largestOrSmallest]) {
                largestOrSmallest = right;
            }
        }

        if (largestOrSmallest != i) {
            int temp = arr[i];
            arr[i] = arr[largestOrSmallest];
            arr[largestOrSmallest] = temp;
            swaps++;

            swaps += heapify(arr, n, largestOrSmallest, ascending);
        }
        return swaps;
    }
}
