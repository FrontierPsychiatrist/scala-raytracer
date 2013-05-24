package raytracer

object Math {

  case class Vector(x: Double, y: Double, z: Double) {
    def length: Double = scala.math.sqrt(x*x + y*y + z*z)

    override def toString = "Vector(" + x + "," + y + "," + z + ")"
    def +(other: Vector) = Vector(x + other.x, y + other.y, z + other.z)
    def *(lambda: Double) = Vector(lambda*x, lambda*y, lambda*z)
  }

  case class Point(x: Double, y: Double, z: Double) {
    override def toString = "Vector(" + x + "," + y + "," + z + ")"
    def +(v: Vector) = Point( x + v.x, y + v.y, z + v.z)
    def toVector = Vector(x,y,z)
  }

  case class Color(r: Int, g: Int, b: Int) {
    override def toString = "Color(" + r + "," + g + "," + b + ")"
    def +(other: Color) = Color(r + other.r, g + other.g, b + other.b)
    def *(lambda: Double) = Color( (r*lambda).toInt, (g*lambda).toInt, (b*lambda).toInt)
    def normalize = {
      val scale = 255.0/scala.math.max(r, scala.math.max(g,b))
      if(scale < 1) this*scale
      else this
    }
  }

  def difference(p1: Point, p2: Point) = Vector(p1.x - p2.x, p1.y - p2.y, p1.z - p2.z)
  def scalarProduct(v1: Vector, v2: Vector) = v1.x*v2.x + v1.y*v2.y + v1.z*v2.z
  def crossProduct(v1: Vector, v2: Vector) = Vector(v1.y*v2.z - v1.z*v2.y, v1.z*v2.x - v1.x*v2.z, v1.x*v2.y - v1.y*v2.x)
}

