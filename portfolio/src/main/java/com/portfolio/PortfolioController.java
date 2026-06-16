package com.portfolio;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
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

    // /api/readme 요청 시 GitHub에서 README를 가져와서 반환
    @GetMapping("/api/readme")
    public Map<String, String> getReadme() throws Exception {
        // GitHub API 주소: 내 저장소의 README 정보를 요청
        String url = "https://api.github.com/repos/hjhj2004/portfolio-web/readme";

        // HTTP 요청 보낼 클라이언트 생성
        HttpClient client = HttpClient.newHttpClient();

        // 요청 만들기 (GET 방식)
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        // 요청 보내고 응답 받기
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // 응답 본문 (JSON 형식)
        String body = response.body();

        // GitHub은 README 내용을 Base64로 인코딩해서 보내줌
        // JSON에서 content 부분만 추출
        int contentStart = body.indexOf("\"content\":\"") + 11;
        int contentEnd = body.indexOf("\"", contentStart);
        String encodedContent = body.substring(contentStart, contentEnd);

        // 줄바꿈 문자(\n) 제거
        encodedContent = encodedContent.replace("\\n", "");

        // Base64 디코딩 (다시 원래 글자로 변환)
        byte[] decodedBytes = Base64.getDecoder().decode(encodedContent);
        String readmeContent = new String(decodedBytes, "UTF-8");

        // 변환한 README 내용을 JSON으로 응답
        return Map.of("content", readmeContent);
    }
}