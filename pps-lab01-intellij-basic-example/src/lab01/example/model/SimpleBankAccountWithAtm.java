package lab01.example.model;

public class SimpleBankAccountWithAtm extends AbstractSimpleBankAccount {
    private static final double TRANSACTION_FEE = 1;

    public SimpleBankAccountWithAtm(final AccountHolder accountHolder, final double initialBalance) {
        super(accountHolder, initialBalance);
    }

    @Override
    public double getDepositFee() {
        return TRANSACTION_FEE;
    }

    @Override
    public double getWithdrawalFee() {
        return TRANSACTION_FEE;
    }

    @Override
    protected boolean isWithdrawAllowed(double amountToWithdraw) {
        return this.getBalance() >= amountToWithdraw + TRANSACTION_FEE;
    }
}
