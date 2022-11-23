package com.primes.main;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

//TODO Запуск потоков обработать в ExecutorService и другими способами (из конспекта)

/**
 * Лабораторная работа - сравнение эффективности различных алгоритмов поиска
 * простых чисел из заданного диапазона чисел. Подсчитываются: примерное время
 * работы алгоритма, количество выполненных шагов.
 * 
 * @author bratishka
 * @version 1.0
 */
public class Main {
	static final Logger logger = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) throws InterruptedException {
		//количество чисел, среди которых будет выполняться поиск простых чисел
		long nums = 100_000;

		Thread linearErat = new Thread(new LinearEratosphen(nums), "linear");
		linearErat.start();
		linearErat.join();

		Thread linearErat2 = new Thread(new LinearEratosphen2(nums), "linear2");
		linearErat2.start();
		linearErat2.join();

		Thread t1 = new Thread(new First(nums), "first");
		t1.start();
		t1.join();

		Thread t2 = new Thread(new Second(nums), "second");
		t2.start();
		t2.join();

		Thread t3 = new Thread(new Third(nums), "third");
		t3.start();
		t3.join();

		Thread t4 = new Thread(new Fourth(nums), "fourth");
		t4.start();
		t4.join();

		Thread t42 = new Thread(new Fourth2(nums), "fourth-2");
		t42.start();
		t42.join();

		Thread t5 = new Thread(new Fifth(nums), "fifth");
		t5.start();
		t5.join();

		Thread t52 = new Thread(new Fifth2(nums), "fifth-2");
		t52.start();
		t52.join();

		Thread t6 = new Thread(new Sixth(nums), "sixth");
		t6.start();
		t6.join();

		Thread t62 = new Thread(new Sixth2(nums), "sixth-2");
		t62.start();
		t62.join();

		Thread t7 = new Thread(new Seventh(nums), "seventh");
		t7.start();

		Thread t72 = new Thread(new Seventh2(nums), "seventh-2");
		t72.start();

		System.out.println("returning main");
	}
}
