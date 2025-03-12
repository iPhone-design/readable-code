package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.pass.Pass;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;

public class StudyCafePassMachine {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final Pass pass;

    public StudyCafePassMachine(InputHandler inputHandler, OutputHandler outputHandler, Pass pass) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        this.pass = pass;
    }

    public void run() {
        try {
            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();

            outputHandler.askPassTypeSelection();
            StudyCafePassType studyCafePassType = inputHandler.getPassTypeSelectingUserAction();
            if (doseUserSelectedFixedPass(studyCafePassType)) {
                orderFixedPass(studyCafePassType);
                return;
            }

            orderTimePass(studyCafePassType);
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private boolean doseUserSelectedFixedPass(StudyCafePassType studyCafePassType) {
        return studyCafePassType == StudyCafePassType.FIXED;
    }

    private void orderFixedPass(StudyCafePassType studyCafePassType) {
        List<StudyCafePass> studyCafePasses = pass.getStudyCafePasses(studyCafePassType);
        outputHandler.showPassListForSelection(studyCafePasses);

        StudyCafePass selectedPass = inputHandler.getSelectPass(studyCafePasses);
        StudyCafeLockerPass lockerPass = pass.getLockerPass(selectedPass);
        outputHandler.askLockerPass(lockerPass);

        boolean lockerSelection = inputHandler.getLockerSelection();
        outputHandler.showPassOrderSummary(selectedPass, lockerSelection ? lockerPass : null);
    }

    private void orderTimePass(StudyCafePassType studyCafePassType) {
        List<StudyCafePass> studyCafePasses = pass.getStudyCafePasses(studyCafePassType);
        outputHandler.showPassListForSelection(studyCafePasses);

        StudyCafePass selectedPass = inputHandler.getSelectPass(studyCafePasses);
        outputHandler.showPassOrderSummary(selectedPass, null);
    }

}
