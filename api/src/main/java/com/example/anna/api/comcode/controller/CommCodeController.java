package com.example.anna.api.comcode.controller;

import com.example.anna.entity._common.EnumMapper;
import com.example.anna.entity._common.EnumValue;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@Tag(name = "공통 코드 조회 API")
@RestController
@RequestMapping("/commcode")
@RequiredArgsConstructor
public class CommCodeController {

    private final EnumMapper enumMapper;

    /**
     * enum 리스트 조회
     * @param names
     * @return
     */
    @Operation(summary = "ENUM 목록 조회")
    @GetMapping("/enums/{names}")
    public ResponseEntity<Map<String, List<EnumValue>>> findByEnums(@PathVariable String names) {
        return ResponseEntity.ok(enumMapper.get(names));
    }
}
