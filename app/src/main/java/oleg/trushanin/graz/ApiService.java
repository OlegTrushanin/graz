package oleg.trushanin.graz;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("price")
    Single<List<Price>> loadPriceJSON(@Query("token") String value);

    @GET("bazaModel")
    Single<List<BazaModel>> loadBazaModelJSON(@Query("token") String value);

    @GET("mainOptions")
    Single<List<MainOptions>> loadMainOptionsJSON(@Query("token") String value);

    @GET("kO")
    Single<List<Ko>> loadKoJSON(@Query("token") String value);

    @GET("specialDark")
    Single<List<SpecialDarkOptions>> loadSpecialDarkJSON(@Query("token") String value);

    @GET("specialLight")
    Single<List<SpecialLightOptions>> loadSpecialLightJSON(@Query("token") String value);

}
