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
        const frequency = {}; // 번호 빈도수 계산

        function selectCard(card, number) {
            const index = selectedNumbers.indexOf(number);

            if (index === -1 && selectedNumbers.length < maxSelections) {
                // 선택되지 않은 상태에서 추가
                card.classList.add('disabled');
                selectedNumbers.push(number);
            } else {
                // 이미 선택된 상태에서 제거
                card.classList.remove('disabled');
                selectedNumbers.splice(index, 1);
            }

            updateLottoCircles();
        }

        function updateLottoCircles() {
            const lottoCircles = document.querySelectorAll('.lotto-number');
            selectedNumbers.sort((a, b) => a - b); // 선택된 숫자 오름차순 정렬
            lottoCircles.forEach((circle, index) => {
                circle.innerHTML = selectedNumbers[index] || ''; // 선택된 숫자만 표시
            });
        }

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

        async function fetchLottoData(drawNo) {
            try {
                const response = await fetch('http://localhost:3000/lotto?drawNo='+drawNo);
                const data = await response.json();
                return [
                    parseInt(data.drwtNo1),
                    parseInt(data.drwtNo2),
                    parseInt(data.drwtNo3),
                    parseInt(data.drwtNo4),
                    parseInt(data.drwtNo5),
                    parseInt(data.drwtNo6),
                    parseInt(data.bnusNo)
                ];
            } catch (error) {
                console.error("Error fetching lotto data:", error);
                return [];
            }
        }

        async function calculateFrequency(range) {
            const today = new Date();
            const baseDate = new Date("2002-12-07");
            const daysSinceBase = Math.floor((today - baseDate) / (1000 * 3600 * 24));
            const latestDrawNo = Math.floor(daysSinceBase / 7) + 1;

            const startDraw = latestDrawNo - range + 1;

            for (let i = latestDrawNo; i >= startDraw; i--) {
                const numbers = await fetchLottoData(i);
                numbers.forEach(num => {
                    frequency[num] = (frequency[num] || 0) + 1;
                });
            }
        }

        async function applyFilters() {
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

                // 필터5: 가장 많이 나온 번호 10개 제외
                if (filters.filter5) {
                    const sortedFreq = Object.entries(frequency).sort((a, b) => b[1] - a[1]);
                    const top10 = sortedFreq.slice(0, 10).map(entry => parseInt(entry[0]));
                    if (top10.includes(number)) {
                        showCard = false;
                    }
                }

                // 필터6: 가장 적게 나온 번호 10개 제외
                if (filters.filter6) {
                    const sortedFreq = Object.entries(frequency).sort((a, b) => a[1] - b[1]);
                    const bottom10 = sortedFreq.slice(0, 10).map(entry => parseInt(entry[0]));
                    if (bottom10.includes(number)) {
                        showCard = false;
                    }
                }

                // 카드 표시 여부 결정
                card.style.display = showCard ? 'inline-block' : 'none';
            });
        }

        async function filterChanged() {
            // 새로운 범위를 입력할 때 frequency 초기화
            Object.keys(frequency).forEach(key => delete frequency[key]);

            const start = document.getElementById("rangeStart").value;
            const end = document.getElementById("rangeEnd").value;

            filters.range.start = start ? parseInt(start) : null;
            filters.range.end = end ? parseInt(end) : null;

            const range = parseInt(document.getElementById("filterRange").value) || 0;

            filters.even = document.getElementById("even").checked;
            filters.odd = document.getElementById("odd").checked;
            filters.filter4 = document.getElementById("filter4").checked;
            filters.filter5 = document.getElementById("filter5").checked;
            filters.filter6 = document.getElementById("filter6").checked;

            // 범위를 변경하면 frequency를 다시 계산
            if (filters.filter5 || filters.filter6) {
                await calculateFrequency(range);
            }
            applyFilters();
        }

        window.onload = function() {
            applyFilter4();  // 페이지 로드 시 필터4가 자동 적용되도록 수정
            filterChanged();
        };

        function prepareSubmit() {
            const visibleCards = [];
            const cards = document.querySelectorAll('.card');

            cards.forEach(card => {
                if (card.style.display !== 'none') { // 보이는 카드만 필터링
                    visibleCards.push(card.innerText.trim());
                }
            });

            // 숨겨진 input 필드에 visibleCards 배열을 문자열로 변환해 저장
            document.getElementById('visibleCards').value = JSON.stringify(visibleCards);

            const filterStates = {
                even: document.getElementById('even').checked,
                odd: document.getElementById('odd').checked,
                range: {
                    start: document.getElementById('rangeStart').value || null,
                    end: document.getElementById('rangeEnd').value || null
                },
                filter4: document.getElementById('filter4').checked,
                filter5: document.getElementById('filter5').checked,
                filter6: document.getElementById('filter6').checked
            };

            // 숨겨진 input 필드에 filterStates 객체를 JSON 문자열로 저장
            document.getElementById('filterStates').value = JSON.stringify(filterStates)

        }

    </script>
</head>
<body>
<div class="filter-container">
    <label><input type="checkbox" id="even" onclick="filterChanged()" /> 짝수</label>
    <label><input type="checkbox" id="odd" onclick="filterChanged()" /> 홀수</label>
    <label>
        범위: <input type="text" id="rangeStart" placeholder="시작 번호" oninput="filterChanged()" />
        ~
        <input type="text" id="rangeEnd" placeholder="끝 번호" oninput="filterChanged()" />
    </label>
    <label><input type="checkbox" id="filter4" onclick="filterChanged()" /> 필터4</label>
    <label>범위:
        <input type="text" id="filterRange" placeholder="숫자 범위" oninput="filterChanged()" />
    </label>
    <label><input type="checkbox" id="filter5" onclick="filterChanged()" /> 필터5</label>
    <label><input type="checkbox" id="filter6" onclick="filterChanged()" /> 필터6</label>
</div>

<div class="lotto-container">
    <div class="lotto-number"></div>
    <div class="lotto-number"></div>
    <div class="lotto-number"></div>
    <div class="lotto-number"></div>
    <div class="lotto-number"></div>
    <div class="lotto-number"></div>
</div>

<div class="card-box">
    <% for (int i = 1; i <= 45; i++) { %>
    <div class="card" onclick="selectCard(this, <%= i %>)"><%= i %></div>
    <% } %>
</div>

<form action="/recommend/number-pool" method="post" onsubmit="prepareSubmit()">
    <input type="hidden" id="visibleCards" name="visibleCards" />
    <input type="hidden" id="filterStates" name="filterStates" />
    <button class="submit-btn" type="submit">Submit</button>
</form>
</body>
</html>