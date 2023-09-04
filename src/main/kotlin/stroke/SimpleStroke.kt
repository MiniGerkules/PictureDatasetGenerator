package stroke

import java.awt.Color
import java.awt.Image
import java.awt.image.BufferedImage

import kotlin.math.*

import stroke.positionManager.getPolygon

class SimpleStroke : Stroke, StrokeSkeleton {
    val vertexes: MutableList<Vertex> = mutableListOf()
    var color: Color = Color.WHITE

    private var bounds: Bounds = Bounds(Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY,
                                        Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY)

    override fun addVertex(newVertex: Vertex, avgColor: Color) {
        vertexes.add(newVertex)

        val leftBound = min(bounds.left, newVertex.position.x - newVertex.radius)
        val rightBound = max(bounds.right, newVertex.position.x + newVertex.radius)
        val upBound = max(bounds.up, newVertex.position.y + newVertex.radius)
        val downBound = min(bounds.down, newVertex.position.y - newVertex.radius)
        bounds = Bounds(leftBound, rightBound, downBound, upBound)

        var r = 0; var g = 0; var b = 0; var a = 0
        for (vertex in vertexes) {
            r += vertex.color.red
            g += vertex.color.green
            b += vertex.color.blue
            a += vertex.color.alpha
        }
        color = Color(r / vertexes.size, g / vertexes.size, b / vertexes.size)
    }

    override fun getParams(): StrokeParameters {
        return StrokeParameters(
            getLength(),
            getStraightness(),
            getOrientation(),
            getNbsNb(),
            getNbsSo(),
            getOsdNb()
        )
    }

    override fun toPicture(): Image? {
        if (vertexes.isEmpty()) return null

        val width = bounds.right - bounds.left
        val height = bounds.up - bounds.down

        val image = BufferedImage(width.toInt(), height.toInt(), BufferedImage.TYPE_3BYTE_BGR)
        val graphics = image.createGraphics()

        for ((prev, next) in vertexes.zip(vertexes.drop(1))) {
            val polygon = getPolygon(prev, next)
            graphics.fillPolygon(polygon)
        }

        for (vertex in vertexes) {
            graphics.fillOval(vertex.position.x.toInt(), vertex.position.y.toInt(),
                              (vertex.radius * 2).toInt(), (vertex.radius * 2).toInt())
        }

        return image
    }

    // Private method to make work more convenient
    private fun getLength(): Double {
        var length = 0.0

        for ((prev, next) in vertexes.zip(vertexes.drop(1))) {
            length += sqrt(
                (next.position.x - prev.position.x).pow(2.0) +
                        (next.position.y - prev.position.y).pow(2.0)
            )
        }

        return length
    }

    private fun getStraightness(): Double {
        TODO("Not yet implemented")
    }

    private fun getOrientation(): Double {
        TODO("Not yet implemented")
    }

    private fun getNbsNb(): UInt {
        TODO("Not yet implemented")
    }

    private fun getNbsSo(): UInt {
        TODO("Not yet implemented")
    }

    private fun getOsdNb(): Double {
        TODO("Not yet implemented")
    }
}
