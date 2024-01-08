package io.github.mrgsrylm.composeshop.domain.use_cases.local

import com.google.common.truth.Truth.assertThat
import io.github.mrgsrylm.composeshop.common.Resource
import io.github.mrgsrylm.composeshop.data.db.entities.UserEntity
import io.github.mrgsrylm.composeshop.data.mappers.toUser
import io.github.mrgsrylm.composeshop.domain.model.user.User
import io.github.mrgsrylm.composeshop.domain.repository.BackendRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetSavedUserUseCaseTest {
    @Mock
    private lateinit var mockRepository: BackendRepository

    private lateinit var useCase: GetSavedUserUseCase

    @Before
    fun setUp() {
        useCase = GetSavedUserUseCase(mockRepository)
    }

    @Test
    fun `invoke should emit Success resource when getSavedUser is successful`() =
        runBlocking {
            val savedUser = listOf(
                UserEntity(
                    id = 1,
                    username = "johndoe",
                    email = "johndoe@example.com",
                    firstName = "John",
                    lastName = "Doe",
                    image = "https://example.com/images/johndoe.jpg",
                    token = "abc123xyz456"
                )
            )
            `when`(mockRepository.getSavedUser()).thenReturn(savedUser)
            val expectedResource = Resource.Success(savedUser[0].toUser())

            var actualResource: Resource.Success<User>? = null

            useCase().collect { result ->
                if (result is Resource.Success) actualResource = result
            }

            assertThat(actualResource?.data).isEqualTo(expectedResource.data)
        }

    @Test
    fun `invoke should emit Error resource when getSavedUser is empty`() =
        runBlocking {
            val savedUser = emptyList<UserEntity>()
            `when`(mockRepository.getSavedUser()).thenReturn(savedUser)
            val expectedResource = Resource.Error<Nothing>("")

            var actualResource: Resource.Error<User>? = null

            useCase().collect { result ->
                if (result is Resource.Error) actualResource = result
            }

            assertThat(actualResource?.message).isEqualTo(expectedResource.message)
        }

    @Test
    fun `invoke should emit Error resource when getSavedUser throws an exception`() =
        runBlocking {
            `when`(mockRepository.getSavedUser()).thenThrow(RuntimeException("Test exception message"))
            val expectedResource = Resource.Error<Nothing>("Test exception message")

            var actualResource: Resource.Error<User>? = null

            useCase().collect { result ->
                if (result is Resource.Error) actualResource = result
            }

            assertThat(actualResource?.message).isEqualTo(expectedResource.message)
        }
}