package com.diana.draz.lb2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends ActivityBase implements View.OnClickListener {

    public static String FIRSTNAME_KEY = "fn";
    public static String SURNAME_KEY = "surname";
    public static String PHONE_KEY = "phone";

    private EditText _firstNameEditText;
    private EditText _surnameEditText;
    private EditText _phoneEditText;
    private Button _loginButton;

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        _firstNameEditText.setText(prefs.getString(FIRSTNAME_KEY, ""));
        _surnameEditText.setText(prefs.getString(SURNAME_KEY, ""));
        _phoneEditText.setText(prefs.getString(PHONE_KEY, ""));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Регистрация");
        setContentView(R.layout.activity_login);

        _firstNameEditText = findViewById(R.id.firstname_edit_text);
        _surnameEditText = findViewById(R.id.surname_edit_text);
        _phoneEditText = findViewById(R.id.phone_edit_text);
        _loginButton = findViewById(R.id.registration);

        _loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        sharedPreferences.edit()
            .putString(FIRSTNAME_KEY, _firstNameEditText.getText().toString())
            .putString(SURNAME_KEY, _surnameEditText.getText().toString())
            .putString(PHONE_KEY, _phoneEditText.getText().toString())
            .commit();

        Intent intent = new Intent(this, CallAсtivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(FIRSTNAME_KEY, _firstNameEditText.getText().toString());
        bundle.putString(SURNAME_KEY, _surnameEditText.getText().toString());
        bundle.putString(PHONE_KEY, _phoneEditText.getText().toString());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
