package com.sismics.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

/**
 * Tests for message utilities.
 */
public class TestMessageUtil {
    @Test
    public void testExistingMessageFormat() {
        String message = MessageUtil.getMessage(Locale.ENGLISH, "email.template.password_recovery.hello", "Jane");
        Assert.assertEquals("Hello Jane.", message);
    }

    @Test
    public void testMissingMessageKey() {
        String message = MessageUtil.getMessage(Locale.ENGLISH, "missing.key", "Jane");
        Assert.assertEquals("**missing.key**", message);
    }

    @Test
    public void testGetMessageBundle() {
        Assert.assertNotNull(MessageUtil.getMessage(Locale.ENGLISH));
    }
}
