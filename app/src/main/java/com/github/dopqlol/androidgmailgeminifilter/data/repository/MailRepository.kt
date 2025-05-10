package com.github.dopqlol.androidgmailgeminifilter.data.repository // ディレクトリ構造に合わせてください

import com.github.dopqlol.androidgmailgeminifilter.ui.model.MailDetail
import com.github.dopqlol.androidgmailgeminifilter.ui.model.MailItem

interface MailRepository {
    suspend fun getMails(): List<MailItem>
    suspend fun getMailDetail(id: String): MailDetail?
}
