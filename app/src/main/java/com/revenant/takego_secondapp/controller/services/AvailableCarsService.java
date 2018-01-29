package com.revenant.takego_secondapp.controller.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.revenant.takego_secondapp.model.backend.Constants;
import com.revenant.takego_secondapp.model.backend.DBManagerFactory;

public class AvailableCarsService extends Service {
    private boolean isRunnning = false;
    private int carListSize;

    public AvailableCarsService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        isRunnning = true;


        Thread thread=new Thread() {

            @Override
            public void run() {
                carListSize= DBManagerFactory.getDB_SQL().allAvailableCars().size();
                while (isRunnning) {

                    try {
                        Thread.sleep(10000);
                        int newCarListSize= DBManagerFactory.getDB_SQL().allAvailableCars().size();
                        if(newCarListSize!=carListSize){
                            carListSize=newCarListSize;
                             sendOutBroadcast();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();

        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        isRunnning=false;
        super.onDestroy();
    }

    private void sendOutBroadcast(){
        Intent i = new Intent();
        i.setAction(Constants.AVAILABLE_CARS_CHANGED);
        i.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(i);
    }
}
