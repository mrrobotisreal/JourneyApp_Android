package io.winapps.journeyapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import io.winapps.journeyapp.ui.theme.NexaScript

@Composable
fun SplashScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier
                .align(Alignment.Center)
                .widthIn(max = 340.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 7.dp),
            border = BorderStroke(2.dp, Color(0xFF024873)),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF88DFF2))
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Welcome to...",
                    fontFamily = NexaScript,
                    fontWeight = FontWeight.Light,
                    fontSize = 18.sp,
                    color = Color(0xFF022840)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "JourneyApp.me!",
                    fontFamily = NexaScript,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 32.sp,
                    color = Color(0xFF022840)
                )
                Spacer(modifier = Modifier.height(8.dp))
                // TODO: Add Image with .cornerRadius(12) in Compose
                Image(
                    painter = painterResource(id = R.drawable.icon),
                    contentDescription = "Main splash image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.clip(RoundedCornerShape(12.dp)).shadow(
                        elevation = 7.dp,
                        shape = RoundedCornerShape(12.dp)
                    )
                )
                Text(
                    text = "Your personal journal for letting your creativity flow, tracking your daily progress toward goals, or even just writing about the highlights of your day...",
                    fontFamily = NexaScript,
                    fontWeight = FontWeight.Light,
                    fontSize = 12.sp,
                    color = Color(0xFF022840)
                )
            }
        }

//        Box(
//            modifier = Modifier.align(Alignment.Center).background(
//                color = Color(0xFF88DFF2),
//                shape = RoundedCornerShape(12)
//            )
//                .border(
//                    width = 2.dp,
//                    color = Color(0xFF024873),
//                    shape = RoundedCornerShape(12)
//                )
//                .shadow(
//                    elevation = 7.dp,
//                    shape = RoundedCornerShape(12),
//                    ambientColor = Color.Black.copy(alpha = 0.9f),
//                    spotColor = Color.Black.copy(alpha = 0.9f)
//                )
//                .padding(16.dp)
//                .widthIn(max = 340.dp)
//        ) {
//            Column(
//                modifier = Modifier.padding(16.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text(
//                    text = "Welcome to...",
//                    fontSize = 18.sp,
//                    color = Color(0xFF022840)
//                )
//                Spacer(modifier = Modifier.height(4.dp))
//                Text(
//                    text = "JourneyApp.me!",
//                    fontSize = 32.sp,
//                    color = Color(0xFF022840)
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//                // TODO: Add Image with .cornerRadius(12) in Compose
//                Text(
//                    text = "Your personal journal for letting your creativity flow, tracking your daily progress toward goals, or even just writing about the highlights of your day...",
//                    fontSize = 12.sp,
//                    color = Color(0xFF022840)
//                )
//            }
//        }
    }
}