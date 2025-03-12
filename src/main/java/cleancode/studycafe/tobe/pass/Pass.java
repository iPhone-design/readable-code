package cleancode.studycafe.tobe.pass;

import cleancode.studycafe.tobe.file.FileHandler;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;
import java.util.NoSuchElementException;

public class Pass {

    private final FileHandler fileHandler;

    public Pass(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    public List<StudyCafePass> getStudyCafePasses(StudyCafePassType selectedPassType) {
        List<StudyCafePass> studyCafePasses = fileHandler.readStudyCafePasses();
        return studyCafePasses.stream()
            .filter(studyCafePass -> studyCafePass.getPassType() == selectedPassType)
            .toList();
    }

    public StudyCafeLockerPass getLockerPass(StudyCafePass selectedPass) {
        List<StudyCafeLockerPass> lockerPasses = fileHandler.readLockerPasses();
        return lockerPasses.stream()
            .filter(option ->
                option.getPassType() == selectedPass.getPassType()
                    && option.getDuration() == selectedPass.getDuration()
            )
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException("No such locker pass"));
    }

}
