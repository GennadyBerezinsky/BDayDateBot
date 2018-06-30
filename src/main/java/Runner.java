// Created by User on 16.06.2018
// Project: BDayDateBotTg
// Target: main class

import bot.Bot;
import bot.DateChecker;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.sql.SQLException;


public class Runner {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
        while (true){
            new DateChecker().runChecker();
        }
    }
}
