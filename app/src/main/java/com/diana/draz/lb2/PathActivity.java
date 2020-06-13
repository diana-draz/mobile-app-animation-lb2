package com.diana.draz.lb2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PathActivity extends AppCompatActivity implements View.OnClickListener {

    public static String RESULT_KEY = "result";
    public static int RESULT_CODE = 109;

    private EditText fromStreet;
    private EditText fromHouse;
    private EditText fromFlat;
    private EditText toStreet;
    private EditText toHouse;
    private EditText toFlat;
    private Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path);

        fromStreet = findViewById(R.id.from_street);
        fromHouse= findViewById(R.id.from_house);
        fromFlat = findViewById(R.id.from_flat);
        toStreet = findViewById(R.id.to_street);
        toHouse = findViewById(R.id.to_house);
        toFlat = findViewById(R.id.to_flat);
        ok = findViewById(R.id.ok);

        ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {



        Bundle bundle = new Bundle();
        bundle.putString(RESULT_KEY,
                "Такси будет ехать из улицы " + fromStreet.getText().toString()
                        +", дом "+ fromHouse.getText().toString() +", кв."+fromFlat.getText().toString()
                        +" на улицу "+ toStreet.getText().toString()+", дом "+toHouse.getText().toString()
                        +", кв."+toFlat.getText().toString());
        Intent intent = new Intent();
        intent.putExtras(bundle);

        setResult(PathActivity.RESULT_CODE, intent);
        finish();
    }
}
