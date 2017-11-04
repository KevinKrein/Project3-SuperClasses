import processing.core.PImage;

import java.util.List;

public abstract class DoesSomething extends Entity{

    private int actionPeriod;

    public DoesSomething(String id, Point position, int actionPeriod, List<PImage> images)
    {
        super(id, position, images);
        this.actionPeriod = actionPeriod;
    }

    public Activity createActivityAction(WorldModel world,
                                         ImageStore imageStore)
    {
        return new Activity(this, world, imageStore);
    }


    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore){
        scheduler.scheduleEvent(this,
                createActivityAction(world, imageStore),
                actionPeriod);
    }

    public int getActionPeriod()
    {
        return actionPeriod;
    }

    public abstract void executeActivity(WorldModel world,
                                ImageStore imageStore, EventScheduler scheduler);

}
