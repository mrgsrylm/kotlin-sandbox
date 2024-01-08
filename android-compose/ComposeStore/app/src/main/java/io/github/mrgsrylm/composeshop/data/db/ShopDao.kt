package io.github.mrgsrylm.composeshop.data.db

import androidx.room.*
import io.github.mrgsrylm.composeshop.data.db.entities.ProductEntity
import io.github.mrgsrylm.composeshop.data.db.entities.UserEntity

@Dao
interface ShopDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(user: UserEntity): Long

    @Query("select * from login")
    suspend fun getUser(): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProductToFavorites(product: ProductEntity): Long

    @Query("select * from favorites")
    suspend fun getFavorites(): List<ProductEntity>

    @Query("delete from favorites where productId = :productId and category = :category")
    suspend fun deleteFromFavorites(productId: Int, category: String)
}