import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    public Tunnel(int length, int restriction) {
        this.length = length;
        this.description = "Тоннель " + length + " метров";
        this.restriction = restriction;
    }

    @Override public void go(Car c) {
        Semaphore restrict = new Semaphore(restriction, true);
        try {
            System.out.println(c.getName() + " готовится к этапу(ждет): " + getDescription());
            restrict.acquire();
            System.out.println(c.getName() + " начал этап: " + getDescription());
            Thread.sleep(length / c.getSpeed() * 1000L);
        } catch (InterruptedException ex) { ex.printStackTrace(); }
        finally {
            restrict.release();
            System.out.println(c.getName() + " закончил этап: " + getDescription());
        }
    }
}