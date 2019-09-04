package org.chamberlain;

import com.jidesoft.plaf.LookAndFeelFactory;
import com.jidesoft.swing.Animator;
import com.jogamp.opengl.awt.GLCanvas;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.chamberlain.actions.CalculateAction;
import org.chamberlain.actions.ClearGridAction;
import org.chamberlain.actions.ExitProgramAction;
import org.chamberlain.actions.NewGridAction;
import org.chamberlain.actions.OpenGridAction;
import org.chamberlain.actions.OpenHelpAction;
import org.chamberlain.actions.SaveGridAction;
import org.chamberlain.actions.ShowOpenGLCapsDialogAction;
import org.chamberlain.dialogs.SplashDialog;
import org.chamberlain.model.GridModel;
import org.chamberlain.model.GridModelChangedEvent;
import org.chamberlain.model.GridModelListener;
import org.jdesktop.swingx.JXStatusBar;
import org.jdesktop.swingx.plaf.basic.BasicStatusBarUI;

public class MainFrame extends JFrame {

    public static final String TITLE = "A* Pathfinder ";

    public static PrintStream ps = null;

    AStarPathFinding aStarPathFinding;

    public static MainFrame mainFrame;

    private JButton calculateButton;

    private JMenuItem clearGridItem;

    private JCheckBoxMenuItem cutCornersItem;

    private JMenuItem exitItem;

    private JMenu fileMenu;

    private JPanel glPanel;

    private JLabel gridName;

    private JLabel gridSize;

    private JMenuItem helpItem;

    private JMenu helpMenu;

    private JSeparator jSeparator1;

    private JSeparator jSeparator2;

    private JMenuBar mainMenu;

    private JMenuItem newGridItem;

    private JMenuItem openGlCapsDialogItem;

    private JMenuItem openGridItem;

    private JLabel padding;

    private JMenuItem saveGridItem;

    private JXStatusBar statusBar;

    private JToolBar toolBar;

    private JMenu toolMenu;

    public MainFrame(SplashDialog dialog) {
        setIconImage(IconLoader.createImageIcon("astar.png", "").getImage());
        JPopupMenu.setDefaultLightWeightPopupEnabled(false);
        GLCanvas canvas = new GLCanvas();
        this.aStarPathFinding = new AStarPathFinding(canvas);
        this.aStarPathFinding.addRegisteredListener(new GridModelListener() {
            public void gridModelChanged(GridModelChangedEvent event) {
                GridModel changedModel = event.getModel();
                if (changedModel != null) {
                    MainFrame.this.gridSize.setText(changedModel.getRows() + "x" + changedModel.getColumns());
                    MainFrame.this.gridName.setText(changedModel.getGridName());
                    MainFrame.this.setTitle("A* Pathfinder (" + changedModel.getGridName() + ")");
                }
            }
        });
        initComponents();
        this.statusBar.putClientProperty(BasicStatusBarUI.AUTO_ADD_SEPARATOR, Boolean.valueOf(false));
        this.aStarPathFinding.setModel(new GridModel(8, 12));
        canvas.addGLEventListener(this.aStarPathFinding);
        this.glPanel.add(canvas);
        final Animator animator = new Animator(canvas);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                new Thread(new Runnable() {
                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        setSize(610, 545);
        setLocationRelativeTo(null);
        dialog.setVisible(false);
        setVisible(true);
        animator.start();
    }

    public Action getCalculateButtonAction() {
        return this.calculateButton.getAction();
    }

    private void initComponents() {
        this.toolBar = new JToolBar();
        this.calculateButton = new JButton();
        this.glPanel = new JPanel();
        this.statusBar = new JXStatusBar();
        this.gridSize = new JLabel();
        this.jSeparator2 = new JSeparator();
        this.gridName = new JLabel();
        this.padding = new JLabel();
        this.mainMenu = new JMenuBar();
        this.fileMenu = new JMenu();
        this.newGridItem = new JMenuItem();
        this.openGridItem = new JMenuItem();
        this.saveGridItem = new JMenuItem();
        this.jSeparator1 = new JSeparator();
        this.exitItem = new JMenuItem();
        this.toolMenu = new JMenu();
        this.cutCornersItem = new JCheckBoxMenuItem();
        this.clearGridItem = new JMenuItem();
        this.openGlCapsDialogItem = new JMenuItem();
        this.helpMenu = new JMenu();
        this.helpItem = new JMenuItem();
        getContentPane().setLayout(new GridBagLayout());
        setDefaultCloseOperation(3);
        setTitle("A* Pathfinder");
        this.calculateButton.setAction(new CalculateAction(this.aStarPathFinding.getModel()));
        this.calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                MainFrame.this.calculateButtonActionPerformed(evt);
            }
        });
        this.toolBar.add(this.calculateButton);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0D;
        getContentPane().add(this.toolBar, gridBagConstraints);
        this.glPanel.setLayout(new BorderLayout());
        this.statusBar.setLayout(new GridBagLayout());
        this.gridSize.setText("jLabel1");
        this.statusBar.add(this.gridSize, new GridBagConstraints());
        this.statusBar.add(this.jSeparator2, new GridBagConstraints());
        this.gridName.setText("Default");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(0, 10, 0, 0);
        this.statusBar.add(this.gridName, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0D;
        this.statusBar.add(this.padding, gridBagConstraints);
        this.glPanel.add(this.statusBar, "Last");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0D;
        gridBagConstraints.weighty = 1.0D;
        getContentPane().add(this.glPanel, gridBagConstraints);
        this.fileMenu.setText("File");
        this.newGridItem.setAction(new NewGridAction(this.aStarPathFinding));
        this.fileMenu.add(this.newGridItem);
        this.openGridItem.setAction(new OpenGridAction(this.aStarPathFinding));
        this.fileMenu.add(this.openGridItem);
        this.saveGridItem.setAction(new SaveGridAction(this.aStarPathFinding));
        this.fileMenu.add(this.saveGridItem);
        this.fileMenu.add(this.jSeparator1);
        this.exitItem.setAction(new ExitProgramAction());
        this.fileMenu.add(this.exitItem);
        this.mainMenu.add(this.fileMenu);
        this.toolMenu.setText("Tools");
        this.cutCornersItem.setSelected(true);
        this.cutCornersItem.setText("Cut corners?");
        this.cutCornersItem.setIcon(new ImageIcon(getClass().getResource("/org/chamberlain/resources/cut_corners.png")));
        this.toolMenu.add(this.cutCornersItem);
        this.clearGridItem.setAction(new ClearGridAction(this.aStarPathFinding));
        this.toolMenu.add(this.clearGridItem);
        this.openGlCapsDialogItem.setAction(new ShowOpenGLCapsDialogAction(this.aStarPathFinding.getCaps()));
        this.toolMenu.add(this.openGlCapsDialogItem);
        this.mainMenu.add(this.toolMenu);
        this.helpMenu.setText("Help");
        this.helpItem.setAction(new OpenHelpAction());
        this.helpMenu.add(this.helpItem);
        this.mainMenu.add(this.helpMenu);
        setJMenuBar(this.mainMenu);
        pack();
    }

