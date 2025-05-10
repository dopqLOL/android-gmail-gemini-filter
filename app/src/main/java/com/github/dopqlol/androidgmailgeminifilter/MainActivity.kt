package com.github.dopqlol.androidgmailgeminifilter
import dagger.hilt.android.AndroidEntryPoint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.dopqlol.androidgmailgeminifilter.ui.theme.AppTheme
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.github.dopqlol.androidgmailgeminifilter.navigation.AppNavigation
import com.github.dopqlol.androidgmailgeminifilter.ui.screens.LoginScreen
import androidx.hilt.navigation.compose.hiltViewModel
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding -> // innerPadding を受け取る
                    AppNavigation(modifier = Modifier.padding(innerPadding))


                }
            }
        }
    }
}

//色チェック用
@Composable
fun DarkThemeShowcase(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // ヘッダーテキスト - プライマリカラーを使用
        Text(
            text = "ダークテーマのサンプル",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 様々なボタンのサンプル
        Button(onClick = { /* クリック処理 */ }) {
            Text("プライマリボタン")
        }

        Spacer(modifier = Modifier.height(12.dp))

        FilledTonalButton(onClick = { /* クリック処理 */ }) {
            Text("セカンダリトーンボタン")
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(onClick = { /* クリック処理 */ }) {
            Text("アウトラインボタン")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // カードサンプル - プライマリコンテナの色を使用
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "プライマリコンテナカード",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "このカードはプライマリコンテナの色を使用しています。テキストはOnPrimaryContainerの色で表示されます。",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // カードサンプル - セカンダリコンテナの色を使用
        ElevatedCard(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "セカンダリコンテナカード",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "このカードはセカンダリコンテナの色を使用しています。テキストはOnSecondaryContainerの色で表示されます。",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // カードサンプル - ターシャリコンテナの色を使用
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.tertiaryContainer
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "ターシャリコンテナカード",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "このカードはターシャリコンテナの色を使用しています。テキストはOnTertiaryContainerの色で表示されます。",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DarkThemeShowcasePreview() {
    AppTheme {
        DarkThemeShowcase()
    }
}