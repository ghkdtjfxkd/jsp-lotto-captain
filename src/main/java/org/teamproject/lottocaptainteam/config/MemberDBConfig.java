package org.teamproject.lottocaptainteam.config;

import static org.teamproject.lottocaptainteam.service.constant.FilePath.ADMIN_LIST;

import java.io.IOException;
import java.sql.Connection;
import org.teamproject.lottocaptainteam.connection.DBConnectionUtil;
import org.teamproject.lottocaptainteam.repository.delete.MemberDeleteStrategy;
import org.teamproject.lottocaptainteam.repository.delete.MemberDeleteStrategyImpl;
import org.teamproject.lottocaptainteam.repository.search.MemberSearchStrategy;
import org.teamproject.lottocaptainteam.repository.search.MemberSearchStrategyImpl;
import org.teamproject.lottocaptainteam.repository.signup.MemberSignupStrategy;
import org.teamproject.lottocaptainteam.repository.signup.MemberSignupStrategyImpl;
import org.teamproject.lottocaptainteam.repository.update.MemberUpdateStrategy;
import org.teamproject.lottocaptainteam.repository.update.MemberUpdateStrategyImpl;

public class MemberDBConfig {

    private final DBConnectionUtil dbConnectionUtil;

    public MemberDBConfig(DBConnectionUtil dbConnectionUtil) {
        this.dbConnectionUtil = dbConnectionUtil;
    }

    public MemberSignupStrategy memberSignupStrategy() throws IOException {
        return new MemberSignupStrategyImpl(dbConnectionUtil);
    }

    public MemberSearchStrategy memberSearchStrategy() {
        return new MemberSearchStrategyImpl(dbConnectionUtil);
    }

    public MemberUpdateStrategy memberUpdateStrategy() {
        return new MemberUpdateStrategyImpl(dbConnectionUtil);
    }

    public MemberDeleteStrategy memberDeleteStrategy() {
        return new MemberDeleteStrategyImpl(dbConnectionUtil);
    }

    public Connection getConnection() {
        return dbConnectionUtil.getConnection();
    }
}
