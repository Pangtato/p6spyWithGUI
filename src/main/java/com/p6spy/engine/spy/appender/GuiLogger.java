package com.p6spy.engine.spy.appender;

import com.p6spy.engine.gui.UI;
import com.p6spy.engine.logging.Category;

/**
 * @ProjectName: GuiLogger
 * @Description: GUI界面打印日志
 * @Author: pangjx
 * @Date: 2021/4/22 10:57
 */
public class GuiLogger implements P6Logger {
  @Override
  public void logSQL(int connectionId, String now, long elapsed, Category category, String prepared, String sql) {
    UI.getInstance().writeToPanel("/*******logSQL********/",sql);
  }

  @Override
  public void logException(Exception e) {
    UI.getInstance().writeToPanel("/*******logException********/",e.getMessage());
  }

  @Override
  public void logText(String text) {
    UI.getInstance().writeToPanel("/*******logText********/",text);
  }

  @Override
  public boolean isCategoryEnabled(Category category) {
    return true;
  }
}
