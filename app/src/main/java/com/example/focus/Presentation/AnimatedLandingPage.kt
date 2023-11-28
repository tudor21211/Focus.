package com.example.focus.Presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.onEach

class AnimatedLandingPage : ViewModel() {

    companion object {
        val motivation = listOf(
            "Your time is limited, don't waste it living someone else's life.",
            "Believe you can and you're halfway there.",
            "The best way to predict the future is to create it.",
            "Do it with passion or not at all.",
            "The future depends on what you do today.",
            "Don't count the days, make the days count.",
            "The only limit to our realization of tomorrow will be our doubts of today.",
            "Dreams don't work unless you do.",
            "You are never too old to set another goal or to dream a new dream.",
            "The only place where success comes before work is in the dictionary.",
            "Success is stumbling from failure to failure with no loss of enthusiasm.",
            "The secret to getting ahead is getting started.",
            "It's not about having time, it's about making time.",
            "The only person you are destined to become is the person you decide to be.",
            "Don't stop when you're tired. Stop when you're done.",
            "The only way to achieve the impossible is to believe it is possible.",
            "Do something today that your future self will thank you for.",
            "Work hard in silence, let your success be the noise."
        ).asSequence().asFlow().onEach { delay(2500) }
    }

}