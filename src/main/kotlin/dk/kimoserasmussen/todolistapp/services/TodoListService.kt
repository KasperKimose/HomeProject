package dk.kimoserasmussen.todolistapp.services

import dk.kimoserasmussen.todolistapp.db.TodoDAO
import dk.kimoserasmussen.todolistapp.db.TodoListDAO
import dk.kimoserasmussen.todolistapp.models.Todo
import dk.kimoserasmussen.todolistapp.models.TodoList
import javax.ws.rs.NotFoundException

class TodoListService(
        private val todoListDAO: TodoListDAO,
        private val todoDAO: TodoDAO
) {

    fun getAllTodoList() =
            todoListDAO.findAll()


    fun getTodoList(id: Long) =
            todoListDAO.findById(id).orElseThrow { NotFoundException("No list with that id")}


    fun createTodoList(todoList: TodoList) =
            todoListDAO.create(todoList)


    fun createTodo(id: Long, todo: Todo): TodoList{
        val todoList = getTodoList(id)
        todoList.addTodo(todo.copy(todoList = todoList))
        return todoList
    }

    fun deleteTodoList(id: Long): TodoList{
        val todoList = getTodoList(id)
        return todoListDAO.delete(todoList)
    }
}