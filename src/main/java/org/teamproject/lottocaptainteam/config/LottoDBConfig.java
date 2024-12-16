package org.teamproject.lottocaptainteam.config;

import org.teamproject.lottocaptainteam.connection.DBConnectionUtil;
import org.teamproject.lottocaptainteam.domain.LottoTest;
import org.teamproject.lottocaptainteam.repository.create.CreateLottoNumberStrategyImpl;
import org.teamproject.lottocaptainteam.repository.create.CreateStrategy;
import org.teamproject.lottocaptainteam.repository.read.LottoNumberSearchByDrawStrategyImpl;
import org.teamproject.lottocaptainteam.repository.read.LottoNumberSearchByUserIdStrategyImpl;
import org.teamproject.lottocaptainteam.repository.read.ReadListStrategy;

public class LottoDBConfig {

    private final DBConnectionUtil connection;

    public LottoDBConfig(DBConnectionUtil connection) {
        this.connection = connection;
    }

    public CreateStrategy<LottoTest> lotttoCreateStrategy() {
        return new CreateLottoNumberStrategyImpl(connection);
    }

    public ReadListStrategy<LottoTest> readLottoListByUserIdStrategy() {
        return new LottoNumberSearchByUserIdStrategyImpl(connection);
    }

    public ReadListStrategy<LottoTest> readLottoListByDraw() {
        return new LottoNumberSearchByDrawStrategyImpl(connection);
    }
}
