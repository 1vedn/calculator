package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : ComponentActivity() {

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
            R.id.buttonEquals, R.id.buttonClear, R.id.buttonBackspace
        )

        for (id in buttonIds) {
            findViewById<Button>(id).setOnClickListener { onButtonClick(it) }
        }
    }

    private fun onButtonClick(view: View) {
        val button = view as Button
        when (button.text) {
            "C" -> clear()
            "←" -> backspace()
            "=" -> calculate()
            else -> appendToExpression(button.text.toString())
        }
    }

    private fun clear() {
        editTextResult.setText("")
        lastNumeric = false
        lastDot = false
    }

    private fun backspace() {
        val currentText = editTextResult.text.toString()
        if (currentText.isNotEmpty()) {
            editTextResult.setText(currentText.dropLast(1))
        }
    }

    private fun appendToExpression(value: String) {
        val currentText = editTextResult.text.toString()
        if (value == "." && lastDot) {
            return
        }
        if (value in "+-*/" && currentText.isNotEmpty() && currentText.last().toString() in "+-*/") {
            editTextResult.setText(currentText.dropLast(1) + value)
        } else {
            editTextResult.append(value)
        }
        lastNumeric = value !in "+-*/."
        lastDot = value == "."
    }

    private fun calculate() {
        if (lastNumeric) {
            val expression = editTextResult.text.toString()
            val result = evalExpression(expression)
            editTextResult.setText(result)
        } else {
            Toast.makeText(this, "Invalid expression", Toast.LENGTH_SHORT).show()
        }
    }

    private fun evalExpression(expression: String): String {
        return try {
            val result = ExpressionBuilder(expression).build().evaluate()
            result.toString()
        } catch (e: Exception) {
            "Error"
        }
    }
}