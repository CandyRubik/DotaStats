package ru.ruik.dotastats.notes_impl.data.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.ruik.dotastats.notes_impl.data.dao.NotesDao
import ru.ruik.dotastats.notes_impl.data.entities.NoteDto

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
                    context,
                    NotesDatabase::class.java,
                    "notes_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}