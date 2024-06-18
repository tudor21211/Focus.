package com.example.focusparentapp.Presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.onEach

class AnimatedLandingPage : ViewModel() {

    companion object {
        val motivation = listOf(
            "Setting limits helps children learn self-discipline.",
            "Guiding your child's screen time today shapes their future.",
            "Creating safe boundaries online helps keep your child safe.",
            "Parental control is an act of love and protection.",
            "What you teach your child today will echo in their future.",
            "Make every moment count with your child, online and offline.",
            "Instilling good habits now builds a strong foundation for tomorrow.",
            "Your guidance today leads to their success tomorrow.",
            "Every rule you set is a step towards your child's safety.",
            "Empowering your child with the right choices starts with you.",
            "Every limit you set helps them grow stronger and smarter.",
            "Your involvement today makes a difference in their tomorrow.",
            "Teaching balance and boundaries is a gift to your child.",
            "The best security you can offer is your presence and guidance.",
            "Investing time in your child's online safety is investing in their future.",
            "Strong boundaries today lead to safe explorations tomorrow.",
            "Your vigilance today ensures their safety in the digital world.",
            "Helping your child navigate technology wisely is a lifelong gift."
        ).asSequence().asFlow().onEach { delay(2500) }
    }


}