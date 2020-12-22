package dominoconsola;

import java.awt.Color;
import java.awt.FlowLayout;
import static java.awt.FlowLayout.CENTER;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

public class Ventana extends JFrame implements ActionListener {

    JTextField jugador1;
    JTextField jugador2;
    JLabel juga1;
    JLabel juga2;

    int lugarElegido = -1;
    int fichaElegida = -1;

    JPanel panelJuego;
    JPanel panelJuego2;
    Principal p = new Principal();
    FondoImagen img = new FondoImagen();
    JLabel fichasJugadas;
    ArrayList<JLabel> fichasLabel = new ArrayList();
    JLabel fichasDelPozo;
    JLabel tituloLabel;
    ButtonGroup fichasJugador1;
    ButtonGroup fichasJugador2;
    ArrayList<JRadioButton> btnsJugador2;
    ArrayList<JRadioButton> btnsJugador1;
    JRadioButton colocarIzq;
    JRadioButton colocarDer;
    JButton colocarFicha;
    JButton nuevaFicha;
    JButton pasar;
    JButton nuevoJuego;
    JPanel panelJugadas = new JPanel(new FlowLayout());
    boolean turno;
    JLabel tituloJugador;
    JPanel p1;
    JPanel p2;
    JPanel fichasPozo;

    public Ventana() {

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("DOMINO");
        setBounds(250, 50, 800, 600);
        setVisible(true);

        iniciarComponentes();
        ventanaPrincipal();
        this.setVisible(true);

    }

