package pr01;

public class DepositType {
    private String name;
    private double interestRate;

    public DepositType(String name, double interestRate) {
        this.name = name;
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public String getName() {
        return name;
    }
}
