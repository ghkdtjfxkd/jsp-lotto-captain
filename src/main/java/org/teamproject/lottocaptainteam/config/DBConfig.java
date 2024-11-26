package org.teamproject.lottocaptainteam.config;

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

public class DBConfig {

    private final DBConnectionUtil dbConnectionUtil;

    public DBConfig() {
        this.dbConnectionUtil = new DBConnectionUtil();
    }

    public MemberSignupStrategy memberSignupStrategy() {
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
