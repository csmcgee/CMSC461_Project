package csm.controllers;

import java.awt.event.WindowAdapter;

import csm.views.AbstractView;

public abstract class AbstractController {
  public void tearDownView(){
    getView().setVisible(false);
  }
  
  public void setUpView(){
    getView().setVisible(true);
  }
  
  public abstract AbstractView getView();
  
  public void setWindowCloseEvent(WindowAdapter event) {
    getView().addWindowListener(event);
  }
  
}
