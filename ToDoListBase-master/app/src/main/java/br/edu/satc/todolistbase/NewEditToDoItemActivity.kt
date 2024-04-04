package br.edu.satc.todolistbase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.room.Room
import br.edu.satc.todolistbase.roomdatabase.AppDatabase
import br.edu.satc.todolistbase.roomdatabase.ToDoItem

class NewEditToDoItemActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase
    private lateinit var etTitle: EditText
    private lateinit var etDescriptor: EditText
    private lateinit var btSave: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_edit_to_do_item)

        initDatabase()

        etTitle = findViewById(R.id.et_title)

        etDescriptor = findViewById(R.id.et_description)

        btSave = findViewById(R.id.bt_save)

        btSave.setOnClickListener{
            save()
        }

    }

    // Criar o objeto do toDoItem
    // Salvar no banco
    // Mostar uma msg que salvou
    // Fechar a tela
    private fun save(){
        val toDoItem = ToDoItem(
            null,
            etTitle.text.toString(),
            etDescriptor.text.toString(),
            false
        )

        //salva
        db.toDoItemDao().insertAll(toDoItem)

        //mensagem
        Toast.makeText(this, "Tarefa salva com sucesso!", Toast.LENGTH_SHORT).show()

        //finish
        finish()
    }

    private fun initDatabase() {
        // Inicializar nosso banco de dados Android Room Persistence com SQLITE
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-todolist"
        )
            .allowMainThreadQueries()
            .build()

    }
}