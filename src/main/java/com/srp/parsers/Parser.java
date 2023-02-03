package com.srp.parsers;

import java.io.*;
import java.sql.*;
import java.util.*;

import org.json.*;


public class Parser {
    static Scanner console = new Scanner(System.in);
    static String fileName;
    static String SQLCommand = "";                                                                  //Keeps track of SQL command


    public static String getSQLCommand( String fileName, String jsonString ) {


        JSONArray jsonArray = new JSONArray(jsonString);

        JSONSchema schema = new JSONSchema(fileName.substring(0,fileName.length()-4), jsonArray.getJSONObject(0).keys());                     //Reads Schema from JSON


        SQLCommand = SQLCommand + "--Schema Setup\n" + schema.toString() + "\n";

        JSONObject tuple;

        for ( Object jsonObject : jsonArray ) {
            tuple = (JSONObject) jsonObject;
            String command = "INSERT INTO TABLE " + schema.getTableName() + "\n" + "VALUES(";
            for ( String x : schema.getAtt() ) {
                command = command + "\'" + tuple.get(x) + "\'" + ",";
            }
            command = command.substring(0, command.length() - 1) + ")" + "\n";
            SQLCommand = SQLCommand  + "\n" + command + "\n";
        }
        System.out.println(SQLCommand);


        writeToFile(fileName);

        return SQLCommand;

    }

    public static void writeToFile( String fileName ) {                                             //
        try {
            FileWriter fstream = new FileWriter(fileName);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(SQLCommand);
            out.close();
        } catch ( Exception e ) {
            System.err.println("Error: " + e.getMessage());
        }
    }


}
