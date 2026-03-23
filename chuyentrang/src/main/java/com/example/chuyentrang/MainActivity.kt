package com.example.chuyentrang

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AppChuyentrang()
            }
        }
    }
}

@Composable
fun AppChuyentrang(myViewModel: ChuyentrangViewModel = viewModel()) {
    val navController = rememberNavController()
    // Nếu dòng này vẫn đỏ, fen hãy nhấn Alt + Enter vào chữ collectAsState
    val uiState by myViewModel.uiState.collectAsState()

    NavHost(navController = navController, startDestination = "trang1") {

        composable("trang1") {
            ScreenLayout(title = "Xin chào bạn!", buttonText = "Tiếp theo") {
                navController.navigate("trang2")
            }
        }

        composable("trang2") {
            ScreenLayout(title = "Mời bạn nhập tên của mình", buttonText = "Tiếp theo") {
                navController.navigate("trang3")
            }
        }

        composable("trang3") {
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Tên của bạn là:", fontSize = 20.sp)
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = uiState.hoTen,
                    onValueChange = { myViewModel.updateName(it) },
                    label = { Text("Nhập tên vào đây...") }
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = { navController.navigate("trang4") }) {
                    Text("Hoàn thành")
                }
            }
        }

        composable("trang4") {
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Kết quả cuối cùng:", fontSize = 18.sp)
                Text(
                    text = uiState.hoTen,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = {
                    navController.navigate("trang1") {
                        popUpTo("trang1") { inclusive = true }
                    }
                }) {
                    Text("Quay về từ đầu")
                }
            }
        }
    }
}

@Composable
fun ScreenLayout(title: String, buttonText: String, onNext: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = title, fontSize = 24.sp, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onNext) {
            Text(buttonText)
        }
    }
}