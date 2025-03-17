package cleancode.studycafe.tobe.io.provider;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPasses;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SeatPassFileReaderTest {

    private final SeatPassFileReader seatPassFileReader = new SeatPassFileReader();

    @Test
    void 좌석_패스권_목록을_획득한다() {
        // given
        List<StudyCafeSeatPass> seatPasses = new ArrayList<>(Arrays.asList(
            StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 2, 4000, 0.0),
            StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 4, 6500, 0.0),
            StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 6, 9000, 0.0),
            StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 8, 11000, 0.0),
            StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 10, 12000, 0.0),
            StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 12, 13000, 0.0),
            StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 1, 60000, 0.0),
            StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 2, 100000, 0.1),
            StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 3, 130000, 0.1),
            StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 4, 150000, 0.1),
            StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 12, 400000, 0.15),
            StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 250000, 0.1),
            StudyCafeSeatPass.of(StudyCafePassType.FIXED, 12, 700000, 0.15)
        ));
        StudyCafeSeatPasses passes = StudyCafeSeatPasses.of(seatPasses);

        // when
        StudyCafeSeatPasses passesFromFileReader = seatPassFileReader.getSeatPasses();

        // then
        assertThat(passes).isEqualTo(passesFromFileReader);
    }

}
