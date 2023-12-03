package ru.nelshin.learnenglishwordsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.nelshin.learnenglishwordsapp.databinding.ActivityStartLearnBinding
import android.content.Intent

class StartLearn : AppCompatActivity() {

    private lateinit var binding: ActivityStartLearnBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartLearnBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var intent = Intent(this@StartLearn, MainActivity::class.java)

        binding.ivProgrammingLearn.setOnClickListener{
            intent.putExtra("base", getString(R.string.result_programming))
            startActivity(intent)
        }
        binding.ivTravelLearn.setOnClickListener {
            intent.putExtra("base", getString(R.string.result_travel))
            startActivity(intent)
        }
        binding.ivSpotrLearn.setOnClickListener {
            intent.putExtra("base", getString(R.string.result_sport))
            startActivity(intent)
        }
        binding.ivShoolLearn.setOnClickListener{
            intent.putExtra("base", getString(R.string.result_school))
            startActivity(intent)
        }
    }
}