package riddles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PrimeFactorFinder {

    private static final int FIRST_PRIME = 2;

    private List<Integer> primeFactors;
    private boolean[] isPrimeFlags;

    private int currentPrime;

    public List<Integer> findPrimeFactors(int number) {
        setUp(number);

        if (number < 1) {
            return Collections.emptyList();
        } else if (number == 1) {
            return Arrays.asList(1);
        }

        doFindPrimeFactors(number);
        return primeFactors;
    }

    private void doFindPrimeFactors(int number) {
        if (number % currentPrime == 0) {
            primeFactors.add(currentPrime);
            doFindPrimeFactors(number / currentPrime);
        } else if (moveToNextPrime()) {
            doFindPrimeFactors(number);
        } else if (number != 1) {
            primeFactors.add(number);
        }
    }

    public boolean moveToNextPrime() {
        int currentPrimeIndex = getIndexOfNumber(currentPrime);
        crossOffNonPrimes(currentPrimeIndex);

        for (int i = currentPrimeIndex + 1; i < isPrimeFlags.length; i++) {
            if (isPrimeFlags[i]) {
                currentPrime = getNumberForIndex(i);
                return true;
            }
        }

        return false;
    }

    private void crossOffNonPrimes(int currentPrimeIndex) {
        if (currentPrimeIndex == -1) {
            return;
        }

        for (int i = currentPrimeIndex + currentPrime; i < isPrimeFlags.length; i += currentPrime) {
            isPrimeFlags[i] = false;
        }
    }

    private int getNumberForIndex(int index) {
        return 2*index + 3;
    }

    private int getIndexOfNumber(int number) {
        int shift = number - 3;
        if (shift % 2 == 0) {
            return shift / 2;
        } else {
            return -1;
        }
    }

    private void setUp(int number) {
        primeFactors = new ArrayList<>();

        isPrimeFlags = new boolean[(int) (Math.sqrt(number) + 1) / 2];
        Arrays.fill(isPrimeFlags, true);

        currentPrime = FIRST_PRIME;
    }
}
