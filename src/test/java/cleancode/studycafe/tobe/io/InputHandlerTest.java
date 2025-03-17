package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputHandlerTest {

    private final InputHandler inputHandler = new InputHandler();
    private final StudyCafePassType passType = StudyCafePassType.HOURLY;
    private List<StudyCafeSeatPass> passes;

    @BeforeEach
    void setUp() {
        passes = new ArrayList<>(Arrays.asList(
            StudyCafeSeatPass.of(passType, 2, 4000, 0.0),
            StudyCafeSeatPass.of(passType, 4, 6500, 0.0),
            StudyCafeSeatPass.of(passType, 6, 9000, 0.0),
            StudyCafeSeatPass.of(passType, 8, 11000, 0.0),
            StudyCafeSeatPass.of(passType, 12, 13000, 0.0)
        ));
    }

    @Test
    void 이용자가_올바른_값을_입력한_경우_해당하는_패스_타입을_획득한다() {
        // given
        String userInput = "1";
        StudyCafePassType passType = StudyCafePassType.HOURLY;

        // when
        StudyCafePassType studyCafePassType = inputHandler.getPassTypeSelectingUserAction(String.valueOf(userInput));

        // then
        assertThat(studyCafePassType).isEqualTo(passType);
    }

    @Test
    void 이용자가_올바르지_않은_값을_입력한_경우_커스텀_예외가_발생한다() {
        // given
        String userInput = "4";

        // when-then
        assertThatThrownBy(() -> inputHandler.getPassTypeSelectingUserAction(userInput))
            .isInstanceOf(AppException.class)
            .hasMessage("잘못된 입력입니다.");
    }

    @Test
    void 이용자가_올바른_값을_입력한_경우_해당하는_패스권을_획득한다() {
        // given
        String userInput = "1";
        StudyCafeSeatPass studyCafeSeatPass = StudyCafeSeatPass.of(passType, 2, 4000, 0.0);

        // when
        StudyCafeSeatPass selectedSeatPass = inputHandler.getSelectPass(passes, userInput);

        // then
        assertThat(selectedSeatPass).isInstanceOf(studyCafeSeatPass.getClass());
    }

    @Test
    void 이용자가_올바르지_않은_값을_입력한_경우_알_수_없는_예외가_발생한다() {
        // given
        String userInput = "0";

        // when-then
        assertThatThrownBy(() -> inputHandler.getSelectPass(passes, userInput))
            .isInstanceOf(Exception.class);
    }

    @Test
    void 이용자가_락커를_선택한_경우_참을_획득한다() {
        // given
        String userInput = "1";

        // when
        boolean lockerSelection = inputHandler.getLockerSelection(userInput);

        // then
        assertThat(lockerSelection).isTrue();
    }

    @Test
    void 이용자가_락커를_선택하지_않은_경우_거짓을_획득한다() {
        // given
        String userInput = "2";

        // when
        boolean lockerSelection = inputHandler.getLockerSelection(userInput);

        // then
        assertThat(lockerSelection).isFalse();
    }

}
