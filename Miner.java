import processing.core.PImage;

import java.util.List;
import java.util.Optional;

public abstract class Miner extends Moves{
    private int resourceLimit;

    public Miner(String id, int resourceLimit,
                     Point position, int actionPeriod, int animationPeriod,
                     List<PImage> images)
    {
        super(id, position, actionPeriod, animationPeriod, images);
        this.resourceLimit = resourceLimit;

    }

    /*public boolean transform(WorldModel world,
                                     EventScheduler scheduler, ImageStore imageStore)
    {
        Miner miner = transformHelper(world, scheduler, imageStore);
        world.removeEntity(this);
        scheduler.unscheduleAllEvents(this);

        world.addEntity(miner);
        miner.scheduleActions(scheduler, world, imageStore);
        if(this == miner){
            return true;
        }
        return false;
    }*/

    public int getResourceLimit()
    {
        return resourceLimit;
    }

    /*public abstract Miner transformHelper(WorldModel world,
                                          EventScheduler scheduler, ImageStore imageStore);*/
}
