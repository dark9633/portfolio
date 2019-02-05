package search;

public class GeoTrans
{
  public static final int GEO = 0;
  public static final int KATEC = 1;
  public static final int TM = 2;
  public static final int GRS80 = 3;
  private static double[] m_Ind = new double[3];
  private static double[] m_Es = new double[3];
  private static double[] m_Esp = new double[3];
  private static double[] src_m = new double[3];
  private static double[] dst_m = new double[3];

  private static double EPSLN = 1.0E-010D;
  private static double[] m_arMajor = new double[3];
  private static double[] m_arMinor = new double[3];

  private static double[] m_arScaleFactor = new double[3];
  private static double[] m_arLonCenter = new double[3];
  private static double[] m_arLatCenter = new double[3];
  private static double[] m_arFalseNorthing = new double[3];
  private static double[] m_arFalseEasting = new double[3];

  private static double[] datum_params = new double[3];
  private static final double HALF_PI = 1.570796326794897D;
  private static final double COS_67P5 = 0.3826834323650898D;
  private static final double AD_C = 1.0026D;

  static
  {
    m_arScaleFactor[0] = 1.0D;
    m_arLonCenter[0] = 0.0D;
    m_arLatCenter[0] = 0.0D;
    m_arFalseNorthing[0] = 0.0D;
    m_arFalseEasting[0] = 0.0D;
    m_arMajor[0] = 6378137.0D;
    m_arMinor[0] = 6356752.3141999999D;

    m_arScaleFactor[1] = 0.9999D;
    m_arLonCenter[1] = 2.23402144255274D;

    m_arLatCenter[1] = 0.663225115757845D;
    m_arFalseNorthing[1] = 600000.0D;
    m_arFalseEasting[1] = 400000.0D;
    m_arMajor[1] = 6377397.1550000003D;
    m_arMinor[1] = 6356078.9633422494D;

    m_arScaleFactor[2] = 1.0D;

    m_arLonCenter[2] = 2.2165681500328D;
    m_arLatCenter[2] = 0.663225115757845D;
    m_arFalseNorthing[2] = 500000.0D;
    m_arFalseEasting[2] = 200000.0D;
    m_arMajor[2] = 6377397.1550000003D;
    m_arMinor[2] = 6356078.9633422494D;

    datum_params[0] = -146.43000000000001D;
    datum_params[1] = 507.88999999999999D;
    datum_params[2] = 681.46000000000004D;

    double tmp = m_arMinor[0] / m_arMajor[0];
    m_Es[0] = (1.0D - (tmp * tmp));
    m_Es[0] /= (1.0D - m_Es[0]);

    if (m_Es[0] < 1.E-005D)
      m_Ind[0] = 1.0D;
    else {
      m_Ind[0] = 0.0D;
    }

    tmp = m_arMinor[1] / m_arMajor[1];
    m_Es[1] = (1.0D - (tmp * tmp));
    m_Es[1] /= (1.0D - m_Es[1]);

    if (m_Es[1] < 1.E-005D)
      m_Ind[1] = 1.0D;
    else {
      m_Ind[1] = 0.0D;
    }

    tmp = m_arMinor[2] / m_arMajor[2];
    m_Es[2] = (1.0D - (tmp * tmp));
    m_Es[2] /= (1.0D - m_Es[2]);

    if (m_Es[2] < 1.E-005D)
      m_Ind[2] = 1.0D;
    else {
      m_Ind[2] = 0.0D;
    }

    m_arMajor[0] *= mlfn(e0fn(m_Es[0]), e1fn(m_Es[0]), e2fn(m_Es[0]), e3fn(m_Es[0]), m_arLatCenter[0]);
    m_arMajor[0] *= mlfn(e0fn(m_Es[0]), e1fn(m_Es[0]), e2fn(m_Es[0]), e3fn(m_Es[0]), m_arLatCenter[0]);
    m_arMajor[1] *= mlfn(e0fn(m_Es[1]), e1fn(m_Es[1]), e2fn(m_Es[1]), e3fn(m_Es[1]), m_arLatCenter[1]);
    m_arMajor[1] *= mlfn(e0fn(m_Es[1]), e1fn(m_Es[1]), e2fn(m_Es[1]), e3fn(m_Es[1]), m_arLatCenter[1]);
    m_arMajor[2] *= mlfn(e0fn(m_Es[2]), e1fn(m_Es[2]), e2fn(m_Es[2]), e3fn(m_Es[2]), m_arLatCenter[2]);
    m_arMajor[2] *= mlfn(e0fn(m_Es[2]), e1fn(m_Es[2]), e2fn(m_Es[2]), e3fn(m_Es[2]), m_arLatCenter[2]);
  }

