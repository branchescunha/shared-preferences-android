package com.example.a1006desafio

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }

        val etNome = findViewById<EditText>(R.id.edit_text_nome)
        val etIdade = findViewById<EditText>(R.id.edit_text_idade)

        val btnSalvar = findViewById<Button>(R.id.buttonSave)
        val btnLimpar = findViewById<Button>(R.id.buttonClear)

        val tvResultado = findViewById<TextView>(R.id.txtresultado)

        val preferences = getSharedPreferences("dados_usuario", MODE_PRIVATE)

        val nomeSalvo = preferences.getString("nome", "")
        val idadeSalva = preferences.getString("idade", "")

        if (!nomeSalvo.isNullOrEmpty() || !idadeSalva.isNullOrEmpty()) {
            tvResultado.text = "Nome: $nomeSalvo\nIdade: $idadeSalva"
        }

        btnSalvar.setOnClickListener {

            val nome = etNome.text.toString()
            val idade = etIdade.text.toString()

            preferences.edit()
                .putString("nome", nome)
                .putString("idade", idade)
                .apply()

            tvResultado.text = "Nome: $nome\nIdade: $idade"
        }

        btnLimpar.setOnClickListener {

            preferences.edit()
                .clear()
                .apply()

            etNome.text.clear()
            etIdade.text.clear()

            tvResultado.text = ""
        }
    }
}