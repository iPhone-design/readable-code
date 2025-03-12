package cleancode.studycafe.tobe.file;

import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;

import java.util.List;

public interface FileHandler {
    List<StudyCafePass> readStudyCafePasses();
    List<StudyCafeLockerPass> readLockerPasses();
}
