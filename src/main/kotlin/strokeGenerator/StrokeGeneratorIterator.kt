package strokeGenerator

import stroke.Stroke

interface StrokeGeneratorIterator {
    fun updateSettings(json: String)
    fun canIterate(): Boolean
    fun makeIteration(): Stroke
}
