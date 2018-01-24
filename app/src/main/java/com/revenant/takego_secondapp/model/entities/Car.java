package com.revenant.takego_secondapp.model.entities;

/**
 * Created by Or on 22-Jan-18.
 */

public class Car {
    private long modelNumber;
    private long defaultBranchNumber;
    private double mileage;
    private long carId;

    public Car(long modelNumber, long defaultBranchNumber, double mileage, long carId) {
        this.modelNumber = modelNumber;
        this.defaultBranchNumber = defaultBranchNumber;
        this.mileage = mileage;
        this.carId = carId;
    }

    public Car() {
    }

    public long getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(long modelNumber) {
        this.modelNumber = modelNumber;
    }

    public long getDefaultBranchNumber() {
        return defaultBranchNumber;
    }

    public void setDefaultBranchNumber(long defaultBranchNumber) {
        this.defaultBranchNumber = defaultBranchNumber;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    @Override
    public String toString() {
        String car = "Car ID: " + carId + "\n"
                + "Model number: " + modelNumber + "\n"
                + "Default branch number: " + defaultBranchNumber + "\n"
                + "Mileage: "+ mileage;
        return car;
    }
}
