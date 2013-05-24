package raytracer

import raytracer.Math._
import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import java.io.File

object Main extends App {
  val camera = new Camera( Point(0,0,3), Vector(0,0,-1), Vector(0,1,0), 1.9, 1.5, 640, 480 )
  val image = new BufferedImage(camera.res_x, camera.res_y, BufferedImage.TYPE_INT_RGB)

  for(i <- List.range(0,50)) {
    val scene = cornellBox(i.toDouble)

    for( x <- List.range(0, camera.res_x).par; y <- List.range(0,camera.res_y) ) {
      val color = scene.traceRay( camera.getRay(x,y) ).normalize
      image.setRGB(x, y, color.r << 16 | color.g << 8 | color.b)
    }

    ImageIO.write(image, "bmp", new File("animation" + "%02d".format(i) + ".bmp"))
  }

  def cornellBox = {
    val back = Plane( Vector(0, 0, -1), 5, Color(255,255,255))
    val left = Plane( Vector(-1, 0, 0), 2, Color(255,0,100))
    val right = Plane( Vector(1,0,0), 2, Color(0,255,0))
    val ceiling = Plane( Vector(0,1,0), 2, Color(255,255,255))
    val floor = Plane( Vector(0,-1,0), 2, Color(255,255,255))
    val walls = List(back, left, right, ceiling, floor)

    val light = PointLight( new Point(-1.2, -0.9, -3.1), new Color(110,100,120), 1.0)
    new Scene(walls, light)
  }

  def cornellBox(lambda: Double) = {
    val back = Plane( Vector(0, 0, -1), 5, Color(255,255,255))
    val left = Plane( Vector(-1, 0, 0), 2, Color(255,0,100))
    val right = Plane( Vector(1,0,0), 2, Color(0,255,0))
    val ceiling = Plane( Vector(0,1,0), 2, Color(255,255,255))
    val floor = Plane( Vector(0,-1,0), 2, Color(255,255,255))
    val walls = List(back, left, right, ceiling, floor)

    val pos = Point(0, 0, -2.5) + Vector(scala.math.sin(lambda * 2*3.1415/50),scala.math.cos(lambda *2* 3.1415/50),0)
    val light = PointLight( pos, new Color(110,100,120), 1.0)
    new Scene(walls, light)
  }
}
