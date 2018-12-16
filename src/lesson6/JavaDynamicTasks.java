package lesson6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     * <p>
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     * <p>
     * трудоемкость O(nm), ресурсоемкость O(nm)
     */
    public static String longestCommonSubSequence(String first, String second) {
        int[][] max_len = new int[first.length() + 1][second.length() + 1];
        for (int i = 0; i < first.length(); i++)
            for (int j = 0; j < second.length(); j++)
                if (first.charAt(i) == second.charAt(j))
                    max_len[i + 1][j + 1] = 1 + max_len[i][j];
                else
                    max_len[i + 1][j + 1] = Math.max(max_len[i + 1][j], max_len[i][j + 1]);
        StringBuilder result = new StringBuilder();
        for (int i = first.length() - 1, j = second.length() - 1; i >= 0 && j >= 0; ) {
            if (first.charAt(i) == second.charAt(j)) {
                result.insert(0, first.charAt(i));
                i--;
                j--;
            } else if (max_len[i + 1][j] > max_len[i][j + 1])
                j--;
            else
                i--;
        }
        return result.toString();
    }

    /**
     * Наибольшая возрастающая подпоследовательность
     * Средняя
     * <p>
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     */
    //РЕШЕНИЕ ЭТОГО ЗАДАНИЯ В ПРОЦЕССЕ, пока что не отправляю его
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) {
        if (list.size() < 2) return list;
        int n = list.size();
        int[] prev = new int[n];
        int[] d = new int[n];
        for (int i = 0; i < n; i++) {
            d[i] = 1;
            prev[i] = -1;
            for (int j = 0; j < i; j++)
                if (list.get(j) < list.get(i))
                    if (1 + d[j] > d[i]) {
                        d[i] = d[j] + 1;
                        prev[i] = j;
                    }
        }
        int pos = 0;                            // индекс последнего элемента НВП
        int length = d[0];                      // длина НВП
        for (int i = 0; i < n; i++)
            if (d[i] > length) {
                pos = i;
                length = d[i];
            }
        // восстановление ответа
        ArrayList<Integer> answer = new ArrayList<>();
        while (pos != -1) {
            answer.add(list.get(pos));
            pos = prev[pos];
        }
        Collections.reverse(answer);

        return answer;
    }

    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Сложная
     * <p>
     * В файле с именем inputName задано прямоугольное поле:
     * <p>
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     * <p>
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     * <p>
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     * <p>
     * трудоемкость O(nm), где n, m — размеры строк, ресурсое мкость O(nm)
     */
    public static int shortestPathOnField(String inputName) throws FileNotFoundException {
        ArrayList<int[]> desk = new ArrayList<>();
        Scanner sc = new Scanner(new File(inputName));
        int r = 0;
        while (sc.hasNextLine()) {
            String[] nums = sc.nextLine().split(" ");
            desk.add(new int[nums.length]);
            int i = 0;
            for (String n : nums) {
                desk.get(r)[i++] = Integer.valueOf(n);
            }
            r++;
        }
        int[][] work = new int[desk.size()][desk.get(0).length];
        for (int y = desk.size() - 1; y >= 0; y--) {
            for (int x = desk.get(0).length - 1; x >= 0; x--) {
                boolean dx = x + 1 < desk.get(0).length, dy = y + 1 < desk.size();
                int min = -1;
                if (dx && dy)
                    min = work[y + 1][x + 1];
                if (dx && (min > work[y][x + 1] || min == -1))
                    min = work[y][x + 1];
                if (dy && (min > work[y + 1][x] || min == -1))
                    min = work[y + 1][x];
                if (min != -1)
                    work[y][x] = desk.get(y)[x] + min;
            }
        }
        return work[0][0];
    }

    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
}
