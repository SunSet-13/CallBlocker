package com.example.callblocker

import android.telecom.Call
import android.telecom.CallScreeningService

class CallBlocker : CallScreeningService() {
    override fun onScreenCall(callDetails: Call.Details) {
        val incomingNumber = callDetails.handle?.schemeSpecificPart ?: return

        if (isBlockedNumber(incomingNumber)) {
            val response = CallResponse.Builder()
                .setDisallowCall(true)
                .setSkipCallLog(true)
                .setSkipNotification(true)
                .build()
            respondToCall(callDetails, response)
        }
    }

    private fun isBlockedNumber(number: String): Boolean {
        val blockedNumbers = listOf("+84398269310", "0398269310", "0876940967", "+84876940967","0984546458","+84984546458")
        return blockedNumbers.contains(number)
    }
}