package threads;
// Created by User on 25.08.2018
// Project: BDayDateBotTg
// Target: 


import bot.DateChecker;

public class DateCheckerThread implements Runnable {
    @Override
    public void run() {
        while (true){
            new DateChecker().runChecker();
        }
    }
}
