package org.teamproject.lottocaptainteam.parser;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TimeStampParser {

    public static LocalDateTime result(Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }
}
