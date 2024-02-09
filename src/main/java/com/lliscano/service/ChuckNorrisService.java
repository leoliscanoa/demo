package com.lliscano.service;

import com.lliscano.dto.ResponseDTO;
import com.lliscano.dto.ResponseServiceDTO;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChuckNorrisService {

    private final RestTemplate restTemplate;
    private final Long CHUNK = 5L;

    @SneakyThrows
    public ResponseDTO<List<ResponseServiceDTO>> apiChuckNorris(Long count) {
        List<ResponseServiceDTO> data = new ArrayList<>();
        List<CompletableFuture> futures = new ArrayList<>();
        if(count >= CHUNK) {
            final Long total = count > 5 ? (long) Math.floor(count/CHUNK) : 1;
            final Long last = Math.floorMod(count,CHUNK);
            for(var i = 0; i< total; i++) {
                futures.add(CompletableFuture.supplyAsync(() -> getObject(CHUNK)));
            }
            if(last>0) {
                futures.add(CompletableFuture.supplyAsync(() -> getObject(last)));
            }
        } else {
            futures.add(CompletableFuture.supplyAsync(() -> getObject(count)));
        }

        CompletableFuture<Void> merge =  CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        merge.join();
        for (CompletableFuture item : futures) {
            log.info("Item: {}", item.get());
            data.addAll((List<ResponseServiceDTO>) item.get());
        }
        return new ResponseDTO<>(
                new Date().toString(),
                "success",
                data,
                data.stream().count()
        );
    }

    private List<ResponseServiceDTO> getObject(Long count) {
        List<ResponseServiceDTO> data = new ArrayList<>();
        for(var i = 0; i< count ; i++ ) {
            final ResponseServiceDTO object = this.restTemplate.getForObject("https://api.chucknorris.io/jokes/random", ResponseServiceDTO.class);
            final ResponseServiceDTO dto = data.stream().filter(item -> item.getId().equalsIgnoreCase(object.getId()))
                    .findFirst()
                    .orElse(null);
            if(Objects.isNull(dto))
                data.add(object);
        }
        return data;
    }
}
