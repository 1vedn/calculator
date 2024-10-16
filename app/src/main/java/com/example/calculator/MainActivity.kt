package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextResult: EditText
    private var lastNumeric: Boolean = false
    private var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextResult = findViewById(R.id.editTextResult)
        val buttonIds = listOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3,
            R.id.button4, R.id.button5, R.id.button6, R.id.button7,
            R.id.button8, R.id.button9, R.id.buttonPlus,
            R.id.buttonMinus, R.id.buttonMultiply, R.id.buttonDivide,
            R.id.buttonEquals, R.id.buttonClear
        )

        for (id in buttonIds) {
            findViewById<Button>(id).setOnClickListener { onButtonClick(it) }
        }
    }

    private fun onButtonClick(view: View) {
        val button = view as Button
        when (button.text) {
            "C" -> clear()
            "=" -> calculate()
            else -> appendToExpression(button.text.toString())
        }
    }

    private fun clear() {
        editTextResult.setText("")
        lastNumeric = false
        lastDot = false
    }

    private fun appendToExpression(value: String) {
        editTextResult.append(value)
        lastNumeric = true
        lastDot = value == "."
    }

    private fun calculate() {
        if (lastNumeric) {
            val expression = editTextResult.text.toString()
            val result = evalExpression(expression)
            editTextResult.setText(result.toString())
        }
    }

    private fun evalExpression(expression: String): Double {
        // Здесь вы можете реализовать парсер или использовать библиотеку для вычисления выражений
        // В данном примере используем eval (нужно будет реализовать самостоятельно)
        return 0.0 // Замените на реальную логику
    }
}
