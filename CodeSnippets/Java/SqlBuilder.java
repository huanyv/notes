
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
 *         }).append("where id = ?", "ididid123");
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

    public SqlBuilder append(String str, Object... args) {
        if (str == null || str.length() == 0) {
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

    public SqlBuilder append(boolean condition, String str, Object... args) {
        if (condition) {
            append(str, args);
        }
        return this;
    }

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
        return "Sql:\t" + getSql() + "\nArgs:\t" + Arrays.toString(getArgs()) + "\n===========================";
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


}