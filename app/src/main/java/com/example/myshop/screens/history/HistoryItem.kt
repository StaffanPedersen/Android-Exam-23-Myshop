package com.example.myshop.screens.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myshop.data.History

@Composable
fun HistoryItem(
    historyItems: List<History>,
) {
    val totalPrice = historyItems.sumByDouble { it.productPrice * it.quantity }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(Color.LightGray)
    ) {
        Text(text = "Purchase Date: ${historyItems.first().purchaseDate}")
        Spacer(modifier = Modifier.height(8.dp))
        historyItems.forEach { history ->
            Text(text = "Product: ${history.productTitle}")
            Text(text = "Quantity: ${history.quantity}")
            Spacer(modifier = Modifier.height(8.dp))
        }
        Text(text = "Total Price: $${"%.2f".format(totalPrice)}")
    }
}