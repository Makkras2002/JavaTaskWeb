package com.makkras.shop.controller.tag;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.SkipPageException;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

import java.util.Locale;


public class LocaleNameFormatterTag extends SimpleTagSupport {

    private String initialLocaleName;

    public LocaleNameFormatterTag() {
    }

    public void setInitialLocaleName(String initialLocaleName) {
        this.initialLocaleName = initialLocaleName;
    }

    @Override
    public void doTag() throws JspException {
        try {
            String formattedLocaleName = initialLocaleName.replaceAll("ru_","");
            formattedLocaleName = formattedLocaleName.replaceAll("en_","");
            getJspContext().getOut().write(formattedLocaleName);
        } catch (Exception e) {
            throw new SkipPageException(e.getMessage());
        }
    }
}
