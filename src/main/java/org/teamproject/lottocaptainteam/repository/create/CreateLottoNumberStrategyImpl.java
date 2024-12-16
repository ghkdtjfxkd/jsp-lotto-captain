package org.teamproject.lottocaptainteam.repository.create;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.teamproject.lottocaptainteam.connection.DBConnectionUtil;
import org.teamproject.lottocaptainteam.domain.LottoTest;

public class CreateLottoNumberStrategyImpl implements CreateStrategy<LottoTest> {

    private final DBConnectionUtil connectionUtil;

    public CreateLottoNumberStrategyImpl(DBConnectionUtil connectionUtil) {
        this.connectionUtil = connectionUtil;
    }

    @Override
    public LottoTest execute(LottoTest lotto) {
        String sql = "insert into lotto(id, draw, user_id, numbers, created_at) values(?, ?, ?, ?, ?)";

        try (Connection con = connectionUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setLong(1,lotto.getId());
            pstmt.setInt(2, lotto.getDraw());
            pstmt.setString(3, lotto.getUserId());
            pstmt.setString(4, lotto.getNumbersParsedAtJSON());
            pstmt.setTimestamp(5, lotto.getCreatedAtAsTimestamp());

            pstmt.executeUpdate();
            return lotto;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
