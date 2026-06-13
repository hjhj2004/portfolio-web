package com.portfolio;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

// 프로필 정보를 응답하는 컨트롤러
@RestController
public class PortfolioController {

    // /api/profile 요청 시 프로필 데이터 반환
    @GetMapping("/api/profile")
    public Map<String, Object> getProfile() {
        return Map.of(
                "name", "황희제",
                "department", "정보보호학과",
                "year", "4학년 2학기",
                "history", List.of(
                        "2023년 - 수원대학교 정보통신학부 입학",
                        "2024년 - 정보보호병 입대",
                        "2026년 - 수원대학교 정보보호학과 복학",
                        "2027년 - 화이트햇스쿨 수료 (학업 병행)"
                )
        );
    }
}