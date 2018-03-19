package org.abomb4.gdemo.utils;

import org.junit.Test;

import static org.abomb4.gdemo.utils.StringTemplateUtils.St.r;
import static org.junit.Assert.assertEquals;

/**
 * 测试以下St类
 *
 * @author abomb4
 */
public class TestStringTemplateUtils {

    @Test
    public void testResolve() {

        assertEquals("1 + {} = ?", r("{} + {} = ?", 1));
        assertEquals("1 + 2 = ?", r("{} + {} = ?", 1, 2));
        assertEquals("1 + 2 = ?", r("{} + {} = ?", 1, 2, 3));
        assertEquals("1 + { } = ?", r("{} + { } = ?", 1, 2, 3));
    }
}
