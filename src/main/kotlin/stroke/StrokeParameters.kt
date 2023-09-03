package stroke

data class StrokeParameters(
    val length: Double,
    val straightness: Double,
    val orientation : Double,
    val nbs_nb : UInt,       // Number of brushstrokes in the neighborhood
    val nbs_so : UInt,       // Number of brushstrokes with similar orientations in the neighborhood
    val osd_nb : Double,     // Orientation standard deviation in the neighborhood
)
