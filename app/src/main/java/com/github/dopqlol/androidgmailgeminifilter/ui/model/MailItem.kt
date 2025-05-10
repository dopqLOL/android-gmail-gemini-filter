package com.github.dopqlol.androidgmailgeminifilter.ui.model

data class MailItem(
    val id: String,
    val sender: String, // 差出人
    val subject: String, // 件名
    val date: String, // 受信日時
    val importance: String // 重要度 (例: "high", "medium", "low")
)
