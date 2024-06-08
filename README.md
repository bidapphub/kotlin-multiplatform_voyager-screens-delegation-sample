# Voyager Screens Delegation Sample App

## Overview
This project is the mobile application that uses the Voyager library for navigation and manages delegates associated with different screens in the application. 
The delegates are used for handling specific tasks or behaviors associated with the screens.

## Demo Apps
To be able to use such logic in your application you need to:

1. Add Voyager extension to your project

VoyagerScreenDelegate.kt

```kotlin
val Navigator.screenDelegates: VoyagerScreenDelegate
  get() = NavigatorLifecycleStore.get(this) {
    VoyagerScreenDelegate()
  }

class VoyagerScreenDelegate() : NavigatorDisposable {
  private val delegates = mutableStateMapOf<String, Any?>()

  override fun onDispose(navigator: Navigator) {
    clearDelegates()
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

  private fun clearDelegates() {
    delegates.clear()
  }
}
```

2. Add the delegate to the screen that needs to delegate specific tasks handling to outside of it and it's screenModel

```kotlin
data class SecondScreenDelegate(
  val onItemClick: (Int) -> Unit,
  val onDismiss: () -> Unit
)
```

3. Initiate delegate in the FirstScreen before pushing SecondScreen to the Navigator stack.

```kotlin
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
```

4. Provide delegateKey to SecondScreen so it could get delegate instance from the Navigator

```kotlin
navigator.push(SecondScreen(delegateKey = delegateKey))
```

5. In the SecondScreen get the delegate instance from the Navigator

```kotlin
private val _delegate: SecondScreenDelegate?
    get() = delegateKey?.let { navigator.screenDelegates.getDelegate<SecondScreenDelegate>(delegateKey) }
```

6. Fire delegate callbacks in places where it's needed

```kotlin
when (action) {
  is SecondScreenAction.OnItemClick -> {
    _delegate?.onItemClick?.let { it(action.index) }
  }
  is SecondScreenAction.OnDismiss -> {
    _delegate?.onDismiss?.let { it() }
  }
}
```