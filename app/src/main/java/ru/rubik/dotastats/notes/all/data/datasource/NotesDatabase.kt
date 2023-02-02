package ru.rubik.dotastats.notes.all.data.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.rubik.dotastats.notes.all.data.dao.NotesDao
import ru.rubik.dotastats.notes.all.data.entities.NoteDto

@Database(entities = [NoteDto::class], version = 1, exportSchema = true)
abstract class NotesDatabase: RoomDatabase() {

    abstract fun noteDao(): NotesDao

    companion object {
        @Volatile
        private var INSTANCE: NotesDatabase? = null

        fun getDatabase(context: Context): NotesDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDatabase::class.java,
                    "notes_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}