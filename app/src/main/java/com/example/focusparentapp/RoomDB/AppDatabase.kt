package com.example.websocket.RoomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.focusparentapp.RoomDB.Entities.PackageEntity
import com.example.focusparentapp.RoomDB.DAO.PackagesDAO
import com.example.focusparentapp.RoomDB.DAO.UsersDAO
import com.example.focusparentapp.RoomDB.Entities.UserEntity


@Database(entities = [PackageEntity::class, UserEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase(){
        abstract fun packagesDao(): PackagesDAO
        abstract fun userDao(): UsersDAO
        companion object {
                @Volatile
                private var Instance: AppDatabase? = null

                fun getDatabase(context: Context): AppDatabase {
                        return Instance ?: synchronized(this) {
                                Room.databaseBuilder(context, AppDatabase::class.java, "packages_database")
                                        .build()
                                        .also { Instance = it }
                        }
                }
        }
}