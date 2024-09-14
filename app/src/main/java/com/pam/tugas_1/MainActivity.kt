package com.pam.tugas_1

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.pam.tugas_1.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val inputField = binding.field
        var isCalculated = false;

        val buttons = listOf(
            binding.num0Button,
            binding.num1Button,
            binding.num2Button,
            binding.num3Button,
            binding.num4Button,
            binding.num5Button,
            binding.num6Button,
            binding.num7Button,
            binding.num8Button,
            binding.num9Button,
            binding.opAddButton,
            binding.opSubtractButton,
            binding.opDivideButton,
            binding.opMultiplyButton,
            binding.opDecimalButton
        )

        /*
            Athallah Naufal Rismaputra Awwaliyyah
            225150407111070
        */

        buttons.forEach { button ->
            button.setOnClickListener {
                if (isCalculated) {
                    inputField.text = ""
                    isCalculated = false;
                }
                inputField.append(button.text.toString())
            }
        }

        binding.opEqualsButton.setOnClickListener {
            val result = try {
                ExpressionBuilder(
                    inputField.text.toString()
                        .replace("÷", "/")
                        .replace("×", "*")
                )
                    .build()
                    .evaluate()
                    .toString()
            } catch (e: ArithmeticException) {
                "Illegal Division"
            }
            Log.d("MainActivity", "Result: $result")
            inputField.text = if (result.equals("illegal division", true)) {result} else result
            isCalculated = true;
        }
        binding.clearButton.setOnClickListener {
            inputField.text = "";
        }
    }
}