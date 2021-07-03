package com.jvjp.calculadora.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.lifecycle.Observer
import com.jvjp.calculadora.R
import com.jvjp.calculadora.contants.*
import com.jvjp.calculadora.model.CalcVisor
import androidx.activity.viewModels
import com.jvjp.calculadora.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (supportActionBar != null){
            supportActionBar!!.hide()
        }

        val listButton = listOf<View>(
            button0, button1, button2, button3,
            button4, button5, button6, button7,
            button8, button9, button_ponto, button_ac,
            button_limpa, button_mais, button_menos,
            button_mult, button_dividir, button_igual,
            button_sinal
        )
        for (x in listButton) {
            if (x is Button) {
                x.text = getBtnConstant(x).toString()
            }
        }
        for (x in listButton) {
            x.click()
        }
        model.getNumbers().observe(this, Observer<CalcVisor> {
            tv_number.text = it.number
            tv_calc.text = it.calc
        })

    }

    private fun getBtnConstant(any: Any): Any? {
        return when (any) {
            button0 -> N0
            button1 -> N1
            button2 -> N2
            button3 -> N3
            button4 -> N4
            button5 -> N5
            button6 -> N6
            button7 -> N7
            button8 -> N8
            button9 -> N9
            button_ponto -> POINT
            button_sinal -> SIGNAL
            button_limpa -> BACK
            button_ac -> AC
            button_mais -> PLUS
            button_menos -> MINUS
            button_mult -> MULTIPLY
            button_dividir -> DIVIDE
            button_igual -> EQUAL
            else -> null
        }
    }


    private fun View.click() {
        this.setOnClickListener {
            send(it)
        }
    }

    private fun send(view: View) {
        val c = getBtnConstant(view)
        if (c != null) {
            model.setClick(c)
        }
    }

}


