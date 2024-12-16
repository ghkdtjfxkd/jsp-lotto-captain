package org.teamproject.lottocaptainteam.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.teamproject.lottocaptainteam.connection.ConnectionConst;
import org.teamproject.lottocaptainteam.domain.LottoTest;
import org.teamproject.lottocaptainteam.parser.ListParser;
import org.teamproject.lottocaptainteam.parser.TimeStampParser;
import org.teamproject.lottocaptainteam.repository.create.CreateStrategy;
import org.teamproject.lottocaptainteam.repository.read.ReadListStrategy;

public class LottoNumberRepositoryImpl implements LottoNumberRepository {

    private static volatile LottoNumberRepositoryImpl instance;  // volatile 필수

    private final CreateStrategy<LottoTest> createLottoStrategy;
    private final ReadListStrategy<LottoTest> readLottoListByUserId;
    private final ReadListStrategy<LottoTest> readLottoListByDraw;

    private LottoNumberRepositoryImpl(CreateStrategy<LottoTest> createLottoStrategy,
                                      ReadListStrategy<LottoTest> readLottoListByUserId,
                                      ReadListStrategy<LottoTest> readLottoListByDraw) {
        this.createLottoStrategy = createLottoStrategy;
        this.readLottoListByUserId = readLottoListByUserId;
        this.readLottoListByDraw = readLottoListByDraw;
    }

    public static LottoNumberRepositoryImpl getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Repository is not initialized");
        }
        return instance;
    }

    public static void initialize(CreateStrategy<LottoTest> createLottoStrategy,
                                  ReadListStrategy<LottoTest> readLottoListByUserId,
                                  ReadListStrategy<LottoTest> readByDraw) {
        if (instance == null) {
            synchronized (MemberRepositoryImpl.class) {
                if (instance == null) {
                    instance = new LottoNumberRepositoryImpl(
                            createLottoStrategy,
                            readLottoListByUserId,
                            readByDraw
                    );
                }
            }
        } else {
            throw new IllegalStateException("Repository is already initialized");
        }
    }


    @Override
    public LottoTest add(LottoTest lotto) {
        return createLottoStrategy.execute(lotto);
    }

    @Override
    public List<LottoTest> findLottoNumbersByMember(String memberId) {
        return readLottoListByUserId.execute(memberId);
    }

    @Override
    public List<LottoTest> findLottoNumbersByDraw(String drawId) {
        return readLottoListByDraw.execute(drawId);
    }

    @Override
    public List<LottoTest> findAll() {
        String sql = "SELECT * FROM member";
        List<LottoTest> lotteries = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(
                ConnectionConst.URL,
                ConnectionConst.USERNAME,
                ConnectionConst.PASSWORD);

             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery();
             ){

            while (rs.next()) {
                lotteries.add(mapToLotto(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lotteries;
    }

    private LottoTest mapToLotto(ResultSet rs) throws SQLException {
        return LottoTest.of(rs.getLong("id"),
                rs.getInt("draw"),
                rs.getString("user_id"),
                ListParser.createNumbersFrom(rs.getString("numbers"))
        ).createdAt(TimeStampParser.result(rs.getTimestamp("created_at")));
    }
}
