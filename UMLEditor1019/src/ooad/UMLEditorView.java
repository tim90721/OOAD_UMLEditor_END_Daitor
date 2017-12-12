package ooad;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;

import ooad.model.DrawMode;
import ooad.model.IEditNameObserver;
import ooad.model.IMenuItemGroupSubject;
import ooad.model.IModel;
import ooad.model.IPopMsgObserver;
import ooad.model.IPresentationModel;
import ooad.model.Model;
import ooad.model.PresentationModel;
import ooad.viewevent.ButtonEnable;
import ooad.viewevent.CustomButtonHandlerGetter;
import ooad.viewevent.CustomMenuHandlerGetter;
import ooad.viewevent.CustomMouseHandler;
import ooad.viewevent.MenuItemEnable;

import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * 
 * @author daitor main frame
 * 
 */
public class UMLEditorView extends JFrame implements IPopMsgObserver,
		IEditNameObserver{
	private JPanel _panelContentPane;
	private JMenuBar _menuBar;
	private JMenu _menuFile;
	private JMenu _menuEdit;
	private JMenuItem _itemNew;
	private JMenuItem _itemExit;
	private JMenuItem _itemEditName;
	private JMenuItem _itemAddNewName;
	private JMenuItem _itemGroup;
	private JMenuItem _itemUnGroup;
	private JButton _btnSelect;
	private JButton _btnAssociaLine;
	private JButton _btnGeneralLine;
	private JButton _btnCompositionLine;
	private JButton _btnClass;
	private JButton _btnUseCase;
	private PaintPanel _pPanel;
	private String _desktopPath;
	private IPresentationModel _presentationModel;
	private IModel _model;
	private final String MOUSE_IMAGE = "Mouse.jpg";
	private final String ASSOCIATIONLINE_IMAGE = "AssociationLine.jpg";
	private final String GENERALLINE_IMAGE = "GernalizationLine.jpg";
	private final String COMPOSITIONLINE_IMAGE = "CompositionLine.jpg";
	private final String CLASS_IMAGE = "Class.jpg";
	private final String USECASE_IMAGE = "UseCase.jpg";
	private final int IMAGE_SIZE = 40;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UMLEditorView frame = new UMLEditorView(
							new PresentationModel(new Model()));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UMLEditorView(IPresentationModel presentationModel) {
		this._presentationModel = presentationModel;
		this._model = this._presentationModel.getModel();
		_desktopPath = System.getProperty("user.home") + "/Desktop";
		
		// initial frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("UMLEditor");
		setBounds(100, 100, 1000, 800);

		// initial main panel
		_panelContentPane = new JPanel();
		_panelContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(_panelContentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		_panelContentPane.setLayout(gbl_contentPane);

		// initial components
		initiateComponent();

		// add popup message box listener
		CustomMouseHandler mouseEvent = new CustomMouseHandler(_presentationModel);
		mouseEvent.registerPopMsgObserver(this);

		// add canvas
		_pPanel = new PaintPanel(_model, mouseEvent);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 7;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		gbc_panel.fill = GridBagConstraints.BOTH;
		_panelContentPane.add(_pPanel, gbc_panel);
	}

	/**
	 * initial components(Buttons, Menus)
	 */
	private void initiateComponent() {
		initiateMenu();
		initiateButtons();
		initiateButtonIcon();
	}

	/**
	 * initial menus
	 */
	private void initiateMenu() {
		CustomMenuHandlerGetter menuEventGetter = new CustomMenuHandlerGetter(
				_presentationModel);
		_menuBar = new JMenuBar();
		setJMenuBar(_menuBar);

		// file menu
		_menuFile = new JMenu("File");
		_menuFile.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		_menuBar.add(_menuFile);

		_itemNew = new JMenuItem("New File");
		_itemNew.addActionListener(menuEventGetter.getNewFileHandler());
		_menuFile.add(_itemNew);

		_itemExit = new JMenuItem("Exit");
		_menuFile.add(_itemExit);
		_itemExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		// edit menu
		_menuEdit = new JMenu("Edit");
		_menuEdit.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		_menuBar.add(_menuEdit);

		_itemEditName = new JMenuItem("Edit Name");
		_itemEditName.addActionListener(menuEventGetter.getEditNameHandler());
		_menuEdit.add(_itemEditName);

		_itemAddNewName = new JMenuItem("Add New Name");
		_menuEdit.add(_itemAddNewName);

		_menuEdit.addSeparator();

		_itemGroup = new JMenuItem("Group");
		_itemGroup.addActionListener(menuEventGetter.getGroupMenuHandler());
		_menuEdit.add(_itemGroup);

		_itemUnGroup = new JMenuItem("UnGroup");
		_itemUnGroup.addActionListener(menuEventGetter.getUnGroupMenuHandler());
		_menuEdit.add(_itemUnGroup);

		MenuItemEnable menuItemEnable = new MenuItemEnable(_itemGroup,
				_itemUnGroup, _itemEditName, _itemAddNewName,
				_presentationModel);
		((IMenuItemGroupSubject) _model)
				.registerMenuItemGroupObserver(menuItemEnable);
		menuEventGetter.registerMenuItemGroupObserver(menuItemEnable);
		menuEventGetter.registerEditNameObserver(this);
	}

	/**
	 * initial buttons
	 */
	private void initiateButtons() {
		CustomButtonHandlerGetter buttonEvent = new CustomButtonHandlerGetter(
				_presentationModel);

		_btnSelect = new JButton();
		configButton(_btnSelect, 0);
		_btnSelect.addActionListener(buttonEvent.getSelectClickEvent());

		_btnAssociaLine = new JButton();
		configButton(_btnAssociaLine, 1);
		_btnAssociaLine.addActionListener(buttonEvent
				.getAssociaLineClickHandler());

		_btnGeneralLine = new JButton();
		configButton(_btnGeneralLine, 2);
		_btnGeneralLine.addActionListener(buttonEvent
				.getGeneralLineClickHandler());

		_btnCompositionLine = new JButton();
		configButton(_btnCompositionLine, 3);
		_btnCompositionLine.addActionListener(buttonEvent
				.getCompositionLineClickHandler());

		_btnClass = new JButton();
		configButton(_btnClass, 4);
		_btnClass.addActionListener(buttonEvent.getClassModeClickHandler());

		_btnUseCase = new JButton();
		configButton(_btnUseCase, 5);
		_btnUseCase.addActionListener(buttonEvent.getUseCaseModeClickHandler());

		ButtonEnable btnEnable = new ButtonEnable(_btnSelect, _btnAssociaLine,
				_btnGeneralLine, _btnCompositionLine, _btnClass, _btnUseCase,
				_presentationModel);

		buttonEvent.registerBtnEnableObserver(btnEnable);
	}

	/**
	 * config button location
	 * 
	 * @param button
	 *            button need to settle
	 * @param index
	 *            button row index
	 */
	private void configButton(JButton button, int index) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = index;
		_panelContentPane.add(button, gbc);
		button.setContentAreaFilled(false);
		button.setFocusPainted(true);
		button.setMargin(new Insets(0, 0, 0, 0));
	}

	/**
	 * initial buttons icon
	 */
	private void initiateButtonIcon() {
		setButtonIcon(_btnSelect, MOUSE_IMAGE);
		setButtonIcon(_btnAssociaLine, ASSOCIATIONLINE_IMAGE);
		setButtonIcon(_btnGeneralLine, GENERALLINE_IMAGE);
		setButtonIcon(_btnCompositionLine, COMPOSITIONLINE_IMAGE);
		setButtonIcon(_btnClass, CLASS_IMAGE);
		setButtonIcon(_btnUseCase, USECASE_IMAGE);
	}

	/**
	 * initial button icon
	 * 
	 * @param button
	 *            button need to set icon
	 * @param IconName
	 *            icon file name
	 */
	private void setButtonIcon(JButton button, String IconName) {
		String path = "img/" + IconName;
		try {
			Image image = ImageIO.read(getClass().getResource(path));
			Image scaleImage = image.getScaledInstance(IMAGE_SIZE, IMAGE_SIZE,
					Image.SCALE_REPLICATE);
			button.setIcon(new ImageIcon(scaleImage));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("!!Image NOT FOUND!!");
		}
	}

	/**
	 * popup message box for class graph and use case mode
	 */
	@Override
	public void updatePopMsg() {
		if (_model.getDrawMode() == DrawMode.CLASS_MODE
				|| _model.getDrawMode() == DrawMode.USECASE_MODE) {
			String name = showMsgBox();
			_model.addShapeString(name);
		}
	}

	/**
	 * popup message box for edit name
	 */
	@Override
	public void editName() {
		String name = showMsgBox();
		_model.editShapeName(name);
	}

	/**
	 * show message box
	 * @return textbox string for setting object name
	 */
	private String showMsgBox() {
		String name = (String) JOptionPane.showInputDialog(this, "class name:",
				"Set Name", JOptionPane.PLAIN_MESSAGE, null, null, null);
		if (name == null)
			name = " ";
		return name;
	}
}
