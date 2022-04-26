package com.base.exchange.kaspresso.screens

import android.view.View
import com.base.exchange.R
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.screen.Screen
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher

object MainScreen : Screen<MainScreen>() {

    val amountView = KEditText { withId(R.id.amountOfRoubles) }
    val exchangeView = KTextView { withId(R.id.amountOfExchange) }


    val transactionList = KRecyclerView(
        builder = { withId(R.id.exchangeList) },
        itemTypeBuilder = { itemType(::TransactionItem) }
    )

    class TransactionItem(parent: Matcher<View>) : KRecyclerItem<TransactionItem>(parent) {

        val info = KTextView(parent) { withId(R.id.exchangeInfo) }
    }

}