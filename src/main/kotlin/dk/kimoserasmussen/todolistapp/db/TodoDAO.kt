package dk.kimoserasmussen.todolistapp.db

import dk.kimoserasmussen.todolistapp.models.Todo
import io.dropwizard.hibernate.AbstractDAO
import org.hibernate.SessionFactory
import java.util.*
import org.hibernate.query.Query

open class TodoDAO(factory: SessionFactory): AbstractDAO<Todo>(factory) {

    fun findById(id: Long): Optional<Todo>{
        return Optional.of(get(id))
    }

    fun create(todo: Todo): Todo{
        return persist(todo)
    }

    fun findAll(): List<Todo>{
        return list(namedQuery("dk.kimoserasmussen.todoListApp.models.Todo.findAll") as? Query<Todo>)
    }
}