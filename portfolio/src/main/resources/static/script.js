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
            document.getElementById("github-btn").style.display = "block";

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