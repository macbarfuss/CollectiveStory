package de.macbarfuss.collectivestory.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.format.Formatter;

/**
 * Formatter for Date values.
 * 
 * @author geoema
 * 
 */
public final class DateFormatter implements Formatter<Date> {

    private static final Logger LOG = LoggerFactory.getLogger(DateFormatter.class);

    private final MessageSource messageSource;

    public DateFormatter(final MessageSource theMessageSource) {
        super();
        LOG.debug("Initializing new DateFormatter");
        messageSource = theMessageSource;
    }

    public Date parse(final String text, final Locale locale) throws ParseException {
        LOG.debug("Parsing: String => Date");
        final SimpleDateFormat dateFormat = createDateFormat(locale);
        return dateFormat.parse(text);
    }

    public String print(final Date object, final Locale locale) {
        LOG.debug("Parsing: Date => String");
        final SimpleDateFormat dateFormat = createDateFormat(locale);
        return dateFormat.format(object);
    }

    private SimpleDateFormat createDateFormat(final Locale locale) {
        LOG.debug("Create date format for locale " + locale.toString());
        final String format = messageSource.getMessage("date.format.config", null, locale);
        final SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setLenient(false);
        return dateFormat;
    }

}
