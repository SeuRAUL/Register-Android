package com.example.seu_raul.register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import dominio.Person;

public class MainActivity extends AppCompatActivity {

    String name;
    String phone;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.nameText).toString();
        phone = findViewById(R.id.foneText).toString();
        if (findViewById(R.id.FemRB)) {
            gender = "Feminino";
        } else if (findViewById(R.id.MascRB)) {
            gender = "Masculino"
        }

    }

    public void registerPerson(View view) {

        Person p = new Person(name, phone, gender);

        Intent i = new Intent(MainActivity.this, ShowPersonActivity.class);
        i.putExtra("person", p);
    }
}
