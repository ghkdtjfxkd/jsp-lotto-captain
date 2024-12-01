<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
        }

        .card:hover {
            background-color: #e0e0e0;
        }

        .card.disabled {
            background-color: #666;
            color: #fff;
            cursor: pointer; /* 재선택 가능 */
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
        let selectedNumbers = []; // 선택된 숫자 저장
        const maxSelections = 6;

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
            lottoCircles.forEach((circle, index) => {
                circle.innerHTML = selectedNumbers[index] || ''; // 선택된 숫자만 표시
            });
        }

        function filterCards(filter) {
            const cards = document.querySelectorAll('.card');
            cards.forEach(card => {
                const number = parseInt(card.innerText, 10);

                if (filter === 'non') {
                    // Non 필터: 모든 숫자 표시
                    card.style.display = 'inline-block';
                } else {
                    // 특정 필터: A(1~5), B(6~10), ..., H(41~45)
                    const rangeStart = (filter - 1) * 5 + 1;
                    const rangeEnd = filter * 5;

                    if (number >= rangeStart && number <= rangeEnd) {
                        card.style.display = 'inline-block';
                    } else {
                        card.style.display = 'none';
                    }
                }
            });
        }
    </script>
</head>
<body>
<!-- 필터 버튼 -->
<div class="filter-container">
    <label><input type="radio" name="filter" onclick="filterCards('non')" /> Non</label>
    <label><input type="radio" name="filter" onclick="filterCards(1)" /> A</label>
    <label><input type="radio" name="filter" onclick="filterCards(2)" /> B</label>
    <label><input type="radio" name="filter" onclick="filterCards(3)" /> C</label>
    <label><input type="radio" name="filter" onclick="filterCards(4)" /> D</label>
    <label><input type="radio" name="filter" onclick="filterCards(5)" /> E</label>
    <label><input type="radio" name="filter" onclick="filterCards(6)" /> F</label>
    <label><input type="radio" name="filter" onclick="filterCards(7)" /> G</label>
    <label><input type="radio" name="filter" onclick="filterCards(8)" /> H</label>
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
<form action="processLotto.jsp" method="post">
    <button class="submit-btn" type="submit">Submit</button>
</form>
</body>
</html>
