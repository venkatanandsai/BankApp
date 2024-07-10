package com.revature.Utility;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Stream;

public class DatabaseScriptRunner {

    public static void main(String[] args) {

        Path scriptPath = Paths.get("src/main/resources/bank.sql");
        try{

            try(Connection connection = DatabaseConnector.createConnection();
                Stream<String> lines = Files.lines(scriptPath)){

                connection.setAutoCommit(false);
                StringBuilder sqlBuilder = new StringBuilder();
                lines.forEach(sqlBuilder::append);
                String sql = sqlBuilder.toString();
                String[] stmts = sql.split(";");
                for(String stmt : stmts){
                    Statement statement = connection.createStatement();
                    statement.executeUpdate(stmt);
                }
                connection.commit();

            }

        }
        catch (SQLException | IOException e){

            System.out.println(e.getMessage());

        }

    }

}
