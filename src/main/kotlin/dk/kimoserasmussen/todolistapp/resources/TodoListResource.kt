package dk.kimoserasmussen.todolistapp.resources

import dk.kimoserasmussen.todolistapp.models.Todo
import dk.kimoserasmussen.todolistapp.models.TodoList
import dk.kimoserasmussen.todolistapp.services.TodoListService
import io.dropwizard.hibernate.UnitOfWork
import javax.validation.Valid
import javax.validation.constraints.NotNull
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/list")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class TodoListResource(private val todoListService: TodoListService) {

    @GET
    @UnitOfWork
    fun getAllTodoList(): List<TodoList>{
        return todoListService.getAllTodoList();
    }

    @GET
    @Path("{id}")
    @UnitOfWork
    fun getTodoList(@PathParam("id") id: Long): TodoList{
        return todoListService.getTodoList(id)
    }

    @POST
    @UnitOfWork
    fun createTodoList(@NotNull @Valid todoList: TodoList): TodoList{
        return todoListService.createTodoList(todoList)
    }

    @POST
    @Path("{id}")
    @UnitOfWork
    fun createTodo(@PathParam("id") id: Long, @NotNull @Valid todo: Todo): TodoList{
        return todoListService.createTodo(id, todo)
    }

    @DELETE
    @Path("{id}")
    @UnitOfWork
    fun deleteTodoList(@PathParam("id") id: Long): TodoList{
        return todoListService.deleteTodoList(id)
    }
}