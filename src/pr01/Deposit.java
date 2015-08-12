package pr01;


import java.math.BigDecimal;

//
public class Deposit implements Comparable<Deposit> {
    private String customerNumber;
    private DepositType depositType;
    private BigDecimal depositBalance;
    private int durationInDays;
    private BigDecimal calculatedInterest;

    public Deposit(String customerNumber, DepositType depositType, BigDecimal depositBalance, int durationInDas)
            throws DepositTypeException, DepositBalanceException, DepositDurationException {
        this.customerNumber = customerNumber;
        this.depositType = depositType;
        if (depositBalance.compareTo(new BigDecimal(0)) == -1) {
            throw new DepositBalanceException("مانده موجودی صحیح نمی باشد.");
        } else {
            this.depositBalance = depositBalance;
        }

        this.durationInDays = durationInDas;
        if (this.durationInDays < 0) {
            throw new DepositDurationException("روز مشکل دارد");
        }
    }

    public void calculateInterest() throws DepositBalanceException, DepositDurationException {
        double result;
        if (depositBalance.compareTo(new BigDecimal(0)) < 0) {
            throw new DepositBalanceException("مانده موجودی صحیح نمی باشد. ");
        }
        if (this.durationInDays < 0) {
            throw new DepositDurationException("تعداد روزها صحیح نمی باشد");
        }
        result = durationInDays * depositType.getInterestRate();
        calculatedInterest = (depositBalance.multiply(new BigDecimal(result)).divide(new BigDecimal(36500), 0, BigDecimal.ROUND_HALF_UP));
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public DepositType getDepositType() {
        return depositType;
    }

    public BigDecimal getDepositBalance() {
        return depositBalance;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public void setDepositType(DepositType depositType) {
        this.depositType = depositType;
    }

    public void setDepositBalance(BigDecimal depositBalance) {
        this.depositBalance = depositBalance;
    }

    public void setDurationInDays(int durationInDays) {
        this.durationInDays = durationInDays;
    }

    public BigDecimal getCalculatedInterest() {
        return calculatedInterest;
    }

    public void setCalculatedInterest(BigDecimal calculatedInterest) {
        this.calculatedInterest = calculatedInterest;
    }

    public int compareTo(Deposit deposit) {
        return this.getCalculatedInterest().compareTo(deposit.getCalculatedInterest());
    }

    public String toString() {
        return "customer Number " + getCustomerNumber() + " #interest " + calculatedInterest;
    }
}
