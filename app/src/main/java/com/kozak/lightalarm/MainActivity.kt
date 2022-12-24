package com.kozak.lightalarm


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kozak.lightalarm.ui.theme.FirstColor
import com.kozak.lightalarm.ui.theme.FirstTextColor
import com.kozak.lightalarm.ui.theme.SecondColor
import com.kozak.lightalarm.ui.theme.ThirdColor

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setPermission()
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

    private fun setPermission(){
        val myIntent = Intent()
        myIntent.action = Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
        myIntent.data = Uri.parse("package:$packageName")
        this.startActivity(myIntent)
    }
}
