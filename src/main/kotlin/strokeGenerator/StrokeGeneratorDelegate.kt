package strokeGenerator

import stroke.Stroke

interface StrokeGeneratorDelegate : ProcessDelegate {
    fun generated(stroke: Stroke, progress: UInt)
}
