package Concepts;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Non-Thread version
class DateDisplayer1 {

    // Instance field
    LocalDateTime now;
    DateTimeFormatter formatter;

    // DateDisplayer Method
    void print() {
        // Local variable
        String curTime;
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Loop for print current times
        for(int i=0 ; i<7 ; i++) {
            // get current time
            now = LocalDateTime.now();
            // apply formatter
            curTime = now.format(formatter);

            // Try ~ catch : To handle 'InterruptException'
            try {
                // print the current time
                System.out.printf("Current datetime -> %s\n", curTime);
                // Sleep for 1 second
                Thread.sleep(1000);
            }
            catch(InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}

// Thread Version
class DateDisplayer2 extends Thread {

    // Instance field
    LocalDateTime now;
    DateTimeFormatter formatter;

    // Override the run method
    @Override
    public void run() {
        // Local variable
        String curTime;
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Loop for print current times
        for(int i=0 ; i<7 ; i++) {
            // get current time
            now = LocalDateTime.now();
            // apply formatter
            curTime = now.format(formatter);

            try {
                System.out.printf("(%s) Current datetime -> %s\n", this.getName(), curTime);
                Thread.sleep(1000);
            }
            catch(InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}

public class Tester {

    public static void main(String[] args) {

        // Time variable
        long startingTime;
        long finishedTime;
        long processedTime1;
        long processedTime2;
        double timeDiff;

        // Create an instance of DateDisplayer1
        DateDisplayer1 date1 = new DateDisplayer1();
        // invoke the print() method 3 times
        startingTime = System.currentTimeMillis(); // To calculate the total processing time
        System.out.println("< Task 1 >");
        for(int i=0 ; i<3 ; i++) {
            System.out.printf("(Task 1-%d)\n", i + 1);
            date1.print();
            System.out.println(); // 줄바꿈
        }
        finishedTime = System.currentTimeMillis();
        processedTime1 = finishedTime - startingTime;
        System.out.printf("Total processing time : %d(ms)\n", processedTime1);

        System.out.println(); // 줄바꿈

        // Create 3 instances of DataDisplayer2
        DateDisplayer2 date2_1 = new DateDisplayer2();
        DateDisplayer2 date2_2 = new DateDisplayer2();
        DateDisplayer2 date2_3 = new DateDisplayer2();
        // start the 3 threads
        startingTime = System.currentTimeMillis(); // To calculate the total processing time
        System.out.println("< Task 2 >");
        date2_1.start(); date2_2.start(); date2_3.start();
        // check the finishedTime when all threads have done
        while(date2_1.isAlive() || date2_2.isAlive() || date2_3.isAlive())
            finishedTime = System.currentTimeMillis();
        processedTime2 = finishedTime - startingTime;
        System.out.println(); // 줄바꿈
        System.out.printf("Total processing time : %d(ms)\n", processedTime2);

        System.out.println(); // 줄바꿈

        // Print the time difference between the 2 total processing time
        System.out.println("< Task 3 >");
        // convert the time units
        timeDiff = Math.abs(processedTime1 - processedTime2) / 1000.0;
        System.out.printf("Time difference = %.1f(secs)", timeDiff);

    }

}
