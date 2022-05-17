package com.epam.task8.util.searcher_prime_number;

import com.epam.task8.util.searcher_prime_number.calculate_part_strategy.AbsoluteUniformSeparation;
import com.epam.task8.util.searcher_prime_number.calculate_part_strategy.UniformSeparation;
import com.epam.task8.util.searcher_prime_number.collecting_strategy.GeneralCollecting;
import com.epam.task8.util.searcher_prime_number.collecting_strategy.SeparateCollecting;
import com.epam.task8.util.searcher_prime_number.impl.ParallelSearcherPNBaseImpl;
import com.epam.task8.util.searcher_prime_number.impl.ParallelSearcherPNExecutorsImpl;

import java.util.List;
import java.util.Scanner;


public class Demo {
    private static final String DELIMITER = "*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Поиск простых чисел внутри заданного диапазона.\nВведите интервал поиска чисел\nот: ");
        long from = Long.parseLong(scanner.nextLine());
        System.out.print("до: ");
        long to = Long.parseLong(scanner.nextLine());
        System.out.printf("Количество доступных процессоров: %d%n", Runtime.getRuntime().availableProcessors());
        System.out.print("Введите число потоков: ");
        int numberOfThreads = Integer.parseInt(scanner.nextLine());

        System.out.printf("%nОтчет паралельного поиска простых чисел в диапазоне [%d, %d], с количеством потоков (%d):%n", from, to, numberOfThreads);

        System.out.print(DELIMITER);

        testParallelSearcherPN(new ParallelSearcherPNBaseImpl(from, to, numberOfThreads, new UniformSeparation(), new GeneralCollecting()),
                "\nБазовая реализация (стратегия сохранения чисел в общую коллекцию сразу по их нахождению):");
        testParallelSearcherPN(new ParallelSearcherPNBaseImpl(from, to, numberOfThreads, new AbsoluteUniformSeparation(), new GeneralCollecting()),
                "Базовая реализация (модифицированное деление) (хороший вариант для числа степени двойки потоков):");

        System.out.print(DELIMITER);

        testParallelSearcherPN(new ParallelSearcherPNBaseImpl(from, to, numberOfThreads, new UniformSeparation(), new SeparateCollecting()),
                "\nБазовая реализация (стратегия сохранения найденых чисел в отдельные коллекции каждого потока,\nс последующим групированием их содержимого в общую коллекцию):");
        testParallelSearcherPN(new ParallelSearcherPNBaseImpl(from, to, numberOfThreads, new AbsoluteUniformSeparation(), new SeparateCollecting()),
                "Базовая реализация (модифицированное деление) (хороший вариант для числа степени двойки потоков):");

        System.out.print(DELIMITER);

        testParallelSearcherPN(new ParallelSearcherPNExecutorsImpl(from, to, numberOfThreads, new AbsoluteUniformSeparation()),
                "\nРеализация с использованием Executor’ов (стратегия сохранения в отдельные коллекции):");

        scanner.close();
    }

    private static void testParallelSearcherPN(ParallelSearcherPrimeNumbers searcherPN, String msg) {
        System.out.println(msg);
        long beforeTime = System.currentTimeMillis();
        List<Long> result = searcherPN.findAllInRange();
        System.out.println("Время поиска: " + (System.currentTimeMillis() - beforeTime));
        System.out.println("Количество найденых простых чисел: " + result.size());
        // System.out.println("Результат: " + result);
    }
}
