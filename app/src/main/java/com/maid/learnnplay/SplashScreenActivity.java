package com.maid.learnnplay;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class SplashScreenActivity extends AppCompatActivity {

    private static final int AGREEMENT_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        if (hasAgreedToCurriculum()) {
            startMainActivity();
        } else {
            showAgreementDialog();
        }
    }


    private boolean hasAgreedToCurriculum() {
        // Return true if the user has agreed; otherwise, return false
        // For simplicity, let's assume the user has not agreed initially
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getBoolean("agreed_to_curriculum", false);
    }

    private void showAgreementDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Curriculum Agreement");
        String instructions = "Welcome to Learn n' Play!\n\n"
                + "Please review the following instructions before allowing your child to use the app:\n"
                + "- Supervise your child while using the app\n"
                + "- Encourage active participation and engagement\n"
                + "- Ensure a safe and comfortable learning environment\n"
                + "- Support your child's learning journey\n"
                + "- Contact us if you have any questions or concerns\n\n"
                + "By agreeing to the curriculum, you acknowledge that you have read and understood these instructions and you consent to your child using the app.";

        builder.setMessage(instructions);

        builder.setPositiveButton("Agree", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User agrees to the curriculum
                saveAgreementStatus(true);
                startMainActivity();
            }
        });

        builder.setNegativeButton("Disagree", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User does not agree to the curriculum
                saveAgreementStatus(false);
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void saveAgreementStatus(boolean agreed) {
        // Save the agreement status to SharedPreferences or other storage methods
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("agreed_to_curriculum", agreed);
        editor.apply();
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
