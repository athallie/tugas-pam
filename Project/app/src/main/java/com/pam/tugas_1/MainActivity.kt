package com.pam.tugas_1

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.pam.tugas_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val showResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {}
        binding.hitungButton.setOnClickListener {
            val operand1 = binding.operand1.text?.toString()?.toDoubleOrNull()
            val operand2 = binding.operand2.text?.toString()?.toDoubleOrNull()
            val operator = findViewById<RadioButton>(binding.operatorsRadioGroup.checkedRadioButtonId)?.text.toString()
            val result: Double? = calculate(
                operand1,
                operand2,
                operator,
            )
            if (result != null) {
                showResult.launch(
                    Intent(this, ResultActivity::class.java).apply {
                        action = Intent.ACTION_SEND
                        putExtra("Result", result.toString())
                        putExtra("Operand 1", operand1.toString())
                        putExtra("Operand 2", operand2.toString())
                        putExtra("Operator", operator)
                    }
                )
            }
        }
    }

    private fun calculate(operand1: Double?, operand2: Double?, operator: String): Double? {
        if (operand1 != null && operand2 != null && operator != "null") {
            when(operator) {
                "+" -> return operand1 + operand2
                "-" -> return operand1 - operand2
                "*" -> return operand1 * operand2
                "/" -> return operand1 / operand2
            }
        }
        Toast.makeText(this, "Operand atau operator tidak valid", Toast.LENGTH_SHORT).show()
        return null
    }
}