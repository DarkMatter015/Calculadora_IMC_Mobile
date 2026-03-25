package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var etPeso: EditText
    private lateinit var etAltura: EditText
    private lateinit var tvResultado: TextView
    private lateinit var btCalcular: Button
    private lateinit var btLimpar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etPeso = findViewById(R.id.etPeso)
        etAltura = findViewById(R.id.etAltura)

        tvResultado = findViewById(R.id.tvResultado)

        btCalcular = findViewById(R.id.btCalcular)
        btLimpar = findViewById(R.id.btLimpar)

        btCalcular.setOnLongClickListener {
            Toast.makeText(this, "Calcula o IMC", Toast.LENGTH_SHORT).show()
            true
        }

        btCalcular.setOnClickListener {
            calcularImc()
        }

        btLimpar.setOnClickListener {
            limparCampos()

        }
    }

    fun limparCampos() {
        etPeso.text.clear()
        etAltura.text.clear()
        tvResultado.text = "0.0"
        etPeso.requestFocus()

        Toast.makeText(this, "Campos limpos", Toast.LENGTH_SHORT).show()
    }

    fun calcularImc() {
//        Entrada
        if (etPeso.text.isEmpty()) {
            etPeso.error = "Peso é obrigatório"
            return
        }
        if (etAltura.text.isEmpty()) {
            etAltura.error = "Altura é obrigatória"
            return
        }
        val peso = etPeso.text.toString().toDouble()
        val altura = etAltura.text.toString().toDouble()

//        Processamento
        val imc = peso / (altura.pow(2.0))

//        Saída
        tvResultado.text = "%.2f".format(imc)
        Toast.makeText(this, "IMC calculado", Toast.LENGTH_SHORT).show()

    }
}