package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {
    private static final String LOG_FILE_PATH = "logs/application.log";
    private static final String JSON_FILE_PATH = "user_profiles.json";

    // REGEX pattern to match the log format
    private static final Pattern LOG_PATTERN = Pattern.compile("(\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}\\+\\d{2}:\\d{2})\\s+INFO\\s+\\d+\\s+---\\s+\\[demo\\]\\s+\\[\\S+\\]\\s+(\\S+)\\s+:\\s+Operation=(\\w+),\\s+Entity=(\\w+),\\s+Action=(\\w+)(?:,\\s+ProductId=(\\d+))?,\\s+UserId=(\\d+)");

    public static void main(String[] args) {
        List<UserProfile> profiles = parseLogs();
        saveProfilesToJson(profiles);
    }

    private static List<UserProfile> parseLogs() {
        Map<Long, UserProfile> userProfiles = new HashMap<>();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(LOG_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                LPS lps = parseLine(line);
                if (lps != null) {
                    Long userId = lps.getUserId();
                    UserProfile profile = userProfiles.computeIfAbsent(userId, UserProfile::new);
                    profile.incrementOperationCount(lps.getOperation());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(userProfiles.values());
    }

    private static LPS parseLine(String line) {
        Matcher matcher = LOG_PATTERN.matcher(line);
        if (matcher.find()) {
            return new LPS.Builder()
                .setTimestamp(LocalDateTime.parse(matcher.group(1), DateTimeFormatter.ISO_DATE_TIME))
                .setComponent(matcher.group(2))
                .setOperation(matcher.group(3))
                .setEntity(matcher.group(4))
                .setAction(matcher.group(5))
                .setProductId(matcher.group(6) != null ? Long.parseLong(matcher.group(6)) : null)
                .setUserId(Long.parseLong(matcher.group(7)))
                .build();
        }
        return null;
    }

    private static void saveProfilesToJson(List<UserProfile> profiles) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(Paths.get(JSON_FILE_PATH).toFile(), profiles);
            System.out.println("Profiles saved to JSON.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}