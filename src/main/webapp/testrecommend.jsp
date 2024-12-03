<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로또 번호 선택</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin: 0;
            padding: 0;
        }

        .filter-container {
            margin: 20px;
        }

        .filter-container label {
            margin: 0 10px;
            cursor: pointer;
        }

        .lotto-container {
            margin: 20px auto;
            display: flex;
            justify-content: center;
            gap: 10px;
        }

        .lotto-number {
            width: 50px;
            height: 50px;
            line-height: 50px;
            text-align: center;
            border: 1px solid #000;
            border-radius: 50%;
            background-color: #fff;
            font-size: 18px;
        }

        .card-box {
            margin: 20px auto;
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            max-width: 700px;
            border: 1px solid #ccc;
            padding: 10px;
            border-radius: 10px;
            height: 420px;
            overflow-y: auto;
        }

        .card {
            width: 60px;
            height: 60px;
            margin: 5px;
            line-height: 60px;
            text-align: center;
            border: 1px solid #000;
            border-radius: 10px;
            background-color: #f9f9f9;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .card:hover {
            background-color: #e0e0e0;
        }

        .card.disabled {
            background-color: #666;
            color: #fff;
            cursor: not-allowed;
        }

        .submit-btn {
            display: inline-block;
            margin: 20px 0;
            padding: 10px 20px;
            font-size: 16px;
            color: #fff;
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .submit-btn:hover {
            background-color: #0056b3;
        }
    </style>
    <script>
        let selectedNumbers = [];
        const maxSelections = 6;
        let filters = {
            even: false,
            odd: false,
            range: { start: null, end: null },
            filter4: false,
            filter5: false,
            filter6: false
        };

        const excludedNumbers = []; // 제외할 번호 리스트

        // 카드 선택 함수
        function selectCard(card, number) {
            if (selectedNumbers.length >= maxSelections && !selectedNumbers.includes(number)) {
                return; // 이미 6개 숫자가 선택되었으면 더 이상 선택되지 않음
            }

            const index = selectedNumbers.indexOf(number);

            if (index === -1 && selectedNumbers.length < maxSelections) {
                card.classList.add('disabled');
                selectedNumbers.push(number);
            } else {
                card.classList.remove('disabled');
                selectedNumbers.splice(index, 1);
            }

            updateLottoCircles();
        }

        // 중앙 원에 숫자 표시
        function updateLottoCircles() {
            const lottoCircles = document.querySelectorAll('.lotto-number');
            lottoCircles.forEach((circle, index) => {
                circle.innerHTML = selectedNumbers[index] || '';
            });
        }

        // 필터 적용 함수
        function applyFilters() {
            const cards = document.querySelectorAll('.card');
            const start = filters.range.start;
            const end = filters.range.end;

            cards.forEach(card => {
                const number = parseInt(card.innerText, 10);
                let showCard = true;

                // 짝수 필터
                if (filters.even && number % 2 !== 0) {
                    showCard = false;
                }

                // 홀수 필터
                if (filters.odd && number % 2 === 0) {
                    showCard = false;
                }

                // 범위 필터
                if (start !== null && end !== null && (number < start || number > end)) {
                    showCard = false;
                }

                // 필터4: 제외할 번호
                if (filters.filter4 && excludedNumbers.includes(number)) {
                    showCard = false;
                }

                // 필터5, 필터6은 아무 기능 없음

                // 카드 표시 여부 결정
                card.style.display = showCard ? 'inline-block' : 'none';
            });
        }

        // 필터 변경 함수
        function filterChanged() {
            // 범위 필터 처리
            const start = document.getElementById("rangeStart").value;
            const end = document.getElementById("rangeEnd").value;

            filters.range.start = start ? parseInt(start) : null;
            filters.range.end = end ? parseInt(end) : null;

            // 체크박스 상태 업데이트
            filters.even = document.getElementById("even").checked;
            filters.odd = document.getElementById("odd").checked;
            filters.filter4 = document.getElementById("filter4").checked;
            filters.filter5 = document.getElementById("filter5").checked;
            filters.filter6 = document.getElementById("filter6").checked;

            // 필터 적용
            applyFilters();
        }

        // Submit 시 표시된 숫자 카드 리스트 생성 및 전송
        function prepareSubmit() {
            const visibleCards = document.querySelectorAll('.card:not([style*="display: none"])');
            const cardNumbers = Array.from(visibleCards).map(card => card.innerText);
            document.getElementById("visibleCards").value = cardNumbers.join(",");
        }

        // 필터4 적용: 로또 API에서 최근 번호를 가져와서 제외 처리
        function applyFilter4() {
            const today = new Date();
            const baseDate = new Date("2002-12-07"); // 1회차 기준 날짜
            const daysSinceBase = Math.floor((today - baseDate) / (1000 * 3600 * 24));
            const drawNo = Math.floor(daysSinceBase / 7) + 1;

            fetch('http://localhost:3000/lotto?drawNo='+drawNo)
                .then(response => response.json())
                .then(data => {
                    const excluded = [
                        parseInt(data.drwtNo1),
                        parseInt(data.drwtNo2),
                        parseInt(data.drwtNo3),
                        parseInt(data.drwtNo4),
                        parseInt(data.drwtNo5),
                        parseInt(data.drwtNo6),
                        parseInt(data.bnusNo)
                    ];
                    excludedNumbers.length = 0;  // 기존 제외된 숫자 리스트 초기화
                    excludedNumbers.push(...excluded);
                    if (filters.filter4) {
                        applyFilters(); // 필터4가 체크되어 있으면 필터 적용
                    }
                });
        }

        // 페이지 로드 시 필터4 자동 적용
        window.onload = function() {
            applyFilter4();  // 페이지 로드 시 필터4가 자동 적용되도록 수정
        };
    </script>
</head>
<body>
<!-- 필터 체크박스 -->
<div class="filter-container">
    <label><input type="checkbox" id="even" onclick="filterChanged()" /> 짝수</label>
    <label><input type="checkbox" id="odd" onclick="filterChanged()" /> 홀수</label>
    <label>
        범위: <input type="text" id="rangeStart" placeholder="시작 번호" oninput="filterChanged()" />
        ~
        <input type="text" id="rangeEnd" placeholder="끝 번호" oninput="filterChanged()" />
    </label>
    <label><input type="checkbox" id="filter4" onclick="filterChanged()" /> 필터4</label>
    <label><input type="checkbox" id="filter5" onclick="filterChanged()" /> 필터5</label>
    <label><input type="checkbox" id="filter6" onclick="filterChanged()" /> 필터6</label>
</div>

<!-- 중앙의 로또 번호 -->
<div class="lotto-container">
    <div class="lotto-number"></div>
    <div class="lotto-number"></div>
    <div class="lotto-number"></div>
    <div class="lotto-number"></div>
    <div class="lotto-number"></div>
    <div class="lotto-number"></div>
</div>

<!-- 카드 상자 -->
<div class="card-box">
    <%
        for (int i = 1; i <= 45; i++) {
    %>
    <div class="card" onclick="selectCard(this, <%= i %>)"><%= i %></div>
    <%
        }
    %>
</div>

<!-- Submit 버튼 -->
<form action="processLotto.jsp" method="post" onsubmit="prepareSubmit()">
    <input type="hidden" id="visibleCards" name="visibleCards" />
    <button class="submit-btn" type="submit">Submit</button>
</form>
</body>
</html>
