package ru.ymv.yvideolite.components.text

import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.foundation.text.TextAutoSizeDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit

/**
 * A text composable that automatically adjusts its font size to fit within its bounds.
 *
 * This composable is a wrapper around [BasicText] that provides automatic font size scaling. It
 * allows you to specify minimum and maximum font sizes, and the text will automatically resize to
 * fit within the available space.
 *
 * @param text The text to display.
 * @param modifier Modifier to be applied to the text.
 * @param color The color of the text. If [Color.Unspecified], the color will be inherited from the
 *  [LocalTextStyle] or [LocalContentColor].
 * @param fontStyle The font style of the text.
 * @param fontWeight The font weight of the text.
 * @param fontFamily The font family of the text.
 * @param letterSpacing The letter spacing of the text.
 * @param textDecoration The text decoration of the text.
 * @param textAlign The horizontal alignment of the text.
 * @param lineHeight The line height of the text.
 * @param overflow How visual overflow should be handled.
 * @param softWrap Whether the text should break at soft line breaks. If false, the glyphs in the text
 *  will be positioned as if there was unlimited horizontal space.
 * @param maxLines The maximum number of lines for the text to span.
 * @param minLines The minimum number of lines for the text to span.
 * @param minFontSize The minimum font size to use when auto-sizing the text. Defaults to [TextAutoSizeDefaults.MinFontSize].
 * @param maxFontSize The maximum font size to use when auto-sizing the text. Defaults to [TextAutoSizeDefaults.MaxFontSize].
 * @param onTextLayout Callback that is executed when a new text layout is calculated.
 * @param autoSize The auto-size configuration to use. Defaults to [TextAutoSize.StepBased] with the
 *  specified [minFontSize] and [maxFontSize].
 */
@Composable
fun AutoSizeText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = 1,
    minLines: Int = 1,
    minFontSize: TextUnit = TextAutoSizeDefaults.MinFontSize,
    maxFontSize: TextUnit = TextAutoSizeDefaults.MaxFontSize,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
    autoSize: TextAutoSize? = TextAutoSize.StepBased(minFontSize, maxFontSize)
) {
    val style: TextStyle = LocalTextStyle.current
    val textColor = color.takeOrElse { style.color.takeOrElse { LocalContentColor.current } }

    BasicText(
        text = text,
        modifier = modifier,
        style = style.merge(
            color = textColor,
            fontWeight = fontWeight,
            textAlign = textAlign ?: TextAlign.Unspecified,
            lineHeight = lineHeight,
            fontFamily = fontFamily,
            textDecoration = textDecoration,
            fontStyle = fontStyle,
            letterSpacing = letterSpacing
        ),
        onTextLayout = onTextLayout,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        autoSize = autoSize
    )
}