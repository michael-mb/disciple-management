package disciple.online.portal.util;

import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

public final class PropertiesHandler {
    private PropertiesHandler(){}

    public static String getActiveSpringProfileValue(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:application");
        messageSource.setDefaultEncoding("UTF-8");
        String profile = messageSource.getMessage("spring.profiles.active", null, LocaleContextHolder.getLocale());
        return messageSource.getMessage("spring.profiles.active", null, LocaleContextHolder.getLocale());
    }

    /**
     * Method similar to the method in LanguageService
     * if the following is given:   mail.moderator.ticketreopend.content=hat soeben das {0} {1} wieder ge\u00F6ffnet. Folgende Begr\u00FCndung wurde angegeben:
     * then getValue("mail.moderator.ticketreopend.content") returns:     "hat soeben das "
     */
    public static String getI18nMessageValue(final String key) {

        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");

        if (key == null) throw new NullPointerException("Key must not be null.");
        if (key.isEmpty()) throw new IllegalArgumentException("Key must not be empty.");

        String back;
        try {
            back = messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            return "Message for key '" + key + "' not found.";
        }
        String[] parts = back.split("\\{[0-9]+}");

        return parts[0];
    }

    /**
     * Method similar to the method in LanguageService
     * if the following is given:   mail.moderator.ticketreopend.content=hat soeben das {0} {1} wieder ge\u00F6ffnet. Folgende Begr\u00FCndung wurde angegeben:
     * then getValue("mail.moderator.ticketreopend.content", 0) returns:     "hat soeben das "
     * and  getValue("mail.moderator.ticketreopend.content", 1) returns:     " "
     * and  getValue("mail.moderator.ticketreopend.content", 2) returns:     " wieder ge\u00F6ffnet. Folgende Begr\u00FCndung wurde angegeben:"
     */
    public static String getI18nMessageValue(final String key, final int partOfString) {

        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");

        if (key == null) throw new NullPointerException("Key must not be null.");
        if (key.isEmpty()) throw new IllegalArgumentException("Key must not be empty.");
        String back;
        try {
//            back = messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
            back = messageSource.getMessage(key, null, Locale.GERMAN);
        } catch (NoSuchMessageException e) {
            throw new IllegalArgumentException("Message for key '" + key + "' not found.");
        }
        String[] parts = back.split("\\{[0-9]+}");
        try {
            return parts[partOfString];
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Tried to access part " + partOfString + " but the message has only " + parts.length + " parts");
        }
    }
}
