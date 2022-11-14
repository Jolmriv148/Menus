package com.example.menus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ActionMode am;
    TextView tv1;
    EditText et,et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1=findViewById(R.id.tv1);
        et=findViewById(R.id.et);
        et1=findViewById(R.id.et1);

        //Para menú contextual
        registerForContextMenu(et);

        //Para menú con barra de acción
        et1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //Si existe no hacemos nada
                if(am!=null) return false;
                //Si no existe lo creamos
                am=startActionMode(amc);
                return true;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menudeopciones, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int n=item.getItemId();
        float tamano = tv1.getTextSize();
        switch(n){
            case R.id.agrandar:

                tamano+=20;
                tv1.setTextSize(TypedValue.COMPLEX_UNIT_PX,tamano);
                return true;
            case R.id.disminuir:
                tamano-=20;
                tv1.setTextSize(TypedValue.COMPLEX_UNIT_PX,tamano);
                return true;

            case R.id.azul:
                tv1.setTextColor(Color.BLUE);
                return true;


            case R.id.rojo:
                tv1.setTextColor(Color.RED);
                return true;

            case R.id.verde:
                tv1.setTextColor(Color.GREEN);
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menucontextual, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int n=item.getItemId();
        switch (n){
            case R.id.mayus:
                et.setText(et.getText().toString().toUpperCase());
                return true;
            case R.id.minus:
                et.setText(et.getText().toString().toLowerCase());
                return true;
        }
        return super.onContextItemSelected(item);
    }

    ActionMode.Callback amc=new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            MenuInflater inflater = actionMode.getMenuInflater();
            inflater.inflate(R.menu.menucontextualaccion, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            int n=menuItem.getItemId();
            switch (n){
                case R.id.mayus:
                    et.setText(et.getText().toString().toUpperCase());
                    return true;
                case R.id.minus:
                    et.setText(et.getText().toString().toLowerCase());
                    return true;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {

            actionMode=null;
        }
    };

    public void mostrarMenuPopup(View view) {
        ImageView iv=findViewById(R.id.imageButton);
        //Creamos menú
        PopupMenu popupMenu=new PopupMenu(this,iv);
        //inflamos
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.menupooup, popupMenu.getMenu());
        //Implimentar método para elegir opción
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int n=menuItem.getItemId();
                switch(n){
                    case R.id.mensaje1:
                        Toast.makeText(getApplicationContext(),"Mensaje 1",Toast.LENGTH_LONG).show();
                        return true;
                        case R.id.mensaje2:
                        Toast.makeText(getApplicationContext(),"Mensaje 2",Toast.LENGTH_LONG).show();
                        return true;

                }
                return false;
            }
        });

        popupMenu.show();

    }
}