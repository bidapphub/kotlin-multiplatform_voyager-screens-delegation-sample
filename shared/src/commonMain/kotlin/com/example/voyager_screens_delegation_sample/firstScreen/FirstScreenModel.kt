package com.example.voyager_screens_delegation.FirstScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.navigator.Navigator
import com.example.voyager_screens_delegation.SecondScreen.SecondScreen
import com.example.voyager_screens_delegation.SecondScreen.SecondScreenDelegate
import com.example.voyager_screens_delegation.screenDelegates

sealed interface FirstScreenAction {
  data object OnButtonClick: FirstScreenAction
}

class FirstScreenModel(
  private val navigator: Navigator
): ScreenModel {
  var selectedItemIndex: Int by mutableStateOf(-1)

  fun dispatch(action: FirstScreenAction) {
    when (action) {
      is FirstScreenAction.OnButtonClick -> {
        val delegateKey = this.hashCode().toString()
        navigator.screenDelegates.setDelegate(
          delegateKey = delegateKey,
          delegate = SecondScreenDelegate(
            onItemClick = {
              selectedItemIndex = it
              navigator.screenDelegates.clearDelegate(delegateKey)
              navigator.pop()
            },
            onDismiss = {
              navigator.screenDelegates.clearDelegate(delegateKey)
              navigator.pop()
            }
          )
        )
        navigator.push(SecondScreen(delegateKey = delegateKey))
      }
    }
  }
}