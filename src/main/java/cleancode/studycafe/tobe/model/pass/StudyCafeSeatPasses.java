package cleancode.studycafe.tobe.model.pass;

import java.util.List;
import java.util.Objects;

public class StudyCafeSeatPasses {

    private final List<StudyCafeSeatPass> passes;

    private StudyCafeSeatPasses(List<StudyCafeSeatPass> passes) {
        this.passes = passes;
    }

    public static StudyCafeSeatPasses of(List<StudyCafeSeatPass> passes) {
        return new StudyCafeSeatPasses(passes);
    }

    public List<StudyCafeSeatPass> findPassBy(StudyCafePassType studyCafePassType) {
        return passes.stream()
            .filter(studyCafePass -> studyCafePass.isSamePassType(studyCafePassType))
            .toList();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StudyCafeSeatPasses that = (StudyCafeSeatPasses) o;
        return Objects.equals(passes, that.passes);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(passes);
    }

}
