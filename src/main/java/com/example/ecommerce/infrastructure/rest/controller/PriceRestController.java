package com.example.ecommerce.infrastructure.rest.controller;

import com.example.ecommerce.common.dto.PriceDto;
import com.example.ecommerce.domain.model.Price;
import com.example.ecommerce.usecase.IFindPriceInteractor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/price", produces = MediaType.APPLICATION_JSON_VALUE)
public class PriceRestController {

    private final IFindPriceInteractor iFindPriceInteractor;
    private final ModelMapper modelMapper = new ModelMapper();

    @Operation(
            security = @SecurityRequirement(name = "bearerAuth"),
            summary = "Obtain a price",
            description = "Obtain a price by productId, brandId and applicationDate",
            operationId = "getPrice",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Request has succeeded."),
                    @ApiResponse(responseCode = "400", description = "Invalid request data"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized access"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error"),
                    @ApiResponse(responseCode = "502", description = "Bad Gateway")
            }
    )
    @GetMapping(path = "/{productId}/{brandId}")
    public ResponseEntity<PriceDto> getPrice(@PathVariable("productId") Integer productId,
                                             @PathVariable("brandId") Integer brandId,
                                             @RequestParam(name = "applicationDate") LocalDateTime applicationDate) {
        Price price = iFindPriceInteractor.findPriceByElements(applicationDate,productId, brandId);
        return ResponseEntity.ok(modelMapper.map(price, PriceDto.class));
    }
}
