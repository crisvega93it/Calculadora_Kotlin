package es.crisvega.cursokotlinprincipiantes

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var firstNumber: Double = 0.0
    private var secondNumber: Double = 0.0
    private var operation: String? = null

    private val bt0: AppCompatButton by lazy { findViewById(R.id.bt0) }
    private val bt1: AppCompatButton by lazy { findViewById(R.id.bt1) }
    private val bt2: AppCompatButton by lazy { findViewById(R.id.bt2) }
    private val bt3: AppCompatButton by lazy { findViewById(R.id.bt3) }
    private val bt4: AppCompatButton by lazy { findViewById(R.id.bt4) }
    private val bt5: AppCompatButton by lazy { findViewById(R.id.bt5) }
    private val bt6: AppCompatButton by lazy { findViewById(R.id.bt6) }
    private val bt7: AppCompatButton by lazy { findViewById(R.id.bt7) }
    private val bt8: AppCompatButton by lazy { findViewById(R.id.bt8) }
    private val bt9: AppCompatButton by lazy { findViewById(R.id.bt9) }
    private val btComa: AppCompatButton by lazy { findViewById(R.id.btComa) }
    private val btplus: AppCompatButton by lazy { findViewById(R.id.btplus) }
    private val btMinus: AppCompatButton by lazy { findViewById(R.id.btMinus) }
    private val btEqual: AppCompatButton by lazy { findViewById(R.id.btEqual) }
    private val btClear: AppCompatButton by lazy { findViewById(R.id.btClear) }
    private val btMult: AppCompatButton by lazy { findViewById(R.id.btMult) }
    private val btDiv: AppCompatButton by lazy { findViewById(R.id.btDiv) }
    private val tvScreen: TextView by lazy { findViewById(R.id.screen) }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        operation = null

        bt0.setOnClickListener(this)
        bt1.setOnClickListener(this)
        bt2.setOnClickListener(this)
        bt3.setOnClickListener(this)
        bt4.setOnClickListener(this)
        bt5.setOnClickListener(this)
        bt6.setOnClickListener(this)
        bt7.setOnClickListener(this)
        bt8.setOnClickListener(this)
        bt9.setOnClickListener(this)
        btComa.setOnClickListener(this)
        btplus.setOnClickListener(this)
        btMinus.setOnClickListener(this)
        btEqual.setOnClickListener(this)
        btClear.setOnClickListener(this)
        btMult.setOnClickListener(this)
        btDiv.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.bt0 -> onNumberPressed("0")
            R.id.bt1 -> onNumberPressed("1")
            R.id.bt2 -> onNumberPressed("2")
            R.id.bt3 -> onNumberPressed("3")
            R.id.bt4 -> onNumberPressed("4")
            R.id.bt5 -> onNumberPressed("5")
            R.id.bt6 -> onNumberPressed("6")
            R.id.bt7 -> onNumberPressed("7")
            R.id.bt8 -> onNumberPressed("8")
            R.id.bt9 -> onNumberPressed("9")
            R.id.btComa -> onNumberPressed(".")
            R.id.btplus -> onOperationPressed("+")
            R.id.btMinus -> onOperationPressed("-")
            R.id.btMult -> onOperationPressed("*")
            R.id.btDiv -> onOperationPressed("/")
            R.id.btEqual -> onEqualPressed()
            R.id.btClear -> onClearPressed()
            else -> ""
        }
    }

    private fun onNumberPressed(number: String) {
        renderScreen(number)
        checkOperation()
    }

    private fun renderScreen(number: String) {
        val result: String = if (tvScreen.text == "0" && number != ",")
            number
        else
            "${tvScreen.text}$number"

        tvScreen.text = result
    }

    private fun checkOperation(){
        if(operation==null){
            firstNumber = tvScreen.text.toString().toDouble()
        }
        else{
            secondNumber = tvScreen.text.toString().toDouble()
        }
    }

    private fun onOperationPressed(operation: String){
        this.operation = operation
        firstNumber = tvScreen.text.toString().toDouble()

        tvScreen.text = "0"
    }

    private fun onEqualPressed(){
        val result : Double = when(operation) {
            "+" -> firstNumber + secondNumber
            "-" -> firstNumber - secondNumber
            "*" -> firstNumber * secondNumber
            "/" -> firstNumber / secondNumber
            else -> 0.0
        }

        operation = null
        firstNumber = result.toDouble()
        try {
            tvScreen.text = if(result.toString().endsWith(".0")) {
                result.toString().replace(".0", "")
            }
            else{
                "%.2f".format(result)
            }

            if(result.toString() == "Infinity"){
                Toast.makeText(this, "No se puede dividir entre 0", Toast.LENGTH_SHORT).show()
                onClearPressed()
            }
        }catch (e : Exception){
            e.printStackTrace()

        }
    }

    private fun onClearPressed(){
        tvScreen.text = "0"
        firstNumber = 0.0
        secondNumber = 0.0
    }
}