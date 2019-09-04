package org.chamberlain;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import org.chamberlain.actions.CalculateAction;
import org.chamberlain.model.GridModel;
import org.chamberlain.model.GridModelChangedEvent;
import org.chamberlain.model.GridModelListener;
import org.chamberlain.model.Square;

public class Controller implements GLEventListener, KeyListener, MouseListener, MouseMotionListener {

    private final List<GridModelListener> registeredListeners;
    private final GLCapabilities caps;
    private final GLCanvas canvas;
    private final GridPopupMenu popUp;

    private GLU glu;
    private GridModel model;
    private MouseEvent mouseEvent;
    private double currentMousePointX;
    private double currentMousePointY;
    private Square underMouse;

    public Controller(GLCanvas canvas) {
        this.registeredListeners = new ArrayList();
        addCanvasRepainter();
        this.canvas = canvas;
        this.caps = new GLCapabilities(GLProfile.getDefault());
        canvas.addGLEventListener(this);
        canvas.addKeyListener(this);
        canvas.addMouseListener(this);
        canvas.addMouseMotionListener(this);
        this.popUp = new GridPopupMenu(this);
    }

    private void addCanvasRepainter() {
        this.registeredListeners.add(e -> repaint());
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        this.glu = new GLU();
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(16640);
        gl.glLoadIdentity();
        getModel().getGrid().stream().forEach(square -> {
            gl.glBegin(7);
            List<Square> open = null;
            List<Square> closed = null;
            try {
                CalculateAction calc = (CalculateAction) MainFrame.mainFrame.getCalculateButtonAction();
                open = calc.getCalc().getOpen();
                closed = calc.getCalc().getClosed();
            } catch (NullPointerException npe) {
            }
            if (isUnderMouse(square)) {
                gl.glColor3f(0.75F, 0.75F, 0.75F);
            } else if (closed != null && closed.contains(square)) {
                gl.glColor3f(0.0F, 0.0F, 1.0F);
            } else if (open != null && open.contains(square)) {
                gl.glColor3f(1.5F, 1.5F, 0.5F);
            } else if (square.isObstacle()) {
                gl.glColor3f(0.0F, 0.0F, 0.0F);
            } else if (square.isStartPoint()) {
                gl.glColor3f(0.5F, 1.0F, 0.5F);
            } else if (square.isFinishPoint()) {
                gl.glColor3f(1.5F, 0.5F, 0.5F);
            } else {
                gl.glColor3f(1.0F, 1.0F, 1.0F);
            }
            gl.glVertex2f((float) square.getTopLeft().getX(), (float) square.getTopLeft().getY());
            gl.glVertex2f((float) square.getTopRight().getX(), (float) square.getTopRight().getY());
            gl.glVertex2f((float) square.getBottomRight().getX(), (float) square.getBottomRight().getY());
            gl.glVertex2f((float) square.getBottomLeft().getX(), (float) square.getBottomLeft().getY());
            gl.glEnd();
            gl.glBegin(1);
            gl.glColor3f(0.85F, 0.85F, 0.85F);
            gl.glVertex2f((float) square.getTopLeft().getX(), (float) square.getTopLeft().getY());
            gl.glVertex2f((float) square.getTopRight().getX(), (float) square.getTopRight().getY());
            gl.glVertex2f((float) square.getTopRight().getX(), (float) square.getTopRight().getY());
            gl.glVertex2f((float) square.getBottomRight().getX(), (float) square.getBottomRight().getY());
            gl.glVertex2f((float) square.getBottomRight().getX(), (float) square.getBottomRight().getY());
            gl.glVertex2f((float) square.getBottomLeft().getX(), (float) square.getBottomLeft().getY());
            gl.glVertex2f((float) square.getBottomLeft().getX(), (float) square.getBottomLeft().getY());
            gl.glVertex2f((float) square.getTopLeft().getX(), (float) square.getTopLeft().getY());
            gl.glEnd();
        });
        mouseMoveChecker(gl, false);
        gl.glFlush();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(5889);
        gl.glLoadIdentity();
        this.glu.gluOrtho2D(0.0D, width, 0.0D, height);
        gl.glMatrixMode(5888);
        gl.glLoadIdentity();
        System.out.println("Resized to " + width + "x" + height);
    }

    @Override
    public void keyTyped(KeyEvent key) {
        // Do nothing...
    }

    @Override
    public void keyPressed(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent key) {
        // Do nothing...
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Do nothing...
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 1) {
            if (this.underMouse != null) {
                this.underMouse.setObstacle(!this.underMouse.isObstacle());
                this.underMouse.setFinishPoint(false);
                this.underMouse.setStartPoint(false);
            }
        } else if (e.getButton() == 3) {
            this.popUp.setCurrentSquare(this.underMouse);
            this.popUp.setLocation(e.getLocationOnScreen());
            this.popUp.setVisible(true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Do nothing...
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Do nothing...
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Do nothing...
    }

    private boolean isUnderMouse(Square square) {
        if (square == null) {
            return false;
        }
        if (this.currentMousePointX >= square.getTopLeft().getX()
                && this.currentMousePointX <= square.getTopRight().getX()
                && this.currentMousePointY <= square.getTopLeft().getY()
                && this.currentMousePointY >= square.getBottomLeft().getY()) {
            if (square != this.underMouse) {
                this.underMouse = square;
                this.popUp.setVisible(false);
            }
            return true;
        }
        return false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Do nothing...
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.mouseEvent = e;
    }

    private void mouseMoveChecker(GL2 gl, boolean printDetails) {
        if (this.mouseEvent != null) {
            int x = this.mouseEvent.getX(), y = this.mouseEvent.getY();
            int[] viewport = new int[4];
            double[] mvmatrix = new double[16];
            double[] projmatrix = new double[16];
            int realY;
            double[] wcoord = new double[4];
            gl.glGetIntegerv(2978, viewport, 0);
            gl.glGetDoublev(2982, mvmatrix, 0);
            gl.glGetDoublev(2983, projmatrix, 0);
            realY = viewport[3] - y - 1;
            if (printDetails) {
                System.out.println("\nCoordinates at cursor are (" + x + ", " + realY);
            }
            this.glu.gluUnProject(x, realY, 0.0D, mvmatrix, 0, projmatrix, 0, viewport, 0, wcoord, 0);
            if (printDetails) {
                System.out.println("World coords at z=0.0 are ( " + wcoord[0] + ", " + wcoord[1] + ", " + wcoord[2] + ")");
            }
            this.currentMousePointX = wcoord[0];
            this.currentMousePointY = wcoord[1];
            this.glu.gluUnProject(x, realY, 1.0D, mvmatrix, 0, projmatrix, 0, viewport, 0, wcoord, 0);
            if (printDetails) {
                System.out.println("World coords at z=1.0 are (" + wcoord[0] + ", " + wcoord[1] + ", " + wcoord[2] + ")");
            }
        }
    }

    public GridModel getModel() {
        return this.model;
    }

    public GLCapabilities getCaps() {
        return this.caps;
    }

    public void setModel(GridModel model) {
        this.model = model;
        this.registeredListeners.forEach(listener
                -> listener.gridModelChanged(new GridModelChangedEvent(this.model)));
    }

    public void removeRegisteredListener(GridModelListener listener) {
        this.registeredListeners.remove(listener);
    }

    public void addRegisteredListener(GridModelListener listener) {
        this.registeredListeners.add(listener);
    }

    @Override
    public void dispose(GLAutoDrawable glad) {
        // Do nothing...
    }

    void repaint() {
        canvas.repaint();
    }
}
