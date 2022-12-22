package com.primes.ultimate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Решето Аткина с битовой упаковкой (опорный тип решета - {@code byte[]}).
 * <p>
 * Основные зависимости:<br>
 * 1) нечётное число {@code p} по индексу {@code i}: {@code p = 2 * i + 3},<br>
 * 2) индекс {@code i} нечётного числа {@code p}: {@code i = (p - 3) / 2},<br>
 * 3) индекс квадрата {@code i} нечётного числа {@code p} (подставив (1) в (2)):
 * {@code i = (p - 3) / 2 = ((2 * i + 3) * (2 * i + 3) - 3) / 2 = 2 * i * (i + 3) + 3}.
 * <p>
 * 
 * @author bratishka (на основе <a href=
 *         "https://ru.wikipedia.org/wiki/%D0%A0%D0%B5%D1%88%D0%B5%D1%82%D0%BE_%D0%90%D1%82%D0%BA%D0%B8%D0%BD%D0%B0">Википедии</a
 *         href>, <a href="https://habr.com/ru/post/133037/">habr.com</a href>)
 * @version 1.0
 */
public class AtkinsBitSieve {
	private static final Logger logger = LoggerFactory.getLogger(AtkinsSieve.class);
	/**
	 * Находит все простые числа, начиная с числа {@code 3} заканчивая числом
	 * {@code limit}.
	 * 
	 * @param lmt число, до которого будут искаться простые числа.
	 */
	static void doAtkinsSieve(int lmt) {
		if (lmt <= 0) {
			logger.info("Отрицательное n не допускается.");
			return;
		}
		if (lmt == 1) {
			logger.info("Невозможно инициализировать решето (n должно быть больше 2).");
			return;
		}

		/* Размеры буферов. */
		final int bitsz = ((lmt - 3) >> 4) + 1;
		final int bytesz = bitsz << 3;

		/* Битовое решето. Подготовка решета. */
		byte[] isPrime = new byte[bitsz];
		isPrime[0] ^= 1; // Число 3.

		for (int x = 1, x2 = 1; x2 <= lmt; x2 += (x << 1) + 1, x++) {
			for (int y = 1, y2 = 1; y2 <= lmt; y2 += (y << 1) + 1, y++) {
				/*
				 * 1-ый случай: число-кандидат представимо как 12 * k + 1 или 12 * k + 5 и
				 * уравнение 4 * x^2 + y^2 = n имеет нечётное количество решений.
				 */
				int n = (x2 << 2) + y2;
				if (n <= lmt && (n % 12 == 1 || n % 12 == 5)) {
					int bi = (n - 3) >> 1;
					isPrime[bi >> 3] ^= (1 << (bi & 7));
				}

				/*
				 * 2-ой случай: число-кандидат представимо как 12 * k + 7 и уравнение 3 * x^2 +
				 * y^2 = n имеет нечётное количество решений.
				 */
				n -= x2; // То же самое, что n = 3 * x^2 + y^2
				if (n <= lmt && (n % 12) == 7) {
					int bi = (n - 3) >> 1;
					isPrime[bi >> 3] ^= 1 << (bi & 7);
				}

				/*
				 * 3-й вариант: число-кандидат представимо как 12 * k + 11 и уравнение 3 * x^2 -
				 * y^2 = n имеет нечётное количество решений, при этом > y.
				 */
				n -= 2 * y2; // То же самое, что n = 3 * x^2 - y^2
				if (x > y && n <= lmt && n % 12 == 11) {
					int bi = (n - 3) >> 1;
					isPrime[bi >> 3] ^= 1 << (bi & 7);
				}
			}
		}

		/*
		 * Высеивание квадратов, начиная с 5^2.
		 */
		for (int bi = 0, x = 3; x * x <= lmt; bi++, x = (bi << 1) + 3) {
			if ((isPrime[bi >> 3] & (1 << (bi & 7))) != 0) {
				for (int bi_sqr = (bi << 1) * (bi + 3) + 3; bi_sqr < bytesz; bi_sqr += (bi << 1) + 3) {
					isPrime[bi_sqr >> 3] &= ~(1 << (bi_sqr & 7));
				}
			}
		}

		/* Результаты. */
		for (int bi = 0, counter = 0; bi < bytesz; bi++) {
			if ((isPrime[bi >> 3] & (1 << (bi & 7))) != 0) {
				logger.info("prime={}, counter={}", (bi << 1) + 3, ++counter);
			}
		}
	}
}
