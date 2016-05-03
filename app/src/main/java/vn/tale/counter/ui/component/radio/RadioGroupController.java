package vn.tale.counter.ui.component.radio;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giang Nguyen at Tiki on 5/3/16.
 */
public class RadioGroupController {

  public static interface OnItemSelectedListener {

    void onItemSelected(RadioItem radioItem);
  }

  private List<RadioItem> items;
  private int selection = -1;
  private OnItemSelectedListener onItemSelectedListener;

  public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
    this.onItemSelectedListener = onItemSelectedListener;
  }

  public void setSelection(int position) {
    if (selection == position) {
      return;
    }
    if (selection != -1) {
      items.get(selection).setSelect(false);
    }
    final RadioItem radioItem = items.get(position);
    radioItem.setSelect(true);
    selection = position;
    if (onItemSelectedListener != null) {
      onItemSelectedListener.onItemSelected(radioItem);
    }
  }

  public void addItem(RadioItem radioItem) {
    if (items == null) {
      items = new ArrayList<>();
    }
    items.add(radioItem);
  }
}
