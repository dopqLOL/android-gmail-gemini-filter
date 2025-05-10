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
import androidx.compose.runtime.collectAsState // これを追加
import androidx.compose.runtime.getValue // これを追加
import androidx.hilt.navigation.compose.hiltViewModel // これを追加
import com.github.dopqlol.androidgmailgeminifilter.ui.viewmodels.MailDetailViewModel // これを追加
import com.github.dopqlol.androidgmailgeminifilter.ui.model.MailDetail // これを追加 (もし未追加の場合)

@Composable
fun MailDetailScreen(
    viewModel: MailDetailViewModel = hiltViewModel() // ViewModel を取得
) {
    val mailDetail by viewModel.mailDetail.collectAsState() // ViewModelからメール詳細を取得

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (mailDetail != null) {
            Text(
                text = "From: ${mailDetail!!.sender}", // ViewModelのデータを表示
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Subject: ${mailDetail!!.subject}", // ViewModelのデータを表示 (件名も表示するように変更)
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = mailDetail!!.body, // ViewModelのデータを表示
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        } else {
            // メール詳細データがない場合の表示 (例: ローディング中やエラー表示)
            Text("Loading mail detail...")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MailDetailScreenPreview() {
    AppTheme {
        // Previewでは実際のViewModelの動作は期待できない場合がある
        // 必要に応じてプレビュー用のダミーViewModelを渡すなどの対応を検討
        MailDetailScreen() // ViewModelはデフォルト引数でhiltViewModel()が使われる
    }
}

