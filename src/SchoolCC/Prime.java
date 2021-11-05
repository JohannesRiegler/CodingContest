package SchoolCC;

import java.util.ArrayList;

public class Prime {
    static ArrayList<Long> primes = new ArrayList<Long>();
    static int ways = 0;


    static boolean checkPrime(long prime) {
        if (prime == 1) {
            return false;
        } else if (prime == 2) {
            return true;
        }

        if (primes.size() == 0)
            primes.add(2L);
        int j = 0;
        for (long i = primes.get(primes.size() - 1); i < prime / 2; j++, i = primes.get(j)) {
            if (prime % i == 0) {
                return false;
            }

        }

        return true;
    }

    static long digits(long prime) {
        long digits = 0;
        for (; ; digits++) {
            if (prime >= Math.pow(10, digits)) {
                continue;
            } else {
                break;
            }
        }

        return digits;

    }

    static long cut(long prime, long digit) {
        long rest = prime % ((long) (Math.pow(10, digit)));
        long value = (long) (prime / (Math.pow(10, digit)));
        prime = (long) ((value - digit) / 10 * Math.pow(10, digit)) + rest;

        return prime;
    }

    public static void recursion(long prime) {
        System.out.println(prime);
        if (digits(prime) == 0) {
            ways++;
            return;
        }
        for (int i = 0; i < digits(prime); i++) {


            if (checkPrime(cut(prime, i)) == true) {
                recursion(cut(prime, i));
            }

        }
    }

    public static void main(String[] args) {

        long xd = 46216567629137L;
        recursion(46216567629137L);
        System.out.println(ways);

    }
}
