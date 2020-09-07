import java.io.InputStream;
import java.lang.*;
import java.util.Scanner;

public class Main {

    int _arraySize;
    int _array[];

    public void readInput(Scanner scanner) {
        _arraySize= scanner.nextInt();
        _array = new int[_arraySize];
        for (int i = 0; i < _arraySize; i++) {
            _array[i] = scanner.nextInt();
        }
    }

    public void printAll() {
        System.out.println();
        for (int i = 0; i < _arraySize; i++) {
            System.out.print(_array[i] + " ");
        }
    }

    public void heap_sort(int array[]) {
        int n = array.length;

        build_max_heap(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        heap_extract_max(array, n);
    }

    public void build_max_heap(int array[]) {
        int n = array.length;
//        for (int i = Math.floorDiv(n, 2); i > -1; i--) {
//            heapfy(array, n, i);
//        }
        for (int i = n/2 -1; i>=0; i--)  {

            printAll();
            System.out.print("pa ");
            heapfy(array, n, i);
            printAll();
            System.out.print("hp ");
        }
    }

    public void heapfy(int[] array, int n, int i) {
        int max = i;
        int left = 2 * i + 1;
        int right = left + 1;

        if (left < n && array[left] > array[max]) {
            max = left;
        }
        if (right < n && array[right] > array[max])
            max = right;

        if (max != i)
        {
            swap(array, i, max);

            heapfy(array, n, max);
        }
    }

    public static void swap(int[] array, int i, int max){
        int temp = array[i];
        array[i] = array[max];
        array[max] = temp;
    }

    public void heap_extract_max(int array[], int n) {
        for (int i = n - 1; i >= 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            heapfy(array, i, 0);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main main = new Main();

        main.readInput(scanner);

        main.heap_sort(main._array);

        main.printAll();
    }
}
