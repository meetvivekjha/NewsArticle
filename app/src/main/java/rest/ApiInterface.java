package rest;

import model.ArticleResponse;
import model.SourceResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Freeware Sys on 9/18/2017.
 */

public interface ApiInterface {
    @GET("articles")
    Call<ArticleResponse> getArticleResponse(@Query("source") String source, @Query("apiKey") String apiKey);

    @GET("sources")
    Call<SourceResponse> getSourceResponse(@Query("category") String category,@Query("apiKey") String apikey);//"apiKey" must be same as what is required in api endpoint.

}
