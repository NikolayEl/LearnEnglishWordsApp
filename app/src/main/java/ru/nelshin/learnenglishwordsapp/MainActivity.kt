package ru.nelshin.learnenglishwordsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import ru.nelshin.learnenglishwordsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = _binding
            ?: throw IllegalStateException("Binding for ActivityMainBinding must not be null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding =
            ActivityMainBinding.inflate(layoutInflater) //связывает класс байндинг с мэйнактивити, мы раздели разметку - из разметки xml сделали view
        setContentView(binding.root) //Меняем на получение корневого элемента разметки

        var answer: Boolean = false

        markAnswerNeutral()
        binding.llAnswer3.setOnClickListener {
            if (!answer) {
                markAnswerCorrect(
                    binding.llAnswer3,
                    binding.tvVariantNumber3,
                    binding.tvVariantValue3
                )
                answer = true
            }
        }
        binding.llAnswer1.setOnClickListener {
            if (!answer) {
                markAnswerWrong(
                    binding.llAnswer1,
                    binding.tvVariantNumber1,
                    binding.tvVariantValue1
                )
                answer = true
            }
        }
        binding.llAnswer2.setOnClickListener {
            if (!answer) {
                markAnswerWrong(
                    binding.llAnswer2,
                    binding.tvVariantNumber2,
                    binding.tvVariantValue2
                )
                answer = true
            }
        }
        binding.llAnswer4.setOnClickListener {
            if (!answer) {
                markAnswerWrong(
                    binding.llAnswer4,
                    binding.tvVariantNumber4,
                    binding.tvVariantValue4
                )
                answer = true
            }
        }
        binding.btnContinue.setOnClickListener {
            markAnswerNeutral()
            answer = false
        }
        binding.btnSkip.setOnClickListener {
            markAnswerNeutral()
            answer = false
        }

    }

    private fun markAnswerNeutral() {
        with(binding) {

            for (layout in listOf(llAnswer1, llAnswer2, llAnswer3, llAnswer4)) {
                layout.background = ContextCompat.getDrawable(
                    this@MainActivity,
                    R.drawable.shape_rounded_containers
                )
            }

            for (textView in listOf(
                tvVariantValue1,
                tvVariantValue2,
                tvVariantValue3,
                tvVariantValue4
            )) {
                textView.setTextColor(
                    ContextCompat.getColor(
                        this@MainActivity,
                        R.color.textVariantsColor
                    )
                )
            }

            for (textView in listOf(
                tvVariantNumber1,
                tvVariantNumber2,
                tvVariantNumber3,
                tvVariantNumber4
            )) {
//                textView.background = ContextCompat.getDrawable(
//                    this@MainActivity,
//                    R.drawable.shape_rounded_variants
//                )
//                textView.setTextColor(ContextCompat.getColor(
//                    this@MainActivity,
//                    R.color.textVariantsColor))
                textView.apply {
                    background = ContextCompat.getDrawable(
                        this@MainActivity,
                        R.drawable.shape_rounded_variants
                    )
                    setTextColor(
                        ContextCompat.getColor(
                            this@MainActivity,
                            R.color.textVariantsColor
                        )
                    )
                }
            }

            clCorrectContinue.isVisible = false

            btnSkip.isVisible = true
        }
    }

    private fun markAnswerCorrect(ll: LinearLayout, tvNumber: TextView, tvValues: TextView) {
        ll.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_rounded_containers_correct
        )

        tvNumber.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_rounded_variants_correct
        )

        tvNumber.setTextColor(
            ContextCompat.getColor(
                this@MainActivity,
                R.color.white
            )
        )

        tvValues.setTextColor(
            ContextCompat.getColor(
                this@MainActivity,
                R.color.correctVariantValue
            )
        )

        binding.btnSkip.isVisible = false

        binding.clCorrectContinue.isVisible = true

        binding.clCorrectContinue.setBackgroundColor(
            ContextCompat.getColor(
                this@MainActivity,
                R.color.correctVariantValue
            )
        )
        binding.ivCorrect.isVisible = true

        binding.ivCorrect.setImageResource(R.drawable.ic_correct)

        binding.tvAnswerCorrectOrWrong.text = resources.getString(R.string.title_correct)

        binding.btnContinue.setTextColor(
            ContextCompat.getColor(
                this@MainActivity,
                R.color.correctVariantValue
            )
        )

    }

    private fun markAnswerWrong(ll: LinearLayout, tvNumber: TextView, tvValues: TextView) {
        ll.background = ContextCompat.getDrawable(
            this,
            R.drawable.shape_rounded_containers_wrong
        )

        tvNumber.background = ContextCompat.getDrawable(
            this,
            R.drawable.shape_rounded_variants_wrong
        )

        tvNumber.setTextColor(
            ContextCompat.getColor(
                this,
                R.color.white
            )
        )

        tvValues.setTextColor(
            ContextCompat.getColor(
                this,
                R.color.wrongVariantValue
            )
        )

        binding.btnSkip.isVisible = false

        binding.clCorrectContinue.isVisible = true

        binding.clCorrectContinue.setBackgroundColor(
            ContextCompat.getColor(
                this,
                R.color.wrongVariantValue
            )
        )
        binding.ivCorrect.isVisible = true

        binding.ivCorrect.setImageResource(R.drawable.ic_wrong)

        binding.tvAnswerCorrectOrWrong.text = resources.getString(R.string.title_wrong)

        binding.btnContinue.setTextColor(
            ContextCompat.getColor(
                this,
                R.color.wrongVariantValue
            )
        )

    }
}