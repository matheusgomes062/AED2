import java.util.Scanner;

public class Exponentiate {
    static int exponentiation(int x, int n) {
        if(n == 0) {
            return 1;
        } else {
            return x * exponentiation(x, n - 1);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        Scanner inAgain = new Scanner(System.in);
        int exp = inAgain.nextInt();
        Exponentiate exponentiate = new Exponentiate();
        System.out.println(exponentiate.exponentiation(num, exp));
    }
}
