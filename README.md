# Primes
## Project Information
Experiments with different basic and naive algorithms of prime numbers generation. Links to articles describing algorithms can be found inside of each file (Wikipedia, Habr, Stackoverflow).

![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/alevlapla/Primes) ![GitHub last commit](https://img.shields.io/github/last-commit/alevlapla/Primes)

## Description of algorithms
### com.primes.main

![GitHub repo file count](https://img.shields.io/github/directory-file-count/alevlapla/Primes/src/com/primes/main)

|*.java|Description|
|:----:|:---------:|
|First.java|Dumb full brute force based on two for-cycles|
|Second.java|Dumb brute force based on two for-cycles. Leaving the enclosed cycle, when it's obvious, that a candidate is not a prime|
|Third.java|Only already found prime numbers are being checked in the enclosed for-cycle|
|Fourth.java|Dumb brute force only up to a square root of a current candidate|
|Fourth2.java|Dumb brute force only up to a square root of a current candidate: only already found prime numbers are being checked in the enclosed for-cycle|
|Fifth.java|Candidates must have the last digit as: 1, 3, 7, 9|
|Fifth2.java|Candidates must have the last digit as: 1, 3, 7, 9. Only already found prime numbers are being checked in the enclosed for-cycle|
|Sixth.java|Candidates must have the last digit as: 1, 3, 7, 9. The enclosed for-cycle has the step of 2|
|Sixth2.java|Candidates must have the last digit as: 1, 3, 7, 9. The enclosed for-cycle has the step of 2. Only already found prime numbers are being checked in the enclosed for-cycle|
|Seventh.java|Sieve of Eratosthenes: dumb realization|
|Seventh2.java|Sieve of Eratosthenes: discarded numbers are larger than the current squared candidate|
|LinearEratosphen.java|Sieve of Eratosthenes: linear complexity|
|LinearEratosphen2.java|Sieve of Eratosthenes: linear complexity with small optimization|
### com.primes.ultimate

![GitHub repo file count](https://img.shields.io/github/directory-file-count/alevlapla/Primes/src/com/primes/ultimate)

|*.java|Description|
|:----:|:---------:|
|AtkinsSieve.java|Atkin's sieve|
|AtkinsBitSieve.java|Atkin's bit sieve based on the primitive type byte|
