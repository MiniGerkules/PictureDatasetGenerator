package stroke

import java.awt.Image

interface Stroke {
    fun getParams(): StrokeParameters
    fun toPicture(): Image?
}