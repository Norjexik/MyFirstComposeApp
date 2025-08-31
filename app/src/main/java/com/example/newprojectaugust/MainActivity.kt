package com.example.newprojectaugust

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newprojectaugust.ui.theme.NewProjectAugustTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = CounterRepository(context = this)
        setContent {
            NewProjectAugustTheme {
                HabitTrackerApp(repository)
            }
        }
    }
}
@Composable
fun HabitTrackerApp(repository: CounterRepository) {
    val navController = rememberNavController()
    val sharedViewModel = remember { CounterViewModel(repository) }

    NavHost(
        navController = navController,
        startDestination = "counter"
    ){
        composable("counter"){
            CounterScreen(
                viewModel = sharedViewModel,
                onNavigateToStats = { navController.navigate("stats")},
                onNavigateToAbout = { navController.navigate("about")}
            )
        }
        composable("stats") {
            StatsScreen(
                viewModel = sharedViewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable("about") {
            AboutScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
//Экран: Начальный
@Composable
fun CounterScreen(
    viewModel: CounterViewModel,
    onNavigateToStats: () -> Unit,
    onNavigateToAbout: () -> Unit
){
    val count by viewModel.count.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "Счётчик привычки",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = "Сделано: $count раз",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )
        Row {
            Button(
                onClick = { viewModel.increment()},
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Сделал!")
            }
            Button(
                onClick = { viewModel.reset() },
                modifier = Modifier.padding(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("Сброс")
            }
        }
        Spacer(modifier = Modifier.height(32.dp))

        Row{
            TextButton(onClick = onNavigateToStats) {
                Text("Статистика")
            }
            TextButton(onClick = onNavigateToAbout) {
                Text("О приложении")
            }
        }
    }
}
// Экран: "Статистика"
@Composable
fun StatsScreen(
    onNavigateBack: () -> Unit,
    viewModel: CounterViewModel
){
    val count by viewModel.count.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("(\"\uD83D\uDCCA Статистика", style = MaterialTheme.typography.headlineMedium)
        Text("Ты сделал это $count раз! ", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onNavigateBack) {
            Text("Назад")
        }

    }

}
// Экран: "О приложении"
@Composable
fun AboutScreen(onNavigateBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text("ℹ\uFE0F О приложении", style = MaterialTheme.typography.headlineMedium)
        Text("Версия: 1.0.0", modifier = Modifier.padding(8.dp))
        Text("Разработано для обучения", modifier = Modifier.padding(8.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onNavigateBack){
            Text("Назад")
        }
    }
}