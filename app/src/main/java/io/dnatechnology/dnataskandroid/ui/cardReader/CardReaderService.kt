package io.dnatechnology.dnataskandroid.ui.cardReader

import kotlinx.coroutines.delay
import java.util.*
import java.util.Calendar.SECOND

/**
 * This is a mock implementation.
 * For the purposes of this task we simply pretend there is a service we are using.
 *
 * To simulate errors, service throws `CardReaderException` in the second half of any minute.
 */
class CardReaderService {

    suspend fun readCard(): CardData {
        val second = Calendar.getInstance().get(SECOND)

        if (second <= 30) {
            // User will need some time to use the card
            delay(4000)
            return CardData(UUID.randomUUID().toString())
        }

        throw CardReaderException()
    }

}

class CardReaderException : Throwable("Could not read card data")

data class CardData(val token: String)
