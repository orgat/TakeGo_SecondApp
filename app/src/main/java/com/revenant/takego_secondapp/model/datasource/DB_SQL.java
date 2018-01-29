package com.revenant.takego_secondapp.model.datasource;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.revenant.takego_secondapp.model.backend.Constants;
import com.revenant.takego_secondapp.model.backend.DB_Manager;
import com.revenant.takego_secondapp.model.entities.*;
import com.revenant.takego_secondapp.util.PHPTools;

/**
 * Created by Or on 22-Jan-18.
 */

public class DB_SQL implements DB_Manager {
    private final String WEB_URL = "http://ogat.vlab.jct.ac.il/";
    private static DB_SQL instance;


    private DB_SQL() {
    }

    public static DB_SQL getInstance() {
        if (instance == null) {
            instance = new DB_SQL();
        }
        return instance;
    }



    @Override
    public long addCustomer(ContentValues newCustomer) {
        try {
            String result = PHPTools.POST(WEB_URL + "addCustomer.php", newCustomer);
            long id = Long.parseLong(result.replace(" ", ""));
            return id;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }


    @Override
    public long addModel(ContentValues newModel) {
        try {
            String result = PHPTools.POST(WEB_URL + "addCarModel.php", newModel);
            long id = Long.parseLong(result.replace(" ", ""));
            return id;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public long addCar(ContentValues newCar) {
        try {
            String result = PHPTools.POST(WEB_URL + "addCar.php", newCar);
            long id = Long.parseLong(result.replace(" ", ""));
            return id;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public long updateCar(ContentValues newCar) {
        try {
            String result = PHPTools.POST(WEB_URL + "updateCar.php", newCar);
            long id = Long.parseLong(result.replace(" ", ""));
            return id;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public long addBranch(ContentValues newBranch) {
        try {
            String result = PHPTools.POST(WEB_URL + "addBranch.php", newBranch);
            long id = Long.parseLong(result.replace(" ", ""));
            return id;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Branch findBranchById(long branchId) {
        List<Branch> allBranches= allBranches();
        for(Branch b: allBranches){
            if(b.getBranchNumber()==branchId){
                return b;
            }
        }
        return null;
    }

    @Override
    public Reservation findReservationById(long reservationId) {
        List<Reservation> reservations= allReservations();
        for(Reservation r: reservations){
            if(r.getReservationNumber()==reservationId){
                return r;
            }
        }
        return null;
    }

    @Override
    public Customer findCustomerbyId(long id) {
        List<Customer> allCustomers= allCustomers();
        for(Customer c: allCustomers){
            if(c.getId()==id)
                return c;
        }
        return null;
    }

    @Override
    public long addReservation(ContentValues newReservation) {
        try {
            String result = PHPTools.POST(WEB_URL + "addReservation.php", newReservation);
            long id = Long.parseLong(result.replace(" ", ""));
            return id;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<Car> allCars() {
        List<Car> allCars = new ArrayList<>();
        try {
            String str = PHPTools.GET(WEB_URL + "allCars.php");
            JSONArray jsonArray = new JSONObject(str).getJSONArray("cars");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Car car = new Car();
                car.setCarId(jsonObject.getLong(Constants.CarConst.ID));
                car.setModelNumber(jsonObject.getLong(Constants.CarConst.MODEL_NUMBER));
                car.setDefaultBranchNumber(jsonObject.getLong(Constants.CarConst.DEFAULT_BRANCH_NUMBER));
                car.setMileage(jsonObject.getDouble(Constants.CarConst.MILEAGE));
                allCars.add(car);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allCars;
    }

    @Override
    public List<CarModel> allCarModels() {
        List<CarModel> allCarModels = new ArrayList<>();
        try {
            String str = PHPTools.GET(WEB_URL + "allCarModels.php");
            JSONArray jsonArray = new JSONObject(str).getJSONArray("carModels");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                CarModel carModel = new CarModel();
                carModel.setModelNumber(jsonObject.getLong(Constants.CarModelConst.MODEL_NUMBER));
                carModel.setModelName(jsonObject.getString(Constants.CarModelConst.MODEL_NAME));
                carModel.setBrand(jsonObject.getString(Constants.CarModelConst.BRAND));
                carModel.setEngineSize(jsonObject.getInt(Constants.CarModelConst.ENGINE_SIZE));
                carModel.setSeats(jsonObject.getInt(Constants.CarModelConst.SEATS));
                carModel.setGearType(CarModel.Gear.valueOf(jsonObject.getString(Constants.CarModelConst.GEAR_TYPE)));

                allCarModels.add(carModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allCarModels;
    }

    @Override
    public List<Branch> allBranches() {
        List<Branch> allBranches = new ArrayList<>();
        try {
            String str = PHPTools.GET(WEB_URL + "allBranches.php");
            JSONArray jsonArray = new JSONObject(str).getJSONArray("branches");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Branch branch = new Branch();
                branch.setBranchNumber(jsonObject.getLong(Constants.BranchConst.BRANCH_NUMBER));
                branch.setAddress(jsonObject.getString(Constants.BranchConst.ADDRESS));
                branch.setParkingSpaces(jsonObject.getInt(Constants.BranchConst.PARKING_SPACES));

                allBranches.add(branch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allBranches;
    }


    @Override
    public List<Customer> allCustomers() {
        List<Customer> allCustomers = new ArrayList<>();
        try {
            String str = PHPTools.GET(WEB_URL + "allCustomers.php");
            JSONArray jsonArray = new JSONObject(str).getJSONArray("customers");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Customer customer = new Customer();
                customer.setId(jsonObject.getLong(Constants.CustomerConst.ID));
                customer.setName(jsonObject.getString(Constants.CustomerConst.NAME));
                customer.setLastName(jsonObject.getString(Constants.CustomerConst.LAST_NAME));
                customer.setEmail(jsonObject.getString(Constants.CustomerConst.EMAIL));
                customer.setCreditCard(jsonObject.getString(Constants.CustomerConst.CREDIT_CARD));
                customer.setPassword(jsonObject.getString(Constants.CustomerConst.PASSWORD));
                allCustomers.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allCustomers;
    }


    @Override
    public List<Reservation> allReservations() {
        List<Reservation> allReservations = new ArrayList<>();
        try {
            String str = PHPTools.GET(WEB_URL + "allReservations.php");
            JSONArray jsonArray = new JSONObject(str).getJSONArray("reservations");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Reservation reservation = new Reservation();
                reservation.setReservationNumber(jsonObject.getLong(Constants.ReservationConst.RESERVATION_NUMBER));
                reservation.setCustomerNumber(jsonObject.getLong(Constants.ReservationConst.CUSTOMER_NUMBER));
                reservation.setCarNumber(jsonObject.getLong(Constants.ReservationConst.CAR_NUMBER));
                reservation.setOpen(Boolean.valueOf(jsonObject.getString(Constants.ReservationConst.IS_OPEN)));
                reservation.setWasRefueled(Boolean.valueOf(jsonObject.getString(Constants.ReservationConst.WAS_REFUELED)));
                reservation.setPreKMCount(jsonObject.getDouble(Constants.ReservationConst.PRE_KM_COUNT));
                reservation.setPostKMCount(jsonObject.getDouble(Constants.ReservationConst.POST_KM_COUNT));
                reservation.setLitersRefueled(jsonObject.getDouble(Constants.ReservationConst.LITERS_REFUELED));
                reservation.setRentBeginning(jsonObject.getString(Constants.ReservationConst.RENT_BEGINNING));
                reservation.setRentEnd(jsonObject.getString(Constants.ReservationConst.RENT_END));
                reservation.setPrice(jsonObject.getDouble(Constants.ReservationConst.TOTAL_PRICE));
                allReservations.add(reservation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allReservations;
    }

    @Override
    public List<Reservation> openReservations() {
        List<Reservation> openReservations= allReservations();
        for(int i=0;i<openReservations.size();i++){
            if(!openReservations.get(i).isOpen()){
                openReservations.remove(i);
            }
        }
        return openReservations;
    }

    @Override
    public List<Car> allAvailableCars() {

        List<Car> allCars= allCars();
        List<Reservation> allReservations= allReservations();
        for(int i=0;i<allCars.size(); i++){
            for(int j=0;j<allReservations.size(); j++){
                if(allCars.get(i).getCarId()==allReservations.get(j).getCarNumber()){
                    allCars.remove(i);
                }
            }
        }
        return allCars;
    }


    @Override
    public List<Car> availableCarsForBranch(long branchId) {
        List<Car> availableCars= allAvailableCars();
        List<Car> availablePerBranch= new ArrayList<>();
        for(int i=0;i<availableCars.size(); i++){
            if(availableCars.get(i).getDefaultBranchNumber()==branchId){
                availablePerBranch.add(availableCars.get(i));
            }
        }
        return availablePerBranch;
    }

    @Override
    public List<Branch> branchesWithAvailableModel(long carModelId) {
        List<Car> availableCars= allAvailableCars();
        List<Branch> availableBranches= new ArrayList<>();

        for(int i=0;i<availableCars.size(); i++){
            if(availableCars.get(i).getModelNumber()!=carModelId){
                availableCars.remove(i);
            }
        }


        for(int i=0;i<availableCars.size(); i++){
                Branch b= findBranchById(availableCars.get(i).getDefaultBranchNumber());
                availableBranches.add(b);
            }
            return availableBranches;
        }

    @Override
    public boolean closeReservation(long reservationId, double mileage, boolean refueled, double liters, String dateEnd) {
        Reservation reservationToClose = findReservationById(reservationId);
        if(reservationToClose!=null){
            reservationToClose.setPostKMCount(reservationToClose.getPreKMCount()+mileage);
            reservationToClose.setRentEnd(dateEnd);
            reservationToClose.setWasRefueled(refueled);
            reservationToClose.setLitersRefueled(refueled? liters : 0);
            reservationToClose.setOpen(false);
            return true;
        }
        return false;
    }

    @Override
    public List<Reservation> userOpenReservations(long userId) {
        List<Reservation> allRes = allReservations();
        List<Reservation> openRes = new ArrayList<>();
        for(int i=0; i<allRes.size(); i++){
            if(allRes.get(i).getCustomerNumber()==userId && allRes.get(i).isOpen())
                openRes.add(allRes.get(i));
            }

        return openRes;
    }

    @Override
    public long removeReservation(long resId) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constants.ReservationConst.RESERVATION_NUMBER,resId);
            String result = PHPTools.POST(WEB_URL + "removeReservation.php", cv);
            long id = Long.parseLong(result.replace(" ", ""));
            return id;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public void updateReservation(long id, ContentValues newRes) {
        removeReservation(id);
        addReservation(newRes);


    }
}

