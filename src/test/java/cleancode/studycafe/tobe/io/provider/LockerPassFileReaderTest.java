package cleancode.studycafe.tobe.io.provider;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPasses;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LockerPassFileReaderTest {

    private final LockerPassFileReader lockerPassFileReader = new LockerPassFileReader();
    private final StudyCafePassType passType = StudyCafePassType.FIXED;

    @Test
    void 락커_패스권_목록을_획득한다() {
        // given
        List<StudyCafeLockerPass> lockerPasses = new ArrayList<>(Arrays.asList(
            StudyCafeLockerPass.of(passType, 4, 10000),
            StudyCafeLockerPass.of(passType, 12, 30000)
        ));
        StudyCafeLockerPasses passes = StudyCafeLockerPasses.of(lockerPasses);

        // when
        StudyCafeLockerPasses passesFromFileReader = lockerPassFileReader.getLockerPasses();

        // then
        assertThat(passes).isEqualTo(passesFromFileReader);
    }

}
