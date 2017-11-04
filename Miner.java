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

    public int getResourceLimit()
    {
        return resourceLimit;
    }
}
