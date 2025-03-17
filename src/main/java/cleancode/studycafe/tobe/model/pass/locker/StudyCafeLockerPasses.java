package cleancode.studycafe.tobe.model.pass.locker;

import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class StudyCafeLockerPasses {

    private final List<StudyCafeLockerPass> lockerPasses;

    private StudyCafeLockerPasses(List<StudyCafeLockerPass> lockerPasses) {
        this.lockerPasses = lockerPasses;
    }

    public static StudyCafeLockerPasses of(List<StudyCafeLockerPass> lockerPasses) {
        return new StudyCafeLockerPasses(lockerPasses);
    }

    public Optional<StudyCafeLockerPass> findLockerPassBy(StudyCafeSeatPass pass) {
        return lockerPasses.stream()
            .filter(pass::isSameDurationType)
            .findFirst();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StudyCafeLockerPasses that = (StudyCafeLockerPasses) o;
        return Objects.equals(lockerPasses, that.lockerPasses);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(lockerPasses);
    }

}
