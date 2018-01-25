package com.revenant.takego_secondapp.model.backend;

import android.content.ContentValues;

import java.util.List;
import com.revenant.takego_secondapp.model.entities.*;

/**
 * Created by Or on 22-Jan-18.
 */


public interface DB_Manager {


    long addCustomer(ContentValues newCustomer);
    long addModel(ContentValues newModel);
    long addCar(ContentValues newCar);
    long addBranch(ContentValues newBranch);
    long addReservation(ContentValues newReservation);
    long updateCar(ContentValues newCar);
    long removeReservation(long id);
    void updateReservation(long id, ContentValues newRes);


    Branch findBranchById(long branchId);
    Reservation findReservationById(long reservationId);
    Customer findCustomerbyId(long id);


    List<Car> allCars();
    List<CarModel> allCarModels();
    List<Branch> allBranches();
    List<Customer> allCustomers();
    List<Reservation> allReservations();
    List<Car> allAvailableCars();
    List<Car> availableCarsForBranch(long branchId);
    List<Branch> branchesWithAvailableModel(long carModelId);
    List<Reservation> openReservations();
    List<Reservation> userOpenReservations(long userId);
    boolean closeReservation(long reservationId, double mileage, boolean refueled, double liters, String dateEnd);



}
