package ru.diasoft;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CheckSessions {
    private int sessionsCount;
}