    private void calculateButtonActionPerformed(ActionEvent evt) {
        CalculateAction calculateAction = (CalculateAction) this.calculateButton.getAction();
        calculateAction.setCutCorners(this.cutCornersItem.isSelected());
        calculateAction.setModel(this.aStarPathFinding.getModel());
        aStarPathFinding.repaint();
    }

    public static void main(String[] args) {
        SplashDialog dialog = new SplashDialog(null, false);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        if (args.length == 1 && args[0].equals("debug")) {
            outputSystemProperties();
        } else {
            startDebug();
        }
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
        LookAndFeelFactory.installJideExtension(0);
        mainFrame = new MainFrame(dialog);
        mainFrame.setSize(640, 480);
        mainFrame.setVisible(true);
    }

    private static void startDebug() {
        try {
            var log = new File("Application\\Output.log");
            log.getParentFile().mkdirs();
            ps = new PrintStream(log);
            System.setOut(ps);
            System.setErr(ps);
            Date date = new Date();
            System.out.println("-----------------------------------------------------------------------------------------------------------------");
            System.out.println(" A* Pathfinding Log ");
            System.out.println(" " + date);
            System.out.println("-----------------------------------------------------------------------------------------------------------------");
            outputSystemProperties();
        } catch (IOException e) {
            System.out.println("Error routing output file");
            e.printStackTrace();
        }
    }

    private static void outputSystemProperties() {
        System.out.println(" OS - Name         = " + System.getProperty("os.name"));
        System.out.println(" OS - Architecture = " + System.getProperty("os.arch"));
        System.out.println(" OS - Version      = " + System.getProperty("os.version"));
        System.out.println(" Java Home         = " + System.getProperty("java.home"));
        System.out.println(" Java Classpath    = " + System.getProperty("java.class.path"));
        System.out.println(" Java Library Path = " + System.getProperty("java.library.path"));
        System.out.println(" JRE - Vendor      = " + System.getProperty("java.vendor"));
        System.out.println(" JRE - Version     = " + System.getProperty("java.version"));
        System.out.println(" JVM - Name        = " + System.getProperty("java.vm.name"));
        System.out.println(" JVM - Vendor      = " + System.getProperty("java.vm.vendor"));
        System.out.println(" JVM - Version     = " + System.getProperty("java.vm.version"));
        System.out.println(" Working Directory = " + System.getProperty("user.dir"));
        System.out.println(" User Name         = " + System.getProperty("user.name"));
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("");
    }
}