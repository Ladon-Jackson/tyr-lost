package com.example.tyrlost.presentation.components.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.em
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tyrlost.presentation.viewModels.ResizingTextViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

//TODO try make this work somehow
@Composable
fun ResizingTextComponent(
  text: String,
  textAlign: TextAlign? = null,
  fontWeight: FontWeight? = null,
  fontSize: TextUnit = TextUnit.Unspecified,
  color: Color = Color.Unspecified,
  modifier: Modifier = Modifier,
  overflow: TextOverflow = TextOverflow.Clip
) {

  var currentSize: TextUnit by remember {
    mutableStateOf(fontSize)
  }


    //.update { it * 0.95 }
//  resizedTextStyle = resizedTextStyle.copy(
//    fontSize = resizedTextStyle.fontSize * 0.95
//  )


  //  val currentSizeState: MutableStateFlow<TextUnit> = MutableStateFlow(fontSize)
//  fun setCurrentSize(newSize: TextUnit) = currentSizeState.update { newSize }
//  fun shrinkCurrentSize() = currentSizeState.update { it * 0.95 }

  println("THIS IS RUN THIS IS RUN")

  Text(
    modifier = modifier
      .fillMaxSize()
      .wrapContentHeight(align = Alignment.CenterVertically),
    text = text,
    textAlign = textAlign,
    fontWeight = fontWeight,
    fontSize = currentSize,
    color = color,
    overflow = overflow,
    onTextLayout = { result ->
      if(result.didOverflowWidth || result.didOverflowHeight) {
        println("GETS HERE")
        currentSize * 0.95
      }
    }
  )
}