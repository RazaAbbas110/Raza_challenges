import java.util.Scanner;

public class Challenge2 {
    public static Character foo(String s) {
        s = s.toLowerCase();
        int[] freq = new int[26];
        int[] occur = new int[26];

        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            freq[c - 'a']++;
            if (occur[c - 'a'] == 0) {
                occur[c - 'a'] = i + 1;
            }
        }

        int max_freq = 0, max_occur = Integer.MAX_VALUE, f;
        Character res = 'a';
        for (int i = 0; i < 26; i++) {
            f = freq[i];
            if (f > 0 && f > max_freq || (f == max_freq && occur[i] < max_occur)) {
                max_freq = f;
                max_occur = occur[i];
                res = (char) ('a' + i);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter String: ");
        String s = in.next();
        System.out.println("Output: " + foo(s));

        in.close();
    }
}
