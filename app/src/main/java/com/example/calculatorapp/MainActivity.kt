package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var tv_selected: TextView
    var lastNumberic:Boolean = false
    var lastDot:Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_selected = findViewById(R.id.tvSelected)
    }

    private fun removeZeroAfterDot(result:String):String{
        var value = result
        if(result.contains(".0")){
            value = result.substring(0,result.length-2)
        }
        return value
    }

    fun onEqual(view:View){
        if(lastNumberic){
            var tvValue = tv_selected.text.toString()
            var prefix = ""
            try {
                if(tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)

                }

                if(tvValue.contains("-")){
                    var splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }

                    tv_selected.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())

                }else if(tvValue.contains("+")){
                    var splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    tv_selected.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())

                }else if(tvValue.contains("*")){
                    var splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    tv_selected.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                }else if(tvValue.contains("/")){
                    var splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }

                    tv_selected.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                }

            }catch(e:ArithmeticException){
                e.printStackTrace()
            }
        }else{
            tv_selected.text = "not access if"
        }
    }

    fun onOperator(view:View){
        if(lastNumberic && !isOperator(tv_selected.text.toString())){
            tv_selected.append((view as Button).text)
            lastNumberic = false
            lastDot = false
        }
    }

    fun onDigit(view: View){
        tv_selected.append( (view as Button).text )
        lastNumberic = true
    }

    fun onClear(view: View){
        tv_selected.text = ""
        lastNumberic = false
        lastDot = false
    }

    fun onDecimalPoint(view:View){
        if(lastNumberic && !lastDot){
            tv_selected.append((view as Button).text)
            lastNumberic = false
            lastDot = true
        }
    }

    private fun isOperator(value:String):Boolean{
        return if(value.startsWith("-")){false}
        else{
            value.contains("/") ||
                    value.contains("*") ||
                    value.contains("-") ||
                    value.contains("+") }
    }
}