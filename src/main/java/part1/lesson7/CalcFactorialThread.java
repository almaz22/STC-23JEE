package part1.lesson7;

import java.math.BigInteger;

/**
 * CalcFactorialThread
 *
 * @author Almaz_Kamalov
 */
public class CalcFactorialThread extends Thread {
    private final FactorialNumber factorialNumber;

    public CalcFactorialThread(FactorialNumber factorialNumber) {
        this.factorialNumber = factorialNumber;
    }

    @Override
    public void run() {
        if (factorialNumber.getNumber() < 0)
            factorialNumber.setFactorialNumber(BigInteger.valueOf(0));
        else {
            factorialNumber.setFactorialNumber(BigInteger.valueOf(1));
            for (int i = 2; i <= factorialNumber.getNumber(); i++) {
                factorialNumber.setFactorialNumber(
                        factorialNumber.getFactorialNumber().multiply(BigInteger.valueOf(i)));
            }
        }
    }
}
