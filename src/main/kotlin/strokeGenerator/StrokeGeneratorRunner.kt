package strokeGenerator

abstract class StrokeGeneratorRunner {
    abstract var iterator: StrokeGeneratorIterator

    abstract fun start()
    abstract fun stop()
}
