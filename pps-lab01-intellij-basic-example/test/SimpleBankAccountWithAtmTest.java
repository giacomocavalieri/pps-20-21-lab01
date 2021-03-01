import lab01.example.model.AccountHolder;
import lab01.example.model.BankAccount;
import lab01.example.model.SimpleBankAccountWithAtm;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    public void testMultipleWithdrawals() {
        final int withdrawals = 3;
        final double withdrawnAmount = 30;

        IntStream.range(0, withdrawals).forEach(i -> bankAccount.withdraw(accountHolder.getId(), withdrawnAmount));
        assertEquals(INITIAL_BALANCE - (withdrawnAmount + EXPECTED_FEE) * withdrawals, bankAccount.getBalance());
    }

    @Test
    public void testWithdrawalFeeDoesNotExceedAccountLimit() {
        bankAccount.withdraw(accountHolder.getId(), INITIAL_BALANCE);
        assertEquals(INITIAL_BALANCE, bankAccount.getBalance());
    }
}
