package com.example.voyager_screens_delegation.FirstScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

data class FirstScreen(
  override val key: ScreenKey = "FirstScreen"
): Screen {
  @Composable
  override fun Content() {
    val navigator = LocalNavigator.currentOrThrow
    val screenModel = rememberScreenModel {
      FirstScreenModel(navigator)
    }

    Column(
      modifier = Modifier.fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      if (screenModel.selectedItemIndex != -1) {
        Text(
          text = "Selected item index: ${screenModel.selectedItemIndex}",
          modifier = Modifier.align(Alignment.CenterHorizontally),
          style = MaterialTheme.typography.titleLarge,
          color = ButtonDefaults.buttonColors().contentColor
        )
        Spacer(modifier = Modifier.height(16.dp))
      }
      Button(
        onClick = {
          screenModel.dispatch(FirstScreenAction.OnButtonClick)
        }
      ) {
        Text("Push to Second Screen")
      }
    }
  }
}