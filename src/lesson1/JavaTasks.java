package lesson1;

import kotlin.NotImplementedError;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public class JavaTasks {
    /**
     * Сортировка времён
     * <p>
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     * <p>
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС,
     * каждый на отдельной строке. Пример:
     * <p>
     * 13:15:19
     * 07:26:57
     * 10:00:03
     * 19:56:14
     * 13:15:19
     * 00:40:31
     * <p>
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС. Одинаковые моменты времени выводить друг за другом. Пример:
     * <p>
     * 00:40:31
     * 07:26:57
     * 10:00:03
     * 13:15:19
     * 13:15:19
     * 19:56:14
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortTimes(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    static public void sortAddresses(String inputName, String outputName) throws IllegalAccessException {
        Pattern p = Pattern.compile("^[А-Я][а-я]+ [А-Я][а-я]+ - [А-Я][а-я]+ \\d+$");
        ArrayList<Address> address = new ArrayList<>();
        try {
            Scanner scan = new Scanner(new File(inputName));
            while (scan.hasNextLine()) {
                String st = scan.nextLine();
                if (p.matcher(st).matches()) {
                    String[] t = st.split(" ");
                    address.add(new Address(t[3], Integer.parseInt(t[4]), t[0], t[1]));
                } else throw new IllegalAccessException();
            }
            Collections.sort(address);
            FileWriter writer = new FileWriter(new File(outputName));
            String street = null;
            Integer num = 0;
            boolean flag = false;
            StringBuilder builder = new StringBuilder();
            for (Address a : address) {
                if (street == null || !street.equals(a.street) || !num.equals(a.num)) {
                    if (street != null) {
                        writer.write(builder.append("\n").toString());
                        writer.flush();
                    }
                    street = a.street;
                    num = a.num;
                    builder = new StringBuilder();
                    builder.append(street).append(" ").append(num).append(" - ").append(a.sname)
                            .append(" ").append(a.name);
                } else
                    builder.append(", ").append(a.sname).append(" ").append(a.name);
            }
            writer.write(builder.append("\n").toString());
            writer.flush();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Сортировка температур
     * <p>
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     * <p>
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     * <p>
     * 24.7
     * -12.6
     * 121.3
     * -98.4
     * 99.5
     * -12.6
     * 11.0
     * <p>
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     * <p>
     * -98.4
     * -12.6
     * -12.6
     * 11.0
     * 24.7
     * 99.5
     * 121.3
     */
    static public void sortTemperatures(String inputName, String outputName) throws IOException {
        int min = -2730;
        int max = 5000;
        int size = max - min + 1;
        int[] help = new int[size];
        Scanner scan = new Scanner(new File(inputName));
        while (scan.hasNextLine()) {
            Double d = Double.parseDouble(scan.nextLine()) * 10;
            Integer tmp = d.intValue();
            help[tmp - min]++;
        }
        FileWriter writer = new FileWriter(new File(outputName));
        for (int i = 0; i < size; i++) {
            double tmp = ((double) (i + min) / 10);
            for (int j = 0; j < help[i]; j++) {
                writer.write(Double.toString(tmp) + "\n");
                writer.flush();
            }
        }
        writer.close();
    }

    /**
     * Сортировка последовательности
     * <p>
     * Средняя
     * (Задача взята с сайта acmp.ru)
     * <p>
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     * <p>
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     * <p>
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     * <p>
     * 1
     * 3
     * 3
     * 1
     * 2
     * 2A
     * 2
     */
    static public void sortSequence(String inputName, String outputName) throws IOException {
        TreeMap<Integer, Integer> count = new TreeMap<>();
            Scanner scan = new Scanner(new File(inputName));
        while (scan.hasNextLine()) {
            int t = Integer.valueOf(scan.nextLine());
            if (count.containsKey(t))
                count.put(t, count.get(t) + 1);
            else
                count.put(t, 1);
            }
        int min = count.lastKey();
        int max = -1;
        for (Map.Entry<Integer, Integer> i : count.entrySet()) {
            if (i.getValue() > max) {
                max = i.getValue();
                min = i.getKey();
            } else if (i.getValue() == max && i.getKey() < min) {
                min = i.getKey();
                }
            }
            FileWriter writer = new FileWriter(new File(outputName));
            scan = new Scanner(new File(inputName));
            while (scan.hasNextLine()) {
                String tmp = scan.nextLine();
                if (!tmp.equals(Integer.toString(min)))
                    writer.write(tmp + "\n");
            }
            writer.flush();
            for (int i = 0; i < max; i++)
                writer.write(Integer.toString(min) + "\n");
            writer.close();

    }


    /**
     * Соединить два отсортированных массива в один
     * <p>
     * Простая
     * <p>
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     * <p>
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     * <p>
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     */
    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        int i = 0;
        int j = 0;
        int s = first.length;
        while (i < first.length && s < second.length) {
            if (first[i].compareTo(second[s]) < 1) {
                second[j] = first[i];
                i++;
            } else {
                second[j] = second[s];
                s++;
            }
            j++;
        }
        while (i < first.length) {
            second[j] = first[i];
            i++;
            j++;
        }
    }

    /**
     * Сортировка адресов
     * <p>
     * Средняя
     * <p>
     * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
     * где они прописаны. Пример:
     * <p>
     * Петров Иван - Железнодорожная 3
     * Сидоров Петр - Садовая 5
     * Иванов Алексей - Железнодорожная 7
     * Сидорова Мария - Садовая 5
     * Иванов Михаил - Железнодорожная 7
     * <p>
     * Людей в городе может быть до миллиона.
     * <p>
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     * <p>
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static class Address implements Comparable<Address> {
        String street;
        Integer num;
        String sname;
        String name;

        public Address(String street, Integer num, String sname, String name) {
            this.street = street;
            this.num = num;
            this.sname = sname;
            this.name = name;
        }

        @Override
        public int compareTo(@NotNull Address o) {
            if (street.compareTo(o.street) != 0)
                return street.compareTo(o.street);
            if (num.compareTo(o.num) != 0)
                return num.compareTo(o.num);
            if (sname.compareTo(o.sname) != 0)
                return sname.compareTo(o.sname);
            if (name.compareTo(o.name) != 0)
                return name.compareTo(o.name);
            else
                return 0;
        }
    }
}
