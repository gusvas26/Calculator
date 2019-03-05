/*
--------------------------
program created by Gustavo
--------------------------
*/

package calculadora;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException; 

public class Calculadora extends JFrame implements ActionListener {
    
    // argumentos y componentes
        private JFrame frame;
        private JPanel panel1 = new JPanel();
        private JPanel panel2 = new JPanel();
        private JTextField texto, campoTexto1, campoTexto2;
        private String resultado;
        private String formula = "";
        private int a = 0;
        
        // constructor
    public Calculadora() {
        setDefaultCloseOperation(Calculadora.EXIT_ON_CLOSE);
        Container contenedor = getContentPane();

        
        panel1.setLayout(new GridLayout(2, 2));
        texto = new JTextField("OPERACIÓN");
        texto.setEditable(false);
        panel1.add(texto);
        campoTexto1 = new JTextField(10);
        campoTexto1.setEditable(false);
        panel1.add(campoTexto1);
        texto = new JTextField("RESULTADO");
        texto.setEditable(false);
        panel1.add(texto);
        campoTexto2 = new JTextField(10);
        campoTexto2.setEditable(false);
        panel1.add(campoTexto2);
        contenedor.add(panel1, "North");
        panel2.setLayout(new FlowLayout());

        for (int i = 0; i < 10; i++) {
            JButton boton = new JButton("" + i);
            boton.addActionListener(this);
            panel2.add(boton);
        }

        JButton suma = new JButton("+");
        suma.addActionListener(this);
        panel2.add(suma);
        JButton resta = new JButton("-");
        resta.addActionListener(this);
        panel2.add(resta);
        JButton multiplica = new JButton("*");
        multiplica.addActionListener(this);
        panel2.add(multiplica);
        JButton divide = new JButton("/");
        divide.addActionListener(this);
        panel2.add(divide);
        JButton igual = new JButton("=");
        igual.addActionListener(this);
        panel2.add(igual);
        JButton CE = new JButton("CE");
        CE.addActionListener(this);
        panel2.add(CE);
        contenedor.add(panel2, "Center");
        
        // propiedades del frame contenedor
        this.setSize(350, 230);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        
    }

    public void actionPerformed(ActionEvent evento) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        if (a == 1 && (!(evento.getActionCommand()).equals("="))) {
            setResultado("");
        }
        if ((evento.getActionCommand()).equals("CE")) {
            setFormula("");
            setResultado("");
            formula = "";
            resultado = null;
        }
        if (!((evento.getActionCommand()).equals("=") || (evento.getActionCommand()).equals("CE"))) {
            formula = formula + evento.getActionCommand();
            setFormula(formula);
        }
        if ((evento.getActionCommand()).equals("=")) {
            try {
                resultado = "" + engine.eval(formula);
                if (!(resultado.equals("null"))) {
                    if (resultado.equals("Infinity") || resultado.equals("NaN")) {
                        setFormula(formula);
                        setResultado("∞");
                    } else {
                        setFormula(formula);
                        setResultado("" + resultado);
                    }
                }
                formula = "";
            } catch (ScriptException se) {
                formula = "";
                setResultado("Error en la sintaxis ");
            }
            a = 1;
        }
    }

    public void setFormula(String laFormula) {
        campoTexto1.setText(laFormula);
    }

    public void setResultado(String elResultado) {
        campoTexto2.setText(elResultado);
    }

    public static void main(String[] args) {
        new Calculadora().setTitle("Calculadora");
    }
    
}
