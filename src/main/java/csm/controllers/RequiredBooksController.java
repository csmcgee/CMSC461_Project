package csm.controllers;
import csm.views.AbstractView;
import csm.views.RequiredBooksView;

public class RequiredBooksController extends AbstractController{
	private int studentId;
	private final RequiredBooksView view;
	
	public RequiredBooksController(int studentId){
		this.studentId = studentId;
		view = new RequiredBooksView(studentId);
	}

	@Override
	public AbstractView getView() {
		// TODO Auto-generated method stub
		return view;
	}
}
