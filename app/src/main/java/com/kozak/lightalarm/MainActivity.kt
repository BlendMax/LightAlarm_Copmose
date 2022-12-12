package com.kozak.lightalarm


import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val runningState = remember {
                mutableStateOf("Disabled")
            }
            Column(
                modifier = Modifier
                    .background(Color.DarkGray)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = runningState.value,
                    fontSize = 40.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 30.dp)
                )
                Button(
                    modifier = Modifier
                        .padding(vertical = 30.dp)
                        .height(80.dp)
                        .width(180.dp),
                    onClick = {
                    startService(Intent(this@MainActivity,
                        BatteryStatusSupervisorService::class.java))
                        runningState.value = "Enabled"

                }) {
                    Text(text = "Turn")
                }

                Button(
                    modifier = Modifier
                        .padding(vertical = 30.dp)
                        .height(80.dp)
                        .width(180.dp),
                    onClick = {
                    stopService(Intent(this@MainActivity,
                        BatteryStatusSupervisorService::class.java))
                        runningState.value = "Disabled"

                }) {
                    Text(text = "Of")
                }
            }
        }
    }
}
