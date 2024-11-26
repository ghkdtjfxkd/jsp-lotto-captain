package org.teamproject.lottocaptainteam.controller.member;

import java.sql.SQLException;
import java.util.Map;
import org.teamproject.lottocaptainteam.controller.ModelView;

public interface MemberController {
    ModelView process(Map<String, String> paramMap);
}
