package com.github.dopqlol.androidgmailgeminifilter.ui.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    fun onSignInClicked() {
        // TODO: ログイン処理を実装
        println("Googleでサインイン ボタンがクリックされました") // デバッグ用
    }
}
