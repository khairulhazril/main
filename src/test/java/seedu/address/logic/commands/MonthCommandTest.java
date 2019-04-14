package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.MonthCommand.MESSAGE_DUPLICATE_MONTH;
import static seedu.address.logic.commands.MonthCommand.MESSAGE_INVALID_MONTH;
import static seedu.address.logic.commands.MonthCommand.MESSAGE_MONTH_CHANGE_SUCCESS;
import static seedu.address.testutil.TypicalAccounts.NICHOLAS;

import java.time.YearMonth;

import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.account.User;
import seedu.address.model.util.Month;
import seedu.address.testutil.AccountBuilder;

public class MonthCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();
    private CommandHistory commandHistory = new CommandHistory();
    private int currMonth = YearMonth.now().getMonthValue();

    @Test
    public void execute_success() {
        User user = new AccountBuilder(NICHOLAS).build();
        model.newUser(user);
        model.loginUser(user);
        for (int i = 1; i <= 12; i++) {
            if (i != currMonth) {
                Month oldMonth = model.getMonth();
                CommandResult expectedCommandResult = new CommandResult(String.format(MESSAGE_MONTH_CHANGE_SUCCESS,
                        Integer.toString(i)), false, false);
                MonthCommand newMonthCommand = new MonthCommand(Integer.toString(i));
                assertCommandSuccess(newMonthCommand, model, commandHistory, expectedCommandResult, expectedModel);
                assertNotSame(oldMonth, model.getMonth());
                assertEquals(Integer.toString(i), model.getMonth().toString());
            }
        }
    }

    @Test
    public void duplicate_month() {
        User user = new AccountBuilder(NICHOLAS).build();
        model.newUser(user);
        model.loginUser(user);
        MonthCommand sameMonth = new MonthCommand(Integer.toString(currMonth));
        assertCommandFailure(sameMonth, model, commandHistory, MESSAGE_DUPLICATE_MONTH);
    }

    @Test
    public void invalid_input() {
        User user = new AccountBuilder(NICHOLAS).build();
        model.newUser(user);
        model.loginUser(user);
        MonthCommand zeroMonth = new MonthCommand("0");
        MonthCommand thirteenMonth = new MonthCommand("13");
        MonthCommand stringMonth = new MonthCommand("aaa");

        assertCommandFailure(zeroMonth, model, commandHistory, MESSAGE_INVALID_MONTH);
        assertCommandFailure(thirteenMonth, model, commandHistory, MESSAGE_INVALID_MONTH);
        assertCommandFailure(stringMonth, model, commandHistory, MESSAGE_INVALID_MONTH);
    }
}
