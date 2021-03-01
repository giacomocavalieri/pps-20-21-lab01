package lab01.example.model;

public class SimpleBankAccountWithAtm extends AbstractSimpleBankAccount {
    public SimpleBankAccountWithAtm(final AccountHolder accountHolder, final double initialBalance) {
        super(accountHolder, initialBalance);
    }

    @Override
    public double getDepositFee() {
        return 0;
    }

    @Override
    public double getWithdrawalFee() {
        return 0;
    }

    @Override
    protected boolean isWithdrawAllowed(double amountToWithdraw) {
        return false;
    }
}
