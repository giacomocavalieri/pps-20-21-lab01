import lab01.example.model.AccountHolder;
import lab01.example.model.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AbstractSimpleBankAccountTest {
    private final static double INITIAL_BALANCE = 100;
    private final static int HOLDER_ID = 1;
    private final static int WRONG_HOLDER_ID = HOLDER_ID + 1;
    private final static double DEPOSITED_AMOUNT = 100;
    private final static double WITHDRAWN_AMOUNT = 70;

    private AccountHolder accountHolder;
    private BankAccount bankAccount;

    @BeforeEach
    void beforeEach(){
        this.accountHolder = new AccountHolder("Mario", "Rossi", HOLDER_ID);
        this.bankAccount = getTestedBankAccount(accountHolder, INITIAL_BALANCE);
    }

    protected abstract BankAccount getTestedBankAccount(final AccountHolder accountHolder, final double initialBalance);

    @Test
    void testInitialBalance() {
        assertEquals(INITIAL_BALANCE, bankAccount.getBalance());
    }

    protected abstract double getExpectedBalanceAfterDeposit(final double initialBalance, final double depositedAmount);

    @Test
    void testDeposit() {
        bankAccount.deposit(accountHolder.getId(), DEPOSITED_AMOUNT);
        assertEquals(getExpectedBalanceAfterDeposit(INITIAL_BALANCE, DEPOSITED_AMOUNT), bankAccount.getBalance());
    }

    @Test
    void testWrongDeposit() {
        bankAccount.deposit(accountHolder.getId(), DEPOSITED_AMOUNT);
        bankAccount.deposit(WRONG_HOLDER_ID, DEPOSITED_AMOUNT);
        assertEquals(getExpectedBalanceAfterDeposit(INITIAL_BALANCE, DEPOSITED_AMOUNT), bankAccount.getBalance());
    }

    protected abstract double getExpectedBalanceAfterWithdraw(final double initialBalance, final double withdrawnAmount);

    @Test
    void testWithdraw() {
        bankAccount.withdraw(accountHolder.getId(), WITHDRAWN_AMOUNT);
        assertEquals(getExpectedBalanceAfterWithdraw(DEPOSITED_AMOUNT, WITHDRAWN_AMOUNT), bankAccount.getBalance());
    }

    @Test
    void testWrongWithdraw() {
        bankAccount.withdraw(WRONG_HOLDER_ID, WITHDRAWN_AMOUNT);
        assertEquals(INITIAL_BALANCE, bankAccount.getBalance());
    }
}
