package org.jgmp.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;

@Service
public class LoginAttemptService {

    public static final int MAX_ATTEMPT = 3;
    public static final int BLOCK_MINUTES = 3;
    private Cache<String, Integer> attemptsCache;

    @Autowired
    private HttpServletRequest request;

    public LoginAttemptService() {
        super();
        attemptsCache = CacheBuilder.newBuilder().expireAfterWrite(BLOCK_MINUTES,
                TimeUnit.MINUTES).build(new CacheLoader<String, Integer>() {
            @Override
            public Integer load(final String key) {
                return 0;
            }
        });
    }

    public void loginFailed(final String key) {
        int attempts;
        try {
            attempts = attemptsCache.get(key);
        } catch (final ExecutionException e) {
            attempts = 0;
        }
        attempts++;
        attemptsCache.asMap().put(key, attempts);
    }

    public List<String> getBlockedList() {
        List<String> res = new ArrayList<>();
        attemptsCache.asMap().forEach((key, value) -> {
            if (value >= MAX_ATTEMPT) {
                res.add(key);
            }
        });
        return res;
    }

    public boolean isBlocked(String userName) {
        try {
            return attemptsCache.get(userName) >= MAX_ATTEMPT;
        } catch (final ExecutionException e) {
            return false;
        }
    }

    private String getClientIP() {
        final String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader != null) {
            return xfHeader.split(",")[0];
        }
        return request.getRemoteAddr();
    }
}