package io.github.mrgsrylm.composeshop.data.mappers

import com.google.common.truth.Truth.assertThat
import io.github.mrgsrylm.composeshop.data.db.entities.ProductEntity
import io.github.mrgsrylm.composeshop.data.remote.dto.product.ProductDTO
import io.github.mrgsrylm.composeshop.domain.model.product.Product
import org.junit.Test

class ProductMapperTest {

    @Test
    fun `test ProductDto to Product conversion`() {
        val productDto = ProductDTO(
            id = 123,
            thumbnail = "https://example.com/thumbnail.jpg",
            title = "Product Title",
            brand = "Brand Name",
            price = 999,
            discountPercentage = 10.0,
            rating = 4.5,
            description = "Product Description",
            images = listOf("https://example.com/image1.jpg", "https://example.com/image2.jpg"),
            category = "Product Category",
            stock = 10
        )

        val product = productDto.toProduct()

        assertThat(product.productId).isEqualTo(123)
        assertThat(product.image).isEqualTo("https://example.com/thumbnail.jpg")
        assertThat(product.title).isEqualTo("Product Title")
        assertThat(product.subtitle).isEqualTo("Brand Name")
        assertThat(product.price).isEqualTo(999)
        assertThat(product.discount).isEqualTo(10.0)
        assertThat(product.rating).isEqualTo(4.5)
        assertThat(product.description).isEqualTo("Product Description")
        assertThat(product.images).containsExactly(
            "https://example.com/image1.jpg",
            "https://example.com/image2.jpg"
        )
        assertThat(product.category).isEqualTo("Product Category")
    }

    @Test
    fun `test Product to ProductEntity conversion`() {
        val product = Product(
            productId = 123,
            image = "https://example.com/image.jpg",
            title = "Product Title",
            subtitle = "Brand Name",
            price = 999,
            discount = 10.0,
            rating = 4.5,
            description = "Product Description",
            images = listOf("https://example.com/image1.jpg", "https://example.com/image2.jpg"),
            category = "Product Category"
        )

        val productEntity = product.toProductEntity()

        assertThat(productEntity.productId).isEqualTo(123)
        assertThat(productEntity.image).isEqualTo("https://example.com/image.jpg")
        assertThat(productEntity.title).isEqualTo("Product Title")
        assertThat(productEntity.subtitle).isEqualTo("Brand Name")
        assertThat(productEntity.price).isEqualTo(999)
        assertThat(productEntity.discount).isEqualTo(10.0)
        assertThat(productEntity.rating).isEqualTo(4.5)
        assertThat(productEntity.description).isEqualTo("Product Description")
        assertThat(productEntity.images).containsExactly(
            "https://example.com/image1.jpg",
            "https://example.com/image2.jpg"
        )
        assertThat(productEntity.category).isEqualTo("Product Category")
    }

    @Test
    fun `test ProductEntity to Product conversion`() {
        val productEntity = ProductEntity(
            id = 456,
            productId = 123,
            image = "https://example.com/image.jpg",
            title = "Product Title",
            subtitle = "Brand Name",
            price = 999,
            discount = 10.0,
            rating = 4.5,
            description = "Product Description",
            images = listOf("https://example.com/image1.jpg", "https://example.com/image2.jpg"),
            category = "Product Category"
        )

        val product = productEntity.toProduct()

        assertThat(product.productId).isEqualTo(123)
        assertThat(product.image).isEqualTo("https://example.com/image.jpg")
        assertThat(product.title).isEqualTo("Product Title")
        assertThat(product.subtitle).isEqualTo("Brand Name")
        assertThat(product.price).isEqualTo(999)
        assertThat(product.discount).isEqualTo(10.0)
        assertThat(product.rating).isEqualTo(4.5)
        assertThat(product.description).isEqualTo("Product Description")
        assertThat(product.images).containsExactly(
            "https://example.com/image1.jpg",
            "https://example.com/image2.jpg"
        )
        assertThat(product.category).isEqualTo("Product Category")
    }
}