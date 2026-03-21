package io.github.huanyv.melon.sql.utils;

import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;

/**
 * 动态SQL构造器，需要调用getSql()、getArgs()方法获取SQL语句和参数
 *
 * <pre>{@code
 *         SqlBuilder sb = new SqlBuilder("select * from t1");
 *         sb.trim("where", "", "and|or", "", builder -> {
 *             builder.append(SqlBuilder.hasText(name), "  name = ?", name);
 *             builder.append(SqlBuilder.hasText(age), "  and age = ?", age);
 *         });
 *         System.out.println(sb);
 *
 *         sb = new SqlBuilder("update t1");
 *         sb.trim("set", "", "", ",", builder -> {
 *             builder.append(SqlBuilder.hasText(name), " name = ?,", name);
 *             builder.append(SqlBuilder.hasText(age), "  age = ?,  ", age);
 *         }).append("where id = ?", "id");
 *         System.out.println(sb);
 *
 *         sb = new SqlBuilder("insert into t1");
 *         sb.trim("(", ")", "", ",", builder -> {
 *             builder.append(SqlBuilder.hasText(name), " name,");
 *             builder.append(SqlBuilder.hasText(age), "  age,  ");
 *         }).trim("values(", ")", "", ",", builder -> {
 *             builder.append(SqlBuilder.hasText(name), " ?,", name);
 *             builder.append(SqlBuilder.hasText(age), "  ?,  ", age);
 *         });
 *         System.out.println(sb);
 * }</pre>
 *
 * @author huanyv
 */
public class SqlBuilder implements Serializable {

    private static final long serialVersionUID = -6781070557668986751L;

    private final StringBuilder sql;

    private final List<Object> args;

    public SqlBuilder() {
        this.sql = new StringBuilder();
        this.args = new ArrayList<>();
    }

    public SqlBuilder(String str) {
        this();
        this.sql.append(str.trim());
    }

    /**
     * 追加SQL，会自动拼接空格符
     *
     * @param str  SQL字符串
     * @param args SQL参数
     * @return this
     */
    public SqlBuilder append(String str, Object... args) {
        if (str == null || str.isEmpty()) {
            return this;
        }
        if (this.sql.length() != 0) {
            this.sql.append(" ");
        }
        this.sql.append(str.trim());
        if (args == null) {
            args = new Object[]{null};
        }
        this.args.addAll(Arrays.asList(args));
        return this;
    }

    /**
     * 根据条件追加
     *
     * @param condition 条件
     * @param str       SQL字符串
     * @param args      SQL参数
     * @return this
     */
    public SqlBuilder append(boolean condition, String str, Object... args) {
        if (condition) {
            append(str, args);
        }
        return this;
    }

    public SqlBuilder where(Consumer<SqlBuilder> builder) {
        return trim("where", "", "and |or |AND |OR ", "", builder);
    }

    public SqlBuilder set(Consumer<SqlBuilder> builder) {
        return trim("set", "", "", ",", builder);
    }

    /**
     * 自定义拼接SQL字符串，以及前缀、后缀，以及移除多余的前缀或后缀。
     *
     * @param prefix          内容不为空，则在内容前面添加指定的字符串（例如 WHERE, SET, ( 等）
     * @param suffix          内容不为空，则在内容后面添加指定的字符串（例如 ), ; 等）
     * @param prefixOverrides 内容的开头匹配指定的字符串，则将其移除（常用于移除多余的 AND, OR）
     * @param suffixOverrides 内容的结尾匹配指定的字符串，则将其移除（常用于移除多余的逗号 ,）
     * @param builder         回调函数，参数是新的{@link SqlBuilder}
     * @return this
     */
    public SqlBuilder trim(String prefix, String suffix, String prefixOverrides, String suffixOverrides, Consumer<SqlBuilder> builder) {
        prefix = prefix == null ? "" : prefix;
        suffix = suffix == null ? "" : suffix;
        prefixOverrides = prefixOverrides == null ? "" : prefixOverrides;
        suffixOverrides = suffixOverrides == null ? "" : suffixOverrides;
        SqlBuilder sqlBuilder = new SqlBuilder();
        builder.accept(sqlBuilder);
        String subSql = sqlBuilder.getSql();
        if (!subSql.isEmpty()) {
            append(prefix);
            String[] preList = prefixOverrides.isEmpty() ? new String[0] : prefixOverrides.split("\\|");
            String[] sufList = suffixOverrides.isEmpty() ? new String[0] : suffixOverrides.split("\\|");
            subSql = subSql.trim();
            for (String pre : preList) {
                if (subSql.startsWith(pre)) {
                    subSql = subSql.substring(pre.length()).trim();
                    break;
                }
            }
            for (String suf : sufList) {
                if (subSql.endsWith(suf)) {
                    subSql = subSql.substring(0, subSql.length() - suf.length()).trim();
                    break;
                }
            }
            append(subSql, sqlBuilder.getArgs());
            append(suffix);
        }
        return this;
    }

