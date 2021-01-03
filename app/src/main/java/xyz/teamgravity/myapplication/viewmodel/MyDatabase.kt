package xyz.teamgravity.myapplication.viewmodel

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import xyz.teamgravity.myapplication.helper.util.Converter
import xyz.teamgravity.myapplication.model.RunModel

@Database(version = 1, entities = [RunModel::class])
@TypeConverters(Converter::class)
abstract class MyDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "my_database"
    }

    abstract fun runDao(): RunDao

    // we use dagger for the singleton shit
}