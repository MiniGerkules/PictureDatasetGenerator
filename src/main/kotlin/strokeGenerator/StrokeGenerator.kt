package strokeGenerator

open class StrokeGenerator(private val iterator: StrokeGeneratorIterator,
                           private var runner: StrokeGeneratorRunner) {
    var progress: Int = 0
    var delegate: StrokeGeneratorDelegate? = null

    fun setRunner(newRunner: StrokeGeneratorRunner) {
        runner.stop()

        runner = newRunner
        runner.iterator = iterator
    }

    fun updateSettings(json: String) {
        iterator.updateSettings(json)
    }

    fun run() { runner.start() }
    fun stop() { runner.stop() }
}
