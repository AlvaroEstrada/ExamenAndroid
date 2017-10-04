package com.alvaropedrajas.examenandroid;

import android.provider.BaseColumns;

/**
 * Created by Alvaro on 04/10/2017.
 */

public class DataBase {

    private DataBase(){}

    public static class DB_Table implements BaseColumns {
        public static final String TABLE_NAME = "datosPersonales";
        public static final String COLUMN_NAME_1 = "Id";
        public static final String COLUMN_NAME_2 = "Nombre";
        public static final String COLUMN_NAME_3 = "Apellido";

    }

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DB_Table.TABLE_NAME + " (" +
                    DB_Table.COLUMN_NAME_1 + " INTEGER PRIMARY KEY," +
                    DB_Table.COLUMN_NAME_2 + TEXT_TYPE + COMMA_SEP +
                    DB_Table.COLUMN_NAME_3 + TEXT_TYPE + " )";

    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DB_Table.TABLE_NAME;
}
