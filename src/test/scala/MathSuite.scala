import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

import raytracer.Math._
/**
 * User: moritz
 */
@RunWith(classOf[JUnitRunner])
class MathSuite extends FunSuite {

  test("Vector addition") {
    val v1 = Vector(0,1,0)
    val v2 = Vector(0,0,1)
    assert( v1 + v2 === Vector(0,1,1))
  }

  test("vector added to point") {
    val v = Vector(0,0,1)
    val p = Point(0,0,0)
    assert( p + v === Point(0,0,1))
  }

}
