package com.example.investigacion01todolist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView

// Clase de datos para representar una tarea con un nombre y un estado de finalización
data class Task(val name: String, var isCompleted: Boolean)

// Adaptador personalizado para el ListView que muestra tareas
class TaskAdapter(private val context: Context, private val tasks: MutableList<Task>) : BaseAdapter() {

    override fun getCount(): Int = tasks.size

    override fun getItem(position: Int): Any = tasks[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_todo, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val task = tasks[position]

        viewHolder.completed.isChecked = task.isCompleted
        viewHolder.taskName.text = task.name // Asignar el nombre de la tarea al TextView

        viewHolder.completed.setOnCheckedChangeListener { _, isChecked ->
            task.isCompleted = isChecked
        }

        return view
    }

    private class ViewHolder(view: View) {
        val taskName: TextView = view.findViewById(R.id.tvTask)
        val completed: CheckBox = view.findViewById(R.id.cbCompleted)
    }
}

    // Clase ViewHolder para almacenar en caché las vistas de cada ítem
    private class ViewHolder(view: View) {
        val taskName: TextView = view.findViewById(R.id.tvTask)  // TextView para el nombre de la tarea
        val completed: CheckBox = view.findViewById(R.id.cbCompleted)  // CheckBox para el estado de finalización de la tarea
    }

