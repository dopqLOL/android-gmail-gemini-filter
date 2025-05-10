package com.github.dopqlol.androidgmailgeminifilter.data.repository // パッケージ名は実際のディレクトリ構造に合わせてください

import com.github.dopqlol.androidgmailgeminifilter.data.dummyMailDetail
import com.github.dopqlol.androidgmailgeminifilter.data.dummyMailItems
import com.github.dopqlol.androidgmailgeminifilter.ui.model.MailDetail
import com.github.dopqlol.androidgmailgeminifilter.ui.model.MailItem
import javax.inject.Inject

class MockMailRepositoryImpl @Inject constructor() : MailRepository {

    override suspend fun getMails(): List<MailItem> {
        // 固定のダミーメールリストを返す
        return dummyMailItems
    }

    override suspend fun getMailDetail(id: String): MailDetail? {
        // 指定されたIDに基づいてダミーメール詳細を返す (今回は固定のものを返す)
        // 本来は、渡されたidに一致するデータをdummyMailItemsから探すなどの処理が入る
        // または、idごとに異なるダミー詳細データを用意しておく
        return dummyMailItems.find { it.id == id }?.let {
            // 簡単のため、見つかったMailItemの情報を元にMailDetailを生成
            MailDetail(
                sender = it.sender,
                subject = it.subject,
                body = "This is the body for mail ID: ${it.id}. Content should be specific to this mail."
            )
        } ?: dummyMailDetail // 見つからなければデフォルトのダミー詳細を返す (あるいはnull)
    }
}
