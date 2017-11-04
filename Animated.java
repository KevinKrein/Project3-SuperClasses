import processing.core.PImage;

import java.util.List;
import java.util.Optional;

public abstract class Animated extends DoesSomething{

    private int animationPeriod;

    public Animated(String id, Point position,
                   int actionPeriod, int animationPeriod, List<PImage> images)
    {
        super(id, position, actionPeriod, images);
        this.animationPeriod = animationPeriod;
    }

    public int getAnimationPeriod()
    {
        return animationPeriod;
    }

    public Animation createAnimationAction(int repeatCount)
    {
        return new Animation(this, repeatCount);
    }


}
