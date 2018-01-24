package com.revenant.takego_secondapp.model.backend;

import com.revenant.takego_secondapp.model.datasource.DB_SQL;

/**
 * Created by Or on 22-Jan-18.
 */

public class DBManagerFactory {
    public static DB_Manager getDB_SQL() {return DB_SQL.getInstance();}
}
