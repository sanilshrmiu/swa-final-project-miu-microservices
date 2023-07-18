package com.example.demo.service;

import com.example.demo.dto.ElementDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient("ElementService")
public interface ElementFeignClient {
    @GetMapping("{elementId}")
    @ResponseStatus(HttpStatus.OK)
    public ElementDTO getById(@PathVariable("elementId") String id);

}
