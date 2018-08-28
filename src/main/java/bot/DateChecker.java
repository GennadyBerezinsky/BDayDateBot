package bot;

// Created by User on 16.06.2018
// Project: BDayDateBotTg
// Target: class with database & date checker

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateChecker implements ConstStrings  {
    //Inteface ConstStrings contains some contants such as bot token, passwords from database, etg..

        public void newDate(String userID, String firstName, String dateString, String chatID) throws ClassNotFoundException, SQLException {
            boolean flag = false;

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

    public void runChecker(){
        Date thisDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM -yyyy");
        String today = sdf.format(thisDate);
        boolean flag = true;
        try {
            Thread.sleep(300000);
            Date date = new Date();
            String check = sdf.format(date);
            System.out.println(today);
            System.out.println(check);
            if(check.equals(today)){
                System.out.println("date ok");
                flag = true;
            }
            if(flag && check.equals(today)){


                System.out.println("checking date");
                this.checkDate();

                Calendar c = Calendar.getInstance();
                c.setTime(thisDate);
                c.add(Calendar.DATE, 1);
                thisDate = c.getTime();
                today = sdf.format(thisDate);

                flag = false;

            }
        } catch (InterruptedException e){
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void checkDate() throws ClassNotFoundException, SQLException {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        date.setTime(date.getTime());
        String today = format.format(date).toString();
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
