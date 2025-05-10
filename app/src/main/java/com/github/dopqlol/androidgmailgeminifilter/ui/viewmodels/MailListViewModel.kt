package com.github.dopqlol.androidgmailgeminifilter.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.dopqlol.androidgmailgeminifilter.data.dummyMailItems
import com.github.dopqlol.androidgmailgeminifilter.ui.model.MailItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MailListViewModel @Inject constructor() : ViewModel() {

    private val _mailItems = MutableStateFlow<List<MailItem>>(emptyList())
    val mailItems: StateFlow<List<MailItem>> = _mailItems.asStateFlow()

    init {
        loadMailItems()
    }

    private fun loadMailItems() {
        viewModelScope.launch {
            _mailItems.value = dummyMailItems
        }
    }

    fun onMailItemClicked(mailId: String) {
        println("Mail item clicked: $mailId")
    }
}
