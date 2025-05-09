package com.github.dopqlol.androidgmailgeminifilter.data

import com.github.dopqlol.androidgmailgeminifilter.data.model.MailDetail
import com.github.dopqlol.androidgmailgeminifilter.data.model.MailItem

// ダミーのメールデータリスト
val dummyMailItems = listOf(
    MailItem(
        id = "1",
        sender = "Sender A",
        subject = "Subject 1",
        date = "2023/10/27 10:00",
        importance = "high"
    ),
    MailItem(
        id = "2",
        sender = "Sender B",
        subject = "Subject 2",
        date = "2023/10/27 11:00",
        importance = "medium"
    ),
    MailItem(
        id = "3",
        sender = "Sender C",
        subject = "Subject 3",
        date = "2023/10/27 12:00",
        importance = "low"
    )
)

// ダミーのメール詳細データ
val dummyMailDetail = MailDetail(
    sender = "Sender A",
    subject = "Subject 1",
    body = "This is the body of the first email. It contains some sample text to demonstrate the mail detail screen."
)