  private static double D2R(double degree) {
    return (degree * 3.141592653589793D / 180.0D);
  }

  private static double R2D(double radian) {
    return (radian * 180.0D / 3.141592653589793D);
  }

  private static double e0fn(double x) {
    return (1.0D - (0.25D * x * (1.0D + x / 16.0D * (3.0D + 1.25D * x))));
  }

  private static double e1fn(double x) {
    return (0.375D * x * (1.0D + 0.25D * x * (1.0D + 0.46875D * x)));
  }

  private static double e2fn(double x) {
    return (0.05859375D * x * x * (1.0D + 0.75D * x));
  }

  private static double e3fn(double x) {
    return (x * x * x * 0.01139322916666667D);
  }

  private static double mlfn(double e0, double e1, double e2, double e3, double phi) {
    return (e0 * phi - (e1 * Math.sin(2.0D * phi)) + e2 * Math.sin(4.0D * phi) - (e3 * Math.sin(6.0D * phi)));
  }

  private static double asinz(double value) {
    if (Math.abs(value) > 1.0D) value = (value > 0.0D) ? 1 : -1;
    return Math.asin(value);
  }

  public static GeoPoint convert(int srctype, int dsttype, GeoPoint in_pt) {
    GeoPoint tmpPt = new GeoPoint();
    GeoPoint out_pt = new GeoPoint();

    if (srctype == 0) {
      tmpPt.x = D2R(in_pt.x);
      tmpPt.y = D2R(in_pt.y);
    } else {
      tm2geo(srctype, in_pt, tmpPt);
    }
    

    System.out.println("tmpPt.x/y:" + tmpPt.x+"/" + tmpPt.y);
    System.out.println("dsttype:" + dsttype);


    if (dsttype == 0) {
      out_pt.x = R2D(tmpPt.x);
      out_pt.y = R2D(tmpPt.y);
    } else {
      geo2tm(dsttype, tmpPt, out_pt);
    }
    System.out.println("out_pt.x/y:" + out_pt.x+"/"+out_pt.y);

    return out_pt;
  }

  public static void geo2tm(int dsttype, GeoPoint in_pt, GeoPoint out_pt)
  {
    double b;
    transform(0, dsttype, in_pt);
    double delta_lon = in_pt.x - m_arLonCenter[dsttype];
    double sin_phi = Math.sin(in_pt.y);
    double cos_phi = Math.cos(in_pt.y);

    if (m_Ind[dsttype] != 0.0D) {
      b = cos_phi * Math.sin(delta_lon);

      Math.abs(Math.abs(b) - 1.0D);
    }
    else
    {
      b = 0.0D;
      double x = 0.5D * m_arMajor[dsttype] * m_arScaleFactor[dsttype] * Math.log((1.0D + b) / (1.0D - b));
      double con = Math.acos(cos_phi * Math.cos(delta_lon) / Math.sqrt(1.0D - (b * b)));

      if (in_pt.y < 0.0D) {
        con *= -1.0D;
        double d1 = m_arMajor[dsttype] * m_arScaleFactor[dsttype] * (con - m_arLatCenter[dsttype]);
      }
    }

    double al = cos_phi * delta_lon;
    double als = al * al;
    double c = m_Esp[dsttype] * cos_phi * cos_phi;
    double tq = Math.tan(in_pt.y);
    double t = tq * tq;
    double con = 1.0D - (m_Es[dsttype] * sin_phi * sin_phi);
    double n = m_arMajor[dsttype] / Math.sqrt(con);
    double ml = m_arMajor[dsttype] * mlfn(e0fn(m_Es[dsttype]), e1fn(m_Es[dsttype]), e2fn(m_Es[dsttype]), e3fn(m_Es[dsttype]), in_pt.y);

    out_pt.x = (m_arScaleFactor[dsttype] * n * al * (1.0D + als / 6.0D * (1.0D - t + c + als / 20.0D * (5.0D - (18.0D * t) + t * t + 72.0D * c - (58.0D * m_Esp[dsttype])))) + m_arFalseEasting[dsttype]);
    out_pt.y = (m_arScaleFactor[dsttype] * (ml - dst_m[dsttype] + n * tq * als * (0.5D + als / 24.0D * (5.0D - t + 9.0D * c + 4.0D * c * c + als / 30.0D * (61.0D - (58.0D * t) + t * t + 600.0D * c - (330.0D * m_Esp[dsttype]))))) + m_arFalseNorthing[dsttype]);
  }

