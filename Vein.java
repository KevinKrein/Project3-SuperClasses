import processing.core.PImage;
import java.util.List;
import java.util.Optional;

public class Vein extends DoesSomething {

    public Vein(String id, Point position, int actionPeriod,
                                    List<PImage> images)
    {
       super(id, position, actionPeriod, images);
    }

    public void executeActivity(WorldModel world,
                                    ImageStore imageStore, EventScheduler scheduler)
    {
        Optional<Point> openPt = world.findOpenAround(super.getPosition());

        if (openPt.isPresent())
        {
            Ore ore = new Ore(Parser.ORE_ID_PREFIX + super.getID(),
                    openPt.get(), Parser.ORE_CORRUPT_MIN +
                            Parser.rand.nextInt(Parser.ORE_CORRUPT_MAX - Parser.ORE_CORRUPT_MIN),
                    imageStore.getImageList(Parser.ORE_KEY));
            world.addEntity(ore);
            ore.scheduleActions(scheduler, world, imageStore);
        }

        scheduler.scheduleEvent(this,
                this.createActivityAction(world, imageStore),
                super.getActionPeriod());
    }

}
