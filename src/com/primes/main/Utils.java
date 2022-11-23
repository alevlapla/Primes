package com.primes.main;

import java.util.Arrays;

/**
 * Утильный класс, содержащий методы для вывода статистики по расчёту и массиву
 * простых чисел, и для "переупаковки" массива.
 * 
 * @author bratishka
 * @version 1.0
 */
public class Utils {
	/**
	 * Выводит в лог статистическую информацию по данному расчёту и массиву
	 * найденных простых чисел (прошедшее время, количество простых чисел,
	 * количество шагов)
	 * 
	 * @param e       массив с простыми числами
	 * @param elapsed время расчёта
	 * @param count   количество найденных простых чисел
	 * @param steps   количество выполненных шагов в алгоритме
	 * @return результаты расчёта в лог
	 */
	public static void arrayInfo(int[] e, long elapsed, int count, long steps) {
		long[] ee = Arrays.stream(e).mapToLong(x -> x).toArray();
		arrayInfo(ee, elapsed, count, steps);
	}

	/**
	 * Выводит в лог статистическую информацию по данному расчёту и массиву
	 * найденных простых чисел (прошедшее время, количество простых чисел,
	 * количество шагов)
	 * 
	 * @param e       массив с простыми числами
	 * @param elapsed время расчёта
	 * @param count   количество найденных простых чисел
	 * @param steps   количество выполненных шагов в алгоритме
	 * @return результаты расчёта в лог
	 */
	public static <E> void arrayInfo(long[] e, long elapsed, int count, long steps) {
		Main.logger.info(String.format("%-10s [%8dms|count=%10d|steps=%12d] %s", Thread.currentThread().getName(),
				elapsed, count, steps, Arrays.toString(e)));
	}

	/**
	 * Исключает замыкающие нули из исходного массива.
	 * 
	 * @param nums  исходный массив с числами
	 * @param count длина нового массива
	 * @return
	 */
	public static int[] repackArray(int[] nums, int count) {
		int[] res = new int[count];
		System.arraycopy(nums, 0, res, 0, count);
		return res;
	}

	/**
	 * Исключает замыкающие нули из исходного массива с удалением указанного
	 * количества первых элементов.
	 * 
	 * @param nums        исходный массив с числами
	 * @param count       длина нового массива
	 * @param removeFirst количество первых элементов к исключению
	 * @return обработанный массив новой длины с исключёнными первыми элементами
	 */
	public static int[] repackArray(int[] nums, int count, int removeFirst) {
		int[] res = new int[count - removeFirst];
		System.arraycopy(nums, removeFirst, res, 0, count - removeFirst);
		return res;
	}
}