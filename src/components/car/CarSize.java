package components.car;

public enum CarSize {
    REGULAR(4),
    LARGE(6);

    private final int seatNumber;

    CarSize(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getSeatNumber() {return seatNumber;}
}