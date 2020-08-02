package thread.creation;

public class RunnableInterface {
    public static void main(String[] args){
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run(){
                // Code that runs into a new thread
                System.out.println("We are in Thread " + Thread.currentThread().getName());
                System.out.println("Current thread priority is " + Thread.currentThread().getPriority());
            }
        });
        // Use of lambda in Java 1.8+ for compactness
        /*Thread thread1 = new Thread(()->{
            //Code that will run in a new thread
        });*/

        //Providing a name which helps in debugging purposes
        thread.setName("New Worker Thread");

        //Setting a priority for the threads which need more responsiveness
        thread.setPriority(Thread.MAX_PRIORITY);

        //Start the thread by calling start()
        //This instructs the JVM to create a thread and pass on to the OS

        System.out.println("We are in thread: " + Thread.currentThread().getName() + " before starting a new thread");
        thread.start();
        System.out.println("We are in thread: " + Thread.currentThread().getName() + " after starting a new thread");

        //Tells the OS not to schedule the thread for the given time
        try {
            Thread.sleep(1000);
        }catch (Exception e){
            System.out.println(e.getCause());
        }
    }
}
