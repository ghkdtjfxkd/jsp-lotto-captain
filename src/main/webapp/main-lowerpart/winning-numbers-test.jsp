<%--
  Created by IntelliJ IDEA.
  User: syt11
  Date: 2024. 12. 16.
  Time: 오전 11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>로또 당첨 결과</title>
    <style>
        .number { display: inline-block; width: 40px; height: 40px; line-height: 40px; border-radius: 50%; text-align: center; font-weight: bold; }
        .yellow { border: 3px solid yellow; }
        .blue { border: 3px solid blue; }
        .red { border: 3px solid red; }
        .black { border: 3px solid black; }
        .green { border: 3px solid green; }
    </style>
    <script>
        // 숫자에 따른 색상 클래스 반환 함수
        function getColorClass(num) {
            if (num <= 10) return "yellow";
            else if (num <= 20) return "blue";
            else if (num <= 30) return "red";
            else if (num <= 40) return "black";
            else return "green";
        }

        // 로또 데이터 불러오기
        async function fetchLottoData(drawNo) {
            const response = await fetch('http://localhost:3000/lotto?drawNo='+drawNo);
            return await response.json();
        }

        // 데이터 표시 함수
        async function displayLottoData() {
            const baseDate = new Date("2002-12-07"); // 1회차 기준일
            const today = new Date();
            const daysSinceBase = Math.floor((today - baseDate) / (1000 * 3600 * 24));
            const drawNo = Math.floor(daysSinceBase / 7) + 1; // 오늘 날짜 기준 최신 회차 계산

            // 현재 회차 및 이전 두 회차 데이터 가져오기
            const currentData = await fetchLottoData(drawNo);
            const prevData1 = await fetchLottoData(drawNo - 1);
            const prevData2 = await fetchLottoData(drawNo - 2);

            // 데이터 삽입
            renderLottoNumbers("current", currentData);
            renderLottoNumbers("previous1", prevData1);
            renderLottoNumbers("previous2", prevData2);

            document.getElementById("drawInfo").innerText = currentData.drwNo + "회차 당첨번호 (추첨일자: " + currentData.drwNoDate + ")";
            document.getElementById("winnerInfo").innerText = "1등 " + currentData.firstPrzwnerCo + "명 1등 당첨금: " + currentData.firstWinamnt.toLocaleString() + "원";
        }

        // 번호를 화면에 표시하는 함수
        function renderLottoNumbers(containerId, data) {
            var container = document.getElementById(containerId);
            container.innerHTML = ""; // 초기화

            for (var i = 1; i <= 6; i++) {
                var number = data["drwtNo" + i];
                container.innerHTML += "<span class='number " + getColorClass(number) + "'>" + number + "</span>";
            }

            // 보너스 번호
            container.innerHTML += "<span class='number " + getColorClass(data.bnusNo) + "'>+ " + data.bnusNo + "</span>";
        }

        // 페이지 로드 시 실행
        window.onload = displayLottoData;
    </script>
</head>
<body>
<h1 id="drawInfo"></h1>
<div id="current"></div>
<h2 id="winnerInfo"></h2>

<h3>이전 회차 당첨번호</h3>
<div id="previous1"></div>
<div id="previous2"></div>
</body>
</html>