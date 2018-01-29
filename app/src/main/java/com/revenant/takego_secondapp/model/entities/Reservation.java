package com.revenant.takego_secondapp.model.entities;

/**
 * Created by Or on 22-Jan-18.
 */

public class Reservation {
    private long reservationNumber;
    private long carNumber;
    private long customerNumber;
    private boolean isOpen;
    private String rentBeginning;
    private String rentEnd;
    private double preKMCount;
    private double postKMCount;
    private boolean wasRefueled;
    private double litersRefueled;
    private double price;

    public Reservation() {
    }


    public long getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(long carNumber) {
        this.carNumber = carNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isWasRefueled() {
        return wasRefueled;
    }

    public long getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(long reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public long getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(long customerNumber) {
        this.customerNumber = customerNumber;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getRentBeginning() {
        return rentBeginning;
    }

    public void setRentBeginning(String rentBeginning) {
        this.rentBeginning = rentBeginning;
    }

    public String getRentEnd() {
        return rentEnd;
    }

    public void setRentEnd(String rentEnd) {
        this.rentEnd = rentEnd;
    }

    public double getPreKMCount() {
        return preKMCount;
    }

    public void setPreKMCount(double preKMCount) {
        this.preKMCount = preKMCount;
    }

    public double getPostKMCount() {
        return postKMCount;
    }

    public void setPostKMCount(double postKMCount) {
        this.postKMCount = postKMCount;
    }

    public boolean wasRefueled() {
        return wasRefueled;
    }

    public void setWasRefueled(boolean wasRefueled) {
        this.wasRefueled = wasRefueled;
    }

    public double getLitersRefueled() {
        return litersRefueled;
    }

    public void setLitersRefueled(double litersRefueled) {
        this.litersRefueled = litersRefueled;
    }

    @Override
    public String toString() {
        String status = isOpen ? "Open" : "Closed";
        String refueled = wasRefueled ? "Yes" : "No";


        String reservation =
                "Reservation number: " + reservationNumber + "\n"
                        + "Customer number: " + customerNumber + "\n"
                        + "Car number: " + carNumber + "\n"
                        + "Status: " + status + "\n"
                        + "Rent beginning: " + rentBeginning + "\n"
                        + "Rent end: " + rentEnd + "\n"
                        + "Pre-mileage: " + preKMCount + "\n"
                        + "Post-mileage: " + postKMCount + "\n"
                        + "Was refueled: " + refueled + "\n"
                        + "Liters refueled: " + litersRefueled + "\n"
                        + "Total price: " + price;
        return reservation;
    }
}
