//Bibliotecas utilizadas no código!

import java.awt.*; 
import java.awt.event.*; 
import java.util.Arrays; 
import javax.swing.*; 
import javax.swing.border.LineBorder;


//Classe instanciada. Essa sessão possui atributos da classe.
public class Calculator {

  //Definição do tamanho da janela em pixels
  int boardWidth = 360; 
  int boardHeight = 540;

  //Definição de cores utilizadas no programa
  Color customLightGray = new Color(212, 212, 210); 
  Color customDarkGray = new Color (80, 80, 80); 
  Color customBlack = new Color(28, 28, 28); 
  Color customRed = new Color(119, 21, 21);

  //Array de strings(texto) presentes nos botões da calculadora

  String[] buttonValues = {
    "AC", "+/-", "%", "÷", 
    "7", "8", "9", "×", 
    "4","5","6", "-", 
    "1", "2", "3", "+", 
    "0", ".", "√", "="
};

//Definição de posicionamento de símbolos no espaço da calculadora em arrays
  String[] rightSymbols = {"÷", "×", "-", "+", "="}; 
  String[] topSymbols = {"AC", "+/-", "%"};
  
  //A linha a seguir define o nome que aparece no topo da janela da calculadora.
  JFrame frame = new JFrame ("My Chemical Romance Calculator"); 

  // As próximas linhas definem painéis utilizados dentro da janela.

  // displayLabel define o que aparece no display da calculadora, ao topo.
  JLabel displayLabel = new JLabel();

  // displayPanel define o quadro do display, a "tela" da calculadora
  JPanel displayPanel = new JPanel(); 

  // buttonsPanel refere-se aos botões da calculadora
  JPanel buttonsPanel = new JPanel();

  //A+B, A-B, A*B, A/B 
  String A = "0";
  String operator = null;
  String B = null;

  //Para uma classe de fato existir, ela precisa ser construída. Essa sessão se chama "constructor", definindo tudo sobre a classe instanciada no "App.Java"
  Calculator() {

    //Nessa primeira parte, definimos características visuais da janela da calculadora
    frame.setSize(boardWidth, boardHeight); 
    frame.setLocationRelativeTo(null); 
    frame.setResizable(false); 
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    frame.setLayout(new BorderLayout());

    //Aqui definimos características visuais do display ("tela" da calculadora)
    displayLabel.setBackground(customBlack); 
    displayLabel.setForeground(Color.white); 
    displayLabel.setFont(new Font("Arial", Font.PLAIN, 80)); 
    displayLabel.setHorizontalAlignment(JLabel.RIGHT); 
    displayLabel.setText("0"); 
    displayLabel.setOpaque(true);

    displayPanel.setLayout(new BorderLayout()); 
    displayPanel.add(displayLabel); 
    frame.add(displayPanel, BorderLayout.NORTH);

    //Nessa parte, são definidas características visuais dos botões, como o grid
    buttonsPanel.setLayout(new GridLayout(5, 4)); 
    buttonsPanel.setBackground(customBlack); 
    frame.add(buttonsPanel);

    displayLabel.setOpaque(true);
    displayPanel.setLayout(new BorderLayout()); 
    displayPanel.add(displayLabel); 
    frame.add(displayPanel, BorderLayout.NORTH);
    buttonsPanel.setLayout(new GridLayout(5, 4));
    buttonsPanel.setBackground(customBlack); 
    frame.add(buttonsPanel);

  for (int i = 0; i < buttonValues.length; i++) {
    JButton button = new JButton(); 
    String buttonValue = buttonValues[i]; 
    button.setFont(new Font("Arial", Font.PLAIN, 30)); 
    button.setText(buttonValue); 
    button.setFocusable(false); 
    button.setBorder(new LineBorder(customBlack)); 
    
    if (Arrays.asList(topSymbols).contains(buttonValue)) {
      button.setBackground(customLightGray); 
      button.setForeground(customBlack);
    }
    else if (Arrays.asList(rightSymbols).contains(buttonValue)) {
      button.setBackground(customRed); 
      button.setForeground(Color.white);
    }
    else {
      button.setBackground(customDarkGray); 
      button.setForeground(Color.white);
    }

    buttonsPanel.add(button);
    
    //Nessa parte, garantimos que os cliques nos botões são ouvidos e gerarão uma resposta
    button.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e) {
         JButton button = (JButton) e.getSource(); 
         String buttonValue = button.getText(); 
         //Aqui definimos as ações da calculadora para operações presentes nos botões à direita
         if (Arrays.asList(rightSymbols).contains(buttonValue)) {
          if (buttonValue == "=") {
            if (A != null) {
              B = displayLabel.getText();
              double numA = Double.parseDouble(A);
              double numB = Double.parseDouble(B);

              if (operator == "+") {
                displayLabel.setText(removeZeroDecimal (numA+numB));
              }
              else if (operator == "-") {
                displayLabel.setText(removeZeroDecimal (numA-numB));
              }
              else if (operator == "×") {
                displayLabel.setText(removeZeroDecimal (numA*numB));
              }
              else if (operator == "÷") {
                displayLabel.setText(removeZeroDecimal (numA/numB));
              }
              clearAll();
            }

          }
          else if ("+-×÷".contains(buttonValue)) {
            if (operator == null) {
              A = displayLabel.getText();
              displayLabel.setText("0");
              B = "0";
            }
            operator = buttonValue;
          }
         }
         //Aqui definimos as ações da calculadora para operações presentes nos botões no topo do teclado
         else if (Arrays.asList(topSymbols).contains(buttonValue)) {
          if (buttonValue == "AC") {
            clearAll();
            displayLabel.setText("0");

          }
          else if (buttonValue == "+/-") {
            double numDisplay = Double.parseDouble(displayLabel.getText());
            numDisplay *= -1;
            displayLabel.setText(removeZeroDecimal(numDisplay));

          }
          else if (buttonValue == "%") {
            double numDisplay = Double.parseDouble(displayLabel.getText());
            numDisplay /= 100;
            displayLabel.setText(removeZeroDecimal(numDisplay));
          }

         }
         else {
            if (buttonValue == ".") {
              if (!displayLabel.getText().contains(buttonValue)) {
                displayLabel.setText(displayLabel.getText() + buttonValue);
              }
            }
            //Aqui refere-se aos botões de 0 a 9 que são utilizados nas operações
            else if ("0123456789".contains(buttonValue)) {
              if (displayLabel.getText() == "0") {
                  displayLabel.setText (buttonValue);
              }
              else {
                displayLabel.setText(displayLabel.getText() + buttonValue);
              }
            }
          }
        }
      });
      frame.setVisible(true);
    }
  }   

  void clearAll () {
    A = "0";
    operator = null;
    B = null;
  }

  String removeZeroDecimal(double numDisplay) {
    if (numDisplay % 1 == 0) {
      return Integer.toString((int) numDisplay);
    }
    return Double.toString(numDisplay);
  }
}
