package com.primes.main;

import java.util.Arrays;

/**
 * Решето Эратосфена с линейным временем работы (неоптимизированное: числа
 * переписываются много раз)
 * 
 * @author bratishka
 * @version 1.0
 */
public class LinearEratosphen implements Runnable {
	// Максимальное число для проверки
	long max;
	// Список найденных простых чисел (сначала пустой)
	int[] pr;
	// Минимальный простой делитель данного числа (сначала - все 0)
	// Если lp[i] = 0, то для i не нашлось делителей - оно простое
	int[] lp;

	public LinearEratosphen(long max) {
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

			// Транслируем запись в массиве lp[] на позиции, кратные i.
			// В идеале значение должно записываться только единожды
			for (int j = 2 * i; j <= max; j = j + i) {
				if (lp[j] == 0) {
					steps++;
					lp[j] = i;
				} else {
					steps++;
				}
			}
		}

		long elapsed = System.currentTimeMillis() - start;

		counter--; // Компенсация последнего инкремента
		
		Utils.arrayInfo(Utils.repackArray(pr, counter, 1), elapsed, counter, steps);
	}
}
