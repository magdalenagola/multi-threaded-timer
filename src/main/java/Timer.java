public class Timer extends Thread {

    private int counter;


    Timer(String name){
        super(name);
    }

    Timer(String name, int counter){
        super(name);
        this.counter = counter;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.printf("%s received interrupt while sleeping\n", super.getName());
                return;
            }
            counter++;
        }
    }

    public int getCounter() {
        return counter;
    }
}


