package io.dnatechnology.dnataskandroid.ui.api

import io.dnatechnology.dnataskandroid.ui.api.data.PaymentRequest
import io.dnatechnology.dnataskandroid.ui.api.data.PaymentResponse
import io.dnatechnology.dnataskandroid.ui.api.data.PaymentStatus
import kotlinx.coroutines.delay

/**
 * This is a mock implementation.
 * For the purposes of this task we simply pretend there is an API we are using.
 *
 * For ease of testing it has some hardcoded rules:
 * 1. Executing payments with currency different from `EUR` will fail
 * 2. Executing payments with amount < 20.00 will fail
 * 3. Reverting payment with amount < 1.00 will fail
 */
class PaymentApiClient {
    /**
     * Call this method to execute payment on the account connected with provided card token
     */
    suspend fun pay(paymentRequest: PaymentRequest): PaymentResponse {
        delay(2500)

        return if (paymentRequest.currency == "EUR" && paymentRequest.amount >= 20.00 ) {
            PaymentResponse(paymentRequest.transactionID, PaymentStatus.SUCCESS)
        } else {
            PaymentResponse(paymentRequest.transactionID, PaymentStatus.FAILED)
        }
    }

    /**
     * Method meant for reverting payment when transaction could not be completed by the merchant.
      */
    suspend fun revert(paymentRequest: PaymentRequest): PaymentResponse {
        delay(500)
        return if (paymentRequest.amount >= 1.00 ) {
            PaymentResponse(paymentRequest.transactionID, PaymentStatus.SUCCESS)
        } else {
            PaymentResponse(paymentRequest.transactionID, PaymentStatus.FAILED)
        }
    }
}