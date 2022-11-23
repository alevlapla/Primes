package com.primes.main;

import java.util.Arrays;

import com.primes.main.Utils;

/**
 * Делители проверяются только из числа ранее найденных простых чисел (все
 * остальные делители - являются результатом перемножения уже найденных простых
 * чисел и других чисел).
 * 
 * @author bratishka
 * @version 1.0
 */
public class Third implements Runnable {
	// Максимальное число для проверки
	private long max;
	// Массив найденных простых чисел
	private int[] nums;

	public Third(long max) {
		this.max = max;
		if (max > Integer.MAX_VALUE) {
			this.nums = new int[Integer.MAX_VALUE];
		}
		this.nums = new int[(int) max];
		nums[0] = 2;
	}

	@Override
	public void run() {
		int counter = 0; // Количество найденных простых чисел
		boolean isPrime = true; // Является ли данное число простым
		long steps = 0; // Счётчик выполненных шагов

		long start = System.currentTimeMillis();

		for (int i = 3; i <= max; i++) {
			for (int j = 0; j < counter + 1; j++) {
				steps++;
				if (i % nums[j] == 0) {
					isPrime = false;
					break;
				}
			}

			if (isPrime) {
				nums[++counter] = i;
			}
			isPrime = true;
		}

		long elapsed = System.currentTimeMillis() - start;

		Utils.arrayInfo(Utils.repackArray(nums, counter + 1, 1), elapsed, counter, steps);
	}
}
