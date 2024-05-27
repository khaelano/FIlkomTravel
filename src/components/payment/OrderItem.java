package components.payment;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import components.car.*;

public class OrderItem {
    String itemID;
    Vehicle vehicle;
    LocalDate rentStartDate;
    LocalDate rentEndDate;

    public OrderItem(Vehicle vehicle, LocalDate rentStartDate, int duration) {
        this.vehicle = vehicle;
        this.itemID = vehicle.getVehicleID();
        this.rentStartDate = rentStartDate;
        this.rentEndDate = rentStartDate.plusDays(duration);
    }

    public void incrDuration(int duration) {
        this.rentEndDate = this.rentEndDate.plusDays(duration);
    }

    public void decrDuration(int duration) {
        this.rentEndDate = this.rentEndDate.minusDays(duration);
    }

    public int getRentDuration() {
        long duration = ChronoUnit.DAYS.between(rentStartDate, rentEndDate);
        return (int) duration;
    }

    public LocalDate getRentStartDate() {
        return rentStartDate;
    }

    public long getTotal() {
        return vehicle.getRentFee() * getRentDuration();
    }
}
