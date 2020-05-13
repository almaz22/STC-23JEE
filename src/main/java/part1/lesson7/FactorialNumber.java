package part1.lesson7;

import java.math.BigInteger;

/**
 * FactorialNumber
 *
 * @author Almaz_Kamalov
 */
public class FactorialNumber {
    private final int number;
    private BigInteger factorialNumber;

    public int getNumber() {
        return number;
    }

    public BigInteger getFactorialNumber() {
        return factorialNumber;
    }

    public void setFactorialNumber(BigInteger factorialNumber) {
        this.factorialNumber = factorialNumber;
    }

    public FactorialNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return this.number + "! = " + this.factorialNumber;
    }
}
