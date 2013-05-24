package raytracer
import raytracer.Math._

class Camera(origin: Point, pitchYawn: Vector, bank: Vector, fov_x: Double, fov_y: Double, val res_x: Int, val res_y: Int) {
  val h_y = bank * scala.math.tan( fov_y / 2 ) //base vector y for canvas
  val h_x = crossProduct(pitchYawn, h_y) * scala.math.tan( fov_x / 2 ) //base vector x for canvas

  def getRay(x: Int, y: Int) = {
    val direction = pitchYawn + h_x * ((x - res_x / 2).toDouble/res_x.toDouble) + h_y * ((res_y / 2 - y).toDouble/res_y.toDouble)
    new Ray(origin, direction)
  }
}

final class Ray(val origin: Point, val direction: Vector) {
  def apply(lambda: Double): Point = origin + direction * lambda
}

