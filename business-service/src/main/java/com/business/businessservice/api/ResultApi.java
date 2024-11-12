package com.business.businessservice.api;

import com.business.businessservice.dto.TransactionResultDto;
import com.business.businessservice.dto.TransactionResultResponseWrapperDto;
import com.business.businessservice.dto.TransactionResultWrapper;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;


@Component
public interface ResultApi {

    @POST("results/all")
    Call<Void> createAll(@Body TransactionResultResponseWrapperDto transactionResultResponseWrapperDto);

    @DELETE("results/{id}")
    Call<Void> delete(@Path("id") Long id);

    @GET("payer/{name}")
    Call<List<TransactionResultDto>> getAllByPayer(@Path("name") String payerName);
}
