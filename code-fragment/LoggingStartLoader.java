package top.huanyv.start.loader.logging;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;
import top.huanyv.bean.ioc.ApplicationContext;
import top.huanyv.start.config.AppArguments;
import top.huanyv.start.loader.ApplicationLoader;
import top.huanyv.tools.utils.StringUtil;

/**
 * @author huanyv
 * @date 2023/2/11 15:09
 */
public class LoggingStartLoader implements ApplicationLoader {

    private static final String PREFIX = "harbour.log.level.";

    @Override
    public void load(ApplicationContext applicationContext, AppArguments appArguments) {
        String levelConfigName = getLevelConfigName(appArguments);
        if (!StringUtil.hasText(levelConfigName)) {
            return;
        }
        String loggerName = StringUtil.removePrefix(levelConfigName, PREFIX);
        String levelValue = appArguments.get(levelConfigName);
        ILoggerFactory loggerFactory = LoggerFactory.getILoggerFactory();
        if (loggerFactory instanceof  LoggerContext) {
            LoggerContext loggerContext = (LoggerContext) loggerFactory;
            Logger logger = loggerContext.getLogger(loggerName);
            logger.setLevel(Level.toLevel(levelValue));
        }
    }

    private String getLevelConfigName(AppArguments appArguments) {
        for (String name : appArguments.getNames()) {
            if (name.startsWith(PREFIX)) {
                return name;
            }
        }
        return null;
    }
}
