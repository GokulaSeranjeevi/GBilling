package com.skywings.gbilling.common;

import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.JSpinner.DateEditor;
import javax.swing.JSpinner.DefaultEditor;

public class skywingsSpinner extends JSpinner {
   private static final long serialVersionUID = 1L;
   private SpinnerDateModel spinnerDateModel;
   private DateEditor jDateEdtitor;
   private String dateFormatString = "dd-MMM-yyyy";
   private String timeFormatString = "hh:mm:ss a";
   private SimpleDateFormat simpleDateFormat;
   private SpinnerEnum spinnerEnum;

   public skywingsSpinner(SpinnerEnum spinnerEnum) {
      this.spinnerEnum = SpinnerEnum.DATE;
      this.spinnerEnum = spinnerEnum;
      this.spinnerDateModel = new SpinnerDateModel();
      this.setModel(this.spinnerDateModel);
      this.dateFormatString = this.spinnerEnum == SpinnerEnum.DATE ? this.dateFormatString : this.timeFormatString;
      this.jDateEdtitor = new DateEditor(this, this.dateFormatString);
      this.setEditor(this.jDateEdtitor);
      this.setValue((Object)(new Date()));
   }

   public skywingsSpinner() {
      this.spinnerEnum = SpinnerEnum.DATE;
      this.spinnerDateModel = new SpinnerDateModel();
      this.setModel(this.spinnerDateModel);
      this.jDateEdtitor = new DateEditor(this, this.dateFormatString);
      this.setEditor(this.jDateEdtitor);
      this.setValue((Object)(new Date()));
   }

   public skywingsSpinner(String formatString) {
      this.spinnerEnum = SpinnerEnum.DATE;
      this.spinnerDateModel = new SpinnerDateModel();
      this.setModel(this.spinnerDateModel);
      this.dateFormatString = formatString;
      this.jDateEdtitor = new DateEditor(this, this.dateFormatString);
      this.setEditor(this.jDateEdtitor);
      this.setValue((Object)(new Date()));
   }

   public skywingsSpinner(SpinnerModel arg0) {
      super(arg0);
      this.spinnerEnum = SpinnerEnum.DATE;
      this.jDateEdtitor = new DateEditor(this, this.dateFormatString);
      this.setEditor(this.jDateEdtitor);
      this.setValue((Object)(new Date()));
   }

   public skywingsSpinner(SpinnerModel arg0, String formatString) {
      super(arg0);
      this.spinnerEnum = SpinnerEnum.DATE;
      this.dateFormatString = formatString;
      this.jDateEdtitor = new DateEditor(this, this.dateFormatString);
      this.setEditor(this.jDateEdtitor);
      this.setValue((Object)(new Date()));
   }

   public void setValue(Object value) {
      super.setValue(value);
   }

   public void setValue(String date) throws ParseException {
      if (this.spinnerEnum == SpinnerEnum.DATE) {
         this.simpleDateFormat = new SimpleDateFormat(this.dateFormatString);
      } else {
         this.simpleDateFormat = new SimpleDateFormat(this.timeFormatString);
      }

      this.setValue((Object)this.simpleDateFormat.parse(date));
   }

   public Object getValue() {
      return super.getValue();
   }

   public String getDateValue() {
      this.simpleDateFormat = new SimpleDateFormat(this.dateFormatString);
      return this.simpleDateFormat.format(super.getValue());
   }

   public String getTimeValue() {
      this.simpleDateFormat = new SimpleDateFormat(this.timeFormatString);
      return this.simpleDateFormat.format(super.getValue());
   }

   public String getDateValue(String formatString) {
      this.simpleDateFormat = new SimpleDateFormat(formatString);
      return this.simpleDateFormat.format(super.getValue());
   }

   public String getTimeValue(String formatString) {
      this.simpleDateFormat = new SimpleDateFormat(formatString);
      return this.simpleDateFormat.format(super.getValue());
   }

   public String getDateFormatString() {
      return this.dateFormatString;
   }

   public String getTimeFormatString() {
      return this.timeFormatString;
   }

   public void setToolTipText(String text) {
      super.setToolTipText(text);
      ((DefaultEditor)this.getEditor()).getTextField().setToolTipText(text);
   }

   public void requestFocus() {
      super.requestFocus();
      ((DefaultEditor)this.getEditor()).getTextField().requestFocus();
   }

   public synchronized void addKeyListener(KeyListener l) {
      super.addKeyListener(l);
      ((DefaultEditor)this.getEditor()).getTextField().addKeyListener(l);
   }

   public synchronized void addFocusListener(FocusListener arg0) {
      super.addFocusListener(arg0);
      ((DefaultEditor)this.getEditor()).getTextField().addFocusListener(arg0);
   }

   public boolean requestFocusInWindow() {
      super.requestFocus();
      ((DefaultEditor)this.getEditor()).getTextField().requestFocus();
      return true;
   }
}
