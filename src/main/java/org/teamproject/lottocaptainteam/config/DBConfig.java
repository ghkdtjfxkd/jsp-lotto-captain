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
import org.teamproject.lottocaptainteam.service.admin.AdminPermissionService;
import org.teamproject.lottocaptainteam.service.admin.AdminPermissionServiceImpl;

public class DBConfig {

    private final DBConnectionUtil dbConnectionUtil;

    public DBConfig() {
        this.dbConnectionUtil = new DBConnectionUtil();
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

    private AdminPermissionService adminPermissionService() throws IOException {
        return new AdminPermissionServiceImpl(ADMIN_LIST);
    }

    public Connection getConnection() {
        return dbConnectionUtil.getConnection();
    }
}
