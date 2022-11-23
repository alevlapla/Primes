package com.primes.main;

import java.util.Arrays;

/**
 * Поиск простых чисел полным перебором.
 * 
 * @author bratishka
 * @version 1.0
 */
public class First implements Runnable {
	// Максимальное число для проверки
	long max;
	// Массив найденных простых чисел
	int[] nums;

	public First(long max) {
		this.max = max;
		if (max > Integer.MAX_VALUE) {
			this.nums = new int[Integer.MAX_VALUE];
		}
		this.nums = new int[(int) max];
	}

	@Override
	public void run() {
		int k = 0; // Количество найденных множителей у данного проверяемого числа
		int count = 0; // Количество найденных простых чисел
		long steps = 0; // Счётчик выполненных шагов

		long startTime = System.currentTimeMillis();

		for (int i = 3; i <= max; i++) {
			for (int j = 2; j < i; j++) {
				if (i % j == 0) {
					k++;
				}
				steps++;
			}

			if (k == 0) {
				nums[count] = i;
				count++;
			}
			k = 0;
		}

		long elapsed = System.currentTimeMillis() - startTime;

		Utils.arrayInfo(Utils.repackArray(nums, count), elapsed, count, steps);
	}
}
