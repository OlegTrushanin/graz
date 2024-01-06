package oleg.trushanin.graz;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiServiceLoadPrice {



    @GET
    Observable<ResponseBody> downloadPricePdf(@Url String fileUrl);


}
