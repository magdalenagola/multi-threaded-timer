import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    static Map<String, Timer> timers = new HashMap<>();

    public static void main(String[] args) {
        String command;
        do {
            String[] splitCommand = getUserInput();
            String timerName = getTimerName(splitCommand);
            command = getCommand(splitCommand);
            switch (command) {
                case "start":
                    startTimer(timerName);
                    break;
                case "stop":
                    stopTimer(timerName);
                    break;
                case "check":
                    checkTimer(timerName);
                    break;
                case "exit":
                    interruptAllTimers();
                    break;
                default:
                    System.out.println("Command is not valid");
            }
        } while (!command.equals("exit"));
    }

    private static String[] getUserInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Command?");
        return sc.nextLine().split(" ");
    }

    private static String getTimerName(String[] splitCommand) {
        return splitCommand.length > 1 ? splitCommand[1] : "";
    }

    private static String getCommand(String[] splitCommand) {
        return splitCommand[0];
    }

    private static void startTimer(String timerName) {
        if (!timers.containsKey(timerName)) addNewTimer(timerName);
        else restartTimer(timerName);
    }

    private static void restartTimer(String timerName) {
        Timer stoppedTimer = timers.get(timerName);
        timers.remove(timerName);
        Timer restartedTimer = new Timer(timerName, stoppedTimer.getCounter());
        timers.put(timerName, restartedTimer);
        restartedTimer.start();
    }

    private static void addNewTimer(String timerName) {
        Timer timer = new Timer(timerName);
        timer.start();
        timers.put(timerName, timer);
    }

    private static void checkTimer(String timerName) {
        if (timerName.isEmpty()) printAllTimers();
        else printTimer(timers.get(timerName));
    }

    private static void interruptAllTimers() {
        timers.forEach((key, value) -> value.interrupt());
        printAllTimers();
    }

    private static void stopTimer(String timerName) {
        Timer timerToStop = timers.get(timerName);
        printTimer(timerToStop);
        timerToStop.interrupt();
    }

    private static void printTimer(Timer timer) {
        System.out.println("Name: " + timer.getName()
                + " ThreadId: " + timer.getId()
                + " Seconds: " + timer.getCounter());
    }

    private static void printAllTimers() {
        timers.forEach((key, value) -> printTimer(value));
    }
}
