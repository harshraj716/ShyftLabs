import java.util.Scanner;

public class IndexFind{
    public static int findIndex(int[] arr) {
        int left = 0;
        int right = 1;
        
        while (arr[right] != -1) {
            left = right;
            right *= 2;
        }
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == -1) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input: ");
        String input = scanner.nextLine();

        String[] elements = input.split(", ");
        int[] arr = new int[elements.length];
        for (int i = 0; i < elements.length; i++) {
            arr[i] = Integer.parseInt(elements[i]);
        }

        int Index = findIndex(arr);
        System.out.println("Output: " + Index);
    }
}
