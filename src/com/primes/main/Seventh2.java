package com.primes.main;

import java.util.Arrays;

/**
 * Оптимизированное решето Эратосфена (числа вычёркиваются только от p^2).
 * 
 * @author bratishka
 * @version 1.0
 */
public class Seventh2 implements Runnable {
	// Максимальное число для проверки
	long max;
	// Массив найденных простых чисел, которые представляются индексами массива
	boolean[] nums;

	public Seventh2(long max) {
		this.max = max;
		if (max > Integer.MAX_VALUE) {
			this.nums = new boolean[Integer.MAX_VALUE];
		}
		this.nums = new boolean[(int) max + 1]; // Для простоты будем учитывать в массиве первый 0
		// В конце этот ноль ликвидируем
		Arrays.fill(this.nums, true);
	}

	@Override
	public void run() {
		int steps = 0; // Счётчик выполненных шагов
		int counter = 3; // Количество найденных простых чисел (уже учтено уже 0, 1, 2)

		long start = System.currentTimeMillis();

		// Для исключения лишних проверок в основном цикле, отдельно обработаем
		// единственное простое чётное число "2"
		for (int i = 4; i <= max; i = i + 2) {
			steps++;
			nums[i] = false;
		}

		// Проверка нечётных чисел с шагом 2 * i
		for (int i = 3; i <= max; i++) {
			if (nums[(int) i] == true) {
				counter++; // Каждое первое true - простое число
				steps++;

				// Переполнения не боимся - достаточно вычислить квадрат, чтобы понять
				// что инвариант цикла не выполняется (для int переполнение случится для
				// 46341^2). Если результат i * i будет больше Integer.MAX_VALUE, то он заведомо
				// будет больше max
				// (даже если max = Integer.MAX_VALUE). Нас не будет интересовать уже из-за
				// ограничений языка программирования (или добро пожаловать к BigInteger)

				// Продвижение типов в выражении (от int до long)
				for (long j = (long) i * i; j <= max; j = j + 2 * i) {
					nums[(int) j] = false;
					steps++;
				}
			} else {
				steps++;
			}
		}

		long elapsed = System.currentTimeMillis() - start;

		int[] res = new int[counter - 3]; // Исключим 0, 1, 2 в начале для равенства списков
		int currentPrime = 0;
		for (int i = 3; i < nums.length; i++) { // Здесь 3 - не забыли первый 0
			if (nums[i] == true) {
				res[currentPrime++] = i;
			}
		}

		Utils.arrayInfo(res, elapsed, counter - 3, steps);
	}
}
