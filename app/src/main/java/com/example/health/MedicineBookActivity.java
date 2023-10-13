package com.example.health;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MedicineBookActivity extends AppCompatActivity {

    EditText edname,edaddress,edcontact,edpincode;
    Button btnBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_book);

        edname = findViewById(R.id.editTextLBTFullName);
        edaddress = findViewById(R.id.editTextLBTAddress);
        edpincode = findViewById(R.id.editTextLBTPincode);
        edcontact = findViewById(R.id.editTextLBTCN);
        btnBooking = findViewById(R.id.buttonLBTBook);

        Intent intent = getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date = intent.getStringExtra("date");

        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();

                Database db = new Database(getApplicationContext(),"healthcare",null,1);
                db.addOrder(username,edname.getText().toString(),edaddress.getText().toString(),edcontact.getText().toString(),edpincode.getText().toString(),date.toString(),"",Float.parseFloat(price[1].toString()),"medicine");
                db.removeCart(username,"lab");
                Toast.makeText(getApplicationContext(),"Your Booking is done successfully",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MedicineBookActivity.this,HomeActivity.class));
            }
        });
    }
}