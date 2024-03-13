package id.my.mrgsrylm.mylivedataapi.data.remote.retrofit

import id.my.mrgsrylm.mylivedataapi.data.remote.response.PostReviewResponse
import id.my.mrgsrylm.mylivedataapi.data.remote.response.RestaurantResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("detail/{id}")
    fun getRestaurant(
        @Path("id") id: String
    ): Call<RestaurantResponse>

    @FormUrlEncoded
    @Headers("Authorization: token 12345")
    @POST("review")
    fun postReview(
        @Field("id") id: String,
        @Field("name") name: String,
        @Field("review") review: String
    ): Call<PostReviewResponse>
}