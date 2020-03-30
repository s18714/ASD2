import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SmallestFinder {

    public static void mergeSort(List<String> numbers, int l, int r) {
        int m = (l + r) / 2;
        if (l < r) {
            mergeSort(numbers, l, m);
            mergeSort(numbers, m + 1, r);
            merge(numbers, l, m, r);
        }
    }

    public static void merge(List<String> numbers, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        String[] L = new String[n1];
        String[] R = new String[n2];

        for (int i = 0; i < n1; i++)
            L[i] = numbers.get(l + i);

        for (int j = 0; j < n2; j++)
            R[j] = numbers.get(m + 1 + j);

        int i = 0;
        int j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (compare(L[i], R[j]) < 0) {
                numbers.set(k, L[i]);
                i++;
            } else {
                numbers.set(k, R[j]);
                j++;
            }
            k++;
        }

        while (i < n1) {
            numbers.set(k++, L[i++]);
        }

        while (j < n2) {
            numbers.set(k++, R[j++]);
        }
    }

    public static int compare(String s1, String s2) {
        String tmp1 = s1 + s2;
        String tmp2 = s2 + s1;
        int i = 0;
        while (i < tmp1.length()) {
            if (tmp1.charAt(i) != tmp2.charAt(i))
                return tmp1.charAt(i) - tmp2.charAt(i);
            i++;
        }
        return 0;
    }

    public static void main(String[] args) {
        try {
            List<String>[] listOfNumbers = new ArrayList[10];
            StringBuilder sb = new StringBuilder();

            for (int i = 1; i < 10; i++)
                listOfNumbers[i] = new ArrayList<>();

            Scanner scanner = new Scanner(new File(System.getProperty("user.dir") + "/liczby.txt"));

            String tmp;
            while (scanner.hasNext()) {
                tmp = scanner.next();
                listOfNumbers[tmp.charAt(0) - 48].add(tmp);
            }

            for (int i = 1; i < listOfNumbers.length; i++) {
                if (listOfNumbers[i].size() > 1) {
                    mergeSort(listOfNumbers[i], 0, listOfNumbers[i].size() - 1);
                }
                if (listOfNumbers[i].size() > 0) {
                    for (int j = 0; j < listOfNumbers[i].size(); j++) {
                        sb.append(listOfNumbers[i].get(j)).append("_");
                    }
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            System.out.println(sb);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}