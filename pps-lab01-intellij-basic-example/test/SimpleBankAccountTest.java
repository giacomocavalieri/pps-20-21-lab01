import lab01.example.model.AccountHolder;
import lab01.example.model.BankAccount;
import lab01.example.model.SimpleBankAccount;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest extends AbstractBankAccountTest {
    @Override
    protected BankAccount getTestedBankAccount(final AccountHolder accountHolder, final double initialBalance) {
        return new SimpleBankAccount(accountHolder, initialBalance);
    }

    @Override
    protected double getExpectedBalanceAfterDeposit(final double initialBalance, final double depositedAmount) {
        return initialBalance + depositedAmount;
    }

    @Override
    protected double getExpectedBalanceAfterWithdraw(final double initialBalance, final double withdrawnAmount) {
        return initialBalance - withdrawnAmount;
    }
}
