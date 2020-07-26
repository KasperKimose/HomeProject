package dk.kimoserasmussen.todolistapp.models

import javax.persistence.*

@Entity
@Table(name = "todos")
@NamedQueries(NamedQuery(name = "dk.kimoserasmussen.todolistApp.core.Todo.findAll", query = "SELECT tl FROM Todo tl"))
data class Todo (
        @Column(name = "title", nullable = false)
        val title: String = "",

        @ManyToOne(fetch = FetchType.LAZY)
        val todoList: TodoList? = null
){
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0

        @Column(name = "completed", nullable = true)
        val completed: Boolean = false
}
