package com.sismics.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

/**
 * Test of locale utilities.
 *
 * @author jtremeaux
 */
public class TestLocaleUtil {
    @Test
    public void testDefaultLocaleForEmptyValues() {
        Assert.assertEquals(Locale.ENGLISH, LocaleUtil.getLocale(null));
        Assert.assertEquals(Locale.ENGLISH, LocaleUtil.getLocale(""));
    }

    @Test
    public void testLanguageOnlyLocale() {
        Locale locale = LocaleUtil.getLocale("fr");
        Assert.assertEquals("fr", locale.getLanguage());
        Assert.assertEquals("", locale.getCountry());
        Assert.assertEquals("", locale.getVariant());
    }

    @Test
    public void testLanguageAndCountryLocale() {
        Assert.assertEquals(new Locale("fr", "FR", ""), LocaleUtil.getLocale("fr_FR"));
    }

    @Test
    public void testLanguageCountryVariantLocale() {
        Assert.assertEquals(new Locale("fr", "FR", "MAC"), LocaleUtil.getLocale("fr_FR_MAC"));
    }

    @Test
    public void testEmptyCountryWithVariantLocale() {
        Locale locale = LocaleUtil.getLocale("en__POSIX");
        Assert.assertEquals("en", locale.getLanguage());
        Assert.assertEquals("", locale.getCountry());
        Assert.assertEquals("POSIX", locale.getVariant());
    }
}
