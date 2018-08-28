// Created by User on 16.06.2018
// Project: BDayDateBotTg
// Target: main class

import bot.Bot;
import bot.DateChecker;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import threads.BotThread;
import threads.DateCheckerThread;

import java.sql.SQLException;


public class Runner {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
       Runnable rBot = () -> {
           new BotThread().run();
       };
       Thread botThread = new Thread(rBot);
       botThread.start();

       Runnable rDateChecker = () -> {
         new DateCheckerThread().run();
       };
       Thread dateCheckerThread = new Thread(rDateChecker);
       dateCheckerThread.start();



    }
}
