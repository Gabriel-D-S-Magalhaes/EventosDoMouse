package br.com.gabriel.eventos_do_mouse.gui;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

/**
 * JFrame principal.
 *
 * @author Gabriel Magalhaes
 * @see
 * https://docs.oracle.com/javase/8/docs/api/java/awt/event/MouseWheelListener.html#mouseWheelMoved-java.awt.event.MouseWheelEvent-
 */
public class MainJFrame extends JFrame implements MouseWheelListener {

    private final JPanel contentPanel;
    private final JTextArea textArea;

    public MainJFrame() throws HeadlessException {
        super("Roda do Mouse");// Título da janela
        super.setSize(500, 500);// Tamanho 
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// Fecha aplicação quando a janela é fechada
        super.setResizable(false);// Não é redimensionável.
        super.setLocationRelativeTo(null);// parâmetro null p/ centralizar a janela

        super.addMouseWheelListener(this);// Registra essa janela como ouvinte dos eventos da roda do mouse

        contentPanel = new JPanel();
        contentPanel.setBackground(Color.LIGHT_GRAY);
        super.add(contentPanel);

        textArea = new JTextArea("Detalhes do evento serão mostrados aqui!");
        textArea.setEditable(false);// Conteúdo não pode ser editado
        textArea.setLineWrap(true);// Quebra de linha
        super.add(textArea);
    }

    /**
     * Called when the mouse wheel is rotated.
     *
     * @param e An event which indicates that the mouse wheel was rotated in a
     * component.
     * @see
     * https://docs.oracle.com/javase/8/docs/api/java/awt/event/MouseWheelEvent.html
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {        
        textArea.setText(null);// Limpa a Área de Texto.        

        switch (e.getScrollType()) {
            case MouseWheelEvent.WHEEL_BLOCK_SCROLL:
                textArea.append("WHEEL_BLOCK_SCROLL");

                /* Valores negativos se a roda do mouse foi girada 
                para cima / para longe do usuário e valores positivos se a roda 
                do mouse foi girada para baixo / em direção ao usuário.*/               
                textArea.append(String.format("The number of notches the mouse wheel was rotated = %s",
                        (e.getWheelRotation() < 0 ? "UP" : "DOWN")));

                /* Returns the number of "clicks" the mouse wheel was rotated, 
                as a double.*/
                textArea.append(String.format(
                        "\nThe number of \"clicks\" the mouse wheel was rotated = %s",
                        e.getPreciseWheelRotation()));
                break;

            case MouseWheelEvent.WHEEL_UNIT_SCROLL:
                textArea.append("WHEEL_UNIT_SCROLL");

                /* Valores negativos se a roda do mouse foi girada 
                para cima/para longe do usuário e valores positivos se a roda
                do mouse foi girada para baixo/em direção ao usuário*/
                textArea.append(String.format(
                        "\nThe number of notches the mouse wheel was rotated = %s",
                        (e.getWheelRotation() < 0 ? "UP" : "DOWN")));

                /* Returns the number of units that should be scrolled per notch. 
                This is always a positive number and is only valid if the scroll
                type is MouseWheelEvent.WHEEL_UNIT_SCROLL.*/
                textArea.append(String.format(
                        "\nThe number of units that should be scrolled per notch = %s",
                        e.getScrollAmount()));

                /* Returns the positive or negative units to scroll for the 
                current event. This is only valid when the scroll type is 
                MouseWheelEvent.WHEEL_UNIT_SCROLL.*/
                textArea.append(String.format(
                        "\nUnits to scroll for the current event = %s",
                        e.getUnitsToScroll()));

                /* Returns the number of "clicks" the mouse wheel was rotated, 
                as a double.*/
                textArea.append(String.format(
                        "\nThe number of \"clicks\" the mouse wheel was rotated = %s",
                        e.getPreciseWheelRotation()));
                break;
        }
    }
}
