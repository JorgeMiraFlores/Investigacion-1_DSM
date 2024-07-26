package com.example.investigacion01todolist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat

// Clase de datos para representar una tarea con un nombre y un estado de finalización
data class Task(val name: String, var isCompleted: Boolean)

// Adaptador personalizado para el ListView que muestra tareas
class TaskAdapter(private val context: Context, private val tasks: MutableList<Task>) : BaseAdapter() {

    // Devuelve el número de tareas en la lista
    override fun getCount(): Int = tasks.size

    // Devuelve la tarea en una posición específica de la lista
    override fun getItem(position: Int): Any = tasks[position]

    // Devuelve el ID de la tarea en una posición específica de la lista
    override fun getItemId(position: Int): Long = position.toLong()

    // Devuelve la vista para cada ítem en la lista
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder

        // Si no hay una vista reutilizable, infla una nueva y crea un ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_todo, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            // Reutiliza la vista existente y su ViewHolder
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        // Obtiene la tarea para la posición actual
        val task = tasks[position]




        // Establece un listener para actualizar el estado de finalización de la tarea cuando se haga clic en el checkbox
        viewHolder.completed.setOnCheckedChangeListener { _, isChecked ->
            task.isCompleted = isChecked
        }

        // Devuelve la vista para el ítem actual
        return view
    }

    // Clase ViewHolder para almacenar en caché las vistas de cada ítem
    private class ViewHolder(view: View) {
        val taskName: TextView = view.findViewById(R.id.tvTask)  // TextView para el nombre de la tarea
        val completed: CheckBox = view.findViewById(R.id.cbCompleted)  // CheckBox para el estado de finalización de la tarea
    }
}
