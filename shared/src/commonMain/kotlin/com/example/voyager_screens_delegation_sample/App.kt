package com.example.voyager_screens_delegation_sample

import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.example.voyager_screens_delegation.FirstScreen.FirstScreen

@Composable
fun App() {
  MaterialTheme(
    colorScheme = lightColorScheme(),
    shapes = MaterialTheme.shapes.copy(
      small = AbsoluteCutCornerShape(0.dp),
      medium = AbsoluteCutCornerShape(0.dp),
      large = AbsoluteCutCornerShape(0.dp)
    )
  ) {
    Navigator(
      screen = FirstScreen(),
      content = { navigator ->
        SlideTransition(navigator = navigator)
      }
    )
  }
}