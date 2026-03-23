package com.example.lab6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


val DarkTextColor = Color(0xFF3E2723)
val PrimaryPink = Color(0xFF880E4F)

object DataSource {
    val flavors = listOf("Vanilla", "Chocolate", "Red Velvet", "Salted Caramel", "Coffee")
    val pickupOptions = listOf("Hôm nay", "Ngày mai", "Thứ 7", "Chủ nhật")
    val quantityOptions = listOf(1, 6, 12)
}

enum class CupcakeScreen { Start, Flavor, Pickup, Summary }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CupcakeApp()
        }
    }
}

@Composable
fun CupcakeApp(navController: NavHostController = rememberNavController()) {
    var selectedQuantity by remember { mutableIntStateOf(1) }
    var selectedFlavor by remember { mutableStateOf(DataSource.flavors[0]) }
    var selectedDate by remember { mutableStateOf(DataSource.pickupOptions[0]) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            Text(
                text = "Developed by Quang Hoang Phu",
                modifier = Modifier.fillMaxWidth().padding(16.dp).navigationBarsPadding(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelMedium,
                color = DarkTextColor.copy(alpha = 0.7f) // Đậm hơn màu Gray cũ
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding).fillMaxSize().background(Color(0xFFFDF0F5))) {
            NavHost(
                navController = navController,
                startDestination = CupcakeScreen.Start.name
            ) {
                composable(route = CupcakeScreen.Start.name) {
                    StartOrderScreen { quantity ->
                        selectedQuantity = quantity
                        navController.navigate(CupcakeScreen.Flavor.name)
                    }
                }
                composable(route = CupcakeScreen.Flavor.name) {
                    FlavorScreen(
                        currentFlavor = selectedFlavor,
                        onFlavorSelected = { selectedFlavor = it },
                        onNextButtonClicked = { navController.navigate(CupcakeScreen.Pickup.name) },
                        onCancelButtonClicked = {
                            navController.popBackStack(CupcakeScreen.Start.name, false)
                        }
                    )
                }
                composable(route = CupcakeScreen.Pickup.name) {
                    PickupScreen(
                        currentDate = selectedDate,
                        onDateSelected = { selectedDate = it },
                        onNextButtonClicked = { navController.navigate(CupcakeScreen.Summary.name) },
                        onCancelButtonClicked = {
                            navController.popBackStack(CupcakeScreen.Start.name, false)
                        }
                    )
                }
                composable(route = CupcakeScreen.Summary.name) {
                    SummaryScreen(
                        quantity = selectedQuantity,
                        flavor = selectedFlavor,
                        date = selectedDate,
                        onSendButtonClicked = {
                            navController.navigate(CupcakeScreen.Start.name) {
                                popUpTo(CupcakeScreen.Start.name) { inclusive = true }
                            }
                        },
                        onCancelButtonClicked = {
                            navController.popBackStack(CupcakeScreen.Start.name, false)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun StartOrderScreen(onQuantitySelected: (Int) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.cupcake),
            contentDescription = null,
            modifier = Modifier.size(160.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Order Cupcakes",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold,
                color = DarkTextColor
            )
        )
        Spacer(modifier = Modifier.height(32.dp))
        DataSource.quantityOptions.forEach { qty ->
            Button(
                onClick = { onQuantitySelected(qty) },
                modifier = Modifier.width(250.dp).padding(vertical = 4.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryPink)
            ) {
                Text("$qty Cupcakes", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun FlavorScreen(
    currentFlavor: String,
    onFlavorSelected: (String) -> Unit,
    onNextButtonClicked: () -> Unit,
    onCancelButtonClicked: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Chọn hương vị:",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            color = DarkTextColor
        )
        Spacer(modifier = Modifier.height(16.dp))
        DataSource.flavors.forEach { flavor ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (currentFlavor == flavor),
                        onClick = { onFlavorSelected(flavor) }
                    )
                    .padding(vertical = 12.dp)
            ) {
                RadioButton(
                    selected = (currentFlavor == flavor),
                    onClick = null
                )
                Text(
                    text = flavor,
                    fontSize = 18.sp,
                    color = DarkTextColor,
                    fontWeight = if (currentFlavor == flavor) FontWeight.Bold else FontWeight.Medium,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            OutlinedButton(modifier = Modifier.weight(1f), onClick = onCancelButtonClicked) {
                Text("Hủy", color = DarkTextColor)
            }
            Button(
                modifier = Modifier.weight(1f),
                onClick = onNextButtonClicked,
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryPink)
            ) {
                Text("Tiếp theo")
            }
        }
    }
}

@Composable
fun PickupScreen(
    currentDate: String,
    onDateSelected: (String) -> Unit,
    onNextButtonClicked: () -> Unit,
    onCancelButtonClicked: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Chọn ngày nhận:",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            color = DarkTextColor
        )
        Spacer(modifier = Modifier.height(16.dp))
        DataSource.pickupOptions.forEach { date ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (currentDate == date),
                        onClick = { onDateSelected(date) }
                    )
                    .padding(vertical = 12.dp)
            ) {
                RadioButton(
                    selected = (currentDate == date),
                    onClick = null
                )
                Text(
                    text = date,
                    fontSize = 18.sp,
                    color = DarkTextColor,
                    fontWeight = if (currentDate == date) FontWeight.Bold else FontWeight.Medium,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            OutlinedButton(modifier = Modifier.weight(1f), onClick = onCancelButtonClicked) {
                Text("Hủy", color = DarkTextColor)
            }
            Button(
                modifier = Modifier.weight(1f),
                onClick = onNextButtonClicked,
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryPink)
            ) {
                Text("Tiếp theo")
            }
        }
    }
}

@Composable
fun SummaryScreen(
    quantity: Int,
    flavor: String,
    date: String,
    onSendButtonClicked: () -> Unit,
    onCancelButtonClicked: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Tổng kết đơn hàng",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            color = DarkTextColor
        )

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            SummaryItem(label = "SỐ LƯỢNG", value = "$quantity chiếc")
            SummaryItem(label = "HƯƠNG VỊ", value = flavor)
            SummaryItem(label = "NGÀY NHẬN", value = date)
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), thickness = 2.dp, color = DarkTextColor.copy(alpha = 0.2f))

        Text(
            text = "TỔNG TIỀN: $${quantity * 2}.00",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold),
            color = Color(0xFFC2185B)
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onSendButtonClicked,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE91E63))
        ) {
            Text("Gửi đơn hàng", fontWeight = FontWeight.Bold)
        }
        OutlinedButton(modifier = Modifier.fillMaxWidth(), onClick = onCancelButtonClicked) {
            Text("Hủy", color = DarkTextColor)
        }
    }
}

@Composable
fun SummaryItem(label: String, value: String) {
    Column {
        Text(text = label, style = MaterialTheme.typography.labelLarge, color = DarkTextColor.copy(alpha = 0.6f))
        Text(text = value, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold, color = DarkTextColor)
    }
}