// 프로필 보기 버튼 가져오기
const button = document.getElementById("profile-btn");

// 버튼 클릭 시 동작
button.addEventListener("click", function() {

    // 서버에 프로필 정보 요청
    fetch("/api/profile")
        .then(function(response) {
            return response.json();
        })
        .then(function(data) {
            // 화면 전환
            document.getElementById("home-screen").style.display = "none";
            document.getElementById("profile-screen").style.display = "block";
            document.getElementById("nav-bar").style.display = "flex";  // 상단 고정 바 보이기

            // 받은 데이터 화면에 표시
            document.getElementById("name").textContent = data.name;
            document.getElementById("department").textContent = data.department;
            document.getElementById("year").textContent = data.year;

            // 이력 목록 채우기
            const historyList = document.getElementById("history");
            data.history.forEach(function(item) {
                const li = document.createElement("li");
                li.textContent = item;
                historyList.appendChild(li);
            });
        });
});

// README 버튼 가져오기
const readmeBtn = document.getElementById("readme-btn");

// README 버튼 클릭 시 동작
readmeBtn.addEventListener("click", function() {
    fetch("/api/readme")
        .then(function(response) {
            return response.json();
        })
        .then(function(data) {
            document.getElementById("readme-text").textContent = data.content;

            const readmeBox = document.getElementById("readme-content");
            readmeBox.style.display = "block";

            // README 영역이 보이게 된 후, 그 위치까지 부드럽게 스크롤 이동
            readmeBox.scrollIntoView({ behavior: "smooth" });
        });
});

// IntersectionObserver: 요소가 화면에 들어오면 감지하는 도구
const observer = new IntersectionObserver(function(entries) {
    entries.forEach(function(entry) {
        if (entry.isIntersecting) {
            entry.target.classList.add("visible");
        }
    });
}, {
    threshold: 0.1
});

document.querySelectorAll(".fade-up").forEach(function(el) {
    observer.observe(el);
});

// 섹션 이동 버튼 동작
const navButtons = document.querySelectorAll(".nav-btn");

navButtons.forEach(function(btn) {
    btn.addEventListener("click", function() {
        const targetId = btn.getAttribute("data-target");
        const targetEl = document.getElementById(targetId);
        targetEl.scrollIntoView({ behavior: "smooth" });
    });
});