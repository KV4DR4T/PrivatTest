package com.business.businessservice.dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResultResponseWrapperDto implements Serializable {

    @SerializedName("transactionResults")
    private ArrayList<TransactionResultResponseDto> transactionResults = new ArrayList<>();
}
