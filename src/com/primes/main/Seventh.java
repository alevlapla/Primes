package com.primes.main;

import java.util.Arrays;

/**
 * Неоптимизированное решето Эратосфена (вычёркиваются все числа, а не только
 * после p^2).
 * 
 * @author bratishka
 * @version 1.0
 */
public class Seventh implements Runnable {
	// Максимальное число для проверки
	long max;
	// Массив найденных простых чисел, которые представляются индексами массива
	boolean[] nums;

	public Seventh(long max) {
		this.max = max;
		if (max > Integer.MAX_VALUE) {
			this.nums = new boolean[Integer.MAX_VALUE];
		}
		this.nums = new boolean[(int) max + 1]; // Для простоты будем учитывать в массиве первый 0
		// В конце этот ноль ликвидируем
		Arrays.fill(this.nums, true);
		this.nums[0] = false;
	}

	@Override
	public void run() {
		int steps = 0; // Счётчик выполненных шагов
		int counter = 2; // Количество найденных простых чисел (уже "учтено" 0, 1, 2)

		long start = System.currentTimeMillis();

		for (int i = 2; i <= max; i++) {
			if (nums[i] == true) {
				counter++; // Каждое первое true - простое число
				steps++;
				for (int j = i * 2; j <= max; j = j + i) {
					nums[j] = false;
					steps++;
				}
			} else {
				steps++;
			}
		}

		long elapsed = System.currentTimeMillis() - start;

		int[] res = new int[counter - 3]; // Исключили 0, 1, 2
		int currentPrime = 0;
		for (int i = 3; i < nums.length; i++) { // Здесь 3 - не забыли первый 0
			if (nums[i] == true) {
				res[currentPrime++] = i;
			}
		}

		Utils.arrayInfo(res, elapsed, counter - 3, steps);
	}
}
