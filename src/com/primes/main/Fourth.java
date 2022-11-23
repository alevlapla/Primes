package com.primes.main;

/**
 * Поиск простых числей путём просмотра до квадратного корня из очередного
 * числа. Любое число можно представить как произведение M = m*n. Любая пара m*n
 * будет иметь "зеркальную" пару n*m (по принципу коммутативности). Таким
 * образом, в крайнем случае, достаточно просмотреть все множители до n*n = M
 * (то есть до n = M^0.5).
 * 
 * @author bratishka
 * @version 1.0
 */
public class Fourth implements Runnable {
	// Максимальное число для проверки
	private long max;
	// Массив найденных простых чисел
	private int[] nums;

	public Fourth(long max) {
		this.max = max;
		if (max > Integer.MAX_VALUE) {
			this.nums = new int[Integer.MAX_VALUE];
		}
		this.nums = new int[(int) max];
		nums[0] = 3;
	}

	@Override
	public void run() {
		boolean isPrime = true; // Является ли данное число простым
		int counter = 0; // Количество найденных простых чисел
		int steps = 0; // Счётчик выполненных шагов

		long started = System.currentTimeMillis();

		for (int i = 4; i <= max; i++) {
			for (int j = 2; j * j <= i; j++) {
				steps++; // Счётчик шагов обязательно раньше break
				if (i % j == 0) {
					isPrime = false;
					break;
				}
			}

			if (isPrime) {
				nums[++counter] = i;
			}

			isPrime = true;
		}

		long elapsed = System.currentTimeMillis() - started;
		counter++; // Компенсация уже записанного значения в nums[0] при создании класса

		Utils.arrayInfo(Utils.repackArray(nums, counter), elapsed, counter, steps);
	}
}
