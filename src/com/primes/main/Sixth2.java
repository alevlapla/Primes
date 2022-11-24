package com.primes.main;

import com.primes.main.Utils;

/**
 * Числа, оканчивающиеся на 2, 4, 6, 8, 0 гарантированно делятся на 2. Числа,
 * оканчивающиеся на 5, 0 гарантированно делятся на 5. Числа, оканчивающиеся на
 * 1, 3, 7, 9 необходимо проверять. Будем проверять только их.
 * 
 * Вариант оптимизации с итерацией в цикле с шагом 2 (попадающие числа,
 * оканчивающиеся на 5 и 0, будут отсеиваться условием x % 10 == 0 || x % 5 ==
 * 0).
 * 
 * Оптимизированная версия - перебираются только делители из ранее найденных
 * простых чисел.
 * 
 * @author bratishka
 * @version 1.0
 */
public class Sixth2 implements Runnable {
	// Максимальное число для проверки
	long max;
	// Массив найденных простых чисел
	int[] nums;

	public Sixth2(long max) {
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
		boolean isPrime = true; // Является ли данное число простым
		int counter = 2; // Количество найденных простых чисел
		int steps = 0; // Счётчик выполненных шагов

		long start = System.currentTimeMillis();

		for (int i = 7; i < max; i = i + 2) {
			if (i % 10 != 5 || i % 10 != 0) {
				for (int j = 0; j < counter; j++) {
					steps++; // Счётчик шагов обязательно до break
					if (i % nums[j] == 0) {
						isPrime = false;
						break;
					}
				}
			} else {
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
