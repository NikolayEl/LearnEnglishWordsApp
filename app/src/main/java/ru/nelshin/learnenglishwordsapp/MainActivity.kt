package ru.nelshin.learnenglishwordsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import ru.nelshin.learnenglishwordsapp.databinding.ActivityMainBinding
import kotlin.random.Random
import java.io.InputStream

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val random = Random
    private val binding
        get() = _binding
            ?: throw IllegalStateException("Binding for ActivityMainBinding must not be null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding =
            ActivityMainBinding.inflate(layoutInflater) //связывает класс байндинг с мэйнактивити, мы раздели разметку - из разметки xml сделали view
        setContentView(binding.root) //Меняем на получение корневого элемента разметки

        var answer: Boolean = false
        var tempAnswer:Boolean = true
        var exitQuestion:Boolean = true
        val tempMap = inputData(R.raw.base)
        var counterWords: Int = 0
        val englishWords = mutableListOf<String>()
        val answerLauout1 =
            AnswerLauout(binding.llAnswer1, binding.tvVariantNumber1, binding.tvVariantValue1)
        val answerLauout2 =
            AnswerLauout(binding.llAnswer2, binding.tvVariantNumber2, binding.tvVariantValue2)
        val answerLauout3 =
            AnswerLauout(binding.llAnswer3, binding.tvVariantNumber3, binding.tvVariantValue3)
        val answerLauout4 =
            AnswerLauout(binding.llAnswer4, binding.tvVariantNumber4, binding.tvVariantValue4)
        val layoutAll: List<AnswerLauout> =
            listOf(answerLauout1, answerLauout2, answerLauout3, answerLauout4)

        tempMap.forEach {
            englishWords.add(it.key)
        }

        fillingWordsOnTheScreen(tempMap, englishWords[counterWords])

        markAnswerNeutral()

        binding.btnClose.setOnClickListener{
            //finishAffinity()
            binding.llQuestionExitProgramm.isVisible = true
            tempAnswer = answer
            answer = true
            exitQuestion = false

        }
        binding.llAnswer1.setOnClickListener {
            if (!answer) {
                if (wordChek(
                        binding.tvVariantValue1.text.toString(),
                        tempMap,
                        englishWords[counterWords]
                    )
                ) {
                    markAnswerCorrect(layoutAll, 0)
                } else {
                    markAnswerWrong(layoutAll, 0)
                    highlightTheCorrectAnswer(layoutAll, tempMap, englishWords[counterWords])
                }
                answer = true
            }
        }
        binding.llAnswer2.setOnClickListener {
            if (!answer) {
                if (wordChek(
                        binding.tvVariantValue2.text.toString(),
                        tempMap,
                        englishWords[counterWords]
                    )
                ) {
                    markAnswerCorrect(layoutAll, 1)
                } else {
                    markAnswerWrong(layoutAll, 1)
                    highlightTheCorrectAnswer(layoutAll, tempMap, englishWords[counterWords])
                }
                answer = true
            }
        }
        binding.llAnswer3.setOnClickListener {
            if (!answer) {
                if (wordChek(
                        binding.tvVariantValue3.text.toString(),
                        tempMap,
                        englishWords[counterWords]
                    )
                ) {
                    markAnswerCorrect(layoutAll, 2)
                } else {
                    markAnswerWrong(layoutAll, 2)
                    highlightTheCorrectAnswer(layoutAll, tempMap, englishWords[counterWords])
                }
                answer = true
            }
        }
        binding.llAnswer4.setOnClickListener {
            if (!answer) {
                if (wordChek(
                        binding.tvVariantValue4.text.toString(),
                        tempMap,
                        englishWords[counterWords]
                    )
                ) {
                    markAnswerCorrect(layoutAll, 3)
                } else {
                    markAnswerWrong(layoutAll, 3)
                    highlightTheCorrectAnswer(layoutAll, tempMap, englishWords[counterWords])
                }
                answer = true
            }
        }
        binding.btnContinue.setOnClickListener {
            if(exitQuestion){
                markAnswerNeutral()

                if (counterWords < englishWords.size) {
                    counterWords++
                    fillingWordsOnTheScreen(tempMap, englishWords[counterWords])
                    answer = false
                }
            }
        }
        binding.btnSkip.setOnClickListener {
            if(exitQuestion){
                markAnswerNeutral()
                if (counterWords < englishWords.size) {
                    counterWords++
                    fillingWordsOnTheScreen(tempMap, englishWords[counterWords])
                    answer = false
                }
            }
        }
        binding.btnCloseQuestion.setOnClickListener{
            binding.llQuestionExitProgramm.isVisible = false
            answer = tempAnswer
            exitQuestion = true
        }
        binding.btnAnswerNoExit.setOnClickListener {
            binding.llQuestionExitProgramm.isVisible = false
            answer = tempAnswer
            exitQuestion = true
        }
        binding.btnAnswerYesExit.setOnClickListener {
            finishAffinity()
        }

    }

    private fun highlightTheCorrectAnswer(
        linerLayoutAll: List<AnswerLauout>,
        map: MutableMap<String, String>,
        key: String
    ) {
        var index: Int = 0
        linerLayoutAll.forEach {
            if (it.tvVariantValues.text == map.get(key)) {
                it.llAnswers.background = ContextCompat.getDrawable(
                    this@MainActivity,
                    R.drawable.shape_rounded_containers_correct
                )

                it.tvVariantNumbers.background = ContextCompat.getDrawable(
                    this@MainActivity,
                    R.drawable.shape_rounded_variants_correct
                )

                it.tvVariantNumbers.setTextColor(
                    ContextCompat.getColor(
                        this@MainActivity,
                        R.color.white
                    )
                )

                it.tvVariantValues.setTextColor(
                    ContextCompat.getColor(
                        this@MainActivity,
                        R.color.correctVariantValue
                    )
                )
            } else
                index++
        }
    }

    private fun wordChek(text: String, map: MutableMap<String, String>, key: String): Boolean {
        return map.get(key) == text
    }

    private fun fillingWordsOnTheScreen(
        tempMap: MutableMap<String, String>,
        key: String
    ) {
        val tempPrintSet = mutableSetOf<String>()
        val list = mutableListOf<String>()

        tempPrintSet.add(tempMap.get(key).toString())
        while (tempPrintSet.size < 4) {
            tempPrintSet.add(tempMap.random().value)
        }
        tempPrintSet.shuffled().forEach {
            list.add(it)
        }

        binding.tvQuestionWord.text = key
        binding.tvVariantValue1.text = list[0]
        binding.tvVariantValue2.text = list[1]
        binding.tvVariantValue3.text = list[2]
        binding.tvVariantValue4.text = list[3]
    }

    private fun <T, U> Map<T, U>.random(): Map.Entry<T, U> = entries.elementAt(random.nextInt(size))

    private fun inputData(idFile: Int): MutableMap<String, String> {
        val map = mutableMapOf<String, String>()
        val inputStream: InputStream = resources.openRawResource(idFile)
        inputStream.bufferedReader().forEachLine {
            val (englishVershion, russianVersion) = it.split(';', ignoreCase = false, limit = 2)
            map.put(englishVershion, russianVersion)
        }
        return map
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

    private fun markAnswerCorrect(linerLayoutAll: List<AnswerLauout>, index: Int) {
        linerLayoutAll[index].llAnswers.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_rounded_containers_correct
        )

        linerLayoutAll[index].tvVariantNumbers.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_rounded_variants_correct
        )

        linerLayoutAll[index].tvVariantNumbers.setTextColor(
            ContextCompat.getColor(
                this@MainActivity,
                R.color.white
            )
        )

        linerLayoutAll[index].tvVariantValues.setTextColor(
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

    private fun markAnswerWrong(linerLayoutAll: List<AnswerLauout>, index: Int) {
        linerLayoutAll[index].llAnswers.background = ContextCompat.getDrawable(
            this,
            R.drawable.shape_rounded_containers_wrong
        )

        linerLayoutAll[index].tvVariantNumbers.background = ContextCompat.getDrawable(
            this,
            R.drawable.shape_rounded_variants_wrong
        )

        linerLayoutAll[index].tvVariantNumbers.setTextColor(
            ContextCompat.getColor(
                this,
                R.color.white
            )
        )

        linerLayoutAll[index].tvVariantValues.setTextColor(
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