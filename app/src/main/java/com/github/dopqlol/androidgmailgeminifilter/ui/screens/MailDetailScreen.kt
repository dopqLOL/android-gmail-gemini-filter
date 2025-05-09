package com.github.dopqlol.androidgmailgeminifilter.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.dopqlol.androidgmailgeminifilter.data.dummyMailDetail // ダミーデータをインポート
import com.github.dopqlol.androidgmailgeminifilter.ui.theme.AppTheme

@Composable
fun MailDetailScreen(mailId: String?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Received Mail ID: ${mailId ?: "N/A"}", // 受け取った mailId を表示
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(8.dp)) // 余白を追加
        Text( // From: Sender A の表示
            text = "From: ${dummyMailDetail.sender}",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = dummyMailDetail.body,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MailDetailScreenPreview() {
    AppTheme {
        MailDetailScreen(  mailId = 123.toString())
    }
}
