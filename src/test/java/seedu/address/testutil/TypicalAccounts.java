//@@author nicholasleeeee
package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_PASSWORD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_USERNAME;

import seedu.address.model.account.User;

/**
 * Test utility containing accounts to be used in tests
 */

public class TypicalAccounts {

    public static final User NICHOLAS = new AccountBuilder().setUsername("Nicholas Lee")
            .setPassword("L!ke5-BlAckpink").build();

    public static final User JUSTIN = new AccountBuilder().setUsername("Justin Ong")
            .setPassword("loves-to-read-books").build();

    public static final User KAYDEN = new AccountBuilder().setUsername("Sheng Kok")
            .setPassword("Likes_perfumes").build();

    public static final User KHAIRUL = new AccountBuilder().setUsername("Khairul")
            .setPassword("lives-near-school").build();

    public static final User XIAOMING = new AccountBuilder().setUsername(VALID_USERNAME)
            .setPassword(VALID_PASSWORD).build();

}
