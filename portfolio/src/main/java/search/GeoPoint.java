package search;

public class GeoPoint
{
  double x;
  double y;
  double z;

  public GeoPoint()
  {
  }

  public GeoPoint(double x, double y)
  {
    this.x = x;
    this.y = y;
    this.z = 0.0D;
  }

  public GeoPoint(double x, double y, double z)
  {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public double getX() {
    return this.x;
  }

  public double getY() {
    return this.y;
  }

  public double getZ() {
    return this.z;
  }
}