    public <T> SqlBuilder foreach(Collection<T> collection, TiConsumer<SqlBuilder, T, Integer> func) {
        return foreach("", "", "", collection, func);
    }

    public <T> SqlBuilder foreach(String separator, Collection<T> collection, TiConsumer<SqlBuilder, T, Integer> func) {
        return foreach("", "", separator, collection, func);
    }

    /**
     * 集合遍历的核心元素，主要用于构建IN条件语句或批量插入/更新操作。它允许你遍历一个集合（如 List, Set），并将集合中的每个元素拼接到SQL语句中。
     * <pre>{@code
     * Person p1 = new Person("id1", "zhangsan", 18, "111");
     * Person p2 = new Person("id2", "lisi", null, "222");
     * Person p3 = new Person("id3", "wangwu", 17, "333");
     * List<String> names = new ArrayList<>(Arrays.asList("zhang", "li", "wang"));
     * List<Person> personList = new ArrayList<>(Arrays.asList(p1, p2, p3));
     *
     * SqlBuilder sb = new SqlBuilder();
     * sb = new SqlBuilder("select * from t1");
     * sb.trim("where", "", "and|or", "", builder -> {
     * 	builder.append(SqlBuilder.nonNull(p1.getAge()), "age > ?", p1.getAge());
     * 	builder.foreach("or name in (", ")", ",", names, (nameIn, name, index) -> {
     * 		nameIn.append(SqlBuilder.hasText(name), "?", name);
     *    });
     * });
     * System.out.println(sb);
     *
     * sb = new SqlBuilder("insert into t1");
     * sb.trim("(", ")", "", ",", builder -> {
     * 	builder.append("id,");
     * 	builder.append(" name,");
     * 	builder.append("  age,  ");
     * });
     * sb.append("values");
     * sb.foreach("", "", ",", personList, (builder, person, index) -> {
     * 	builder.append("(?,?,?)", person.getId(), person.getName(), person.getAge());
     * });
     * System.out.println(sb);
     * }</pre>
     *
     * @param open       开始前缀
     * @param close      结束后缀
     * @param separator  分隔符
     * @param collection 集合
     * @param func       回调函数
     * @param <T>        集合元素类型
     * @return this
     */
    public <T> SqlBuilder foreach(String open, String close, String separator, Collection<T> collection, TiConsumer<SqlBuilder, T, Integer> func) {
        open = open == null ? "" : open;
        close = close == null ? "" : close;
        separator = separator == null ? "" : separator;
        collection = collection == null ? Collections.emptyList() : collection;
        StringJoiner joiner = new StringJoiner(separator);
        List<Object> params = new ArrayList<>();
        int index = 0;
        for (T item : collection) {
            SqlBuilder builder = new SqlBuilder();
            func.accept(builder, item, index);
            if (!builder.isEmpty()) {
                joiner.add(builder.getSql());
                Collections.addAll(params, builder.getArgs());
            }
            index++;
        }
        if (joiner.length() != 0) {
            append(open);
            append(joiner.toString(), params.toArray());
            append(close);
        }
        return this;
    }

    public boolean isEmpty() {
        return this.sql.length() == 0;
    }

    public String getSql() {
        return this.sql.toString();
    }

    public Object[] getArgs() {
        return this.args.toArray();
    }

    @Override
    public String toString() {
        return "Sql:\t" + getSql() + "\nArgs:\t" + Arrays.toString(getArgs()) + "\n==============================";
    }

    public static boolean nonNull(Object o) {
        return o != null;
    }

    public static boolean hasText(String str) {
        return (str != null && !str.isEmpty() && containsText(str));
    }

    private static boolean containsText(CharSequence str) {
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    @FunctionalInterface
    public interface TiConsumer<T, K, V> {
        void accept(T t, K k, V v);
    }
}
