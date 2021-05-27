package com.gaspol.filmplus.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gaspol.filmplus.core.data.source.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class FilmDatabase : RoomDatabase() {

    abstract fun filmDao(): FilmDao
}