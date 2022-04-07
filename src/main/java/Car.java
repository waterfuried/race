import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private final Race race;
    private final int speed;
    private final String name;
    private final CyclicBarrier barrier;

    static { CARS_COUNT = 0; }

    public Car(int speed, Race race, CyclicBarrier barrier) {
        this.race = race;
        this.speed = speed;
        this.name = "Участник #" + ++CARS_COUNT;
        this.barrier = barrier;
    }

    public String getName() { return name; }
    public int getSpeed() { return speed; }

    @Override public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");

            // каждая машина (участник)...
            // ...ожидает на старте готовности всех других
            barrier.await();

            // ...проезжает трассу, в первой закончившей - победитель
            for (Stage stage : race.getStages()) stage.go(this);
            if (MainClass.winner == null) MainClass.winner = getName();

            // ...ожидает на финише приезда остальных участников
            barrier.await();
        } catch (Exception ex) { ex.printStackTrace(); }
    }
}