    private void iniciarComponentes() {
        //inicia la ventana 
        FondoImagen img = new FondoImagen();
        img.setBorder(new EmptyBorder(5, 5, 5, 5));
        img.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 350));
        img.setBounds(50, 50, 10, 10);
        img.setVisible(true);
        setContentPane(img);

    }

    public void agregarFichas() {
        //agrega al Panel de fichasjugadas todos los elementos del array de fichas creadas en un JLabel
        for (int i = 0; i < fichasLabel.size(); i++) {
            panelJugadas.add(fichasLabel.get(i));
        }
    }

    public void fichaJugadaIzq(Ficha ficha) {
        //agrega al Jpanel de las fichas jugadas la ficha que le llegue como parametro a la Izq(en la posicion 0)
        String fichaString = "[" + ficha.numero1 + "|" + ficha.numero2 + "]";
        fichasJugadas = new JLabel(fichaString);
        fichasLabel.add(0, fichasJugadas);
        agregarFichas();
    }

    public void fichaJugadaDer(Ficha ficha) {
        //agrega al Jpanel de las fichas jugadas la ficha que le llegue como parametro a la Der(al final)
        String fichaString = "[" + ficha.numero1 + "|" + ficha.numero2 + "]";
        fichasJugadas = new JLabel(fichaString);
        fichasLabel.add(fichasJugadas);
        agregarFichas();
    }

    public void fichasJugadas() {
        //se crea JPanel para las fichas Jugadas y se le agregan todas las que esten en el array de la otra clase
        fichasJugadas.setText(null);
        JPanel p1 = new JPanel(new GridBagLayout());
        //p1.setBackground(new Color(0,0,0,0));
        //p1.setOpaque(false);
        p1.add(tituloLabel = new JLabel("Fichas Jugadas"));
        for (int i = 0; i < p.fichasJugadas.size(); i++) {
            if (p.fichasJugadas.isEmpty()) {
                continue;
            } else {
                p1.add(fichasJugadas = new JLabel(p.fichasJugadasString()));
            }
        }
        getContentPane().add(p1);
    }

    public void fichasDelPozo() {
        //se crea un jpanel para las fichas del pozo y se le añade un titulo
        fichasPozo = new JPanel(new FlowLayout(CENTER));
        tituloLabel = new JLabel("Fichas Del Pozo");
        fichasPozo.add(tituloLabel);

        cargarFichasPozo();

        getContentPane().add(fichasPozo);
    }

    public void cargarFichasPozo() {
        //le agrega al jpanel fichasPozo las fichas que estan en el array de fichas de la clase principal
        for (int i = 0; i < p.fichas.size(); i++) {
            fichasPozo.add(fichasDelPozo = new JLabel("[" + Integer.toString(p.fichas.get(i).getNumero1()) + "|" + Integer.toString(p.fichas.get(i).getNumero2()) + "]-"));
        }
    }

    public void actualizarPozo() {
        //borra las fichas del jpanel y carga las nuevas(por si el jugador agarro una nueva ficha)
        fichasPozo.removeAll();
        tituloLabel = new JLabel("Fichas Del Pozo");
        fichasPozo.add(tituloLabel);
        cargarFichasPozo();
    }

    public void btnJugador1() {
        p1 = new JPanel(new GridBagLayout());
        p1.setBackground(new Color(0, 0, 0, 0));
        p1.setOpaque(false);

        fichasJugador1 = new ButtonGroup();
        btnsJugador1 = new ArrayList<JRadioButton>();

        tituloJugador = new JLabel("Jugador 1 :" + jugador1.getText());
        p1.add(tituloJugador);
        
        cargarTodo(btnsJugador1, p.jugador1Fichas, p1, fichasJugador1);
        getContentPane().add(p1);
    }

    public void btnJugador2() {
        //crea jpanel para las fichas del jug2
        p2 = new JPanel(new GridBagLayout());
        p2.setBackground(new Color(0, 0, 0, 0));//para que se vea el fondo verde
        p2.setOpaque(false);//para que se vea el fondo verde
        
        fichasJugador2 = new ButtonGroup();
        btnsJugador2 = new ArrayList<JRadioButton>();

        tituloJugador = new JLabel("Jugador 2 :" + jugador2.getText());//Agrega el titulo del jpanel(no se como ponerlo arriba)
        p2.add(tituloJugador);
        cargarTodo(btnsJugador2, p.jugador2Fichas, p2, fichasJugador2);//esta funcion para cargar los botones
        getContentPane().add(p2);
    }

    private void desabilitarFichas(ArrayList<JRadioButton> fichas) {
        //vuelve todas las fichas del array que se le pasa como parametro a deshabilitadas
        for (int i = 0; i < fichas.size(); i++) {
            fichas.get(i).setEnabled(false);
        }
    }

    private void habilitarFichas(ArrayList<JRadioButton> fichas) {
        //vuelve todas las fichas del array que se le pasa como parametro a habilitadas
        for (int i = 0; i < fichas.size(); i++) {
            fichas.get(i).setEnabled(true);
        }
    }

    public void cargarTodo(ArrayList<JRadioButton> fichas, ArrayList<Ficha> fichasJugador, JPanel panel, ButtonGroup botones) {
        //carga los botones del jugador segun las fichas que tenga en la clase principal
        for (int i = 0; i < fichasJugador.size(); i++) {
            JRadioButton otroBoton = new JRadioButton();
            fichas.add(otroBoton);
            panel.add(fichas.get(i));
            fichas.get(i).setText("[" + Integer.toString(fichasJugador.get(i).getNumero1()) + "|" + Integer.toString(fichasJugador.get(i).getNumero2()) + "]");
            botones.add(fichas.get(i));
            fichas.get(i).addActionListener(this);
        }//for
    }

    private void cargarFichas() {
        //carga primero el panel con el fondo verde
        panelJuego = new PanelJuego();
        panelJuego.setBorder(new EmptyBorder(5, 5, 5, 5));
        panelJuego.setVisible(true);
        setContentPane(panelJuego);
        getContentPane().setLayout(new GridLayout(20, 0));
        
        //le agrega el panel de fichasjugadas, las del pozo, los botones de los jugadores y las opciones para colocar las fichas
        panelJugadas.add(tituloLabel = new JLabel("Fichas Jugadas\n"));
        getContentPane().add(panelJugadas);

        fichasDelPozo();
        btnJugador1();
        btnJugador2();

        botones();
        this.setVisible(true);
    }

    public void ventanaPrincipal() {
        //Agrega botones y el campo para ingresar nombres
        JButton boton1 = new JButton("INICIAR JUEGO");
        boton1.setBounds(getContentPane().getWidth(), 300, 210, 50);//setea el lugar del boton1

        JLabel jug1 = new JLabel("Jugador 1 :");//crea jlabel con un texto
        jugador1 = new JTextField("", 10); // Creo campo de texto con texto)

        JLabel jug2 = new JLabel("Jugador 2 :");
        jugador2 = new JTextField("", 10);

        //añade los botones y los labels
        getContentPane().add(jug1);
        getContentPane().add(jugador1);
        getContentPane().add(jug2);
        getContentPane().add(jugador2);
        getContentPane().add(boton1, 2);

        // Manejo del evento click sobre el boton iniciar juego
        boton1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                //este if recupera el texto ingresado de losJTextField de arriba y si en caso de que su longitud
                //sea 0 quiere decir que no se agregó ningun nombre o numero
                //si se agregan se asignan a las variables de la clase principal
                if (jugador1.getText().length() == 0 || jugador2.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Error!\nIngrese algún numbre");
                } else {
                    p.jugador1 = jugador1.getText();
                    p.jugador2 = jugador2.getText();
                    p.inicioJuego(); //llama a la funcion de la clase principal para repartir las fichas
                    img.setVisible(false); //dejo de mostrar la pantalla de inicio
                    cargarFichas();//carga todos los jpanel
                    turno = p.quienJuegaPrimero(); //asigna a la variable turno verdadero si empieza el jug1 o falso para el jug2
                    if (p.quienJuegaPrimero()) { //se compara con este metodo para que se habiliten los botones del que juegue primero
                        turno(true);//empieza jug1
                    } else {
                        turno(false);//empiezajug2
                    }

                }
            }
        }
        );
    }

    private void botones() {

        JPanel p3 = new JPanel(); //nuevo jpanel para los botones de abajo

        p3.setLayout(new FlowLayout()); //seteamos el layout(no estoy seguro de si funciona sin esta linea)

        p3.add(colocarIzq = new JRadioButton("Colocar a la Izquierda")); //se agrega un JRadioButton al panel para colocar a la izq
        colocarIzq.addActionListener(this);//se agrega un listener al boton

        p3.add(colocarDer = new JRadioButton("Colocar a la Derecha"));
        colocarDer.addActionListener(this);

        ButtonGroup leftRightButtonGroup = new ButtonGroup(); // se crea un ButtonGroup

        leftRightButtonGroup.add(colocarIzq);// se le agregan los JRadioButton

        leftRightButtonGroup.add(colocarDer);

        p3.add(colocarFicha = new JButton("Colocar Ficha"));//Boton Colocar Ficha
        colocarFicha.addActionListener(this);

        p3.add(nuevaFicha = new JButton("Robar del Pozo"));
        nuevaFicha.addActionListener(this);
        nuevaFicha.setVisible(false);//setea a false para esconderlo y activarlo cuando el usuario no tenga movimientos disponibles

        p3.add(pasar = new JButton("Pasar"));
        pasar.addActionListener(this);
        pasar.setVisible(false);
        
        p3.add(nuevoJuego = new JButton("Nuevo Juego"));
        nuevoJuego.addActionListener(this);
        nuevoJuego.setVisible(false);

        p3.setBackground(new Color(0, 0, 0, 0));//Para que se vea el fondo verde
        p3.setOpaque(false);//lo mismo
        getContentPane().setLayout(new GridLayout(5, 1));//GridLayout para que haya 5 filas y una columna
        getContentPane().add(p3);//se agrega elJPanel al JFrame activo

    }

    public void turno(boolean turno) {
        //este metodo sirve para ver a quien se le habilita y deshabilita las fichas
        //y para comprobar si alguno gano o si tienen posibilidad de colocar alguna ficha
        if (turno) {
            desabilitarFichas(btnsJugador2);
            habilitarFichas(btnsJugador1);
            if (p.jugador1Fichas.isEmpty() || p.jugador2Fichas.isEmpty()) {//si alguno de los dos se quedo sin fichas
                p.sinFichas(p.jugador1Fichas, p.jugador2Fichas);//llamamos a la funcion sinfichas para comprobar que estan sinfichas y calcular quien gano
                finJuego();
            } else if (p.ComprobarMovimiento(p.jugador1Fichas)) {//este metodo devuelve true si hay algun tipo de combinacion entre la primera y la ultima ficha jugada con las fichas del jugador
                colocarFicha.setVisible(true);
                nuevaFicha.setVisible(false);
                pasar.setVisible(false);
                p.sinFichas(p.jugador1Fichas, p.jugador2Fichas);
            } else {//si no hay combinacion posible muestra un mensaje y habilita la opcion de agarrar nueva ficha o pasar de turno
                JOptionPane.showMessageDialog(null, "No puede colocar más Fichas\n Agregue otra o Pase de Turno");
                colocarFicha.setVisible(false);
                nuevaFicha.setVisible(true);
                pasar.setVisible(true);
                p.sinFichas(p.jugador1Fichas, p.jugador2Fichas);
            }
        } else {
            desabilitarFichas(btnsJugador1);
            habilitarFichas(btnsJugador2);
            if (p.jugador2Fichas.isEmpty() || p.jugador1Fichas.isEmpty()) {
                p.sinFichas(p.jugador1Fichas, p.jugador2Fichas);
                finJuego();
            } else if (p.ComprobarMovimiento(p.jugador2Fichas)) {
                p.sinFichas(p.jugador1Fichas, p.jugador2Fichas);
                colocarFicha.setVisible(true);
                nuevaFicha.setVisible(false);
                pasar.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "No puede colocar más Fichas\n Agregue otra o Pase de Turno");
                colocarFicha.setVisible(false);
                nuevaFicha.setVisible(true);
                pasar.setVisible(true);
                p.sinFichas(p.jugador1Fichas, p.jugador2Fichas);
            }

        }

    }

    public void seleccionador(ArrayList<Ficha> fichas, ArrayList<JRadioButton> JLabelFichas, ActionEvent e) {
        //itera sobre las fichas del jug y si al seleccionar una es igual al boton entonces se le asigna a la variable global fichaElegida
        for (int i = 0; i < fichas.size(); i++) {
            if (e.getSource() == JLabelFichas.get(i)) {
                fichaElegida = i;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (turno) {//si la variable turno es verdadero juega el jug1
            seleccionador(p.jugador1Fichas, btnsJugador1, e);//se llama a la funcion de arriba pasandole parametros del jug1
        } else {
            seleccionador(p.jugador2Fichas, btnsJugador2, e);
        }

        if (e.getSource() == colocarIzq) {// si esta seleccionada la opcion de colocar a la izq
            lugarElegido = 1; //1 es al principio
        } else if (e.getSource() == colocarDer) {
            lugarElegido = 2; //2 es al final
        }

        if (turno) {//si la variable turno es verdadero juega el jug1
            if (e.getSource() == colocarFicha) {//si apreta el boton colocar ficha
                turnoJugador1();
            } else if (e.getSource() == nuevaFicha) {
                p.RobarDelPozo(p.jugador1Fichas);//usamos la funcion de la otra clase
                cargarTodo(btnsJugador1, p.jugador1Fichas, p1, fichasJugador1); //carga los botones anteriores y los nuevos
                p1.updateUI();// actualiza el estado del jpanel con las fichas del jugador1
                actualizarPozo();// actualiza el pozo de fichas imprimiendo todas sin la ultima que se saco con el metodo RobarDelPozo
                fichasPozo.updateUI();//actualiza el jpanel para mostrar los cambios
                turno1();//variable turno y funcion turno a true
            } else if (e.getSource() == pasar) {
                turno2();// si pasa se vuelve la variable turno y la funcion turno a false para habilitar las fichas del jug2
            }
        } else {
            if (e.getSource() == colocarFicha) {
                turnoJugador2();
            } else if (e.getSource() == nuevaFicha) {
                p.RobarDelPozo(p.jugador2Fichas);
                cargarTodo(btnsJugador2, p.jugador2Fichas, p2, fichasJugador2);
                p2.updateUI();
                actualizarPozo();
                fichasPozo.updateUI();
                turno2();
            } else if (e.getSource() == pasar) {
                turno1();
            }
        }
        
        if(e.getSource() == nuevoJuego){
            //cuando apreten el boton nuevoJuego se limpia todo de la clase principal 
            //y el dispose() elimina todas las ventanas y componentes que hubiera
            p.jugador1Fichas.clear();
            p.jugador2Fichas.clear();
            p.fichas.clear();
            p.fichasJugadas.clear();
            dispose();
            Ventana v = new Ventana();
        }
    }

    public void finJuego() {
        //Deshabilita todo y muestra solo el boton de nuevo juego
        desabilitarFichas(btnsJugador1);
        desabilitarFichas(btnsJugador2);
        colocarIzq.setEnabled(false);
        colocarDer.setEnabled(false);
        colocarFicha.setVisible(false);
        nuevaFicha.setVisible(false);
        pasar.setVisible(false);
        nuevoJuego.setVisible(true);

    }

    public void turno1() {
        //setea la variable turno a verdadero para que juegue el jugador1
        //y le pasa verdadero al metodo turno para que desabilite los botones del jugador2
        turno = true;
        turno(true);
    }

    public void turno2() {
        turno = false;
        turno(false);
    }

    public void turnoJugador1() {

        try {
            Ficha fichaJug = p.jugador1Fichas.get(fichaElegida); // variable para no repetir lo mismo de p.jugador1Fichas.get(fichaElegida)
            if (p.fichasJugadas.isEmpty()) { //este if entra siempre para el que empiece a jugar, si el array de fichas jugadas esta vacio
                fichaJugadaDer(fichaJug); //agrega la ficha elegida a la derecha, a lo ultimo
                p.fichasJugadas.add(fichaJug); //agrega al array fichasjugadas de la clase p(principal) la ficha elegida
                p.jugador1Fichas.remove(fichaElegida);//borra del array la ficha
                btnsJugador1.get(fichaElegida).setVisible(false);//selecciona entre los botones y lo desaparece
                btnsJugador1.remove(fichaElegida);//despues se borra (quise hacer esto directamente pero no funcionaba sin el setVisible)
                panelJugadas.updateUI();//Actualiza el estado de las fichasJugadas
                turno2(); //habilita los botones y funciones para el jugador2
            } else if (lugarElegido == 1 && p.fichaAIzq(fichaJug)) { //el lugarElegido se setea con los JRaddioButton, si 
                fichaJugadaIzq(fichaJug);
                p.jugador1Fichas.remove(fichaElegida);
                btnsJugador1.get(fichaElegida).setVisible(false);
                btnsJugador1.remove(fichaElegida);
                panelJugadas.updateUI();
                turno2();
            } else if (lugarElegido == 2 && p.fichaADer(fichaJug)) {
                fichaJugadaDer(fichaJug);
                p.jugador1Fichas.remove(fichaElegida);
                btnsJugador1.get(fichaElegida).setVisible(false);
                btnsJugador1.remove(fichaElegida);
                panelJugadas.updateUI();
                turno2();
            } else {
                //cartel para avisarle al usuario que no se puede colocar la ficha que eligio a la der o a la izq dependiendo del lugar que quiera
                JOptionPane.showMessageDialog(null, "No se puede colocar la ficha\n Intentelo de nuevo");
                turno1();

            }
        } catch (Exception e) {
            //este try-catch sirve cuando despues de que un jugador coloque una ficha y el otro directamente aprete
            //el boton "colocar" no se rompa, porque al no seleccionar alguna ficha se hace mierda todo
            JOptionPane.showMessageDialog(null, "Intentelo de nuevo");
            turno1();
        }

    }

    public void turnoJugador2() {

        try {
            Ficha fichaJug2 = p.jugador2Fichas.get(fichaElegida);
            if (p.fichasJugadas.isEmpty()) {
                fichaJugadaDer(fichaJug2);
                p.fichasJugadas.add(fichaJug2);
                p.jugador2Fichas.remove(fichaElegida);
                btnsJugador2.get(fichaElegida).setVisible(false);
                btnsJugador2.remove(fichaElegida);
                panelJugadas.updateUI();
                turno1();
            } else if (lugarElegido == 1 && p.fichaAIzq(fichaJug2)) {
                fichaJugadaIzq(fichaJug2);
                p.jugador2Fichas.remove(fichaElegida);
                btnsJugador2.get(fichaElegida).setVisible(false);
                btnsJugador2.remove(fichaElegida);
                panelJugadas.updateUI();
                turno1();
            } else if (lugarElegido == 2 && p.fichaADer(fichaJug2)) {
                fichaJugadaDer(fichaJug2);
                p.jugador2Fichas.remove(fichaElegida);
                btnsJugador2.get(fichaElegida).setVisible(false);
                btnsJugador2.remove(fichaElegida);
                panelJugadas.updateUI();
                turno1();
            } else {
                JOptionPane.showMessageDialog(null, "No se puede colocar la ficha\n Intentelo de nuevo");
                turno2();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Intentelo de nuevo");
            turno2();
        }

    }

}
