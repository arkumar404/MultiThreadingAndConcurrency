package thread.creation;

public class RuntimeEx {
    public static void main(String[] args){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                throw new RuntimeException("Intentional Exception");
            }
        });

        thread.setName("Misbehaving thread");

        //Helpful in debugging and catching the exception which otherwise brings down the whole thread
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){
            @Override
            public void uncaughtException(Thread t, Throwable e){
                System.out.println("A critical error has happened in thread: " + t.getName()
                        + " the error is: " + e.getMessage());
            }
        });

        thread.start();
    }
}
