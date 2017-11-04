import processing.core.PImage;

import java.util.List;

public class Entity {
    private String id;
    private Point position;
    private List<PImage> images;
    private int imageIndex;

    public Entity(String id, Point position,
                    List<PImage> images)
    {
        this.id = id;
        this.position = position;
        this.images = images;
        imageIndex = 0;
    }

    public Point getPosition()
    {
        return position;
    }

    public void setPosition(Point point)
    {
        this.position = point;
    }

    public PImage getCurrentImage()
    {
        return images.get(imageIndex);
    }

    public void nextImage()
    {
        imageIndex = (imageIndex + 1) % images.size();
    }

    public String getID()
    {
        return id;
    }

    public List<PImage> getImages()
    {
        return images;
    }
}
