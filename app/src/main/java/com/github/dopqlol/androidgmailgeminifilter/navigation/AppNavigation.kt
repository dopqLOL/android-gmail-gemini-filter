package com.github.dopqlol.androidgmailgeminifilter.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.dopqlol.androidgmailgeminifilter.ui.screens.LoginScreen
import com.github.dopqlol.androidgmailgeminifilter.ui.screens.MailListScreen
import com.github.dopqlol.androidgmailgeminifilter.ui.screens.MailDetailScreen
import androidx.navigation.NavType
import androidx.navigation.navArgument

// 画面ルートを定義する sealed class
sealed class Screen(val route: String) {
    object Login : Screen("login")
    object MailList : Screen("mailList")
    object MailDetail : Screen("mailDetail/{mailId}") { // メールIDをパラメータとして受け取る
        fun createRoute(mailId: String) = "mailDetail/$mailId"
    }
}

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController() // NavController を取得

    NavHost(navController = navController, startDestination = Screen.Login.route) { // ナビゲーションホスト
        composable(Screen.Login.route) { backStackEntry -> // backStackEntry を受け取るように変更
            LoginScreen(navController = navController) // NavHost の navController を渡す
        }
        composable(Screen.MailList.route) {
            MailListScreen(navController = navController) // メール一覧画面
        }
        composable(
            route = Screen.MailDetail.route,
            arguments = listOf(navArgument("mailId") { type = NavType.StringType }) // mailId の引数を定義
        ) { backStackEntry ->
            // メールIDを引数から取得
            val mailId = backStackEntry.arguments?.getString("mailId")
            // TODO: 取得した mailId を使ってメール詳細データを表示
            MailDetailScreen(mailId = mailId) // メール詳細画面
        }
    }
}
