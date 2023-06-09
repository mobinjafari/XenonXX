package org.lotka.bp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ConnectivityMonitor(
  isNetworkAvailable: Boolean,
  modifier: Modifier
){
  if(!isNetworkAvailable){
    Column(modifier = modifier){
      Text(
        "No network connection",
        modifier = Modifier.fillMaxWidth().background(Color.Red).align(Alignment.CenterHorizontally).padding(8.dp),
        style = MaterialTheme.typography.h6,
        textAlign = TextAlign.Center
      )
    }
  }
}