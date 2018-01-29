package com.revenant.takego_secondapp.model.backend;

import android.content.ContentValues;
import com.revenant.takego_secondapp.model.entities.*;
/**
 * Created by Or on 22-Jan-18.
 */
public class Constants {
    public static final String SHOW_ALL= "show all";
    public static final String AVAILABLE_CARS_CHANGED = "availableCarsChanged";


    public static class BranchConst {
        public static final String BRANCH = "branch";
        public static final String ADDRESS = "address";
        public static final String PARKING_SPACES = "parkingSpaces";
        public static final String BRANCH_NUMBER = "_id";
    }

    public static class CarConst {
        public static final String CAR = "car";
        public static final String MODEL_NUMBER = "modelNumber";
        public static final String DEFAULT_BRANCH_NUMBER = "defaultBranchNumber";
        public static final String MILEAGE = "mileage";
        public static final String ID = "_id";
    }

    public static class CarModelConst {
        public static final String MODEL = "model";
        public static final String MODEL_NUMBER = "_id";
        public static final String BRAND = "brand";
        public static final String MODEL_NAME = "modelName";
        public static final String ENGINE_SIZE = "engineSize";
        public static final String GEAR_TYPE = "gearType";
        public static final String SEATS = "seats";
    }

    public static class CustomerConst {
        public static final String CUSTOMER = "customer";
        public static final String NAME = "name";
        public static final String LAST_NAME = "lastName";
        public static final String ID = "_id";
        public static final String PHONE_NUMBER = "phone";
        public static final String EMAIL = "email";
        public static final String CREDIT_CARD = "creditCard";
        public static final String PASSWORD = "password";
    }

    public static class ReservationConst {
        public static final String RESERVATION_NUMBER = "_id";
        public static final String CAR_NUMBER = "carNumber";
        public static final String RESERVATION = "reservation";
        public static final String CUSTOMER_NUMBER = "customerNumber";
        public static final String IS_OPEN = "isOpen";
        public static final String RENT_BEGINNING = "rentBeginning";
        public static final String RENT_END = "rentEnd";
        public static final String PRE_KM_COUNT = "preKMCount";
        public static final String POST_KM_COUNT = "postKMCount";
        public static final String WAS_REFUELED = "wasRefueled";
        public static final String LITERS_REFUELED = "litersRefueled";
        public static final String TOTAL_PRICE = "price";
    }

    public static ContentValues BranchToContentValues(Branch branch) {
        ContentValues cv = new ContentValues();
        cv.put(BranchConst.ADDRESS, branch.getAddress());
        cv.put(BranchConst.PARKING_SPACES, branch.getParkingSpaces());
        cv.put(BranchConst.BRANCH_NUMBER, branch.getBranchNumber());

        return cv;
    }

    public static ContentValues CarToContentValues(Car car) {
        ContentValues cv = new ContentValues();
        cv.put(CarConst.ID, car.getCarId());
        cv.put(CarConst.DEFAULT_BRANCH_NUMBER, car.getDefaultBranchNumber());
        cv.put(CarConst.MILEAGE, car.getMileage());
        cv.put(CarConst.MODEL_NUMBER, car.getModelNumber());

        return cv;
    }

    public static ContentValues CarModelToContentValues(CarModel carModel) {
        ContentValues cv = new ContentValues();
        cv.put(CarModelConst.GEAR_TYPE, carModel.getGearType().toString());
        cv.put(CarModelConst.BRAND, carModel.getBrand());
        cv.put(CarModelConst.ENGINE_SIZE, carModel.getEngineSize());
        cv.put(CarModelConst.MODEL_NAME, carModel.getModelName());
        cv.put(CarModelConst.MODEL_NUMBER, carModel.getModelNumber());
        cv.put(CarModelConst.SEATS, carModel.getSeats());
        return cv;
    }

    public static ContentValues CustomerToContentValues(Customer customer) {
        ContentValues cv = new ContentValues();
        cv.put(CustomerConst.ID, customer.getId());
        cv.put(CustomerConst.CREDIT_CARD, customer.getCreditCard());
        cv.put(CustomerConst.EMAIL, customer.getEmail());
        cv.put(CustomerConst.NAME, customer.getName());
        cv.put(CustomerConst.LAST_NAME, customer.getLastName());
        cv.put(CustomerConst.PHONE_NUMBER, customer.getPhoneNumber());
        return cv;
    }

