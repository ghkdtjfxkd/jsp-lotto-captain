package org.teamproject.lottocaptainteam.service;

import static org.teamproject.lottocaptainteam.service.constant.FilePath.TOTAL_DRAW;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import org.teamproject.lottocaptainteam.parser.DrawParser;

public class PreviousDrawDataWriter {

    private static final String API_URL = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=";
    private static final String INNER_SEPARATOR = ",";

    private final int totalDrawCount;

    public PreviousDrawDataWriter() {
        totalDrawCount = DrawParser.result();
    }

    public void makeTotalDrawDataFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(TOTAL_DRAW.getPath())))) {
            String line = br.readLine();
            br.close();
            if (line == null) {
                try (BufferedWriter bw = new BufferedWriter(
                        new OutputStreamWriter(new FileOutputStream(TOTAL_DRAW.getPath())))) {
                    bw.write(header());
                    for (int i = 1; i <= this.totalDrawCount; i++) {
                        JSONObject lottoData = getLottoData(i);
                        bw.write(parseToInputForm(lottoData));
                    }
                    bw.flush();
                }
            }
        }
        System.out.println("작업완료");
    }

    private String header() {
        return String.format("%-10s | %-15s | %-20s | %-10s%n",
                "로또 회차", "로또 일자", "당첨번호", "보너스 번호");
    }

    private String drawNumber() {
        return String.format("%-10s | %-15s | %-20s | %-10s%n",
                "로또 회차", "로또 일자", "당첨번호", "보너스 번호");
    }

    private String parseToInputForm(JSONObject json) {
        return String.format("%-10d | %-15s | %-20s | %-10d%n",
                json.getInt("drwNo"),
                json.getString("drwNoDate"),
                winningEntry(json),
                json.getInt("bnusNo"));
    }

    private String winningEntry(JSONObject json) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 5; i++) {
            sb.append(json.getInt("drwtNo" + i));
            sb.append(INNER_SEPARATOR);
        }
        sb.append(json.getInt("drwtNo6"));
        return sb.toString();
    }

    private JSONObject getLottoData(int drawNo) {
        String apiUrl = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=" + drawNo;
        JSONObject data = new JSONObject();

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
            data = new JSONObject(response.toString());
        } catch (Exception e) {
            System.out.println("오류가 발생했습니다: " + e.getMessage());
        }
        return data;
    }

    public static void main(String[] args) throws IOException {
        PreviousDrawDataWriter pdw = new PreviousDrawDataWriter();
        pdw.makeTotalDrawDataFile();
    }
}
