import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val ImageIcon: ImageVector
  get() {
    if (_Image_search != null) {
      return _Image_search!!
    }
    _Image_search = ImageVector.Builder(
      name = "Image_search",
      defaultWidth = 24.dp,
      defaultHeight = 24.dp,
      viewportWidth = 960f,
      viewportHeight = 960f
    ).apply {
      path(
        fill = SolidColor(Color.Black),
        fillAlpha = 1.0f,
        stroke = null,
        strokeAlpha = 1.0f,
        strokeLineWidth = 1.0f,
        strokeLineCap = StrokeCap.Butt,
        strokeLineJoin = StrokeJoin.Miter,
        strokeLineMiter = 1.0f,
        pathFillType = PathFillType.NonZero
      ) {
        moveTo(200f, 840f)
        quadToRelative(-33f, 0f, -56.5f, -23.5f)
        reflectiveQuadTo(120f, 760f)
        verticalLineToRelative(-560f)
        quadToRelative(0f, -33f, 23.5f, -56.5f)
        reflectiveQuadTo(200f, 120f)
        horizontalLineToRelative(200f)
        verticalLineToRelative(80f)
        horizontalLineTo(200f)
        verticalLineToRelative(560f)
        horizontalLineToRelative(560f)
        verticalLineToRelative(-214f)
        lineToRelative(80f, 80f)
        verticalLineToRelative(134f)
        quadToRelative(0f, 33f, -23.5f, 56.5f)
        reflectiveQuadTo(760f, 840f)
        close()
        moveToRelative(40f, -160f)
        lineToRelative(120f, -160f)
        lineToRelative(90f, 120f)
        lineToRelative(120f, -160f)
        lineToRelative(150f, 200f)
        close()
        moveToRelative(622f, -144f)
        lineTo(738f, 412f)
        quadToRelative(-21f, 14f, -45f, 21f)
        reflectiveQuadToRelative(-51f, 7f)
        quadToRelative(-74f, 0f, -126f, -52.5f)
        reflectiveQuadTo(464f, 260f)
        reflectiveQuadToRelative(52.5f, -127.5f)
        reflectiveQuadTo(644f, 80f)
        reflectiveQuadToRelative(127.5f, 52.5f)
        reflectiveQuadTo(824f, 260f)
        quadToRelative(0f, 27f, -8f, 52f)
        reflectiveQuadToRelative(-20f, 46f)
        lineToRelative(122f, 122f)
        close()
        moveTo(644f, 360f)
        quadToRelative(42f, 0f, 71f, -29f)
        reflectiveQuadToRelative(29f, -71f)
        reflectiveQuadToRelative(-29f, -71f)
        reflectiveQuadToRelative(-71f, -29f)
        reflectiveQuadToRelative(-71f, 29f)
        reflectiveQuadToRelative(-29f, 71f)
        reflectiveQuadToRelative(29f, 71f)
        reflectiveQuadToRelative(71f, 29f)
      }
    }.build()
    return _Image_search!!
  }

private var _Image_search: ImageVector? = null
