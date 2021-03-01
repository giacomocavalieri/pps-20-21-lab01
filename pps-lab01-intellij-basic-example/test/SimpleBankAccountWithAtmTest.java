import lab01.example.model.AccountHolder;
import lab01.example.model.BankAccount;
import lab01.example.model.SimpleBankAccountWithAtm;

public class SimpleBankAccountWithAtmTest extends AbstractSimpleBankAccountTest{
    private final static double EXPECTED_FEE = 1;

    @Override
    protected BankAccount getTestedBankAccount(AccountHolder accountHolder, double initialBalance) {
        return new SimpleBankAccountWithAtm(accountHolder, initialBalance);
    }

    @Override
    protected double getExpectedBalanceAfterDeposit(double initialBalance, double depositedAmount) {
        return initialBalance + depositedAmount - EXPECTED_FEE;
    }

    @Override
    protected double getExpectedBalanceAfterWithdraw(double initialBalance, double withdrawnAmount) {
        return initialBalance - withdrawnAmount - EXPECTED_FEE;
    }
}
