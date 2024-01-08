package io.github.mrgsrylm.composeshop.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.mrgsrylm.composeshop.data.db.entities.ProductEntity
import io.github.mrgsrylm.composeshop.data.db.entities.UserEntity

@Database(entities = [UserEntity::class, ProductEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ShopDatabase : RoomDatabase() {
    abstract val dao: ShopDao

    companion object {
        const val DATABASE_NAME = "shop_dao"
    }
}