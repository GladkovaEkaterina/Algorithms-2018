package lesson2;

import kotlin.NotImplementedError;
import kotlin.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

@SuppressWarnings("unused")
public class JavaAlgorithms {
    /**
     * Балда
     * Сложная
     * <p>
     * В файле с именем inputName задана матрица из букв в следующем формате
     * (отдельные буквы в ряду разделены пробелами):
     * <p>
     * И Т Ы Н
     * К Р А Н
     * А К В А
     * <p>
     * В аргументе words содержится множество слов для поиска, например,
     * ТРАВА, КРАН, АКВА, НАРТЫ, РАК.
     * <p>
     * Попытаться найти каждое из слов в матрице букв, используя правила игры БАЛДА,
     * и вернуть множество найденных слов. В данном случае:
     * ТРАВА, КРАН, АКВА, НАРТЫ
     * <p>
     * И т Ы Н     И т ы Н
     * К р а Н     К р а н
     * А К в а     А К В А
     * <p>
     * Все слова и буквы -- русские или английские, прописные.
     * В файле буквы разделены пробелами, строки -- переносами строк.
     * Остальные символы ни в файле, ни в словах не допускаются.
     */
    static ArrayList<String[]> worksheet = new ArrayList<>();

    /**
     * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
     * Простая
     * <p>
     * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
     * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
     * <p>
     * 201
     * 196
     * 190
     * 198
     * 187
     * 194
     * 193
     * 185
     * <p>
     * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
     * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
     * Вернуть пару из двух моментов.
     * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
     * Например, для приведённого выше файла результат должен быть Pair(3, 4)
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public Pair<Integer, Integer> optimizeBuyAndSell(String inputName) {
        throw new NotImplementedError();
    }

    /**
     * Задача Иосифа Флафия.
     * Простая
     * <p>
     * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
     * <p>
     * 1 2 3
     * 8   4
     * 7 6 5
     * <p>
     * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
     * Человек, на котором остановился счёт, выбывает.
     * <p>
     * 1 2 3
     * 8   4
     * 7 6 х
     * <p>
     * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
     * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
     * <p>
     * 1 х 3
     * 8   4
     * 7 6 Х
     * <p>
     * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
     * <p>
     * 1 Х 3
     * х   4
     * 7 6 Х
     * <p>
     * 1 Х 3
     * Х   4
     * х 6 Х
     * <p>
     * х Х 3
     * Х   4
     * Х 6 Х
     * <p>
     * Х Х 3
     * Х   х
     * Х 6 Х
     * <p>
     * Х Х 3
     * Х   Х
     * Х х Х
     */
    static public int josephTask(int menNumber, int choiceInterval) {
        throw new NotImplementedError();
    }

    /**
     * Наибольшая общая подстрока.
     * Средняя
     * <p>
     * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
     * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
     * Если общих подстрок нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     * Если имеется несколько самых длинных общих подстрок одной длины,
     * вернуть ту из них, которая встречается раньше в строке first.
     */
    /*static State[] st = new State [1000000];
    static int size, last;
    static class State {
        int length;
        int link;
        Map<Character, Integer> next;
    }

    static private void sa_init(){
        size = 0;
        last = 0;
        for (int i = 0; i < st.length; i++) {
            st[i] = new State();
            st[i].next = new HashMap<>();
        }
        st[0].length = 0;
        st[0].link = - 1;
        size++;
    }

    static private void sa_extend(char c){
        int cur = size++;
        st[cur].length = st[last].length + 1;
        int p;
        for (p = last; p != -1 && !st[p].next.containsValue(c); p = st[p].link)
            st[p].next.put(c, cur);
        if (p == -1) st[cur].link = 0;
        else {
            int q = st[p].next.get(c);
            if (st[p].length + 1 == st[q].length)
                st[cur].link = q;
            else {
                int clone = size++;
                st[clone].length = st[p].length + 1;
                st[clone].next = st[q].next;
                st[clone].link = st[q].link;
                for(; p != -1 && st[p].next.get(c) == q; p = st[p].link)
                    st[p].next.put(c, clone);
                st[q].link = st[cur].link;
                st[cur].link = clone;
            }
        }
        last = cur;
    }
    static public String longestCommonSubstring(String firs, String second) {
    sa_init();
    for (int i = 0; i < firs.length(); ++i)
        sa_extend(firs.charAt(i));
    int v = 0;
    int l = 0;
    int best = 0;
    int bestPos = 0;
    for (int i = 0; i < second.length(); ++i){
        while (v != 0 && !st[v].next.containsKey(second.charAt(i))){
            v = st[v].link;
            l = st[v].length;
        }
        if (st[v].next.containsValue(second.charAt(i))){
            v = st[v].next.get(second.charAt(i));
            ++l;
        }
        if (l > best) {
            best = l;
            bestPos = i;
        }
    }
    if (bestPos == 0)  return "";
    return firs.substring(bestPos - best + 1, best);
    }*/
    static public String longestCommonSubstring(String firs, String second) {
        int start = 0;
        int m = 0;
        for (int i = 0; i < firs.length(); i++) {
            for (int j = 0; j < second.length(); j++) {
                int x = 0;
                while (firs.charAt(i + x) == second.charAt(j + x)) {
                    x++;
                    if (((i + x) >= firs.length()) || ((j + x) >= second.length())) break;
                }
                if (x > m) {
                    m = x;
                    start = i;
                }
            }
        }
        return firs.substring(start, (start + m));
    }

    /**
     * Число простых чисел в интервале
     * Простая
     * <p>
     * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
     * Если limit <= 1, вернуть результат 0.
     * <p>
     * Справка: простым считается число, которое делится нацело только на 1 и на себя.
     * Единица простым числом не считается.
     */
    static public int calcPrimesNumber(int limit) {
        throw new NotImplementedError();
    }

    static public Set<String> baldaSearcher(String inputName, Set<String> words) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(inputName));
        while (scanner.hasNextLine())
            worksheet.add(scanner.nextLine().split(" "));
        Set<String> res = new HashSet<>();
        for (int i = 0; i < worksheet.size(); i++)
            for (int j = 0; j < worksheet.get(i).length; j++) {
                for (String word : words) {
                    String tst = recBalda(j, i, word);
                    if (tst != null) res.add(tst);
                }
                words.removeAll(res);
                if (words.size() == 0) return res;
            }
        return res;
    }

    static private String recBalda(int x, int y, String word) {
        if (!worksheet.get(y)[x].equals(word.substring(0, 1)))
            return null;
        else if (word.length() == 1)
            return word;
        for (int i = -1; i <= 1; i += 2) {
            int dX = x + i;
            int dY = y + i;
            if (dX >= 0 && dX < worksheet.get(y).length) {
                String res = recBalda(dX, y, word.substring(1));
                if (res != null)
                    return word;
            }
            if (dY >= 0 && dY < worksheet.size()) {
                String res = recBalda(x, dY, word.substring(1));
                if (res != null)
                    return word;
            }
        }
        return null;
    }
}
