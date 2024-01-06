package oleg.trushanin.graz;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;

public class ApiFactoryLoadPrice {

    static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://drive.google.com/")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build();


   static ApiServiceLoadPrice apiServiceLoadPrice = retrofit.create(ApiServiceLoadPrice.class);


}
