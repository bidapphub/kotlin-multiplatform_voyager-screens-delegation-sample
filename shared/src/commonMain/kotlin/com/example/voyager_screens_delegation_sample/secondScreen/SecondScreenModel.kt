package com.example.voyager_screens_delegation.SecondScreen

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.navigator.Navigator
import com.example.voyager_screens_delegation.screenDelegates

sealed interface SecondScreenAction {
  data class OnItemClick(val index: Int): SecondScreenAction
  data object OnDismiss: SecondScreenAction
}

class SecondScreenModel(
  private val navigator: Navigator,
  private val delegateKey: String?
): ScreenModel {

  private val _delegate: SecondScreenDelegate?
    get() = delegateKey?.let { navigator.screenDelegates.getDelegate<SecondScreenDelegate>(delegateKey) }

  fun dispatch(action: SecondScreenAction) {
    when (action) {
      is SecondScreenAction.OnItemClick -> {
        _delegate?.onItemClick?.let { it(action.index) }
      }
      is SecondScreenAction.OnDismiss -> {
        _delegate?.onDismiss?.let { it() }
      }
    }
  }
}