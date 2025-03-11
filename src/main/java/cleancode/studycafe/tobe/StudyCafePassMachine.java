package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.io.StudyCafeFileHandler;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;

public class StudyCafePassMachine {

    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();
    private boolean lockerSelection = false;

    public void run() {
        try {
            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();

            outputHandler.askPassTypeSelection();
            StudyCafePassType studyCafePassType = inputHandler.getPassTypeSelectingUserAction();

            if (doseUserSelectedHourlyPass(studyCafePassType)) {
                StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();
                List<StudyCafePass> studyCafePasses = studyCafeFileHandler.readStudyCafePasses();
                List<StudyCafePass> hourlyPasses = studyCafePasses.stream()
                    .filter(studyCafePass -> doseUserSelectedHourlyPass(studyCafePass.getPassType()))
                    .toList();
                outputHandler.showPassListForSelection(hourlyPasses);
                StudyCafePass selectedPass = inputHandler.getSelectPass(hourlyPasses);
                outputHandler.showPassOrderSummary(selectedPass, null);
                return;
            }
            if (doseUserSelectedWeeklyPass(studyCafePassType)) {
                StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();
                List<StudyCafePass> studyCafePasses = studyCafeFileHandler.readStudyCafePasses();
                List<StudyCafePass> weeklyPasses = studyCafePasses.stream()
                    .filter(studyCafePass -> doseUserSelectedWeeklyPass(studyCafePass.getPassType()))
                    .toList();
                outputHandler.showPassListForSelection(weeklyPasses);
                StudyCafePass selectedPass = inputHandler.getSelectPass(weeklyPasses);
                outputHandler.showPassOrderSummary(selectedPass, null);
                return;
            }
            if (doseUserSelectedFixedPass(studyCafePassType)) {
                StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();
                List<StudyCafePass> studyCafePasses = studyCafeFileHandler.readStudyCafePasses();
                List<StudyCafePass> fixedPasses = studyCafePasses.stream()
                    .filter(studyCafePass -> doseUserSelectedFixedPass(studyCafePass.getPassType()))
                    .toList();
                outputHandler.showPassListForSelection(fixedPasses);
                StudyCafePass selectedPass = inputHandler.getSelectPass(fixedPasses);

                List<StudyCafeLockerPass> lockerPasses = studyCafeFileHandler.readLockerPasses();
                StudyCafeLockerPass lockerPass = lockerPasses.stream()
                    .filter(option ->
                        option.getPassType() == selectedPass.getPassType()
                            && option.getDuration() == selectedPass.getDuration()
                    )
                    .findFirst()
                    .orElse(null);

                if (hasLockerPasses(lockerPass)) {
                    outputHandler.askLockerPass(lockerPass);
                    lockerSelection = inputHandler.getLockerSelection();
                }

                if (doseUserSelectedLockerPass(lockerSelection)) {
                    outputHandler.showPassOrderSummary(selectedPass, lockerPass);
                } else {
                    outputHandler.showPassOrderSummary(selectedPass, null);
                }
            }
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private static boolean doseUserSelectedLockerPass(boolean lockerSelection) {
        return lockerSelection;
    }

    private static boolean hasLockerPasses(StudyCafeLockerPass lockerPass) {
        return lockerPass != null;
    }

    private static boolean doseUserSelectedFixedPass(StudyCafePassType studyCafePassType) {
        return studyCafePassType == StudyCafePassType.FIXED;
    }

    private static boolean doseUserSelectedWeeklyPass(StudyCafePassType studyCafePassType) {
        return studyCafePassType == StudyCafePassType.WEEKLY;
    }

    private static boolean doseUserSelectedHourlyPass(StudyCafePassType studyCafePassType) {
        return studyCafePassType == StudyCafePassType.HOURLY;
    }

}
