import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.wb.swt.SWTResourceManager;

public class Main {

	protected Shell shlTarea;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Main window = new Main();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlTarea.open();
		shlTarea.layout();
		while (!shlTarea.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlTarea = new Shell();
		shlTarea.setSize(450, 300);
		shlTarea.setText("Tarea#1");
		shlTarea.setLayout(null);
		
		Position pos = new Position(10, 10);
		
		Canvas canvas = new Canvas(shlTarea, SWT.NONE);
		canvas.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		canvas.addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				e.gc.drawRectangle(pos.getX(), pos.getY(), 10, 10);
				e.gc.dispose();
				
			}
		});
		canvas.setBounds(10, 10, 414, 158);
		
		Label lblDirection = new Label(shlTarea, SWT.NONE);
		lblDirection.setAlignment(SWT.CENTER);
		lblDirection.setText("...");
		lblDirection.setBounds(196, 205, 40, 15);
		
		Button btnUp = new Button(shlTarea, SWT.NONE);
		btnUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				lblDirection.setText("Up");
				pos.move(0, -5);
				canvas.redraw();
			}
		});
		btnUp.setBounds(196, 174, 40, 25);
		btnUp.setText("Up");
		
		Button btnLeft = new Button(shlTarea, SWT.NONE);
		btnLeft.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				lblDirection.setText("Left");
				pos.move(-5, 0);
				canvas.redraw();
			}
		});
		btnLeft.setBounds(150, 200, 40, 25);
		btnLeft.setText("Left");
		
		Button btnRight = new Button(shlTarea, SWT.NONE);
		btnRight.addMouseListener(new MouseAdapter() { 
			@Override public void mouseDown(MouseEvent e) {
				lblDirection.setText("Right"); 
					pos.move(5, 0); 
					canvas.redraw();
			}
		});
		btnRight.setBounds(242, 200, 40, 25);
		btnRight.setText("Right");
		
		Button btnDown = new Button(shlTarea, SWT.NONE);
		btnDown.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				lblDirection.setText("Down");
				pos.move(0, 5);
				canvas.redraw();
			}
		});
		btnDown.setBounds(196, 226, 40, 25);
		btnDown.setText("Down");
	}
	
	private class Position {
		private int x;
		private int y;
		
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
		
		public void move(int x, int y) {
			this.x += x;
			this.y += y;
		}
	}
}
