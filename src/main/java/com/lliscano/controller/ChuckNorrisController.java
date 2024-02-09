package com.lliscano.controller;

import com.lliscano.dto.ResponseDTO;
import com.lliscano.dto.ResponseServiceDTO;
import com.lliscano.service.ChuckNorrisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChuckNorrisController {

    private final ChuckNorrisService service;

    @Operation(summary = "Returns a list of random objects based on input param")
    @ApiResponses(
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true))
    @GetMapping(value = "/chuck-norris/{total}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<ResponseServiceDTO>>> getChuckNorris(
            @Schema(implementation = Integer.class, description = "Total number of objects", requiredMode = Schema.RequiredMode.REQUIRED)
            @PathVariable(value = "total")
            final Long total
    ) {
        return new ResponseEntity<>(this.service.apiChuckNorris(total), HttpStatus.OK);
    }
}
