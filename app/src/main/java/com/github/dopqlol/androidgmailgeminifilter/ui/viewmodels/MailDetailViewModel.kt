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
import com.github.dopqlol.androidgmailgeminifilter.data.repository.MailRepository


@HiltViewModel
class MailDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val mailRepository: MailRepository
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
            if (mailId != null) {
                println("MailDetailViewModel: Received mailId = $mailId")
                _mailDetail.value = mailRepository.getMailDetail(mailId)
            } else {
                println("MailDetailViewModel: mailId is null")
                _mailDetail.value = null
            }
        }
    }
}
