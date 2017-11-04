import processing.core.PImage;

import java.util.List;
import java.util.Optional;

public abstract class Moves extends Animated{

    public Moves(String id, Point position,
                    int actionPeriod, int animationPeriod, List<PImage> images)
    {
        super(id, position, actionPeriod, animationPeriod, images);
    }

    public boolean moveTo(WorldModel world,
                          Entity target, EventScheduler scheduler)
    {
        if (super.getPosition().adjacent(target.getPosition()))
        {
            return moveToHelper(world, target, scheduler);
        }
        else
        {
            Point nextPos = nextPosition(world, target.getPosition());

            if (!super.getPosition().equals(nextPos))
            {
                Optional<Entity> occupant = world.getOccupant(nextPos);
                if (occupant.isPresent())
                {
                    scheduler.unscheduleAllEvents(occupant.get());
                }

                world.moveEntity(this, nextPos);
            }
            return false;
        }
    }

    public Point nextPosition(WorldModel world,
                              Point destPos)
    {
        int horiz = Integer.signum(destPos.getX() - super.getPosition().getX());
        return nextPositionHelper(world, destPos, horiz);
    }

    public abstract Point nextPositionHelper(WorldModel world, Point destPos, int horiz);
    public abstract boolean moveToHelper(WorldModel world,
                                         Entity target, EventScheduler scheduler);
}
