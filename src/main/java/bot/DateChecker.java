package bot;

// Created by User on 16.06.2018
// Project: BDayDateBotTg
// Target: class with database & date checker

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateChecker {




    public void newDate(String userID, String firstName, String dateString, String chatID) throws ClassNotFoundException, SQLException {
            boolean flag = false;
            String userName = "root";
            String password = "***"; //password was removed
            String connectionURL = "***";//connection URL was removed
            java.lang.Class.forName("com.mysql.jdbc.Driver");

            String valdates = userID + ", " + dateString;
            String valusers = userID + ", " + chatID;

            try(Connection connection = DriverManager.getConnection(connectionURL, userName, password);
                Statement statement = connection.createStatement()){
                statement.executeUpdate("insert into dates (UserID, Date, UserName, ChatID) VALUES ('" + userID +
                        "', '"+dateString+"', '"+firstName+"' , '" + chatID + "')");
                System.out.println("LOG: insert compleate");
            }
    }

    public void checkDate() throws ClassNotFoundException, SQLException {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        date.setTime(date.getTime());
        String today = format.format(date).toString();
        String userName = "root";
        String password = "***";//password was removed
        String connectionURL = "***"; //connection URL was removed
        java.lang.Class.forName("com.mysql.jdbc.Driver");


        try(Connection connection = DriverManager.getConnection(connectionURL, userName, password);
            Statement statement = connection.createStatement()){
                ResultSet rs = statement.executeQuery("select * from dates");
                while (rs.next()){
                    String userId = rs.getString(2);
                    String dateS = rs.getString(3);
                    String name = rs.getString(4);
                    String chat = rs.getString(5);
                    String todayDM = today.substring(0, 6);
                    String dateSDM = dateS.substring(0, 6);
                    if(todayDM.equals(dateSDM)){
                        new Bot().sendMsg(chat, "С днюхой, " + name + "!!!");
                    }

                }
        }
    }
}