  public static void tm2geo(int srctype, GeoPoint in_pt, GeoPoint out_pt)
  {
    GeoPoint tmpPt = new GeoPoint(in_pt.getX(), in_pt.getY());
    int max_iter = 6;

    if (m_Ind[srctype] != 0.0D) {
      double f = Math.exp(in_pt.x / m_arMajor[srctype] * m_arScaleFactor[srctype]);
      double g = 0.5D * (f - (1.0D / f));
      double temp = m_arLatCenter[srctype] + tmpPt.y / m_arMajor[srctype] * m_arScaleFactor[srctype];
      double h = Math.cos(temp);
      double con = Math.sqrt((1.0D - (h * h)) / (1.0D + g * g));
      out_pt.y = asinz(con);

      if (temp < 0.0D) out_pt.y *= -1.0D;

      if ((g == 0.0D) && (h == 0.0D))
        out_pt.x = m_arLonCenter[srctype];
      else {
        out_pt.x = (Math.atan(g / h) + m_arLonCenter[srctype]);
      }
    }

    tmpPt.x -= m_arFalseEasting[srctype];
    tmpPt.y -= m_arFalseNorthing[srctype];

    double con = (src_m[srctype] + tmpPt.y / m_arScaleFactor[srctype]) / m_arMajor[srctype];
    double phi = con;

    int i = 0;
    while (true)
    {
      double delta_Phi = (con + e1fn(m_Es[srctype]) * Math.sin(2.0D * phi) - (e2fn(m_Es[srctype]) * Math.sin(4.0D * phi)) + e3fn(m_Es[srctype]) * Math.sin(6.0D * phi)) / e0fn(m_Es[srctype]) - phi;
      phi += delta_Phi;

      if (Math.abs(delta_Phi) <= EPSLN)
        break;
      if (i >= max_iter)
      {
        break;
      }

      ++i;
    }

    if (Math.abs(phi) < 1.570796326794897D) {
      double sin_phi = Math.sin(phi);
      double cos_phi = Math.cos(phi);
      double tan_phi = Math.tan(phi);
      double c = m_Esp[srctype] * cos_phi * cos_phi;
      double cs = c * c;
      double t = tan_phi * tan_phi;
      double ts = t * t;
      double cont = 1.0D - (m_Es[srctype] * sin_phi * sin_phi);
      double n = m_arMajor[srctype] / Math.sqrt(cont);
      double r = n * (1.0D - m_Es[srctype]) / cont;
      double d = tmpPt.x / n * m_arScaleFactor[srctype];
      double ds = d * d;
      out_pt.y = (phi - (n * tan_phi * ds / r * (0.5D - (ds / 24.0D * (5.0D + 3.0D * t + 10.0D * c - (4.0D * cs) - (9.0D * m_Esp[srctype]) - (ds / 30.0D * (61.0D + 90.0D * t + 298.0D * c + 45.0D * ts - (252.0D * m_Esp[srctype]) - (3.0D * cs))))))));
      out_pt.x = (m_arLonCenter[srctype] + d * (1.0D - (ds / 6.0D * (1.0D + 2.0D * t + c - (ds / 20.0D * (5.0D - (2.0D * c) + 28.0D * t - (3.0D * cs) + 8.0D * m_Esp[srctype] + 24.0D * ts))))) / cos_phi);
    } else {
      out_pt.y = (1.570796326794897D * Math.sin(tmpPt.y));
      out_pt.x = m_arLonCenter[srctype];
    }
    transform(srctype, 0, out_pt);
  }

