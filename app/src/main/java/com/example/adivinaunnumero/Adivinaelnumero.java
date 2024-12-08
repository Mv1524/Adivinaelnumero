package com.example.juegoadivinarnumero;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int randomNumber;
    private int attemptsLeft = 10;
    private TextView tvFeedback, tvAttempts;
    private EditText etNumberInput;
    private Button btnGuess, btnRestart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvFeedback = findViewById(R.id.tvFeedback);
        tvAttempts = findViewById(R.id.tvAttempts);
        etNumberInput = findViewById(R.id.etNumberInput);
        btnGuess = findViewById(R.id.btnGuess);
        btnRestart = findViewById(R.id.btnRestart);

        startNewGame();

        btnGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeGuess();
            }
        });

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewGame();
            }
        });
    }

    private void startNewGame() {
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;
        attemptsLeft = 10;
        tvFeedback.setText("Introduce un número del 1 al 100");
        tvAttempts.setText("Intentos restantes: " + attemptsLeft);
        etNumberInput.setText("");
        Toast.makeText(this, "¡Nuevo juego iniciado!", Toast.LENGTH_SHORT).show();
    }

    private void makeGuess() {
        String input = etNumberInput.getText().toString();
        if (input.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa un número.", Toast.LENGTH_SHORT).show();
            return;
        }

        int guessedNumber = Integer.parseInt(input);

        if (guessedNumber < 1 || guessedNumber > 100) {
            Toast.makeText(this, "El número debe estar entre 1 y 100.", Toast.LENGTH_SHORT).show();
            return;
        }

        attemptsLeft--;
        tvAttempts.setText("Intentos restantes: " + attemptsLeft);

        if (guessedNumber == randomNumber) {
            tvFeedback.setText("¡Correcto! El número era " + randomNumber);
            btnGuess.setEnabled(false);
        } else if (guessedNumber > randomNumber) {
            tvFeedback.setText("Demasiado alto. Intenta con un número más bajo.");
        } else {
            tvFeedback.setText("Demasiado bajo. Intenta con un número más alto.");
        }

        if (attemptsLeft == 0 && guessedNumber != randomNumber) {
            tvFeedback.setText("Has perdido. El número era " + randomNumber);
            btnGuess.setEnabled(false);
        }

        etNumberInput.setText("");
    }
}
