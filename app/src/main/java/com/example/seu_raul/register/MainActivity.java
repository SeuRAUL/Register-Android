package com.example.seu_raul.register;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.icu.text.SimpleDateFormat;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.renderscript.Element;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.Calendar;
import java.util.Date;

import dominio.Person;

public class MainActivity extends AppCompatActivity {

    String name;
    String phone;
    String gender;

    ImageButton fotoBtn;
    private static final int FOTO = 1;
    DatePickerDialog dataDialog;
    SimpleDateFormat formatador;
//    Button btnCadastrar;
//    Date dataText;
    Button registerButton;
    EditText dataText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.nameText).toString();
//        phone = findViewById(R.id.foneText).toString();
//        if (findViewById(R.id.FemRB)) {
//            gender = "Feminino";
//        } else if (findViewById(R.id.MascRB)) {
//            gender = "Masculino";
//        }


        fotoBtn = (ImageButton) findViewById(R.id.fotoBtn);
        fotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, FOTO);
            }
        });

        formatador = new SimpleDateFormat("dd/MM/yyyy");
        dataText = (EditText) findViewById(R.id.dataText);

        Calendar novaData = Calendar.getInstance();
        dataDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar data = Calendar.getInstance();
                data.set(year, monthOfYear, dayOfMonth);
                dataText.setText(formatador.format(data.getTime()));
            }
        }, novaData.get(Calendar.YEAR), novaData.get(Calendar.MONTH), novaData.get(Calendar.DAY_OF_MONTH));

        registerButton = (Button) findViewById(R.id.RegisterButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmarDialog();
            }
        });

        dataText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) dataDialog.show();
            }
        });

        dataText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataDialog.show();
            }
        });

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==FOTO) {
            Bitmap foto = (Bitmap) data.getExtras().get("data");
            fotoBtn.setImageBitmap(foto);
        }
    }

    public void confirmarDialog() {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Atenção");
        alerta.setMessage("Deseja realmente cadastrar?");
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               finish();
           }
        });
        alerta.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alerta.show();
    }

    public void registerPerson(View view) {

        Person p = new Person(name, phone, gender);

        Intent i = new Intent(MainActivity.this, ShowPersonActivity.class);
        i.putExtra("person", p);
    }
}
