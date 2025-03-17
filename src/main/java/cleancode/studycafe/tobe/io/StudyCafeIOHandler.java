package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.model.order.StudyCafePassOrder;
import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;

import java.util.List;
import java.util.Scanner;

public class StudyCafeIOHandler {

    private static final Scanner SCANNER = new Scanner(System.in);

    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();

    public void showWelcomeMessage() {
        outputHandler.showWelcomeMessage();
    }

    public void showAnnouncement() {
        outputHandler.showAnnouncement();
    }

    public void showSimpleMessage(String message) {
        outputHandler.showSimpleMessage(message);
    }

    public void showPassOrderSummary(StudyCafePassOrder passOrder) {
        outputHandler.showPassOrderSummary(passOrder);
    }

    public StudyCafePassType askPassTypeSelecting() {
        outputHandler.askPassTypeSelection();
        String userInput = SCANNER.nextLine();
        return inputHandler.getPassTypeSelectingUserAction(userInput);
    }

    public StudyCafeSeatPass askPassSelection(List<StudyCafeSeatPass> passCandidates) {
        outputHandler.showPassListForSelection(passCandidates);

        String userInput = SCANNER.nextLine();
        return inputHandler.getSelectPass(passCandidates, userInput);
    }

    public boolean askLockerPass(StudyCafeLockerPass lockerPassCandidate) {
        outputHandler.askLockerPass(lockerPassCandidate);

        String userInput = SCANNER.nextLine();
        return inputHandler.getLockerSelection(userInput);
    }
}
