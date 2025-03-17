package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;

import java.util.List;

public class InputHandler {

    public StudyCafePassType getPassTypeSelectingUserAction(String userInput) {
        if ("1".equals(userInput)) {
            return StudyCafePassType.HOURLY;
        }
        if ("2".equals(userInput)) {
            return StudyCafePassType.WEEKLY;
        }
        if ("3".equals(userInput)) {
            return StudyCafePassType.FIXED;
        }
        throw new AppException("잘못된 입력입니다.");
    }

    public StudyCafeSeatPass getSelectPass(List<StudyCafeSeatPass> passes, String userInput) {
        int selectedIndex = Integer.parseInt(userInput) - 1;
        return passes.get(selectedIndex);
    }

    public boolean getLockerSelection(String userInput) {
        return "1".equals(userInput);
    }

}
