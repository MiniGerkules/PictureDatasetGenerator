package math

import stroke.Position

fun countK(a: Position, b: Position): Double {
    return (b.y - a.y) / (b.x - a.x);
}