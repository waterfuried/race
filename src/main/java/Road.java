public class Road extends Stage {
    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }

    @Override public void go(Car c) {
        try {
            System.out.println(c.getName() + " начал этап: " + getDescription());
            Thread.sleep(length / c.getSpeed() * 1000L);
            System.out.println(c.getName() + " закончил этап: " + getDescription());
        } catch (InterruptedException ex) { ex.printStackTrace(); }
    }
}