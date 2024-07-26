package com.example.investigacion01todolist

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.ComponentActivity
import com.example.investigacion01todolist.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Lista que se mostrará
        val datos = mutableListOf<Task>()

        // Creamos el adaptador personalizado con la lista de tareas
        val taskAdapter = TaskAdapter(this, datos)

        // Capturamos el ListView de nuestro layout
        val lvdatos = findViewById<ListView>(R.id.lvDatos)

        // Capturamos el campo de entrada y el botón
        val etNewTask = findViewById<EditText>(R.id.etNewTask)
        val btnAddTask = findViewById<Button>(R.id.btnAddTask)

        lvdatos.adapter = taskAdapter

        // Configuramos el botón para añadir tareas
        btnAddTask.setOnClickListener {
            // Capturamos el dato del campo de entrada
            val newTask = etNewTask.text.toString()
            // Si no está vacío
            if (newTask.isNotEmpty()) {
                // Agregamos el dato a nuestro arreglo
                datos.add(Task(newTask, false))
                taskAdapter.notifyDataSetChanged() // Notificamos al adaptador del cambio
                etNewTask.text.clear() // Limpiamos el campo de entrada
            }
        }

        //agregamos una funcion al listview que es cuando se mantenga presionado por momento le permita eliminar
        //se utiliza el parent, la vista, la posicion del objeto y el id
        lvdatos.setOnItemLongClickListener{parent,view, position,id ->

            //Creamos un cuadro de alerta que permita el usuario elegir
            AlertDialog.Builder(this).apply {
                //titulo de la alerta
                setTitle("Confirmar Eliminacion")
                //mensaje de la alerta
                setMessage("Estas seguro de eliminar esta tarea")
                //si presiona si
                setPositiveButton("SI"){dialog, which ->

                    //con esta funcion removemos el objeto de la posicion
                    datos.removeAt(position)
                    //notificamos si quiere ser eliminado
                    taskAdapter.notifyDataSetChanged()
                }//si presiona NO
                setNegativeButton("No") { dialog, which ->
                    // Cancelamos la acción
                    dialog.dismiss()
                }
                create()
                show()
            }
            true
        }
    }
}
