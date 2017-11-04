import processing.core.PImage;

import java.util.List;
import java.util.Optional;

public class MinerFull extends Miner{

    public MinerFull(String id, int resourceLimit,
                                         Point position, int actionPeriod, int animationPeriod,
                                         List<PImage> images)
    {
        super(id, resourceLimit, position, actionPeriod, animationPeriod, images);
    }

    public void executeActivity(WorldModel world,
                                         ImageStore imageStore, EventScheduler scheduler)
    {
        Entity fullTarget = world.findNearest(getPosition(),
                "Blacksmith");

        if (fullTarget != null &&
                super.moveTo(world, fullTarget, scheduler))
        {
            transformFull(world, scheduler, imageStore);
        }
        else
        {
            scheduler.scheduleEvent(this,
                    createActivityAction(world, imageStore),
                    super.getActionPeriod());
        }
    }



    private void transformFull(WorldModel world,
                               EventScheduler scheduler, ImageStore imageStore)
    {
        MinerNotFull emptyMiner = new MinerNotFull(super.getID(), super.getResourceLimit(),
                super.getPosition(), super.getActionPeriod(), super.getAnimationPeriod(),
                super.getImages());

        world.removeEntity(this);
        scheduler.unscheduleAllEvents(this);

        world.addEntity(emptyMiner);
        emptyMiner.scheduleActions(scheduler, world, imageStore);
    }

    public Point nextPositionHelper(WorldModel world,
                                    Point destPos, int horiz) {
        Point newPos = new Point(getPosition().getX() + horiz,
                getPosition().getY());

        if (horiz == 0 || world.isOccupied(newPos)) {
            int vert = Integer.signum(destPos.getY() - getPosition().getY());
            newPos = new Point(getPosition().getX(),
                    getPosition().getY() + vert);

            if (vert == 0 || world.isOccupied(newPos)) {
                newPos = getPosition();
            }
        }

        return newPos;
    }

    public boolean moveToHelper(WorldModel world,
                                Entity target, EventScheduler scheduler)
    {
        return true;
    }
}
