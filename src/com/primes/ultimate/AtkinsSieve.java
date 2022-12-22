package com.primes.ultimate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Решето Аткина.
 * 
 * @author bratishka (на основе <a href=
 *         "https://ru.wikipedia.org/wiki/%D0%A0%D0%B5%D1%88%D0%B5%D1%82%D0%BE_%D0%90%D1%82%D0%BA%D0%B8%D0%BD%D0%B0">Википедии</a
 *         href>, <a href="https://habr.com/ru/post/133037/">habr.com</a href>)
 * @version 1.0
 */
public class AtkinsSieve {
	private static final Logger logger = LoggerFactory.getLogger(AtkinsSieve.class);

	/**
	 * Находит все простые числа, начиная с числа {@code 3} заканчивая числом
	 * {@code limit}.
	 * 
	 * @param lmt число, до которого будут искаться простые числа.
	 */
	public static void doAtkinsSieve(int limit) {
		if (limit < 0) {
			logger.info("Отрицательное n не допускается.");
			return;
		}
		if (limit <= 1) {
			logger.info("Невозможно инициализировать решето (n должно быть больше 2).");
			return;
		}

		/* Уменьшим чётное число до ближайшего нечётного числа. */
		if ((limit & 1) == 0) {
			limit--;
		}

		/* Подготовка решета. */
		boolean[] isPrime = new boolean[limit + 1];
		isPrime[2] = true;
		if (limit > 2) {
			isPrime[3] = true;
		}

		/*
		 * Квадратный корень от limit - до него будут итерации в циклах, в инвариантах
		 * которых вычисляются квадраты при помощи операций умножения и сложения.
		 */
		/* Квадраты чисел: 1, 2^2 = 4, 3^2 = 9, 4^2 = 16... */
		for (int x = 1, x2 = 2 * x - 1; x2 <= limit; x++, x2 += 2 * x - 1) {
			for (int y = 1, y2 = 2 * y - 1; y2 <= limit; y++, y2 += 2 * y - 1) {

				/*
				 * 1-ый случай: число-кандидат представимо как 12 * k + 1 или 12 * k + 5 и
				 * уравнение 4 * x^2 + y^2 = n имеет нечётное количество решений.
				 */
				int n = 4 * x2 + y2;
				if (n <= limit && (n % 12 == 1 || n % 12 == 5)) {
					isPrime[n] = !isPrime[n];
				}
				/*
				 * 2-ой случай: число-кандидат представимо как 12 * k + 7 и уравнение 3 * x^2 +
				 * y^2 = n имеет нечётное количество решений.
				 */
				n -= x2; // То же самое, что n = 3 * x^2 + y^2
				if (n <= limit && n % 12 == 7) {
					isPrime[n] = !isPrime[n];
				}
				/*
				 * 3-й вариант: число-кандидат представимо как 12 * k + 11 и уравнение 3 * x^2 -
				 * y^2 = n имеет нечётное количество решений, при этом > y.
				 */
				n -= 2 * y2; // То же самое, что n = 3 * x^2 - y^2
				if (x > y && n <= limit && n % 12 == 11) {
					isPrime[n] = !isPrime[n];
				}
			}
		}

		/*
		 * Высеивание квадратов, начиная с 5^2 до sqr_lim (дальше нет смысла: sqr_lim^2
		 * > limit).
		 */
		for (int i = 5; i * i <= limit; i++) {
			if (isPrime[i]) {
				for (int j = i * i; j <= limit; j += i) {
					isPrime[j] = false;
				}
			}
		}

		/* Результаты. */
		for (int i = 0, counter = 0; i <= limit; i++) {
			if (isPrime[i]) {
				logger.info("prime={}, counter={}", i, ++counter);
			}
		}
	}
}
