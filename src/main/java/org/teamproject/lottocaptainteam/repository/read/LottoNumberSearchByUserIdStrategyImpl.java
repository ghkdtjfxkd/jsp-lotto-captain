package org.teamproject.lottocaptainteam.repository.read;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.teamproject.lottocaptainteam.connection.DBConnectionUtil;
import org.teamproject.lottocaptainteam.domain.LottoTest;
import org.teamproject.lottocaptainteam.parser.ListParser;
import org.teamproject.lottocaptainteam.parser.TimeStampParser;

public class LottoNumberSearchByUserIdStrategyImpl implements ReadListStrategy<LottoTest> {

    private final DBConnectionUtil connectionUtil;

    public LottoNumberSearchByUserIdStrategyImpl(DBConnectionUtil connectionUtil) {
        this.connectionUtil = connectionUtil;
    }

    @Override
    public List<LottoTest> execute(String infoForSearch) {
        String sql = "select * from lotto where user_id = ?";
        List<LottoTest> lottoList = new ArrayList<>();

        try (Connection conn = connectionUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, infoForSearch);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                LottoTest lotto = mapToLotto(rs);
                lottoList.add(lotto);
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

        return lottoList;
    }

    private LottoTest mapToLotto(ResultSet rs) throws SQLException {
        return LottoTest.of(rs.getLong("id"),
                        rs.getInt("draw"),
                        rs.getString("user_id"),
                        ListParser.createNumbersFrom(rs.getString("numbers"))
                ).createdAt(TimeStampParser.result(rs.getTimestamp("created_at")));
    }
}
