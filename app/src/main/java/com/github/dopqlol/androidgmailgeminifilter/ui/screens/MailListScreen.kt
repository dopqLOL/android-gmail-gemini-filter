package com.github.dopqlol.androidgmailgeminifilter.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.github.dopqlol.androidgmailgeminifilter.data.dummyMailItems // ダミーデータをインポート
import com.github.dopqlol.androidgmailgeminifilter.data.model.MailItem // MailItem データクラスをインポート
import com.github.dopqlol.androidgmailgeminifilter.navigation.Screen
import com.github.dopqlol.androidgmailgeminifilter.ui.theme.AppTheme

@Composable
fun MailListScreen(navController: NavController) {
    // LazyColumn を使ってリストを表示
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(dummyMailItems) { mailItem ->
            MailItemRow(
                mailItem = mailItem, // LazyColumn から提供される現在のメールアイテム
                onItemClick = { mailId ->
                    navController.navigate(Screen.MailDetail.createRoute(mailId))
                }
            )
        }
    }
}

@Composable
fun MailItemRow(mailItem: MailItem, onItemClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clickable { onItemClick(mailItem.id) }, // clickable 修飾子を追加
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 重要度を示す丸いインジケータのプレースホルダー
            Box(
                modifier = Modifier
                    .size(12.dp)
                    // TODO: 重要度に応じて色を変える
                    .background(MaterialTheme.colorScheme.primary) // 仮の色
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = mailItem.sender,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = mailItem.subject,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = mailItem.date,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MailListScreenPreview() {
    AppTheme {
        // Previewではダミーの NavController を渡す必要があります
        MailListScreen(navController = rememberNavController()) // ダミーの NavController を渡す
    }
}
