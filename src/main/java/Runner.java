// Created by User on 16.06.2018
// Project: BDayDateBotTg
// Target: main classты админ


import bot.Bot;
import bot.DateChecker;
import org.aopalliance.reflect.Class;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Date thisDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM -yyyy");
        String today = sdf.format(thisDate);
        boolean flag = true;
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
        while (true){                   //////////   todo: check once 12 hours
            try {
                Thread.sleep(3000);
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
                    new DateChecker().checkDate();

                    Calendar c = Calendar.getInstance();
                    c.setTime(thisDate);
                    c.add(Calendar.DATE, 1);
                    thisDate = c.getTime();
                    today = sdf.format(thisDate);

                    flag = false;

                }
            } catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
