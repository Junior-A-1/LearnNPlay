package com.maid.learnnplay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private BroadcastReceiver myReceiver;

    private EditText EDT_USERNAME, EDT_EMAIL, EDT_PASSWORD;
    private TextView TXT1;
    private Button BTN_SUBMIT;
    private boolean isSignUp = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EDT_USERNAME = findViewById(R.id.edt_username);
        EDT_EMAIL = findViewById(R.id.edt_email);
        EDT_PASSWORD = findViewById(R.id.edt_password);
        TXT1 = findViewById(R.id.txt1);
        BTN_SUBMIT = findViewById(R.id.btn_submit);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(MainActivity.this, MainActivity2.class));
            finish();
        }

        myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter("com.maid.learnnplay.ACTION_RECEIVER");
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(myReceiver, intentFilter);

        BTN_SUBMIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSignUp) {
                    handleSignUp();
                } else {
                    handleLogin();
                }
            }
        });

        TXT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EDT_EMAIL.getText().toString().isEmpty() || EDT_PASSWORD.getText().toString().isEmpty()) {
                    if (isSignUp && EDT_USERNAME.getText().toString().isEmpty()) {
                        Toast.makeText(MainActivity.this, "Please Enter UserName", Toast.LENGTH_SHORT)
                                .show();
                        return;
                    }
                }
                if (isSignUp) {
                    isSignUp = false;
                    EDT_USERNAME.setVisibility(View.GONE);
                    BTN_SUBMIT.setText("Log in");
                    TXT1.setText("Don't have an account? Sign in");
                } else {
                    isSignUp = true;
                    EDT_USERNAME.setVisibility(View.VISIBLE);
                    BTN_SUBMIT.setText("Sign Up");
                    TXT1.setText("Already have an account? Log in");
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Unregister the broadcast receiver
        if (myReceiver != null) {
            unregisterReceiver(myReceiver);
        }
    }

    private void handleSignUp() {
        FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(EDT_EMAIL.getText().toString(), EDT_PASSWORD.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // Sending broadcast to Receiver
                        Intent intentReceiver = new Intent("com.maid.learnnplay.ACTION_RECEIVER");
                        sendBroadcast(intentReceiver);

                        if (task.isSuccessful()) {
                            FirebaseDatabase
                                    .getInstance()
                                    .getReference("user/" + FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(new User(EDT_USERNAME.getText().toString(), EDT_EMAIL.getText().toString(), "",0,0,0));
                            startActivity(new Intent(MainActivity.this, MainActivity2.class));
                            Toast.makeText(MainActivity.this, "Signed Up Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void handleLogin() {
        FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(EDT_EMAIL.getText().toString(), EDT_PASSWORD.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(MainActivity.this, MainActivity2.class));
                            Toast.makeText(MainActivity.this, "Logged In Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
