package lab01.example.model;

/**
 * This class represent a particular instance of a BankAccount.
 * In particular, a Simple Bank Account allows always the deposit
 * while the withdraw is allowed only if the balance greater or equal the withdrawal amount
 */
public class SimpleBankAccount extends AbstractSimpleBankAccount {
    private final static int DEPOSIT_FEE = 0;
    private final static int WITHDRAWAL_FEE = 0;

    public SimpleBankAccount(final AccountHolder holder, final double balance) {
        super(holder, balance);
    }

    @Override
    public double getDepositFee() {
        return DEPOSIT_FEE;
    }

    @Override
    public double getWithdrawalFee() {
        return WITHDRAWAL_FEE;
    }

    @Override
    protected boolean isWithdrawAllowed(double amountToWithdraw) {
        return amountToWithdraw <= this.getBalance();
    }

}
