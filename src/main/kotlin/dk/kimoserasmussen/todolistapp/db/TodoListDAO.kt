package dk.kimoserasmussen.todolistapp.db

import dk.kimoserasmussen.todolistapp.models.TodoList
import io.dropwizard.hibernate.AbstractDAO
import org.hibernate.SessionFactory
import org.hibernate.query.Query
import java.util.*

open class TodoListDAO(factory: SessionFactory): AbstractDAO<TodoList>(factory){

    fun findAll(): List<TodoList>{
        return list(namedQuery("dk.kimoserasmussen.todolistApp.models.TodoList.findAll") as? Query<TodoList>)
    }

    fun findById(id: Long): Optional<TodoList>{
        return Optional.of(get(id))
    }

    fun create(todoList: TodoList): TodoList{
        return persist(todoList)
    }

    fun delete(todoList: TodoList): TodoList{
        return delete(todoList)
    }
}
