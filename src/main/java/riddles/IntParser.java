package riddles;

import static java.lang.Math.ceil;
import static java.lang.Math.log10;

public class IntParser {

    private static final int MAX_DIGITS_NUMBER = (int) ceil(log10(Integer.MAX_VALUE));

    public int parseInt(String string) {
        if (isEmpty(string)) {
            return 0;
        }

        return doParseInt(string.toCharArray());
    }

    private int doParseInt(char[] chars) {
        int result = 0;
        int numberOfDigits = chars.length;
        boolean negative = isNegative(chars);
        boolean closeToOverflow = isCloseToOverflow(negative ? numberOfDigits - 1 : numberOfDigits);

        for (int i = negative ? 1 : 0; i < numberOfDigits; i++) {
            int digit = getDigitFromChar(chars[i]);
            int position = getDigitPosition(i, numberOfDigits);
            int weight = getDigitWeight(position);

            if (closeToOverflow) {
                closeToOverflow = checkForOverFlow(digit, weight);
            }

            result += digit * weight;
        }

        return negative ? -result : result;
    }

    private boolean isNegative(char[] chars) {
        if (chars[0] == '-') {
            return true;
        }

        return false;
    }

    private boolean isCloseToOverflow(int digitsNumber) {
        if (digitsNumber > MAX_DIGITS_NUMBER) {
            throw new NumberFormatException("Integer overflow");
        } else if (digitsNumber == MAX_DIGITS_NUMBER) {
            return true;
        }

        return false;
    }

    private boolean checkForOverFlow(int digit, int weight) {
        int maxDigitValue = Integer.MAX_VALUE / weight % 10;
        if (digit > maxDigitValue) {
            throw new NumberFormatException("Integer overflow");
        } else if (digit == maxDigitValue) {
            return true;
        }

        return false;
    }

    private int getDigitWeight(int digitPosition) {
        return (int) Math.pow(10, digitPosition);
    }

    private int getDigitPosition(int charIndex, int numberOfDigits) {
        int largestPosition = numberOfDigits - 1;
        return largestPosition - charIndex;
    }

    private int getDigitFromChar(char c) {
        int digit = c - '0';
        if (digit > 9 || digit < 0) {
            throw new NumberFormatException();
        }

        Character.getNumericValue(c);
        return digit;
    }

    private boolean isEmpty(String string) {
        return string == null || string.isEmpty();
    }
}
