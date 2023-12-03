package ru.nelshin.learnenglishwordsapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import android.content.Intent

class ResultsActivity : AppCompatActivity() {

    private lateinit var inAnimation: Animation
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)
        val lottieView: LottieAnimationView = findViewById(R.id.lottieView)
        val btnReload: Button = findViewById(R.id.btnReload)
        val btnCloseResult: ImageButton = findViewById(R.id.btnCloseResult)
        val clBlockResult: androidx.constraintlayout.widget.ConstraintLayout = findViewById(R.id.clBlockResult)

        var tvCorrectAnswerResult: TextView = findViewById(R.id.tvCorrectAnswerResult)
        var tvWrongAnswerResult: TextView = findViewById(R.id.tvWrongAnswerResult)
        var tvSkipAnswerResult: TextView = findViewById(R.id.tvMisseAnswerResult)

        inAnimation = AnimationUtils.loadAnimation(this, R.anim.words_in_slowly)


        lottieView.setMinProgress(0.0f)
        lottieView.setMaxProgress(1.0f)
        lottieView.repeatCount = 0
        lottieView.repeatMode = LottieDrawable.RESTART
        lottieView.playAnimation()
        btnReload.startAnimation(inAnimation)
        btnCloseResult.startAnimation((inAnimation))
        clBlockResult.startAnimation(inAnimation)

        tvCorrectAnswerResult.text = intent.getStringExtra("CorrectAnswer")
        tvWrongAnswerResult.text = intent.getStringExtra("wrongAnswer")
        tvSkipAnswerResult.text = intent.getStringExtra("missedAnswer")

        btnReload.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnCloseResult.setOnClickListener{
            finishAffinity()
        }

    }
}