package com.example.voyager_screens_delegation.SecondScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

data class SecondScreenDelegate(
  val onItemClick: (Int) -> Unit,
  val onDismiss: () -> Unit
)

data class SecondScreen(
  override val key: ScreenKey = "SecondScreen",
  val delegateKey: String? = null
) : Screen {
  @OptIn(ExperimentalMaterial3Api::class)
  @Composable
  override fun Content() {
    val navigator = LocalNavigator.currentOrThrow
    val screenModel = rememberScreenModel {
      SecondScreenModel(navigator, delegateKey)
    }

    Scaffold(
      modifier = Modifier.fillMaxSize(),
      topBar = {
        TopAppBar(
          title = {
            Text("Second Screen")
          },
          navigationIcon = {
            IconButton(onClick = {
              screenModel.dispatch(SecondScreenAction.OnDismiss)
            }) {
              Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
              )
            }
          }
        )
      }
    ) { innerPadding ->
      Box(Modifier
        .fillMaxSize()
        .padding(innerPadding)
      ) {
        val items = remember { (0..100).map { it } }
        LazyColumn(
          modifier = Modifier
            .fillMaxSize()
            .imePadding(),
          verticalArrangement = Arrangement.spacedBy(8.dp),
          contentPadding = PaddingValues(12.dp)
        ) {
          items(
            count = items.count(),
            key = { index -> items[index].toString() },
          ) { index ->
            Card(
              modifier = Modifier
                .fillMaxWidth()
                .clickable {
                  screenModel.dispatch(SecondScreenAction.OnItemClick(items[index]))
                }
            ) {
              Text("Item $index", modifier = Modifier.fillMaxWidth().padding(8.dp))
            }
          }
        }
      }
    }
  }
}