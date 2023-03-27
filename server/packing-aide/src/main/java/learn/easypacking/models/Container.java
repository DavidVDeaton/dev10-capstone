package learn.easypacking.models;

public class Container {
    private int containerId;

    public int getContainerId() {
        return containerId;
    }

    public void setContainerId(int containerId) {
        this.containerId = containerId;
    }

    public int getParentContainerId() {
        return parentContainerId;
    }

    public void setParentContainerId(int parentContainerId) {
        this.parentContainerId = parentContainerId;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    private int parentContainerId;

    private String containerName;

    private int eventId;


}
