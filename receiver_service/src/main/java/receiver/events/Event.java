package receiver.events;

public abstract class Event<Model> {
    private Model value;

    Model getModel() {
        return value;
    }

    public void setValue(Model value) {
        this.value = value;
    }

    public Model getValue() {
        return this.value;
    }
}
