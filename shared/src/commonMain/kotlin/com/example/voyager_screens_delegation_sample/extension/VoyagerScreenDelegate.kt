package com.example.voyager_screens_delegation

import androidx.compose.runtime.mutableStateMapOf
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.lifecycle.NavigatorDisposable
import cafe.adriel.voyager.navigator.lifecycle.NavigatorLifecycleStore

val Navigator.screenDelegates: VoyagerScreenDelegate
  get() = NavigatorLifecycleStore.get(this) {
    VoyagerScreenDelegate()
  }

class VoyagerScreenDelegate() : NavigatorDisposable {
  private val delegates = mutableStateMapOf<String, Any?>()

  override fun onDispose(navigator: Navigator) {
    clearDelegate()
  }

  fun setDelegate(delegateKey: String, delegate: Any?) {
    delegates[delegateKey] = delegate
  }

  fun <T> getDelegate(delegateKey: String): T? {
    return delegates[delegateKey] as? T
  }

  fun clearDelegate(delegateKey: String) {
    delegates[delegateKey] = null
  }

  private fun clearDelegate() {
    delegates.clear()
  }
}