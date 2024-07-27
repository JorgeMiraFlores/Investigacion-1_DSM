package com.example.investigacion01todolist

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.ComponentActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : ComponentActivity() {
    private lateinit var taskAdapter: TaskAdapter
    private val datos = mutableListOf<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Cargar tareas desde SharedPreferences
        loadTasks()

        // Creamos el adaptador personalizado con la lista de tareas
        taskAdapter = TaskAdapter(this, datos)

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
                saveTasks() // Guardamos las tareas en SharedPreferences
            }
        }

        // Configuramos el ListView para eliminar tareas con una pulsación larga
        lvdatos.setOnItemLongClickListener { parent, view, position, id ->
            // Creamos un cuadro de alerta que permita al usuario elegir
            AlertDialog.Builder(this).apply {
                // Título de la alerta
                setTitle("Confirmar Eliminación")
                // Mensaje de la alerta
                setMessage("¿Estás seguro de eliminar esta tarea?")
                // Si presiona "Sí"
                setPositiveButton("Sí") { dialog, which ->
                    // Removemos el objeto de la posición
                    datos.removeAt(position)
                    // Notificamos al adaptador del cambio
                    taskAdapter.notifyDataSetChanged()
                    saveTasks() // Guardamos las tareas en SharedPreferences
                }
                // Si presiona "No"
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

    private fun saveTasks() {
        val sharedPreferences = getSharedPreferences("tasks_prefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(datos)
        editor.putString("tasks_list", json)
        editor.apply()
    }

    private fun loadTasks() {
        val sharedPreferences = getSharedPreferences("tasks_prefs", MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("tasks_list", null)
        val type = object : TypeToken<MutableList<Task>>() {}.type
        if (json != null) {
            val loadedTasks: MutableList<Task> = gson.fromJson(json, type)
            datos.addAll(loadedTasks)
        }
    }
}
