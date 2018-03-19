package org.abomb4.gdemo.utils;

import java.util.Optional;

/**
 * 字符串模板类。
 *
 * @author abomb4
 */
public class StringTemplateUtils {

    /**
     * 解析字符串模板。模板格式与slf4j相同。
     * <p>
     * 示例：
     * <pre>
     *     StringTemplateUtils.resolve("{} + {} = ?", 1); -> "1 + {} = ?"
     *     StringTemplateUtils.resolve("{} + {} = ?", 1, 2); -> "1 + 2 = ?"
     *     StringTemplateUtils.resolve("{} + {} = ?", 1, 2, 3); -> "1 + 2 = ?"
     *     StringTemplateUtils.resolve("{} + { } = ?", 1, 2, 3); -> "1 +   = ?"
     * </pre>
     * </p>
     *
     * @param template 模板
     * @param values   填充物
     * @return 填充过后的模板
     */
    public static String resolve(String template, Object... values) {
        final StringBuilder builder = new StringBuilder(template.length() + 36);
        final String[] strs = template.split("\\{}", values.length + 1);

        int currentValue = 0;
        for (String str : strs) {
            builder.append(str);
            if (currentValue < values.length && currentValue < strs.length - 1) {
                final Object value = Optional.ofNullable(values[currentValue]).orElse("");
                final String valueStr = value.toString();
                builder.append(valueStr);
                currentValue++;
            }
        }

        return builder.toString();
    }

    /**
     * 字符串模板类的缩写
     */
    public static class St {

        /**
         * StringTemplateUtils.resolve方法的缩写。
         *
         * @param template 模板
         * @param values   填充物
         * @return 填充过后的模板
         * @see StringTemplateUtils#resolve(String, Object...)
         */
        public static String r(String template, Object... values) {
            return resolve(template, values);
        }
    }
}
