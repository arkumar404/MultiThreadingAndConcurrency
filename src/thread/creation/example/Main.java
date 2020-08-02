package thread.creation.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static final int MAX_PASSWORD = 9999;
    public static void main(String[] args) {
        Random random = new Random();

        //Sets a random value less than MAX_PASSWORD as a password
        Vault vault = new Vault(random.nextInt(MAX_PASSWORD));

        List<Thread> threads = new ArrayList<>();

        //Adds each thread type for running
        threads.add(new AscendingHackerThread(vault));
        threads.add(new DescendingHackerThread(vault));
        threads.add(new PoliceThread());

        //Starts each thread
        for(Thread thread: threads){
            thread.start();
        }
    }

    //This private class has password as a variable which gets set through the constructor
    //A single method which checks if the 'guess' is correct or not
    private static class Vault{
        private int password;
        public Vault(int password){
            this.password = password;
        }

        public boolean isCorrectPassword(int guess){
            try{
                Thread.sleep(5);
            } catch (InterruptedException e){
                e.getCause();
            }
            return this.password == guess;
        }
    }

    //Encapsulating common functionalities for a group of threads
    private static abstract class HackerThread extends Thread{
        protected Vault vault;

        //Constructor which accepts a vault object and sets name & priority for them
        public HackerThread(Vault vault){
            this.vault = vault;
            this.setName(this.getClass().getSimpleName());
            this.setPriority(Thread.MAX_PRIORITY);
        }

        @Override
        public void start(){
            System.out.println("Starting thread: " + this.getName());
            super.start();
        }
    }

    // Guess the password by iterating through all the numbers in ascending order
    private static class AscendingHackerThread extends HackerThread{

        //Constructor which takes vault object and sets to the parent thread
        public AscendingHackerThread(Vault vault){
            super(vault);
        }

        @Override
        public void run(){
            for (int guess=0; guess<MAX_PASSWORD; guess++){
                if (vault.isCorrectPassword(guess)) {
                    System.out.println(this.getName() + " guessed the correct password: " + guess);
                    System.exit(0);
                }
            }
        }
    }

    // Guess the password by iterating through all the numbers in descending order
    private static class DescendingHackerThread extends HackerThread{
        public DescendingHackerThread(Vault vault){
            super(vault);
        }

        @Override
        public void run(){
            for (int guess=MAX_PASSWORD; guess>0; guess--){
                if(vault.isCorrectPassword(guess)) {
                    System.out.println(this.getName() + " guessed the password: " + guess);
                    System.exit(0);
                }
            }
        }
    }

    //Police thread which countdown for ten seconds and after that terminates the program
    private static class PoliceThread extends Thread{

        @Override
        public void run(){
            for(int i=10; i>0; i--){
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    System.out.println(e.getMessage());
                }
                System.out.println(i);
            }
            System.out.println("Game over for you Hackers !!");
            System.exit(0);
        }
    }

    //Note: Always use curly braces even only one statement is used with the clause
}
