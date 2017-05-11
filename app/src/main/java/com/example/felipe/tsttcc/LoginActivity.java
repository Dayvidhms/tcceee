package com.example.felipe.tsttcc;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.modelo.ControleDAO;

public class LoginActivity extends AppCompatActivity {

    TextView tvCadastrar;
    EditText etEmail, etSenha;
    Button btnLogar;
    ControleDAO dao;
    String emailEmpresa, senhaEmpresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvCadastrar = (TextView) findViewById(R.id.tvCadastrar);
        btnLogar = (Button) findViewById(R.id.btnEntrarLogin);
        etEmail = (EditText) findViewById(R.id.etEmailLogin);
        etSenha = (EditText) findViewById(R.id.etSenhaLogin);
        dao = new ControleDAO(this);

        tvCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(it);
            }
        });

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etEmail.getText().length()!=0 & etSenha.getText().length()!=0) {

                    emailEmpresa = etEmail.getText().toString();
                    senhaEmpresa = etSenha.getText().toString();

                    Cursor cursorValidate = dao.validateRegister(emailEmpresa, senhaEmpresa);

                    if (cursorValidate.getCount()==0){
                        Toast.makeText(LoginActivity.this, "Email ou senha incorretos", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent it = new Intent(LoginActivity.this, OperacoesActivity.class);
                        startActivity(it);
                    }

                } else {
                    Context contexto = getApplicationContext();
                    String mensagem = "Preencha todos os campos";
                    Toast toast = Toast.makeText(contexto, mensagem, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }
}
