import com.example.listapitest.Car
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface RentMyCarApiClient {
    @GET("cars") fun getCars(): Observable<ArrayList<Car>>

    companion object {

        fun create(): RentMyCarApiClient {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://10.0.2.2:8086/")
                //.baseUrl("http://192.168.137.1:8080/")
                //.baseUrl("http://10.180.124.215:8080/")

                .build()

            return retrofit.create(RentMyCarApiClient::class.java)
        }
    }
}