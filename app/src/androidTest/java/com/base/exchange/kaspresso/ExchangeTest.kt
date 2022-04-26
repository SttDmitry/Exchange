package com.base.exchange.kaspresso


import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.base.exchange.MainActivity
import com.base.exchange.kaspresso.screens.MainScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TransactionsTest : KTestCase() {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    @TestCase(name = "Test-5", description = "Check if Login input displayed with hint")
    fun checkExchangeListAndRate() {
        run {
            step("Pass login screen") {
                MainScreen {
                    amountView {
                        typeText("100")
                    }
                    exchangeView {
                        hasText("1.8583")
                    }
                }
            }

            step("Check transactions content") {
                checkTransactions(
                    Transaction(info = "доллар"),
                    Transaction(info = "манат"),
                    Transaction(info = "стерлингов"),
                    Transaction(info = "драмов"),
                    Transaction(info = "рубль")
                )
            }
        }
    }


    data class Transaction(val info: String)

    private fun checkTransactions(vararg transactions: Transaction) {
        transactions.forEachIndexed { index, transaction ->
            MainScreen {
                transactionList {
                    childAt<MainScreen.TransactionItem>(index) {
                        info {
                            isDisplayed()
                            containsText(transaction.info)
                        }
                    }
                }
            }
        }
    }

}