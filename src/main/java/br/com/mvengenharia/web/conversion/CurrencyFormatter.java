package br.com.mvengenharia.web.conversion;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;


public class CurrencyFormatter implements Formatter<BigDecimal> {

   //@Autowired
   // private MessageSource messageSource;


    public CurrencyFormatter() {
        super();
    }

    public BigDecimal parse(final String text, final Locale locale) throws ParseException {
        final NumberFormat format = NumberFormat.getNumberInstance(locale);
        if (format instanceof DecimalFormat) {
            ((DecimalFormat) format).setParseBigDecimal(true);
        }
        return (BigDecimal) format.parse(text.replaceAll("[^\\d.,]",""));
    }

    public String print(final BigDecimal object, final Locale locale) {
    	NumberFormat money = NumberFormat.getCurrencyInstance(locale);
    	money.setMinimumFractionDigits(2);
    	money.setMaximumFractionDigits(2);
    	return money.format(object.doubleValue()); 
    }   

}
