package stroke

import java.awt.Image

class PictureStroke(val stroke: SimpleStroke, val strokePicture: Image) : Stroke {
    override fun getParams(): StrokeParameters { return stroke.getParams() }
    override fun toPicture(): Image { return strokePicture }
}
