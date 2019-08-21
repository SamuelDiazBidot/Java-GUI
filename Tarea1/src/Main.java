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
import org.eclipse.swt.events.MouseListener;

import java.util.ArrayList;
import java.util.function.Consumer;

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
		
		int[] pos = new int[] {207, 79};
		ArrayList<Rectangle> recs = new ArrayList<Rectangle>();
		recs.add(new Rectangle(pos[0], pos[1], 10, 10));
		
		Canvas canvas = new Canvas(shlTarea, SWT.NONE);
		canvas.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		canvas.addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				e.gc.setBackground(e.display.getSystemColor(SWT.COLOR_BLACK));
				for(Rectangle rect : recs) {
					e.gc.fillRectangle(rect);
				}
				e.gc.dispose();
				
			}
		});
		canvas.setBounds(10, 10, 414, 158);
		
		canvas.getBounds();
		
		Label lblDirection = new Label(shlTarea, SWT.NONE);
		lblDirection.setAlignment(SWT.CENTER);
		lblDirection.setText("...");
		lblDirection.setBounds(196, 205, 40, 15);
		
		Button btnUp = new Button(shlTarea, SWT.NONE);
		btnUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				lblDirection.setText("Up");
				pos[1] += -10;
				recs.add(new Rectangle(pos[0], pos[1], 10, 10));
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
				pos[0] += -10;
				recs.add(new Rectangle(pos[0], pos[1], 10, 10));
				canvas.redraw();
			}
		});
		btnLeft.setBounds(150, 200, 40, 25);
		btnLeft.setText("Left");
		
		Button btnRight = new Button(shlTarea, SWT.NONE);
		btnRight.addMouseListener(new MouseAdapter() { 
			@Override public void mouseDown(MouseEvent e) {
				lblDirection.setText("Right"); 
					pos[0] += 10;
					recs.add(new Rectangle(pos[0], pos[1], 10, 10));
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
				pos[1] += 10;
				recs.add(new Rectangle(pos[0], pos[1], 10, 10));
				canvas.redraw();
			}
		});
		btnDown.setBounds(196, 226, 40, 25);
		btnDown.setText("Down");
		
		Button clrBtn = new Button(shlTarea, SWT.NONE);
		clrBtn.addMouseListener(new MouseAdapter() {
			@Override public void mouseDown(MouseEvent e) {
				lblDirection.setText("Clear");
				recs.clear();
				canvas.redraw();
			}
		});
		clrBtn.setBounds(342, 200, 40, 25);
		clrBtn.setText("Clear");
	}
}
	
