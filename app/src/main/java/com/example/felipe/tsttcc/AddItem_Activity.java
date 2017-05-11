package com.example.felipe.tsttcc;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.modelo.ItemDAO;


public class AddItem_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button btnCadastrar;
    EditText txtEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_);

        btnCadastrar = (Button) findViewById(R.id.buttonCadastrarItem);
        txtEdit = (EditText) findViewById(R.id.editTextItem);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemDAO dao = new ItemDAO();

                System.out.println(dao.inserirItem(txtEdit.getText().toString()));
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent it = new Intent(AddItem_Activity.this, OperacoesActivity.class);
            startActivity(it);
        } else if (id == R.id.nav_about) {
            Intent it = new Intent(AddItem_Activity.this, SobreNosActivity.class);
            startActivity(it);

        } else if (id == R.id.nav_logout) {

            AlertDialog.Builder alerta = new AlertDialog.Builder(AddItem_Activity.this);
            alerta.setTitle("Sair");
            alerta
                    .setIcon(R.mipmap.ic_logo)
                    .setMessage("Deseja realmente sair do aplicativo?")
                    .setCancelable(false)
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent it = new Intent(AddItem_Activity.this, OperacoesActivity.class);
                            startActivity(it);
                        }
                    })
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent it = new Intent(AddItem_Activity.this, LoginActivity.class);
                            startActivity(it);
                        }
                    });

            AlertDialog alertDialog = alerta.create();
            alertDialog.show();
        } else if (id == R.id.nav_addItem) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
