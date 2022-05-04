import java.util.Scanner;

public class Challenge1 {
    // RETURN NTH NUMBER IN THE FIBONACCI SEQUENCE
    public static int nFiboSeq(int n) {
        int a = 0, b = 1;
        if (n == 1) {
            return a;
        }
        for (int i = 3; i <= n; i++) {
            b = a + b;
            a = b - a;
        }
        return b;
    }

    // PRINT IF F IS A FIBONACCI NUMBER
    // PRINT CLOSEST INDEX IN FIIBONACCI SEQUENCE TO N
    public static void closestFibIndex(int F) {
        int i;
        for (i = 1; nFiboSeq(i) < F; i++) {
        }
        if (nFiboSeq(i) == F) {
            System.out.println(F + " is a Fibonacci Number");
            System.out.println("Index: " + i);
        } else {
            System.out.println(F + " is a not Fibonacci Number");
            int index = nFiboSeq(i) - F < F - nFiboSeq(i - 1) ? i : i - 1;
            System.out.println("Index: " + index);
        }
    }

    // DRIVER PROGRAM
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter n: ");
        int n = in.nextInt();
        System.out.println("nth number in Fibonacci Sequence: " + nFiboSeq(n));

        System.out.print("Enter F: ");
        int F = in.nextInt();
        closestFibIndex(F);

        in.close();
    }
}
