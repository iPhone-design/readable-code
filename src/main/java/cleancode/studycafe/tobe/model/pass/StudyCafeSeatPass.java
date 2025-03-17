package cleancode.studycafe.tobe.model.pass;

import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;

import java.util.Objects;

public class StudyCafeSeatPass implements StudyCafePass {

    private final StudyCafePassType passType;
    private final int duration;
    private final int price;
    private final double discountRate;

    private StudyCafeSeatPass(StudyCafePassType passType, int duration, int price, double discountRate) {
        this.passType = passType;
        this.duration = duration;
        this.price = price;
        this.discountRate = discountRate;
    }

    public static StudyCafeSeatPass of(StudyCafePassType passType, int duration, int price, double discountRate) {
        return new StudyCafeSeatPass(passType, duration, price, discountRate);
    }

    public boolean cannotUseLocker() {
        return this.passType.isNotLockerType();
    }

    public boolean isSamePassType(StudyCafePassType pasType) {
        return this.passType == pasType;
    }

    public boolean isSameDurationType(StudyCafeLockerPass lockerPass) {
        return lockerPass.isSamePassType(this.passType)
            && lockerPass.isSameDuration(this.duration);
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

    public int getDiscountPrice() {
        return (int) (this.price * this.discountRate);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StudyCafeSeatPass that = (StudyCafeSeatPass) o;
        return duration == that.duration && price == that.price && Double.compare(discountRate, that.discountRate) == 0 && passType == that.passType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(passType, duration, price, discountRate);
    }

}
