package com.sismics.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for environment utilities.
 */
public class TestEnvironmentUtil {
    @Test
    public void testWebappContextToggle() {
        boolean original = EnvironmentUtil.isWebappContext();
        try {
            EnvironmentUtil.setWebappContext(true);
            Assert.assertTrue(EnvironmentUtil.isWebappContext());
            Assert.assertFalse(EnvironmentUtil.isUnitTest());

            EnvironmentUtil.setWebappContext(false);
            Assert.assertFalse(EnvironmentUtil.isWebappContext());
            Assert.assertTrue(EnvironmentUtil.isUnitTest());
        } finally {
            EnvironmentUtil.setWebappContext(original);
        }
    }

    @Test
    public void testTeedyHomeMatchesSystemProperty() {
        Assert.assertEquals(System.getProperty("docs.home"), EnvironmentUtil.getTeedyHome());
    }
}
