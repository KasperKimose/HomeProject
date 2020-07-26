package dk.kimoserasmussen.todolistapp

import com.fasterxml.jackson.module.kotlin.KotlinModule
import dk.kimoserasmussen.todolistapp.db.TodoDAO
import dk.kimoserasmussen.todolistapp.db.TodoListDAO
import dk.kimoserasmussen.todolistapp.models.Todo
import dk.kimoserasmussen.todolistapp.models.TodoList
import dk.kimoserasmussen.todolistapp.resources.TodoListResource
import dk.kimoserasmussen.todolistapp.services.TodoListService
import io.dropwizard.Application
import io.dropwizard.Configuration
import io.dropwizard.db.DataSourceFactory
import io.dropwizard.db.PooledDataSourceFactory
import io.dropwizard.hibernate.HibernateBundle
import io.dropwizard.migrations.MigrationsBundle
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment

class App : Application<AppConfig>() {

    companion object{
        @JvmStatic fun main(args: Array<String>) = App().run(*args)
    }

    private val hibernateBundle = object : HibernateBundle<AppConfig>(TodoList::class.java, Todo::class.java){
        override fun getDataSourceFactory(configuration: AppConfig): DataSourceFactory {
            return configuration.dataSourceFactory
        }

    }

    override fun initialize(bootstrap: Bootstrap<AppConfig>) {
        super.initialize(bootstrap)

        bootstrap.objectMapper.registerModule(KotlinModule())

        bootstrap.addBundle(object : MigrationsBundle<AppConfig>() {
            override fun getDataSourceFactory(configuration: AppConfig): DataSourceFactory {
                return configuration.dataSourceFactory
            }
        })
        bootstrap.addBundle(hibernateBundle)
    }

    override fun run(config: AppConfig, env: Environment) {
        val todoListDAO = TodoListDAO(hibernateBundle.sessionFactory)
        val todoDAO = TodoDAO(hibernateBundle.sessionFactory)

        env.jersey().register(TodoListResource(TodoListService(todoListDAO, todoDAO)))
    }
}
