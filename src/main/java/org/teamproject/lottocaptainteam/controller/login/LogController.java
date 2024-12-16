package org.teamproject.lottocaptainteam.controller.login;

import java.util.Map;
import org.teamproject.lottocaptainteam.controller.ModelView;

@FunctionalInterface
public interface LogController {
    ModelView process(Map<String, Object> paramMap);
}
