package me.leojlindo.parstagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity {

    private EditText usernameInput;
    private EditText passwordInput;
    private Button loginBtn;
    private Button signUpbtn;

    ParseUser user = new ParseUser();

    @Override
    protected void onStart(){
        super.onStart();
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        } else {
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameInput = findViewById(R.id.username_et);
        passwordInput = findViewById(R.id.password_et);
        loginBtn = findViewById(R.id.login_btn);
        signUpbtn = findViewById(R.id.signUp_btn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String username = usernameInput.getText().toString();
                final String password = passwordInput.getText().toString();

                login(username, password);

            }
        });

        signUpbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                final String username = usernameInput.getText().toString();
                final String password = passwordInput.getText().toString();

                signUp(view);
            }
        });

    }

    private void signUp(View view){
        usernameInput = findViewById(R.id.username_et);
        passwordInput = findViewById(R.id.password_et);
        signUpbtn = findViewById(R.id.signUp_btn);

        ParseUser user = new ParseUser();

        user.setPassword(passwordInput.getText().toString());
        user.setUsername(usernameInput.getText().toString());

        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("SignUp", "Sign Up Success");
                    final Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Log.d("SignUp", "Sign Up Failed");
                    Toast.makeText(MainActivity.this,"Sign Up Failed",  Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
    }

    private void login(String username, String password){
        ParseUser.logInInBackground(username, password, new LogInCallback(){
            @Override
            public void done(ParseUser user, ParseException e){
                if (e == null){
                    Log.d("LoginActivity", "Login successfull!");

                    final Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Log.e("LoginActivity", "Login Failure");
                    Toast.makeText(MainActivity.this,"Login Failed",  Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
    }

    private void logOut(){
        ParseUser.logOut();
        ParseUser user = ParseUser.getCurrentUser();
        finish();
    }
}
