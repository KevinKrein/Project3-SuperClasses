final class Point {
   private final int x;
   private final int y;

   public Point(int x, int y) {
      this.x = x;
      this.y = y;
   }

    public boolean adjacent(Point p2)
    {
        return (x == p2.getX() && Math.abs(y - p2.getY()) == 1) ||
                (y == p2.getY() && Math.abs(x - p2.getX()) == 1);
    }

    public String toString() {
      return "(" + getX() + "," + getY() + ")";
   }

   public boolean equals(Object other) {
      return other instanceof Point &&
              ((Point) other).getX() == this.getX() &&
              ((Point) other).getY() == this.getY();
   }

   public int getX() {
      return x;
   }

   public int getY() {
      return y;
   }
}
