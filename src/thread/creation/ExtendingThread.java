package thread.creation;

public class ExtendingThread {
    public static void main(String[] args) {
        Thread thread = new NewThread();
        thread.start();
    }

    public static class NewThread extends Thread {
        @Override
        public void run(){
            //System.out.println("Hello from " + Thread.currentThread().getName());
            //By extending Thread class, we get all the static methods through 'this' object of the current thread
            System.out.println("Hello from " + this.getName());
        }
    }
}
