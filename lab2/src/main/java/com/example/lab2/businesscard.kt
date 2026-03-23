package com.example.lab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFFD2E8D4)) {
                BusinessCard()
            }
        }
    }
}

@Composable
fun BusinessCard() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f).wrapContentHeight(Alignment.CenterVertically)
        ) {
            Image(
                painter = painterResource(id = R.drawable.android_logo),
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .background(Color(0xFF073042))
                    .padding(10.dp)
            )
            Text(
                text = "Quang Hoang Phu",
                fontSize = 35.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(top = 10.dp)
            )
            Text(
                text = "Android Developer",
                color = Color(0xFF006D3B),
                fontWeight = FontWeight.Bold
            )
        }

        Column(modifier = Modifier.padding(bottom = 60.dp)) {
            ContactInfo(icon = Icons.Rounded.Phone, text = "+84 359721316")
            ContactInfo(icon = Icons.Rounded.Share, text = "@hoangphu")
            ContactInfo(icon = Icons.Rounded.Email, text = "phuqh.24ic@vku.udn.vn")
        }
    }
}

@Composable
fun ContactInfo(icon: ImageVector, text: String) {
    Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = icon, contentDescription = null, tint = Color(0xFF006D3B))
        Spacer(modifier = Modifier.width(15.dp))
        Text(text = text, fontSize = 16.sp)
    }
}