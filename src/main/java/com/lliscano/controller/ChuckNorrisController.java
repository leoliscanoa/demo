package com.lliscano.controller;

import com.lliscano.dto.ResponseDTO;
import com.lliscano.dto.ResponseServiceDTO;
import com.lliscano.service.ChuckNorrisService;
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

    @GetMapping(value = "/chuck-norris/{count}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO<List<ResponseServiceDTO>>> getChuckNorris(
            @PathVariable(value = "count")
            final Long count
    ) {
        return new ResponseEntity<>(this.service.apiChuckNorris(count), HttpStatus.OK);
    }
}
