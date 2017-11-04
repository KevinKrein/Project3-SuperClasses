import processing.core.PImage;
import java.util.List;
import java.util.Optional;

public class OreBlob extends Moves{

    public OreBlob(String id, Point position,
                   int actionPeriod, int animationPeriod, List<PImage> images)
    {
        super(id, position, actionPeriod, animationPeriod, images);
    }

    public void executeActivity(WorldModel world,
                                       ImageStore imageStore, EventScheduler scheduler)
    {
        Entity blobTarget = world.findNearest(
                super.getPosition(), "Vein");
        long nextPeriod = super.getActionPeriod();

        if (blobTarget != null)
        {
            Point tgtPos = blobTarget.getPosition();

            if (super.moveTo(world, blobTarget, scheduler))
            {
                Quake quake = new Quake(tgtPos,
                        imageStore.getImageList(Parser.QUAKE_KEY));

                world.addEntity(quake);
                nextPeriod += super.getActionPeriod();
                quake.scheduleActions(scheduler, world, imageStore);
            }
        }

        scheduler.scheduleEvent(this,
                createActivityAction(world, imageStore),
                nextPeriod);
    }

    public Point nextPositionHelper(WorldModel world,
                                    Point destPos, int horiz)
    {
        Point newPos = new Point(super.getPosition().getX() + horiz,
                super.getPosition().getY());

        Optional<Entity> occupant = world.getOccupant(newPos);

        if (horiz == 0 ||
                (occupant.isPresent() && !(occupant.get().getClass().getName().equals("Ore"))))
        {
            int vert = Integer.signum(destPos.getY() - super.getPosition().getY());
            newPos = new Point(super.getPosition().getX(), super.getPosition().getY() + vert);
            occupant = world.getOccupant(newPos);

            if (vert == 0 ||
                    (occupant.isPresent() && !(occupant.get().getClass().getName().equals("Ore"))))
            {
                newPos = super.getPosition();
            }
        }

        return newPos;
    }

    public boolean moveToHelper(WorldModel world,
                                         Entity target, EventScheduler scheduler)
    {
        world.removeEntity(target);
        scheduler.unscheduleAllEvents(target);
        return true;
    }

    public void scheduleActions(EventScheduler scheduler,
                                WorldModel world, ImageStore imageStore)
    {
        scheduler.scheduleEvent(this,
                createActivityAction(world, imageStore),
                super.getAnimationPeriod());
        scheduler.scheduleEvent(this,
                createAnimationAction( 0), super.getAnimationPeriod());
    }



}
