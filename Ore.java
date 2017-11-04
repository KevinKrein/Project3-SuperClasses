import processing.core.PImage;
import java.util.List;

public class Ore extends DoesSomething {

    public Ore(String id, Point position, int actionPeriod, List<PImage> images)
    {
        super(id, position, actionPeriod, images);
    }

    public void executeActivity(WorldModel world,
                                   ImageStore imageStore, EventScheduler scheduler)
    {
        Point pos = super.getPosition();  // store current position before removing

        world.removeEntity(this);
        scheduler.unscheduleAllEvents(this);

        OreBlob blob = new OreBlob(super.getID() + Parser.BLOB_ID_SUFFIX,
                pos, super.getActionPeriod() / Parser.BLOB_PERIOD_SCALE,
                Parser.BLOB_ANIMATION_MIN +
                        Parser.rand.nextInt(Parser.BLOB_ANIMATION_MAX - Parser.BLOB_ANIMATION_MIN),
                imageStore.getImageList(Parser.BLOB_KEY));

        world.addEntity(blob);
        blob.scheduleActions(scheduler, world, imageStore);
    }
}
