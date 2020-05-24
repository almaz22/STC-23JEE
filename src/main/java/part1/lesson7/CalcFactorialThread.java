package part1.lesson7;

import java.math.BigInteger;
import java.util.concurrent.ConcurrentHashMap;

/**
 * CalcFactorialThread
 *
 * @author Almaz_Kamalov
 */
public class CalcFactorialThread extends Thread {
    private final FactorialNumber factorialNumber;
    private final ConcurrentHashMap<Integer, BigInteger> results;

    public CalcFactorialThread(FactorialNumber factorialNumber,
                               ConcurrentHashMap<Integer, BigInteger> results) {
        this.factorialNumber = factorialNumber;
        this.results = results;
    }

    @Override
    public void run() {
        if (factorialNumber.getNumber() < 0)
            factorialNumber.setFactorialNumber(BigInteger.valueOf(0));
        else {
            factorialNumber.setFactorialNumber(BigInteger.valueOf(1));
            for (int i = factorialNumber.getNumber(); i >= 2; i--) {
                if (results.containsKey(i)) {
                    BigInteger result = results.get(i);
                    factorialNumber.setFactorialNumber(
                            factorialNumber.getFactorialNumber().multiply(result));
                    break;
                }
                factorialNumber.setFactorialNumber(
                        factorialNumber.getFactorialNumber().multiply(BigInteger.valueOf(i)));
            }
        }
        results.put(factorialNumber.getNumber(), factorialNumber.getFactorialNumber());
    }
}
