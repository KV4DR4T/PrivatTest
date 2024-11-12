package com.business.businessservice.api;

import com.business.businessservice.dto.InstructionDto;
import com.business.businessservice.dto.InstructionResponseDto;
import com.business.businessservice.dto.InstructionWrapper;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import java.util.List;


@Component
public interface InstructionApi {

    @POST("instructions")
    Call<Void> createInstruction(@Body InstructionDto instructionDto);

    @PUT("instructions/{instructionId}")
    Call<InstructionResponseDto> updateInstruction(@Body InstructionDto instructionDto, @Path("instructionId") Long instructionId);

    @DELETE("instructions/{instructionId}")
    Call<Void> deleteInstruction(@Path("instructionId") Long instructionId);

    @GET("instructions/{instructionId}")
    Call<InstructionDto> getInstructionById(@Path("instructionId") Long instructionId);

    @GET("instructions/{instructionTin}")
    Call<InstructionDto> getInstructionByTin(@Path("instructionTin") String instructionId);

    @GET("instructions/{instructionOkpo}")
    Call<InstructionDto> getInstructionByOkpo(@Path("instructionOkpo") String instructionOkpo);

    @GET("instructions/time")
    Call<List<InstructionResponseDto>> getAllByPaymentTime();

    @GET("instructions/payer/{name}")
    Call<List<InstructionDto>> getInstructionByPayer(@Path("name") String name);

    @GET("instructions/receiver/{name}")
    Call<List<InstructionDto>> getInstructionByReceiver(@Path("name") String name);

}