    public static ContentValues ReservationToContentValues(Reservation reservation) {
        ContentValues cv = new ContentValues();
        cv.put(ReservationConst.CUSTOMER_NUMBER, reservation.getCustomerNumber());
        cv.put(ReservationConst.IS_OPEN, reservation.isOpen());
        cv.put(ReservationConst.LITERS_REFUELED, reservation.getLitersRefueled());
        cv.put(ReservationConst.WAS_REFUELED, reservation.wasRefueled());
        cv.put(ReservationConst.PRE_KM_COUNT, reservation.getPreKMCount());
        cv.put(ReservationConst.POST_KM_COUNT, reservation.getPostKMCount());
        cv.put(ReservationConst.RENT_BEGINNING, reservation.getRentBeginning());
        cv.put(ReservationConst.RENT_END, reservation.getRentEnd());
        cv.put(ReservationConst.RESERVATION_NUMBER, reservation.getReservationNumber());
        cv.put(ReservationConst.TOTAL_PRICE , reservation.getPrice());
        cv.put(ReservationConst.CAR_NUMBER, reservation.getCarNumber());

        return cv;
    }


    public static Branch ContentValuesToBranch(ContentValues cv) {
        Branch branch = new Branch();
        branch.setAddress(cv.getAsString(BranchConst.ADDRESS));
        branch.setBranchNumber(cv.getAsLong(BranchConst.BRANCH_NUMBER));
        branch.setParkingSpaces(cv.getAsInteger(BranchConst.PARKING_SPACES));

        return branch;
    }

    public static Car ContentValuesToCar(ContentValues cv) {
        Car car = new Car();
        car.setCarId(cv.getAsLong(CarConst.ID));
        car.setDefaultBranchNumber(cv.getAsLong(CarConst.DEFAULT_BRANCH_NUMBER));
        car.setMileage(cv.getAsDouble(CarConst.MILEAGE));
        car.setModelNumber(cv.getAsLong(CarConst.MODEL_NUMBER));

        return car;
    }

    public static CarModel ContentValuesToCarModel(ContentValues cv) {

        CarModel carModel = new CarModel();
        carModel.setGearType(CarModel.Gear.valueOf(cv.getAsString(CarModelConst.GEAR_TYPE)));
        carModel.setBrand(cv.getAsString(CarModelConst.BRAND));
        carModel.setEngineSize(cv.getAsInteger(CarModelConst.ENGINE_SIZE));
        carModel.setModelName(cv.getAsString(CarModelConst.MODEL_NAME));
        carModel.setModelNumber(cv.getAsLong(CarModelConst.MODEL_NUMBER));
        carModel.setSeats(cv.getAsInteger(CarModelConst.SEATS));

        return carModel;
    }

    public static Customer ContentValuesToCustomer(ContentValues cv) {
        Customer customer = new Customer();

        customer.setId(cv.getAsLong(CustomerConst.ID));
        customer.setName(cv.getAsString(CustomerConst.NAME));
        customer.setLastName(cv.getAsString(CustomerConst.LAST_NAME));
        customer.setEmail(cv.getAsString(CustomerConst.EMAIL));
        customer.setCreditCard(cv.getAsString(CustomerConst.CREDIT_CARD));
        customer.setPhoneNumber(cv.getAsString(CustomerConst.PHONE_NUMBER));
        customer.setPassword(cv.getAsString(CustomerConst.PASSWORD));

        return customer;
    }

    public static Reservation ContentValuesToReservation(ContentValues cv) {

        Reservation reservation = new Reservation();
        reservation.setCustomerNumber(cv.getAsLong(ReservationConst.CUSTOMER_NUMBER));
        reservation.setOpen(cv.getAsBoolean(ReservationConst.IS_OPEN));
        reservation.setPreKMCount(cv.getAsDouble(ReservationConst.PRE_KM_COUNT));
        reservation.setPostKMCount(cv.getAsDouble(ReservationConst.POST_KM_COUNT));
        reservation.setRentBeginning(cv.getAsString(ReservationConst.RENT_BEGINNING));
        reservation.setRentEnd(cv.getAsString(ReservationConst.RENT_END));
        reservation.setWasRefueled(cv.getAsBoolean(ReservationConst.WAS_REFUELED));
        reservation.setLitersRefueled(cv.getAsDouble(ReservationConst.LITERS_REFUELED));
        reservation.setReservationNumber(cv.getAsLong(ReservationConst.RESERVATION_NUMBER));
        reservation.setPrice(cv.getAsDouble(ReservationConst.TOTAL_PRICE));
        reservation.setCarNumber(cv.getAsLong(ReservationConst.CAR_NUMBER));

        return reservation;
    }

}
