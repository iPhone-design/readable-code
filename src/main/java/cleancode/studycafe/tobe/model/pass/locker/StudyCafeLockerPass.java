package cleancode.studycafe.tobe.model.pass.locker;

import cleancode.studycafe.tobe.model.pass.StudyCafePass;
import cleancode.studycafe.tobe.model.pass.StudyCafePassType;

import java.util.Objects;

public class StudyCafeLockerPass implements StudyCafePass {

    private final StudyCafePassType passType;
    private final int duration;
    private final int price;

    private StudyCafeLockerPass(StudyCafePassType passType, int duration, int price) {
        this.passType = passType;
        this.duration = duration;
        this.price = price;
    }

    public static StudyCafeLockerPass of(StudyCafePassType passType, int duration, int price) {
        return new StudyCafeLockerPass(passType, duration, price);
    }

    public boolean isSamePassType(StudyCafePassType passType) {
        return this.passType == passType;
    }

    public boolean isSameDuration(int duration) {
        return this.duration == duration;
    }

    @Override
    public StudyCafePassType getPassType() {
        return passType;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StudyCafeLockerPass that = (StudyCafeLockerPass) o;
        return duration == that.duration && price == that.price && passType == that.passType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(passType, duration, price);
    }
    
}
