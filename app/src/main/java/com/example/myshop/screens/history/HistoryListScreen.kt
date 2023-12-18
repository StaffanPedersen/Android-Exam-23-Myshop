package com.example.myshop.screens.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.example.myshop.components.TopBar

@Composable
fun HistoryListScreen(
    viewModel: HistoryViewModel,
    navController: NavController
) {
    val historyItems = viewModel.historyItems.collectAsState().value
    val groupedHistoryItems = historyItems.groupBy { it.sharedId }

    Column {
        TopBar(
            title = "Purchase History",
            onBackButtonClick = { navController.navigate("productListScreen") },
            onCartClick = { navController.navigate("shoppingCartScreen") },
            onOptionsClick = {},
            navController = navController
        )

        LazyColumn {
            groupedHistoryItems.forEach { (sharedId, items) ->
                item {
                    HistoryItem(historyItems = items)
                }
            }
        }
    }
}