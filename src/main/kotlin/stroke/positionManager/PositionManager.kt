package stroke.positionManager

import java.awt.Polygon

import kotlin.math.*

import stroke.Vertex
import stroke.getLength

import math.countK

// Public functions
fun getPolygon(prev: Vertex, next: Vertex): Polygon {
    val left: Vertex; val right: Vertex
    if (prev.position.x < next.position.x) {
        left = prev
        right = next
    } else {
        left = next
        right = prev
    }

    return if (abs(left.radius - right.radius) <= 1e-5) // Same radii
        getPolygonSameRadii(left, right)
    else
        getPolygonDiffRadii(left, right)
}

// Private types
typealias StepFunction = (Double) -> Pair<Double, Double>

// Private functions
private fun getPolygonSameRadii(left: Vertex, right: Vertex): Polygon {
    val centerK = countK(left.position, right.position)
    val angle = atan(centerK)

    val stepX = left.radius * sin(angle)
    val stepY = left.radius * cos(angle)

    val lowerBoundLeftX = left.position.x + stepX
    val lowerBoundLeftY = left.position.y - stepY
    val upperBoundLeftX = left.position.x - stepX
    val upperBoundLeftY = left.position.y + stepY

    val lowerBoundRightX = right.position.x + stepX
    val lowerBoundRightY = right.position.y - stepY
    val upperBoundRightX = right.position.x - stepX
    val upperBoundRightY = right.position.y + stepY

    val x = intArrayOf(lowerBoundLeftX.toInt(), lowerBoundRightX.toInt(),
                       right.position.x.toInt(), upperBoundRightX.toInt(),
                       upperBoundLeftX.toInt(), left.position.x.toInt())
    val y = intArrayOf(lowerBoundLeftY.toInt(), lowerBoundRightY.toInt(),
                       right.position.y.toInt(), upperBoundRightY.toInt(),
                       upperBoundLeftY.toInt(), left.position.y.toInt())

    return Polygon(x, y, x.size)
}

private fun getPolygonDiffRadii(left: Vertex, right: Vertex): Polygon {
    val (lowerStepFunc, upperStepFunc) = getStepFunctions(left, right)

    val (stepXLeftLower, stepYLeftLower) = lowerStepFunc(left.radius)
    val (stepXRightLower, stepYRightLower) = lowerStepFunc(right.radius)

    val (stepXLeftUpper, stepYLeftUpper) = upperStepFunc(left.radius)
    val (stepXRightUpper, stepYRightUpper) = upperStepFunc(right.radius)

    val upperBoundLeftX = left.position.x - stepXLeftUpper
    val upperBoundRightX = right.position.x - stepXRightUpper
    val upperBoundLeftY = left.position.y + stepYLeftUpper
    val upperBoundRightY = right.position.y + stepYRightUpper

    val lowerBoundLeftX = left.position.x + stepXLeftLower
    val lowerBoundRightX = right.position.x + stepXRightLower
    val lowerBoundLeftY = left.position.y - stepYLeftLower
    val lowerBoundRightY = right.position.y - stepYRightLower

    val x = intArrayOf(lowerBoundLeftX.toInt(), lowerBoundRightX.toInt(),
                       right.position.x.toInt(), upperBoundRightX.toInt(),
                       upperBoundLeftX.toInt(), left.position.x.toInt())
    val y = intArrayOf(lowerBoundLeftY.toInt(), lowerBoundRightY.toInt(),
                       right.position.y.toInt(), upperBoundRightY.toInt(),
                       upperBoundLeftY.toInt(), left.position.y.toInt())

    return Polygon(x, y, x.size)
}

private fun getStepFunctions(left: Vertex, right: Vertex): Pair<StepFunction, StepFunction> {
    val radiiDiff = abs(left.radius - right.radius)

    val angleToTangent = acos(radiiDiff / getLength(left, right))
    val centerLineAngle = atan(countK(left.position, right.position))
    val angle1 = PI/2 - angleToTangent + centerLineAngle
    val angle2 = angleToTangent + centerLineAngle - PI/2

    return if (left.radius > right.radius && centerLineAngle > 0 ||
        left.radius < right.radius && centerLineAngle < 0) {
        Pair(
            { radius -> Pair(radius * sin(angle1), radius * cos(angle1)) },
            { radius -> Pair(radius * sin(angle2), radius * cos(angle2)) }
        )
    } else {
        Pair(
            { radius -> Pair(radius * sin(angle2), radius * cos(angle2)) },
            { radius -> Pair(radius * sin(angle1), radius * cos(angle1)) }
        )
    }
}
