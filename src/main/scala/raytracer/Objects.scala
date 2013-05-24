package raytracer

import raytracer.Math._

abstract class Object {
  def color: Color
  def intersection(ray: Ray): Option[Intersection]
}

case class Sphere(origin: Point, radius: Double, color: Color) extends Object {
  def intersection(ray: Ray): Option[Intersection] = ???
}

case class Plane(normal: Vector, distance: Double, color: Color) extends Object {
  def intersection(ray: Ray): Option[Intersection] = {
    val gamma = scalarProduct(ray.direction, normal)
    val side = distance - scalarProduct(normal, difference(ray.origin, Point(0,0,0)))
    if( gamma * side > 0.000000001)
      Some(new Intersection(ray(side/gamma), side/gamma, this))
    else
      None
  }
}

case class PointLight(position: Point, color: Color, intensity: Double)

final class Intersection(val point: Point, val lambda: Double, val hitObject: Object) {
  override def toString = point.toString
}

