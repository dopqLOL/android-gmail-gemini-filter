package com.github.dopqlol.androidgmailgeminifilter.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.dopqlol.androidgmailgeminifilter.data.dummyMailDetail // 仮のダミーデータをインポート
import com.github.dopqlol.androidgmailgeminifilter.ui.model.MailDetail // MailDetail データクラスをインポート
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MailDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle // ナビゲーション引数を受け取るために追加
) : ViewModel() {

    // UIに公開するメール詳細の状態 (読み取り専用)
    private val _mailDetail = MutableStateFlow<MailDetail?>(null)
    val mailDetail: StateFlow<MailDetail?> = _mailDetail.asStateFlow()

    // ナビゲーション引数からメールIDを取得 (キーはAppNavigationで定義したものと一致させる)
    private val mailId: String? = savedStateHandle.get<String>("mailId")

    init {
        loadMailDetail()
    }

    private fun loadMailDetail() {
        viewModelScope.launch {
            // TODO: mailId を使ってRepositoryから実際のメール詳細データを取得する
            // 現時点では、渡されたmailIdに関わらず固定のダミーデータを表示
            if (mailId != null) {
                // ここで mailId を使って何か処理をする (例: ログ出力)
                println("MailDetailViewModel: Received mailId = $mailId")
                _mailDetail.value = dummyMailDetail // 固定のダミーデータを設定
            } else {
                // mailId が渡されなかった場合の処理 (例: エラー表示など)
                println("MailDetailViewModel: mailId is null")
                _mailDetail.value = null
            }
        }
    }
}
