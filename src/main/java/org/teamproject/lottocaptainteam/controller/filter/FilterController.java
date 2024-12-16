package org.teamproject.lottocaptainteam.controller.filter;

import java.util.Map;
import org.teamproject.lottocaptainteam.controller.ModelView;

public interface FilterController {
    ModelView process(Map<String, String> paramMap);
}

