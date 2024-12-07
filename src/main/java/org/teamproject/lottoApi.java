package org.teamproject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;


public class lottoApi {


    public static void getLottoNumber(int drawNo) {
        String apiUrl = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=" + drawNo;

        try {
            // URL 연결 생성
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            // HTTP 상태 코드 확인
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("HTTP 요청 실패: 상태 코드 " + responseCode);
            }

            // 응답 데이터 읽기
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // JSON 파싱
            JSONObject data = new JSONObject(response.toString());

            // 결과 출력
            System.out.println("로또 회차: " + data.getInt("drwNo"));
            System.out.println("로또 일자: " + data.getString("drwNoDate"));
            System.out.print("당첨 번호: ");
            for (int i = 1; i <= 6; i++) {
                System.out.print(data.getInt("drwtNo" + i) + " ");
            }
            System.out.println();
            System.out.println("보너스 번호: " + data.getInt("bnusNo"));
            System.out.println("---------------------------------");

        } catch (Exception e) {
            System.out.println("오류가 발생했습니다: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        getLottoNumber(1147); // 1회차 정보 호출
    }
}


