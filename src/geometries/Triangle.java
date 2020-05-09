package geometries;

import primitives.*;


import java.util.List;

import static primitives.Util.isZero;

/**
 * The type Triangle.
 */
public class Triangle extends Polygon {
    /**
     * Instantiates a new Triangle.
     *
     * @param p1 the Point 1
     * @param p2 the Point 2
     * @param p3 the Point 3
     */
    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        super(p1, p2, p3);
    }

    public Triangle(Color emmission, Point3D _p1, Point3D _p2, Point3D _p3) {
        super(emmission, _p1, _p2, _p3);
    }

    public Triangle(Color emmission, Point3D _p1, Point3D _p2, Point3D _p3, Material material) {
        this(emmission, _p1, _p2, _p3);
        _material = material;
    }

    @Override
    public String toString() {
        return "Triangle : " + _vertices + " " + _plane;
    }

    @Override
    public List<GeoPoint> findIntsersections(Ray ray) {
        List<GeoPoint> intersections = _plane.findIntsersections(ray);
        if (intersections == null) return null;
        Point3D point = ray.getP();
        Vector vec = ray.getDirection();
        Vector v1 = _vertices.get(0).subtract(point);
        Vector v2 = _vertices.get(1).subtract(point);
        Vector v3 = _vertices.get(2).subtract(point);
        double s1 = vec.dotProduct(v1.crossProduct(v2));
        if (isZero(s1)) return null;
        double s2 = vec.dotProduct(v2.crossProduct(v3));
        if (isZero(s2)) return null;
        double s3 = vec.dotProduct(v3.crossProduct(v1));
        if (isZero(s3)) return null;
        intersections.get(0)._geometry = this;
        return ((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0)) ? intersections : null;
    }
}
