package com.sismics.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for HTML to plain-text conversion.
 */
public class TestHtmlToPlainText {
    @Test
    public void testPlainTextConversion() {
        String html = "<html><body><p>Hello <a href=\"https://example.com\">link</a></p>"
                + "<ul><li>Item 1</li><li>Item 2</li></ul>"
                + "<dl><dt>Term</dt><dd>Definition</dd></dl>"
                + "<p>Line<br>Break</p>"
                + "</body></html>";

        Element body = Jsoup.parse(html).body();
        String text = new HtmlToPlainText().getPlainText(body);

        Assert.assertTrue(text.contains("link <https://example.com>"));
        Assert.assertTrue(text.contains("\n * Item 1"));
        Assert.assertTrue(text.contains("\n * Item 2"));
        Assert.assertTrue(text.contains("Term"));
        Assert.assertTrue(text.contains("Definition"));
        Assert.assertTrue(text.contains("Line"));
        Assert.assertTrue(text.contains("Break"));
    }
}
