package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.StudyCafeFileHandler;
import cleancode.studycafe.tobe.io.StudyCafeIOHandler;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;
import java.util.Optional;

public class StudyCafePassMachine {

    private final StudyCafeIOHandler ioHandler = new StudyCafeIOHandler();
    private final StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();

    public void run() {
        try {
            ioHandler.showWelcomeMessage();
            ioHandler.showAnnouncement();

            StudyCafePass selectedPass = selectedPass();
            Optional<StudyCafeLockerPass> optionalLockerPass = selectLockerPass(selectedPass);

            optionalLockerPass.ifPresentOrElse(
                lockerPass -> ioHandler.showPassOrderSummary(selectedPass, lockerPass),
                () -> ioHandler.showPassOrderSummary(selectedPass)
            );
        } catch (AppException e) {
            ioHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            ioHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private StudyCafePass selectedPass() {
        StudyCafePassType passType = ioHandler.askPassTypeSelecting();
        List<StudyCafePass> passCandidates = findPassCandidateBy(passType);

        return ioHandler.askPassSelection(passCandidates);
    }

    private List<StudyCafePass> findPassCandidateBy(StudyCafePassType studyCafePassType) {
        List<StudyCafePass> allPasses = studyCafeFileHandler.readStudyCafePasses();

        return allPasses.stream()
            .filter(studyCafePass -> studyCafePass.isSamePassType(studyCafePassType))
            .toList();
    }

    private Optional<StudyCafeLockerPass> selectLockerPass(StudyCafePass selectedPass) {
        if (selectedPass.cannotUseLocker()) {
            return Optional.empty();
        }

        List<StudyCafeLockerPass> lockerPasses = studyCafeFileHandler.readLockerPasses();

        StudyCafeLockerPass lockerPassCandidate = findLockerPassCandidateBy(selectedPass, lockerPasses);

        if (lockerPassCandidate != null) {
            boolean lockerSelection = ioHandler.askLockerPass(lockerPassCandidate);

            if (lockerSelection) {
                return Optional.of(lockerPassCandidate);
            }
        }

        return Optional.empty();
    }

    private static StudyCafeLockerPass findLockerPassCandidateBy(StudyCafePass pass, List<StudyCafeLockerPass> lockerPasses) {
        return lockerPasses.stream()
            .filter(pass::isSameDurationType)
            .findFirst()
            .orElse(null);
    }

}
