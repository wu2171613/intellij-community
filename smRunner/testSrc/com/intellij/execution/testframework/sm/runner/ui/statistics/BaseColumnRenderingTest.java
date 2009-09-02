package com.intellij.execution.testframework.sm.runner.ui.statistics;

import com.intellij.execution.testframework.sm.runner.BaseSMTRunnerTestCase;
import com.intellij.execution.testframework.sm.runner.SMTestProxy;
import com.intellij.execution.testframework.sm.UITestUtil;
import com.intellij.util.ui.ColumnInfo;

/**
 * @author Roman Chernyatchik
 */
public abstract class BaseColumnRenderingTest extends BaseSMTRunnerTestCase {
  protected ColumnInfo<SMTestProxy, String> myColumn;

  protected ColoredRenderer mySimpleTestRenderer;
  protected ColoredRenderer mySuiteRenderer;
  protected UITestUtil.FragmentsContainer myFragmentsContainer;

  protected abstract ColoredRenderer createRenderer(final SMTestProxy testProxy,
                                                             final UITestUtil.FragmentsContainer fragmentsContainer);
  protected abstract ColumnInfo<SMTestProxy, String> createColumn();

  @Override
  protected void setUp() throws Exception {
    super.setUp();

    myColumn = createColumn();    
    myFragmentsContainer = new UITestUtil.FragmentsContainer();

    mySimpleTestRenderer = createRenderer(mySimpleTest, myFragmentsContainer);
    mySuiteRenderer = createRenderer(mySuite, myFragmentsContainer);
  }

  protected void doRender(final SMTestProxy proxy) {
    if (proxy.isSuite()) {
      mySuiteRenderer.customizeCellRenderer(null, myColumn.valueOf(proxy), false, false, 0, 0);
    } else {
      mySimpleTestRenderer.customizeCellRenderer(null, myColumn.valueOf(proxy), false, false, 0, 0);
    }
  }

  protected void doRender(final SMTestProxy proxy, final int row) {
    if (proxy.isSuite()) {
      mySuiteRenderer.customizeCellRenderer(null, myColumn.valueOf(proxy), false, false, row, 0);
    } else {
      mySimpleTestRenderer.customizeCellRenderer(null, myColumn.valueOf(proxy), false, false, row, 0);
    }
  }

  protected void assertFragmentsSize(final int expectedSize) {
    assertEquals(expectedSize, myFragmentsContainer.getFragments().size());
  }
}
