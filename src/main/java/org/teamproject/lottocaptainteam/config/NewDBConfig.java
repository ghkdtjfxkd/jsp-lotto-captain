package org.teamproject.lottocaptainteam.config;

import java.io.IOException;
import org.teamproject.lottocaptainteam.connection.DBConnectionUtil;

public class NewDBConfig {

    private final MemberDBConfig memberDBConfig;
    private final LottoDBConfig lottoDBConfig;

    public NewDBConfig() {
        DBConnectionUtil dbConnectionUtil = new DBConnectionUtil();
        memberDBConfig = new MemberDBConfig(dbConnectionUtil);
        lottoDBConfig = new LottoDBConfig(dbConnectionUtil);
    }

    public MemberDBConfig loadMemberDBConfig() {
        return memberDBConfig;
    }

    public LottoDBConfig loadLottoDBConfig() throws IOException {
        return lottoDBConfig;
    }
}
