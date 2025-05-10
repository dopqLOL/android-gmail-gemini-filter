package com.github.dopqlol.androidgmailgeminifilter.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.dopqlol.androidgmailgeminifilter.ui.theme.AppTheme // AppThemeをインポート
import androidx.navigation.NavController // これを追加
import androidx.navigation.compose.rememberNavController
import com.github.dopqlol.androidgmailgeminifilter.navigation.AppNavigation
import com.github.dopqlol.androidgmailgeminifilter.navigation.Screen
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.dopqlol.androidgmailgeminifilter.ui.viewmodels.LoginViewModel 



@Composable
fun LoginScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = hiltViewModel() // ViewModel を取得
) {
    // 画面全体を占めるコンテナ
    Column(

        modifier = Modifier
            .fillMaxSize() // 画面いっぱいに広がる
            .padding(16.dp), // 余白を追加
        verticalArrangement = Arrangement.Center, // 垂直方向中央寄せ
        horizontalAlignment = Alignment.CenterHorizontally // 水平方向中央寄せ
    ) {
        // ここにUI要素を追加します
        Button(onClick = {
            // TODO: ログイン処理を実装
            navController.navigate(Screen.MailList.route) // メール一覧画面へ遷移
        }) {
            Text("Googleでサインイン")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    AppTheme {
        LoginScreen(navController = rememberNavController())
    }
}