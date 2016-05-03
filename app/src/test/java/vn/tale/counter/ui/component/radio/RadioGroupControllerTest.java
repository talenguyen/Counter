package vn.tale.counter.ui.component.radio;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Created by Giang Nguyen at Tiki on 5/3/16.
 */
public class RadioGroupControllerTest {

  @Mock RadioItem item1;
  @Mock RadioItem item2;
  @Mock RadioItem item3;
  @Mock RadioGroupController.OnItemSelectedListener onItemSelectedListener;

  private RadioGroupController radioGroupController;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);

    radioGroupController = new RadioGroupController();
    radioGroupController.addItem(item1);
    radioGroupController.addItem(item2);
    radioGroupController.addItem(item3);
  }

  @Test
  public void testSetSelection() throws Exception {
    radioGroupController.setSelection(0);
    Mockito.verify(item1).setSelect(Mockito.eq(true));
    radioGroupController.setSelection(2);
    Mockito.verify(item1).setSelect(Mockito.eq(false));
    Mockito.verify(item3).setSelect(Mockito.eq(true));
  }

  @Test
  public void testListener() throws Exception {
    radioGroupController.setOnItemSelectedListener(onItemSelectedListener);
    radioGroupController.setSelection(0);
    Mockito.verify(onItemSelectedListener).onItemSelected(Mockito.eq(item1));
  }
}