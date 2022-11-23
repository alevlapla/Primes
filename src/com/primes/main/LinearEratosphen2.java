package com.primes.main;

import java.util.Arrays;

/**
 * Решето Эратосфена с линейным временем работы (оптимизированное - числа
 * переписываются только единожды)
 * 
 * @author bratishka
 * @version 1.0
 */
public class LinearEratosphen2 implements Runnable {
	// Максимальное число для проверки
	long max;
	// Список найденных простых чисел (сначала пустой)
	int[] pr;
	// Минимальный простой делитель данного числа (сначала - все 0)
	// Если lp[i] = 0, то для i не нашлось делителей - оно простое
	int[] lp;

	public LinearEratosphen2(long max) {
		this.max = max;
		this.lp = new int[(int) max + 1];
		this.pr = new int[(int) max + 1];
	}

	@Override
	public void run() {
		int steps = 0; // Счётчик выполненных шагов
		int counter = 2; // Количество найденных простых чисел (уже "учтено" 0, 1, 2)

		long start = System.currentTimeMillis();

		for (int i = 2; i <= max; i++) {
			// Если lp[i] == 0, то i - простое число, т. к. для него не нашлось делителей
			if (lp[i] == 0) {
				lp[i] = i;
				pr[counter++] = i;
			}

			// Транслируем запись в массиве lp[] на позиции, кратные i
			// Здесь оптимизация: каждая позиция массива lp[] записывается только единожды
			for (int p = 0; (p <= counter && pr[p] * i <= max && pr[p] <= lp[i]); p++) {
				int p0 = pr[p];
				lp[(int) pr[p] * i] = p0;
				steps++;
			}
		}

		long elapsed = System.currentTimeMillis() - start;
		
		counter--; // Компенсация последнего инкремента
		Utils.arrayInfo(Utils.repackArray(pr, counter, 1), elapsed, counter, steps);
	}
}
