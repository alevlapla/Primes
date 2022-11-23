package com.primes.main;

import static com.primes.main.Utils.arrayInfo;

/**
 * Поиск простых чисел частичным перебором (выход на следующую итерацию
 * немедленно, как станет понятно, что данное число составное т. е. x % 2 == 0).
 * 
 * @author bratishka
 * @version 1.0
 */
public class Second implements Runnable {
	// Максимальное число для проверки
	private long max;
	// Массив найденных простых чисел
	private int[] nums;

	public Second(long max) {
		this.max = max;
		if (max > Integer.MAX_VALUE) {
			this.nums = new int[Integer.MAX_VALUE];
		}
		this.nums = new int[(int) max];
	}

	@Override
	public void run() {
		boolean isPrime = true; // Является данное проверяемое число простым
		int counter = 0; // Количество найденных простых чисел 
		long steps = 0; // Счётчик выполненных шагов

		long startTime = System.currentTimeMillis();

		for (int i = 3; i <= max; i++) {
			for (int j = 2; j < i; j++) {
				steps++; // Шаги считать обязательно до break

				if (i % j == 0) {
					isPrime = false;
					break;
				}
			}
			if (isPrime) {
				nums[counter] = i;
				counter++;
			}
			isPrime = true;
		}

		long elapsed = System.currentTimeMillis() - startTime;

		arrayInfo(Utils.repackArray(nums, counter), elapsed, counter, steps);
	}
}
