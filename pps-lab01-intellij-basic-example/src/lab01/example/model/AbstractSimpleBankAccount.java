package lab01.example.model;

public abstract class AbstractSimpleBankAccount implements BankAccount {
    private final AccountHolder accountHolder;
    private double balance;

    public AbstractSimpleBankAccount(final AccountHolder accountHolder, final double initialBalance) {
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    @Override
    public AccountHolder getHolder() {
        return this.accountHolder;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

    @Override
    public void deposit(int usrID, double amount) {
        if (checkUser(usrID)) {
            this.balance += amount - getDepositFee();
        }
    }

    protected abstract double getDepositFee();

    @Override
    public void withdraw(int usrID, double amount) {
        if (checkUser(usrID) && isWithdrawAllowed(amount)) {
            this.balance -= amount + getWithdrawalFee();
        }
    }

    protected abstract double getWithdrawalFee();

    protected abstract boolean isWithdrawAllowed(final double amountToWithdraw);

    private boolean checkUser(final int id) {
        return this.accountHolder.getId() == id;
    }
}
