package seedu.address.logic.commands;

import java.util.List;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.events.ui.JumpToListRequestEvent;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.ReadOnlyPerson;

/**
 * Selects a person identified using it's last displayed index from the address book.
 */
public class ArrangeCommand extends Command {

    public static final String COMMAND_WORD = "arrange";
    public static final String COMMAND_ALIAS = "ar";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": arrange meeting time for a list of indices of persons who appeared in the last list \n"
            + "Parameters: Indices separated by spaces (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 2 5 7 8";

    public static final String MESSAGE_ARRANGE_PERSON_SUCCESS = "meeting successfully arranged!";

    private final Index[] ListOfIndex;

    public ArrangeCommand(int[] ListOfIndex) {
        this.ListOfIndex = new Index[ListOfIndex.length];
        for(int i = 0; i < ListOfIndex.length; i++){
            this.ListOfIndex[i] = Index.fromOneBased(ListOfIndex[i]);
        }
    }

    @Override
    public CommandResult execute() throws CommandException {

        List<ReadOnlyPerson> lastShownList = model.getFilteredPersonList();

        for(int i = 0; i < ListOfIndex.length; i++) {
            if (ListOfIndex[i].getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
        }
        System.out.println(model.generateMeetingTime(ListOfIndex));
        return new CommandResult(String.format(MESSAGE_ARRANGE_PERSON_SUCCESS));

    }
}
