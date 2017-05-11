package com.example.felipe.tsttcc;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.modelo.ControleDAO;

public class CadastroActivity extends AppCompatActivity {

    Button btnCadastrar;
    EditText etCadastroEmail, etCadastroSenha, etCadastroCNPJ;
    String emailEmpresa, senhaEmpresa, cnpjEmpresa;
    ControleDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        etCadastroEmail = (EditText) findViewById(R.id.etEmailCadastro);
        etCadastroSenha = (EditText) findViewById(R.id.etSenhaCadastro);
        etCadastroCNPJ = (EditText) findViewById(R.id.etCNPJCadastro);
        dao = new ControleDAO(this);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etCadastroEmail.getText().length()!=0
                        & etCadastroSenha.getText().length()!=0
                        & etCadastroCNPJ.getText().length()!=0){

                    emailEmpresa = etCadastroEmail.getText().toString();
                    senhaEmpresa = etCadastroSenha.getText().toString();
                    cnpjEmpresa = etCadastroCNPJ.getText().toString();

                    String mensagem = dao.newRegister(emailEmpresa, senhaEmpresa, cnpjEmpresa);

                    Toast.makeText(CadastroActivity.this, mensagem, Toast.LENGTH_SHORT).show();

                    /*==================CAIXA DE DIÁLOGO PARA IR PARA TELA DE LOGIN==================*/

                    AlertDialog.Builder alerta = new AlertDialog.Builder(CadastroActivity.this);
                    alerta.setTitle("Sucesso ao cadastrar");
                    alerta
                            .setIcon(R.mipmap.ic_logo)
                            .setMessage("Cadastro realizado com sucesso, deseja ir para a tela de login?")
                            .setCancelable(false)
                            .setNegativeButton("Cancelar", new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent it = new Intent(CadastroActivity.this, CadastroActivity.class);
                                    startActivity(it);
                                }
                            })
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent it = new Intent(CadastroActivity.this, LoginActivity.class);
                                    startActivity(it);
                                }
                            });

                    AlertDialog alertDialog = alerta.create();
                    alertDialog.show();

                    /*====================FIM DO COMANDO PARA A CAIXA DE DIÁLOGO====================*/

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
