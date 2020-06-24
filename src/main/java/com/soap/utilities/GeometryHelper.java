package com.soap.utilities;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
/**
 *
 * @author Grigorios Ladas
 *
 */

public class GeometryHelper {

    public static Point createPoint(double x, double y) {
        GeometryFactory gf = new GeometryFactory();
        Coordinate coord = new Coordinate(x, y);
        return gf.createPoint(coord);
    }


}




