package lab01.example.model;

/**
 * This class represent a particular instance of a BankAccount.
 * In particular, a Simple Bank Account allows always the deposit
 * while the withdraw is allowed only if the balance greater or equal the withdrawal amount
 */
public class SimpleBankAccount extends AbstractBankAccount {

    public SimpleBankAccount(final AccountHolder holder, final double balance) {
        super(holder, balance);
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
        return amountToWithdraw <= this.getBalance();
    }

}
