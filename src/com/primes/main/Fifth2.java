package com.primes.main;

import com.primes.main.Utils;

/**
 * Числа, оканчивающиеся на 2, 4, 6, 8, 0 гарантированно делятся на 2. Числа,
 * оканчивающиеся на 5, 0 гарантированно делятся на 5. Числа, оканчивающиеся на
 * 1, 3, 7, 9 необходимо проверять. Будем проверять только их.
 * 
 * Оптимизированная версия - делители перебираются только из списка ранее
 * найденных делителей.
 * 
 * @author bratishka
 * @version 1.0
 */
public class Fifth2 implements Runnable {
	// Максимальное число для проверки
	long max;
	// Массив найденных простых чисел
	int[] nums;

	public Fifth2(long max) {
		this.max = max;
		if (max > Integer.MAX_VALUE) {
			this.nums = new int[Integer.MAX_VALUE];
		}
		this.nums = new int[(int) max];
		nums[0] = 3;
		nums[1] = 5;
	}

	@Override
	public void run() {
		int counter = 2; // Счётчик выполненных шагов
		int steps = 0; // Счётчик выполненных шагов
		boolean isPrime = true; // Является ли данное число простым

		long start = System.currentTimeMillis();

		for (int i = 4; i <= max; i++) {
			int remainder = i % 10;
			if (remainder == 1 || remainder == 3 || remainder == 7 || remainder == 9) {
				for (int j = 0; j < counter; j++) {
					steps++; // Счётчик до break
					if (i % nums[j] == 0) {
						isPrime = false;
						break;
					}
				}
			} else {
				isPrime = false;
				steps++;
			}

			if (isPrime) {
				nums[counter++] = i;
			}

			isPrime = true;
		}

		long elapsed = System.currentTimeMillis() - start;

		Utils.arrayInfo(Utils.repackArray(nums, counter), elapsed, counter, steps);
	}
}
