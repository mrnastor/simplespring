/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javahris.springmvc.converter;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.joda.time.LocalDate;
import org.springframework.web.context.request.WebRequest;


/**
 *
 * @author jem
 */
public class DatePropertyEditor extends PropertyEditorSupport {
    private WebRequest webRequest;

    public DatePropertyEditor(WebRequest webRequest) {
        this.webRequest = webRequest;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Map<String, String[]> parameterMap = webRequest.getParameterMap();

        Integer year = Integer.valueOf(parameterMap.get("birthYear")[0]);
        Integer month = Integer.valueOf(parameterMap.get("birthMonth")[0]);
        Integer day = Integer.valueOf(parameterMap.get("birthDay")[0]);

        try {
            String value = String.format("%1$d-%2$d-%3$d", year, month, day);
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(value);
//            setValue(new SimpleDateFormat("yyyy-MM-dd").parse(value));
            setValue(LocalDate.fromDateFields(date));
        } catch (ParseException ex) {
            setValue(null);
        }
    }
}
