package com.example.marsphotos.fake

import com.example.marsphotos.model.MarsPhoto

object FakeDataSource {

    private const val idOne = "img1"
    private const val idTwo = "img2"
    private const val imgOne = "url.one"
    private const val imgTwo = "url.two"
    val photosList = listOf(
        MarsPhoto(
            id = idOne,
            imgSrc = imgOne
        ),
        MarsPhoto(
            id = idTwo,
            imgSrc = imgTwo
        )
    )
}
