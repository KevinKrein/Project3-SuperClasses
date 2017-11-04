final class Animation implements Action {
    private int repeatCount;
    private Animated animated;

    public Animation(Animated animated, int repeatCount)
    {
        this.animated = animated;
        this.repeatCount = repeatCount;
    }

    public void executeAction(EventScheduler scheduler){
        animated.nextImage();

        if (repeatCount != 1)
        {
            scheduler.scheduleEvent(animated,
                    animated.createAnimationAction(Math.max(repeatCount - 1, 0)),
                    animated.getAnimationPeriod());
        }
    }

    public Entity getEntity(){
        return animated;
    }

}
