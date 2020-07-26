package dk.kimoserasmussen.todolistapp.models

import javax.persistence.*

@Entity
@Table(name = "todoLists")
@NamedQueries(NamedQuery(name = "dk.kimoserasmussen.todolistApp.models.TodoList.findAll", query = "SELECT tl FROM TodoList tl"))
data class TodoList (
    @Column(name = "title", nullable = false)
    val title: String = ""
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], mappedBy = "todoList")
    private val _todos: MutableCollection<Todo> = mutableListOf<Todo>()

    val todos get() = _todos.toList()

    fun addTodo(todo: Todo){
        _todos.add(todo)
    }
}