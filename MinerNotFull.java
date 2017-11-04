import processing.core.PImage;

import java.util.List;
import java.util.Optional;

public class MinerNotFull extends Miner{
    private int resourceCount;

    public MinerNotFull(String id, int resourceLimit,
                        Point position, int actionPeriod, int animationPeriod,
                        List<PImage> images)
    {
        super(id, resourceLimit, position, actionPeriod, animationPeriod, images);
        this.resourceCount = 0;
    }

    public void scheduleActions(EventScheduler scheduler,
                                WorldModel world, ImageStore imageStore)
    {
        scheduler.scheduleEvent(this,
                createActivityAction(world, imageStore),
                super.getActionPeriod());
        scheduler.scheduleEvent(this,
                createAnimationAction(0), super.getAnimationPeriod());
    }

    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler)
    {
        Entity notFullTarget = world.findNearest(super.getPosition(),
                "Ore");

        if (notFullTarget==null ||
                !super.moveTo(world, notFullTarget, scheduler) ||
                !transformNotFull(world, scheduler, imageStore))
        {
            scheduler.scheduleEvent(this,
                    createActivityAction(world, imageStore),
                    super.getActionPeriod());
        }
    }

    /*public Miner transformHelper(WorldModel world, EventScheduler scheduler, ImageStore imageStore)
    {
        if (resourceCount >= super.getResourceLimit())
        {
            return new MinerFull(super.getID(), super.getResourceLimit(),
                    super.getPosition(), super.getActionPeriod(), super.getAnimationPeriod(),
                    super.getImages());
        }
        return this;
    }*/

    private boolean transformNotFull(WorldModel world,
                                     EventScheduler scheduler, ImageStore imageStore)
    {
        if (resourceCount >= super.getResourceLimit())
        {
            MinerFull fullMiner = new MinerFull(super.getID(), super.getResourceLimit(),
                    super.getPosition(), super.getActionPeriod(), super.getAnimationPeriod(),
                    super.getImages());

            world.removeEntity(this);
            scheduler.unscheduleAllEvents(this);

            world.addEntity(fullMiner);
            fullMiner.scheduleActions(scheduler, world, imageStore);

            return true;
        }

        return false;
    }

    public Point nextPositionHelper(WorldModel world,
                                    Point destPos, int horiz)
    {
        Point newPos = new Point(getPosition().getX() + horiz,
                getPosition().getY());

        if (horiz == 0 || world.isOccupied(newPos))
        {
            int vert = Integer.signum(destPos.getY() - getPosition().getY());
            newPos = new Point(getPosition().getX(),
                    getPosition().getY() + vert);

            if (vert == 0 || world.isOccupied(newPos))
            {
                newPos = getPosition();
            }
        }

        return newPos;
    }

    public boolean moveToHelper(WorldModel world,
                                Entity target, EventScheduler scheduler)
    {
        resourceCount = resourceCount + 1;
        world.removeEntity(target);
        scheduler.unscheduleAllEvents(target);

        return true;
    }

}
