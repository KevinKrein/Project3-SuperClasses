final class Activity implements Action {
    private DoesSomething item;
    private WorldModel world;
    private ImageStore imageStore;

    public Activity(DoesSomething item, WorldModel world,
                   ImageStore imageStore)
    {
        this.item = item;
        this.world = world;
        this.imageStore = imageStore;
    }

    public void executeAction(EventScheduler scheduler){
        item.executeActivity(world,
                        imageStore, scheduler);
    }
    public Entity getEntity(){
        return item;
    }
}
