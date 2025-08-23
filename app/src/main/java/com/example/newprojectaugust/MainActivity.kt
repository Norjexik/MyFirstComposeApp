package com.example.newprojectaugust

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newprojectaugust.ui.theme.NewProjectAugustTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewProjectAugustTheme {
                CounterApp()
            }
        }
    }
}

@Composable
fun CounterApp() {
    var count by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFFF0F0F0))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Счёт: $count",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Blue
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = { count++ },
            colors = ButtonDefaults.buttonColors(contentColor = Color(0xFF0066CC))
        ){
            Text(
                text = "Нажми меня",
                color = Color.White,
                fontSize = 18.sp
            )
        }
        Button(
            onClick = {count = 0},
            colors = ButtonDefaults.buttonColors(contentColor = Color.Red)
        ){
            Text(
                text = "Сброс",
                color = Color.White,
                fontSize = 18.sp
            )
        }
    }
}

@Preview
@Composable
fun Preview(){
    NewProjectAugustTheme{
        CounterApp()
    }
}