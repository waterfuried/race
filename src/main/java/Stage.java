public abstract class Stage {
    protected int length;
    protected String description;
    protected int restriction;

    public String getDescription() { return description; }
    public abstract void go(Car c);
}