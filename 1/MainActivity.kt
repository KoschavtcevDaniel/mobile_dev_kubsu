package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.example.calcul.R

class MainActivity : AppCompatActivity() {
    var curOp : Char = '='

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttons = listOf<Button>(
            findViewById<Button>(R.id.b1),
            findViewById<Button>(R.id.b2),
            findViewById<Button>(R.id.b3),
            findViewById<Button>(R.id.b4),
            findViewById<Button>(R.id.b5),
            findViewById<Button>(R.id.b6),
            findViewById<Button>(R.id.b7),
            findViewById<Button>(R.id.b8),
            findViewById<Button>(R.id.b9),
            findViewById<Button>(R.id.b13))

        val del = findViewById<ImageButton>(R.id.b16)
        val min = findViewById<Button>(R.id.b12)
        val pls = findViewById<Button>(R.id.b15)
        val div = findViewById<Button>(R.id.b10)
        val umn = findViewById<Button>(R.id.b11)

        val number = findViewById<TextView>(R.id.text1)
        val oper = findViewById<TextView>(R.id.text2)
        val answer = findViewById<EditText>(R.id.outputTextView)


        // Кнопка "DEL" - удаляет последний символ из строки
        // Кнопка "." - добавляет точку к числу для ввода дробных чисел
        del.setOnClickListener{
            val n = answer.text.length
            if (n != 0){
                answer.text = answer.text.delete(n-1, n)
            }
        }

        findViewById<Button>(R.id.b14).setOnClickListener{
            if (answer.text.isNullOrEmpty()){
                answer.setText("0.")
            }else{
                if (answer.text.toString().firstOrNull{ it == '.'} == null){
                    answer.setText(answer.text.toString() + '.')
                }
            }
        }

        // Арифметика
        for (b in buttons){
            b.setOnClickListener{ btn ->
                if ((btn as Button).text == "0" ){
                    if (!answer.text.isNullOrEmpty()){
                        answer.setText(answer.text.toString() + btn.text)
                        answer.error = null
                    }
                } else{
                    answer.error = null
                    if (answer.text.toString() != "0"){
                        answer.setText(answer.text.toString() + btn.text)
                    }else{
                        answer.setText(btn.text)
                    }
                }
                answer.setSelection(answer.length())
            }
        }

        val operations = listOf<Button>(pls, div, min, umn)
        fun calc(a: Float, b: Float, operation: Char) : Float =
            when (operation) {
                '+' -> a+b
                '-' -> a-b
                '*' -> a*b
                '/' -> a/b
                else -> 0.0f
            }

        // Расчёт текущей операции
        for (op in operations){
            op.setOnClickListener{btn ->
                if (curOp == '='){
                    if (answer.text.isNullOrEmpty()){
                        answer.error = "Введите первое число"
                        return@setOnClickListener
                    }else{
                        answer.error = null
                    }
                    number.text = answer.text
                    answer.setText("")
                    oper.text = op.text
                    curOp = op.text.toString()[0]
                }else{
                    if (!answer.text.isNullOrEmpty()){
                        val a = number.text.toString().toFloat()
                        val b = answer.text.toString().toFloat()
                        number.text = calc(a,b,curOp).toString()
                        answer.setText("")
                        oper.text = op.text.toString()
                        curOp = op.text.toString()[0]
                    }else {
                        oper.text = op.text.toString()
                        curOp = op.text.toString()[0]
                    }
                }
            }
        }

        // В случае, если не все данные введены или введены некорректно, выводится сообщение об ошибке
        findViewById<Button>(R.id.b17).setOnClickListener{btn ->
            if (!answer.text.isNullOrEmpty() && !number.text.isNullOrEmpty()){
                answer.error = null
                val a = number.text.toString().toFloat()
                val b = answer.text.toString().toFloat()
                answer.setText(calc(a,b,curOp).toString())
                number.text = ""
                oper.text = ""
                curOp = '='
            }else if(!answer.text.isNullOrEmpty() && oper.text.isNullOrEmpty()) {
                answer.error = "Введите операцию"
            }else if(answer.text.isNullOrEmpty() && !number.text.isNullOrEmpty()){
                answer.error = "Введите второе число"
            }else{
                answer.error = "Введите первое число"
            }
        }
    }
}






