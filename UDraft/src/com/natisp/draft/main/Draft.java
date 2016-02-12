package com.natisp.draft.main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.natisp.draft.entity.Pokemon;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.natisp.draft.entity.Entrenador;
import com.natisp.draft.entity.Pokedex;
import com.natisp.draft.entity.Pokedex.PokemonList;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;

public class Draft {

	private JFrame frmPokemonDraft;
	private JTable table;
	static ArrayList<Entrenador> entrenadores = new ArrayList<Entrenador>();
	private final Action action = new SwingAction();
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// Cargamos entrenadores
		entrenadores.add(new Entrenador("NatiSP"));
		entrenadores.add(new Entrenador("Blazerg"));

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Draft window = new Draft();
					window.frmPokemonDraft.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public Draft() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		frmPokemonDraft = new JFrame();
		frmPokemonDraft.setResizable(false);
		frmPokemonDraft.setTitle("Pokemon Draft");
		frmPokemonDraft.setBounds(100, 100, 680, 390);
		frmPokemonDraft.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPokemonDraft.getContentPane().setLayout(null);
		frmPokemonDraft.setIconImage(ImageIO.read(new FileInputStream("assets/pokeball.png")));
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(373, 11, 146, 20);
		progressBar.setMaximum(6);
		progressBar.setMinimum(0);
		frmPokemonDraft.getContentPane().add(progressBar);
		
		JComboBox comboBox = new JComboBox();
		ArrayList<String> nombres = new ArrayList<String>();
		for(int i = 0; i < entrenadores.size(); i++){
			nombres.add(entrenadores.get(i).getNombre());
		}
		comboBox.setModel(new DefaultComboBoxModel(nombres.toArray()));
		comboBox.setBounds(198, 11, 142, 20);
		comboBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	DefaultTableModel model = (DefaultTableModel) table_1.getModel();
		    	if (model.getRowCount() > 0) {
		    	    for (int i = model.getRowCount() - 1; i > -1; i--) {
		    	        model.removeRow(i);
		    	    }
		    	}
		    	progressBar.setValue(entrenadores.get(comboBox.getSelectedIndex()).getEquipo().size());
		    	for(int i = 0; i < entrenadores.get(comboBox.getSelectedIndex()).getEquipo().size(); i++){
		    		Object[] row = { entrenadores.get(comboBox.getSelectedIndex()).getEquipo().get(i).getNombre() };	    
				    model.addRow(row);
		    	}
		    	
		    }
		});
		frmPokemonDraft.getContentPane().add(comboBox);
		
		JLabel lblEntrenadorSeleccionado = new JLabel("Entrenador seleccionado");
		lblEntrenadorSeleccionado.setBounds(27, 14, 142, 14);
		frmPokemonDraft.getContentPane().add(lblEntrenadorSeleccionado);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(PokemonList.values()));
		comboBox_1.setBounds(253, 224, 142, 20);
		frmPokemonDraft.getContentPane().add(comboBox_1);
		
		JCheckBox chckbxMega = new JCheckBox("Mega");
		chckbxMega.setBounds(422, 223, 97, 23);
		frmPokemonDraft.getContentPane().add(chckbxMega);
		
		JButton btnAadir = new JButton("Añadir");
		btnAadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id = comboBox.getSelectedIndex();
				int pokemon = comboBox_1.getSelectedIndex();
				String pokName = Pokedex.PokemonList.getDexName(pokemon);
				if(chckbxMega.isSelected()){
					pokName = pokName + "-Mega";
				}
				entrenadores.get(id).getEquipo().add(new Pokemon(pokName));
				Object[] row = { entrenadores.get(id).getNombre(), pokName };

			    DefaultTableModel model = (DefaultTableModel) table.getModel();

			    model.addRow(row);
			    progressBar.setValue(entrenadores.get(comboBox.getSelectedIndex()).getEquipo().size());
			}
		});
		btnAadir.setAction(action);
		btnAadir.setBounds(269, 268, 105, 23);
		frmPokemonDraft.getContentPane().add(btnAadir);
		
		JButton btnValidarEquipos = new JButton("Validar Equipos");
		btnValidarEquipos.setBounds(269, 317, 105, 23);
		frmPokemonDraft.getContentPane().add(btnValidarEquipos);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Entrenador/a", "Pokemon"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.setBounds(370, 55, 279, 158);
		frmPokemonDraft.getContentPane().add(table);
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.setBounds(536, 10, 89, 23);
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	            String name = JOptionPane.showInputDialog(frmPokemonDraft,"Introducir nombre", null);
				entrenadores.add(new Entrenador(name));
				nombres.add(name);
				comboBox.setModel(new DefaultComboBoxModel(nombres.toArray()));
			}
		});
		frmPokemonDraft.getContentPane().add(btnNuevo);
		
		JLabel lblSeleccionarPokemon = new JLabel("Seleccionar Pokemon");
		lblSeleccionarPokemon.setBounds(111, 227, 125, 14);
		frmPokemonDraft.getContentPane().add(lblSeleccionarPokemon);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Pokemon"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_1.setBounds(21, 55, 279, 158);
		frmPokemonDraft.getContentPane().add(table_1);
		
		JLabel lblPokemonsDeEste = new JLabel("Pokemons de este entrenador");
		lblPokemonsDeEste.setBounds(27, 39, 209, 14);
		frmPokemonDraft.getContentPane().add(lblPokemonsDeEste);
		
		JLabel lblPicks = new JLabel("Picks");
		lblPicks.setBounds(373, 42, 46, 14);
		frmPokemonDraft.getContentPane().add(lblPicks);
		
		JLabel label = new JLabel("");
		label.setBounds(533, 234, 131, 116);
		label.setIcon(new ImageIcon(ImageIO.read(new FileInputStream("assets/pokeball.png"))));
		frmPokemonDraft.getContentPane().add(label);
	}
	
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Añadir");
			putValue(SHORT_DESCRIPTION, "Añadir nuevo pokemon");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	public JTable getTable() {
		return table;
	}
}
