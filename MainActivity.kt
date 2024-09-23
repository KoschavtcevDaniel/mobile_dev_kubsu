package com.example.calc_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var result : Float? = 0.0f
    var currentOperation : Char = '='
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttons = listOf<Button>(
            findViewById<Button>(R.id.b0),findViewById<Button>(R.id.b1), findViewById<Button>(R.id.b2), findViewById<Button>(R.id.b3), findViewById<Button>(R.id.b4),
            findViewById<Button>(R.id.b5), findViewById<Button>(R.id.b6), findViewById<Button>(R.id.b7), findViewById<Button>(R.id.b8), findViewById<Button>(R.id.b9))
        val del = findViewById<ImageButton>(R.id.b16)
        val comma = findViewById<Button>(R.id.bcomma)

        val del = findViewById<Button>(R.id.bdel)
        val umn = findViewById<Button>(R.id.bumn)
        val minus = findViewById<Button>(R.id.bminus)
        val plus = findViewById<Button>(R.id.bplus)

        val number = findViewById<TextView>(R.id.text1) //cur_num
        val oper = findViewById<TextView>(R.id.text2)
        val answer = findViewById<EditText>(R.id.text3) //place_num


        for (b in buttons){
            b.setOnClickListener{ btn ->
                if ((btn as Button).text == "0" ){
                    if (!place_num.text.isNullOrEmpty()){
                        place_num.setText(place_num.text.toString() + btn.text)
                        place_num.error = null
                    }
                } else{
                    place_num.error = null
                    if (place_num.text.toString() != "0"){
                        place_num.setText(place_num.text.toString() + btn.text)
                    }else{
                        place_num.setText(btn.text)
                    }
                }
                place_num.setSelection(place_num.length())
            }
        }


        val operations = listOf<Button>(plus,del, minus, umn)

        fun calc(a: Float, b: Float, operation: Char) : Float =
            when (operation) {
                '+' -> a+b
                '-' -> a-b
                '*' -> a*b
                '/' -> a/b
                else -> 0.0f
            }


        for (op in operations){
            op.setOnClickListener{btn ->
                if (currentOperation == '='){
                    if (place_num.text.isNullOrEmpty()){
                        place_num.error = "Введите первое число"
                        return@setOnClickListener
                    }else{
                        place_num.error = null
                    }
                    cur_num.text = place_num.text
                    place_num.setText("")
                    oper.text = op.text
                    currentOperation = op.text.toString()[0]
                }else{
                    if (!place_num.text.isNullOrEmpty()){
                        val a = cur_num.text.toString().toFloat()
                        val b = place_num.text.toString().toFloat()
                        cur_num.text = calc(a,b,currentOperation).toString()
                        place_num.setText("")
                        oper.text = op.text.toString()
                        currentOperation = op.text.toString()[0]
                    }else {
                        oper.text = op.text.toString()
                        currentOperation = op.text.toString()[0]
                    }
                }
            }
        }


        findViewById<Button>(R.id.bequal).setOnClickListener{btn ->
            if (!place_num.text.isNullOrEmpty() && !cur_num.text.isNullOrEmpty()){
                place_num.error = null
                val a = cur_num.text.toString().toFloat()
                val b = place_num.text.toString().toFloat()
                place_num.setText(calc(a,b,currentOperation).toString())
                cur_num.text = ""
                oper.text = ""
                currentOperation = '='
            }else if(!place_num.text.isNullOrEmpty() && oper.text.isNullOrEmpty()) {
                place_num.error = "Введите операцию"
            }else if(place_num.text.isNullOrEmpty() && !cur_num.text.isNullOrEmpty()){
                place_num.error = "Введите второе число"
            }else{
                place_num.error = "Введите первое число"
            }
        }

        back.setOnClickListener{
            val n = place_num.text.length
            if (n != 0){
                place_num.text = place_num.text.delete(n-1, n)
            }
        }
        findViewById<Button>(R.id.bcomma).setOnClickListener{
            if (place_num.text.isNullOrEmpty()){
                place_num.setText("0.")
            }else{
                if (place_num.text.toString().firstOrNull{ it == '.'} == null){
                    place_num.setText(place_num.text.toString() + '.')
                }
            }
        }
    }
}