package com.example.investigacion01todolist

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.investigacion01todolist.ui.theme.Investigacion01ToDoListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //creamos un adaptador que es el que nos permitira mostrar los datos
        val arrayAdapter:ArrayAdapter<*>

        //lita que se mostrara
        val datos = mutableListOf("Prueba 01", "Prueba 02")

        //capturamos el listView de nuestro layout
        val lvdatos = findViewById<ListView>(R.id.lvDatos)

        //capturamos el campo de entrada y el boton
        val etNewTask = findViewById<EditText>(R.id.etNewTask)
        val btnAddTask = findViewById<Button>(R.id.btnAddTask)

        //le pasamos los datos a nuestro adapter
        arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, datos)

        lvdatos.adapter = arrayAdapter



        //funcion del boton
        btnAddTask.setOnClickListener{
            //capturamos el dato
            val newTask = etNewTask.text.toString()
            //si no esta vacio
            if (newTask.isNotEmpty()) {
                //agregamos el dato a nuestro arreglo
                datos.add(newTask)
                arrayAdapter.notifyDataSetChanged()
                etNewTask.text.clear()
            }
        }
    }
}

