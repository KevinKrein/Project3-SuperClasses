import processing.core.PApplet;
import processing.core.PImage;

import java.util.Optional;

final class WorldView
{
   private PApplet screen;
   private WorldModel world;
   private int tileWidth;
   private int tileHeight;
   private Viewport viewport;

   public WorldView(int numRows, int numCols, PApplet screen, WorldModel world,
      int tileWidth, int tileHeight)
   {
      this.screen = screen;
      this.world = world;
      this.tileWidth = tileWidth;
      this.tileHeight = tileHeight;
      this.viewport = new Viewport(numRows, numCols);
   }

   public void shiftView(int colDelta, int rowDelta)
   {
      int newCol = clamp(getViewport().getCol() + colDelta, 0,
              getWorld().getNumCols() - getViewport().getNumCols());
      int newRow = clamp(getViewport().getRow() + rowDelta, 0,
              getWorld().getNumRows() - getViewport().getNumRows());

      getViewport().shift(newCol, newRow);
   }

   public void drawViewport()
   {
      drawBackground();
      drawEntities();
   }

   private static int clamp(int value, int low, int high)
   {
      return Math.min(high, Math.max(value, low));
   }

   private void drawBackground()
   {
      for (int row = 0; row < getViewport().getNumRows(); row++)
      {
         for (int col = 0; col < getViewport().getNumCols(); col++)
         {
            Point worldPoint = getViewport().viewportToWorld(col, row);
            Optional<PImage> image = getWorld().getBackgroundImage(
                    worldPoint);
            if (image.isPresent())
            {
               getScreen().image(image.get(), col * getTileWidth(),
                       row * getTileHeight());
            }
         }
      }
   }

   private void drawEntities()
   {
      for (Entity entity : getWorld().getEntities())
      {
         Point pos = entity.getPosition();

         if (getViewport().contains(pos))
         {
            Point viewPoint = getViewport().worldToViewport(pos.getX(), pos.getY());
            getScreen().image(entity.getCurrentImage(),
                    viewPoint.getX() * getTileWidth(), viewPoint.getY() * getTileHeight());
         }
      }
   }

   public PApplet getScreen() {
      return screen;
   }

   public WorldModel getWorld() {
      return world;
   }

   public int getTileWidth() {
      return tileWidth;
   }

   public int getTileHeight() {
      return tileHeight;
   }

   public Viewport getViewport() {
      return viewport;
   }
}
