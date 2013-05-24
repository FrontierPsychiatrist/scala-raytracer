package raytracer
import raytracer.Math._

class Scene(objects: List[Object], light: PointLight) {
  def traceRay(ray: Ray): Color = {
    val intersections =
      objects.map( _.intersection(ray) ).filter({case Some(_) => true case None => false}).map( _.get).sortBy( _.lambda )

    intersections match {
      case Nil => Color(0,0,0)
      case firstIntersection :: is => {
        val length = difference(light.position, firstIntersection.point).length
        (firstIntersection.hitObject.color + light.color) * (light.intensity / (length * length))
      }
    }
  }
}


