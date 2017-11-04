import processing.core.PImage;
import java.util.List;

public class Quake extends Animated{

    public static final String QUAKE_ID = "quake";
    public static final int QUAKE_ACTION_PERIOD = 1100;
    public static final int QUAKE_ANIMATION_PERIOD = 100;
    public static final int QUAKE_ANIMATION_REPEAT_COUNT = 10;


    public Quake(Point position, List<PImage> images)
    {
        super(QUAKE_ID, position, QUAKE_ACTION_PERIOD, QUAKE_ANIMATION_PERIOD, images);
    }

    public void executeActivity(WorldModel world,
                                     ImageStore imageStore, EventScheduler scheduler)
    {
        scheduler.unscheduleAllEvents(this);
        world.removeEntity(this);
    }

    public void scheduleActions(EventScheduler scheduler,
                                WorldModel world, ImageStore imageStore) {
        scheduler.scheduleEvent(this,
                createActivityAction(world, imageStore),
                QUAKE_ACTION_PERIOD);

        scheduler.scheduleEvent(this,
                createAnimationAction(QUAKE_ANIMATION_REPEAT_COUNT),
                QUAKE_ANIMATION_PERIOD);
    }
}
