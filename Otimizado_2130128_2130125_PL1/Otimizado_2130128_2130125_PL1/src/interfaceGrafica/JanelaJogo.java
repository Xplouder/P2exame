package interfaceGrafica;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import outros.Jogo;
import pt.ipleiria.estg.dei.gridpanel.GridPanel;
import pt.ipleiria.estg.dei.gridpanel.MediaLoader;

/**
 * 
 * A classe JanelaJogo é a classe que permite a representação gráfica do jogo,
 * na qual é criada a classe 'Jogo' que controla e gere todo o jogo.
 * 
 * @authors Daniel Pinto e Diogo SimÃ£o.
 * 
 */
@SuppressWarnings("serial")
public class JanelaJogo extends JFrame {
	private JPanel contentPane;
	/**
	 * Classe principal: É o Jogo que controla e gere os três painéis;
	 */
	private Jogo jogo;
	private GridPanel gridPainelPrincipal;
	private GridPanel gridPainelVida;
	private GridPanel gridPainelBonus;
	private Thread threadIteracao;
	private JLabel lblNewLabel;
	private String background;
	private Clip clip;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaJogo frame = new JanelaJogo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * Decidimos que JanelaJogo não tivesse um Random visto deste modo teríamos
	 * random's repetidos, dos quais não poderíamos extrair uma super classe por
	 * serem hierarquias diferentes nem uma delegação porque apenas teríam em
	 * comum o random e o nextint(), e acabávamos por ter na mesma delegações
	 * repetidas, porque ambos teríam a mesma delegação.
	 * 
	 * @author Daniel Pinto e Diogo Simão.
	 */
	public JanelaJogo() {

		setTitle("Cradle of DEI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		double resize = width / 1980;

		if ((int) width <= 1024) {
			background = "/imagens_fundos1024/fundo" + new Random().nextInt(16)
					+ ".jpg";
		}
		if (((int) width > 1024) && ((int) width <= 1280)) {
			background = "/imagens_fundos1280/fundo" + new Random().nextInt(16)
					+ ".jpg";
		} else if (((int) width > 1280) && ((int) width <= 1366)) {
			background = "/imagens_fundos1366/fundo" + new Random().nextInt(16)
					+ ".jpg";
		} else if (((int) width > 1366) && ((int) width <= 1440)) {
			background = "/imagens_fundos1440/fundo" + new Random().nextInt(16)
					+ ".jpg";
		} else if (((int) width > 1440) && ((int) width <= 1600)) {
			background = "/imagens_fundos1600/fundo" + new Random().nextInt(16)
					+ ".jpg";
		} else {
			background = "/imagens_fundos1920/fundo" + new Random().nextInt(16)
					+ ".jpg";
		}

		contentPane = new JPanel() {

			protected void paintComponent(java.awt.Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(JanelaJogo.class
							.getResource(background)), 0, 0, this);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		contentPane.setBorder(new LineBorder(new Color(128, 0, 0), 5));
		setContentPane(contentPane);

		gridPainelPrincipal = new GridPanel();
		gridPainelPrincipal.setBounds(25, 51, 1000, 500);
		gridPainelPrincipal.setShowGridLines(false);

		int sizeGridPainelPrincipal = (int) (75 * resize);
		int sizeGridPainelBonus = (int) (115 * resize);
		int sizeGridPainelVida_altura = (int) (125 * resize);
		int sizeGridPainelVida_largura = (int) (250 * resize);

		contentPane.setLayout(null);
		gridPainelPrincipal.setBounds((int) (width - sizeGridPainelVida_largura
				- (sizeGridPainelPrincipal * 20) - 5),
				(int) ((height / 2) - (3.75 * sizeGridPainelBonus)),
				sizeGridPainelPrincipal * 20, sizeGridPainelPrincipal * 10);
		gridPainelPrincipal.setRowSize(sizeGridPainelPrincipal);
		gridPainelPrincipal.setColumnSize(sizeGridPainelPrincipal);
		gridPainelPrincipal.setRows(10);
		gridPainelPrincipal.setColumns(20);
		contentPane.add(gridPainelPrincipal);

		gridPainelBonus = new GridPanel();
		gridPainelBonus.setBounds(0,
				(int) (height - (1.75 * sizeGridPainelBonus)), (int) width,
				sizeGridPainelBonus);
		gridPainelBonus.setShowGridLines(false);
		gridPainelBonus.setRowSize(sizeGridPainelBonus);
		gridPainelBonus.setRows(1);
		gridPainelBonus.setColumnSize(sizeGridPainelBonus);
		contentPane.add(gridPainelBonus);

		gridPainelVida = new GridPanel();
		gridPainelVida.setBounds(
				(int) (width - (0.55 * sizeGridPainelVida_largura)),
				(int) ((height / 2) - (sizeGridPainelVida_altura)),
				sizeGridPainelVida_altura, sizeGridPainelVida_largura);
		gridPainelVida.setColumnSize(sizeGridPainelVida_altura);
		gridPainelVida.setRows(1);
		gridPainelVida.setColumns(1);
		gridPainelVida.setRowSize(sizeGridPainelVida_largura);
		contentPane.add(gridPainelVida);

		/* --- --- --- --- --- --- --- --- --- --- --- --- */
		jogo = new Jogo(this, gridPainelPrincipal, gridPainelVida,
				gridPainelBonus);

		lblNewLabel = new JLabel("Pontua\u00E7\u00E3o: " + jogo.getPontuacao());
		lblNewLabel.setBounds(0, 0, (int) (width), 30);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBorder(new LineBorder(new Color(128, 0, 0)));
		lblNewLabel.setBackground(new Color(128, 0, 0));
		lblNewLabel.setForeground(new Color(255, 140, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe Script", Font.BOLD, 20));
		contentPane.add(lblNewLabel);

		// clip = audio("/musicas/musicaFundo" + new Random().nextInt(6) +
		// ".wav");
		clip = audio("/musicas/musicaFundo2.wav");

		final Runnable iterar = new Runnable() {

			public void run() {
				jogo.iterar();
				lblNewLabel.setText("Pontua\u00E7\u00E3o: "
						+ jogo.getPontuacao());
			}
		};

		final int iteracao = jogo.getIteracao();
		threadIteracao = new Thread() {
			public void run() {
				while (!jogo.isConcluido()) {
					try {
						setImagemCursor();
						if (!clip.isRunning()) {
							clip = audio("/musicas/musicaFundo2.wav");
							clip.start();
						}
						SwingUtilities.invokeAndWait(iterar);
						sleep(iteracao);
					} catch (InterruptedException | InvocationTargetException e) {
						e.printStackTrace();
					}
				}
				jogo.terminar();
			}
		};
		threadIteracao.start();

	}

	public Clip audio(String nomeFicheiro) {
		return MediaLoader.getClip(nomeFicheiro);
	}

	public void setImagemCursor() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image imagem;
		try {
			if (jogo.getBonusSelecionado() != null) {
				imagem = toolkit.getImage(getClass().getResource(
						"/efeitos/cursor_" + jogo.getNomeBonusSelecionado()
								+ ".gif"));
			} else {
				imagem = toolkit.getImage(getClass().getResource(
						"/efeitos/cursor3.gif"));
			}
			Cursor cursor = toolkit.createCustomCursor(imagem, new Point(0, 0),
					"Cursor of the game");
			setCursor(cursor);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void terminar(boolean venceu) {
		if (venceu) {
			JOptionPane.showMessageDialog(null, "Ganhou o Jogo!\nPontuacao: "
					+ jogo.getPontuacao(), "P A R A B E N S!", 1);
		} else {
			JOptionPane.showMessageDialog(null, "Acabou o tempo!\nPontuacao: "
					+ jogo.getPontuacao(), "G A M E  O V E R!", 1);
		}
		jogo.limparGrelha();
		clip.stop();
	}
}