  public static double getDistancebyGeo(GeoPoint pt1, GeoPoint pt2) {
    double lat1 = D2R(pt1.y);
    double lon1 = D2R(pt1.x);
    double lat2 = D2R(pt2.y);
    double lon2 = D2R(pt2.x);

    double longitude = lon2 - lon1;
    double latitude = lat2 - lat1;

    double a = Math.pow(Math.sin(latitude / 2.0D), 2.0D) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(longitude / 2.0D), 2.0D);
    return (12753.0D * Math.atan2(Math.sqrt(a), Math.sqrt(1.0D - a)));
  }

  public static double getDistancebyKatec(GeoPoint pt1, GeoPoint pt2) {
    pt1 = convert(1, 0, pt1);
    pt2 = convert(1, 0, pt2);

    return getDistancebyGeo(pt1, pt2);
  }

  public static double getDistancebyTm(GeoPoint pt1, GeoPoint pt2) {
    pt1 = convert(2, 0, pt1);
    pt2 = convert(2, 0, pt2);

    return getDistancebyGeo(pt1, pt2);
  }

  private static long getTimebySec(double distance) {
    return Math.round(3600.0D * distance / 4.0D);
  }

 // public static long getTimebyMin(double distance) {
    //return ()Math.ceil(getTimebySec(distance) / 60L);
  //}

  private static void transform(int srctype, int dsttype, GeoPoint point)
  {
    if (srctype == dsttype) {
      return;
    }
    if ((srctype == 0) && (dsttype == 0))
      return;
    geodetic_to_geocentric(srctype, point);

    if (srctype != 0) {
      geocentric_to_wgs84(point);
    }

    if (dsttype != 0) {
      geocentric_from_wgs84(point);
    }

    geocentric_to_geodetic(dsttype, point);
  }

  private static boolean geodetic_to_geocentric(int type, GeoPoint p)
  {
    double Longitude = p.x;
    double Latitude = p.y;
    double Height = p.z;

    if ((Latitude < -1.570796326794897D) && (Latitude > -1.572367123121691D))
      Latitude = -1.570796326794897D;
    else if ((Latitude > 1.570796326794897D) && (Latitude < 1.572367123121691D))
      Latitude = 1.570796326794897D;
    else if ((Latitude < -1.570796326794897D) || (Latitude > 1.570796326794897D)) {
      return true;
    }

    if (Longitude > 3.141592653589793D)
      Longitude -= 6.283185307179586D;
    double Sin_Lat = Math.sin(Latitude);
    double Cos_Lat = Math.cos(Latitude);
    double Sin2_Lat = Sin_Lat * Sin_Lat;
    double Rn = m_arMajor[type] / Math.sqrt(1.0D - (m_Es[type] * Sin2_Lat));
    double X = (Rn + Height) * Cos_Lat * Math.cos(Longitude);
    double Y = (Rn + Height) * Cos_Lat * Math.sin(Longitude);
    double Z = (Rn * (1.0D - m_Es[type]) + Height) * Sin_Lat;

    p.x = X;
    p.y = Y;
    p.z = Z;
    return false;
  }

  private static void geocentric_to_geodetic(int type, GeoPoint p)
  {
    double Longitude;
    double Height;
    double X = p.x;
    double Y = p.y;
    double Z = p.z;

    double Latitude = 0.0D;

    boolean At_Pole = false;
    if (X != 0.0D) {
      Longitude = Math.atan2(Y, X);
    }
    else if (Y > 0.0D) {
      Longitude = 1.570796326794897D;
    }
    else if (Y < 0.0D) {
      Longitude = -1.570796326794897D;
    }
    else {
      At_Pole = true;
      Longitude = 0.0D;
      if (Z > 0.0D) {
        Latitude = 1.570796326794897D;
      }
      else if (Z < 0.0D) {
        Latitude = -1.570796326794897D;
      }
      else {
        Latitude = 1.570796326794897D;
        Height = -m_arMinor[type];
        return;
      }
    }

    double W2 = X * X + Y * Y;
    double W = Math.sqrt(W2);
    double T0 = Z * 1.0026D;
    double S0 = Math.sqrt(T0 * T0 + W2);
    double Sin_B0 = T0 / S0;
    double Cos_B0 = W / S0;
    double Sin3_B0 = Sin_B0 * Sin_B0 * Sin_B0;
    double T1 = Z + m_arMinor[type] * m_Esp[type] * Sin3_B0;
    double Sum = W - (m_arMajor[type] * m_Es[type] * Cos_B0 * Cos_B0 * Cos_B0);
    double S1 = Math.sqrt(T1 * T1 + Sum * Sum);
    double Sin_p1 = T1 / S1;
    double Cos_p1 = Sum / S1;
    double Rn = m_arMajor[type] / Math.sqrt(1.0D - (m_Es[type] * Sin_p1 * Sin_p1));
    if (Cos_p1 >= 0.3826834323650898D) {
      Height = W / Cos_p1 - Rn;
    }
    else if (Cos_p1 <= -0.3826834323650898D) {
      Height = W / -Cos_p1 - Rn;
    }
    else {
      Height = Z / Sin_p1 + Rn * (m_Es[type] - 1.0D);
    }
    if (!(At_Pole)) {
      Latitude = Math.atan(Sin_p1 / Cos_p1);
    }

    p.x = Longitude;
    p.y = Latitude;
    p.z = Height;
  }

  private static void geocentric_to_wgs84(GeoPoint p)
  {
    p.x += datum_params[0];
    p.y += datum_params[1];
    p.z += datum_params[2];
  }

  private static void geocentric_from_wgs84(GeoPoint p)
  {
    p.x -= datum_params[0];
    p.y -= datum_params[1];
    p.z -= datum_params[2];
  }
}