package com.revenant.takego_secondapp.model.entities;

/**
 * Created by Or on 22-Jan-18.
 */

public class CarModel {

    private long modelNumber;
    private String brand;
    private String modelName;
    //Engine size units are: Square Centimeters
    private int engineSize;
    CarModel.Gear gearType;
    private int seats;

    public CarModel() {
    }

    public CarModel(long modelNumber, String brand, String modelName, int engineSize, Gear gearType, int seats) {
        this.modelNumber = modelNumber;
        this.brand = brand;
        this.modelName = modelName;
        this.engineSize = engineSize;
        this.gearType = gearType;
        this.seats = seats;
    }

    public long getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(long modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(int engineSize) {
        this.engineSize = engineSize;
    }

    public Gear getGearType() {
        return gearType;
    }

    public void setGearType(Gear gearType) {
        this.gearType = gearType;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public enum Gear {Automatic, Manual}

    @Override
    public String toString() {
        String model = "Model number: " + modelNumber + "\n"
                + "Model name: " + modelName + "\n"
                + "Brand: " + brand + "\n"
                + "Engine size: " + engineSize + "\n"
                + "Gear type: " + gearType.toString() + "\n"
                + "Number of seats: " + seats;
        return model;
    }
}
