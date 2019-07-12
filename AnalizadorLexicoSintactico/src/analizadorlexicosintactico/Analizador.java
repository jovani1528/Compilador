package analizadorlexicosintactico;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/**
 *
 * @author rodol
 */
public class Analizador extends javax.swing.JFrame {
    public Analizador() {
        initComponents();
        this.setLocationRelativeTo(null);
        txtAnalizar.addKeyListener(new KeyListener() {
        
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyChar()==' '||e.getKeyChar()==';'){
                    modelo.setNumRows(0);
                    modelo.setColumnCount(2);
                    String[] ids={"Token","Valor"};
                    modelo.setColumnIdentifiers(ids);
                    tablaSimbolos.setModel(modelo);
                    generarTokens(txtAnalizar.getText());
                    txtSalida.setText(analizarLexico());
                    System.out.println(txtAnalizar.getCaretPosition());
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    modelo.setNumRows(0);
                    modelo.setColumnCount(2);
                    String[] ids={"Token","Valor"};
                    modelo.setColumnIdentifiers(ids);
                    tablaSimbolos.setModel(modelo);
                    generarTokens(txtAnalizar.getText());
                    txtSalida.setText(analizarLexico());
                }
               else if(e.getKeyCode()==8){
                    modelo.setNumRows(0);
                    modelo.setColumnCount(2);
                    String[] ids={"Token","Valor"};
                    modelo.setColumnIdentifiers(ids);
                    tablaSimbolos.setModel(modelo);
                    generarTokens(txtAnalizar.getText());
                    txtSalida.setText(analizarLexico());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
               
            }

        });
        
        final StyleContext conta = StyleContext.getDefaultStyleContext();
        final AttributeSet attr = conta.addAttribute(conta.getEmptySet(), StyleConstants.Foreground, Color.BLUE);
        final AttributeSet attrBlack = conta.addAttribute(conta.getEmptySet(), StyleConstants.Foreground, Color.BLACK);
        DefaultStyledDocument doc = new DefaultStyledDocument() {
            @Override
            public void insertString (int offset, String str, AttributeSet a) throws BadLocationException {
                super.insertString(offset, str, a);
                String text = getText(0, getLength());
                int before = findLastNonWordChar(text, offset);
                if (before < 0) before = 0;
                int after = findFirstNonWordChar(text, offset + str.length());
                int wordL = before;
                int wordR = before;
                String ver;
                String regex = "\\s++$";
                while (wordR <= after) {
                    ver = text.substring(wordL, wordR).replaceFirst(regex, "");
                    if(ver.matches("\\s")||ver.matches(";")||ver.matches("\\n")){
                        System.out.println("Checa!");
                        }
                    if (wordR == after || String.valueOf(text.charAt(wordR)).matches("\\W")) {
                        if (text.substring(wordL, wordR).matches("(\\W)*(int|String|if|else|switch|byte|break|main|void|new|while|for|true|false|do|case)")){
                            setCharacterAttributes(wordL, wordR - wordL, attr, false); 
                        }
                        else{
                            setCharacterAttributes(wordL, wordR - wordL, attrBlack, false);
                        }
                        wordL = wordR;
                    }
                    wordR++;
                }
            }
                
            @Override
            public void remove (int offs, int len) throws BadLocationException {
                super.remove(offs, len);
                String text = getText(0, getLength());
                int before = findLastNonWordChar(text, offs);
                if (before < 0) before = 0;
                int after = findFirstNonWordChar(text, offs);
                if (text.substring(before, after).matches("(\\W)*(int|String|if|else|switch|byte|break|main|void|new|while|for|true|false|do|case)")){
                    setCharacterAttributes(before, after - before, attr, false);
                } else {
                    setCharacterAttributes(before, after - before, attrBlack, false);
                }
            }
        };
        txtAnalizar.setDocument(doc);
    }
    
    DefaultTableModel modelo= new DefaultTableModel();
    String cadena="", cad="";
    Token token, t;
    String mensaje="";
    String error="";
    String[] tokens;
    String[] valores;
    ArrayList<Token> tokens2;
    char[] caracteres;
    int cont = 0;
    boolean aprobacion = true;
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtSalida = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        btnBorrar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaSimbolos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtAnalizar = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        txtSalida.setColumns(20);
        txtSalida.setRows(5);
        jScrollPane2.setViewportView(txtSalida);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("ANALIZADOR LÉXICO");

        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        tablaSimbolos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Token", "Valor"
            }
        ));
        jScrollPane3.setViewportView(tablaSimbolos);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Texto a analizar");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Resultados");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Tabla de simbolos");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txtAnalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(27, 27, 27)
                                        .addComponent(jLabel2))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(135, 135, 135)
                                        .addComponent(btnBorrar)))
                                .addGap(159, 159, 159)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(308, 308, 308)
                                .addComponent(jLabel4))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(326, 326, 326)
                        .addComponent(jLabel1)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3))
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtAnalizar))
                .addGap(34, 34, 34)
                .addComponent(btnBorrar)
                .addGap(26, 54, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        txtAnalizar.setText("");
        txtSalida.setText("");
        modelo.setNumRows(0);
        modelo.setColumnCount(2);
        String[] ids={"Token","Valor"};
        modelo.setColumnIdentifiers(ids);
        tablaSimbolos.setModel(modelo);
        cont = 0;
        mensaje="";
    }//GEN-LAST:event_btnBorrarActionPerformed

    private int findLastNonWordChar (String text, int index) {
        while (--index >= 0) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
        }
        return index;
    }

    private int findFirstNonWordChar (String text, int index) {
        while (index < text.length()) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
            index++;
        }
        return index;
    }
          
    public String analizarLexico()
    {
        
        mensaje = "";
        //tokens = cadena.split("\\s+");
        modelo.setNumRows(tokens2.size());
        modelo.setColumnCount(2);
        String[] ids={"Token","Valor"};
        modelo.setColumnIdentifiers(ids);
        tablaSimbolos.setModel(modelo);
        cont = 0;
        
        for(int i=0; i<tokens2.size(); i++)
        {
            token = tokens2.get(i);
            System.out.println(token.token);
            
            if((token.token.equals("for")) || (token.token.equals("new")) || (token.token.equals("main")) || (token.token.equals("void")) || (token.token.equals("if")) || (token.token.equals("else")) || (token.token.equals("while"))|| (token.token.equals("switch"))|| (token.token.equals("case"))|| (token.token.equals("break"))|| (token.token.equals("do")))
            {
                mensaje = mensaje+"Palabra reservada -> "+token.token+"\n";
                tablaSimbolos.setValueAt(token.token, cont, 0);
                tablaSimbolos.setValueAt("401", cont, 1);
                cont ++;
            }
            else
                if((token.token.equals("true")) || (token.token.equals("false")))
                {
                   mensaje = mensaje+"Valor booleano -> "+token.token+"\n";
                   tablaSimbolos.setValueAt(token.token, cont, 0);
                   tablaSimbolos.setValueAt("300", cont, 1);
                   cont ++;
                }
            else
                if((token.token.equals("int")) || (token.token.equals("boolean"))||(token.token.equals("String")) || (token.token.equals("byte")))
                {
                  mensaje = mensaje+"Tipo de dato -> "+token.token+"\n";
                  tablaSimbolos.setValueAt(token.token, cont, 0);
                  tablaSimbolos.setValueAt("250", cont, 1);
                  cont ++;
                }
            else
                if((token.token.equals("int[]")) || (token.token.equals("String[]")) || (token.token.equals("char[]")))
                {
                  mensaje = mensaje+"Arreglo -> "+token.token+"\n";
                  tablaSimbolos.setValueAt(token.token, cont, 0);
                  tablaSimbolos.setValueAt("100", cont, 1);
                  cont ++;
                }
                else
                    if((token.token.charAt(0)== 'a') || (token.token.charAt(0)== 'b') || (token.token.charAt(0) == 'c') || (token.token.charAt(0) == 'd')
                       || (token.token.charAt(0) == 'e') || (token.token.charAt(0) == 'f') || (token.token.charAt(0) == 'g') || (token.token.charAt(0) == 'h')
                       || (token.token.charAt(0)== 'i') || (token.token.charAt(0)== 'j') || (token.token.charAt(0) == 'k') || (token.token.charAt(0) == 'l')
                       || (token.token.charAt(0) == 'm') || (token.token.charAt(0) == 'n') || (token.token.charAt(0) == 'o') || (token.token.charAt(0) == 'p')
                       || (token.token.charAt(0) == 'q') || (token.token.charAt(0) == 'r') || (token.token.charAt(0) == 's') || (token.token.charAt(0) == 't')
                       || (token.token.charAt(0) == 'u') || (token.token.charAt(0) == 'v') || (token.token.charAt(0) == 'w') || (token.token.charAt(0) == 'x')
                       || (token.token.charAt(0) == 'y') || (token.token.charAt(0) == 'z')|| (token.token.charAt(0) == '_')
                       || (token.token.charAt(0) == 'A') || (token.token.charAt(0) == 'B') || (token.token.charAt(0) == 'C') || (token.token.charAt(0) == 'D')
                       || (token.token.charAt(0)== 'E') || (token.token.charAt(0) == 'F') || (token.token.charAt(0) == 'G') || (token.token.charAt(0)== 'H')
                       || (token.token.charAt(0) == 'I') || (token.token.charAt(0) == 'J') || (token.token.charAt(0) == 'K') || (token.token.charAt(0) == 'L')
                       || (token.token.charAt(0)== 'M') || (token.token.charAt(0)== 'N') || (token.token.charAt(0)== 'O') || (token.token.charAt(0) == 'P')
                       || (token.token.charAt(0)== 'Q') || (token.token.charAt(0)== 'R') || (token.token.charAt(0) == 'S') || (token.token.charAt(0) == 'T')
                       || (token.token.charAt(0) == 'U') || (token.token.charAt(0)== 'V') || (token.token.charAt(0) == 'W') || (token.token.charAt(0) == 'X')
                       || (token.token.charAt(0) == 'Y') || (token.token.charAt(0) == 'Z'))
                    {
                       
                       for(int j=1; j<token.token.length(); j++)
                       {  
                         if((token.token.charAt(j) == 'a') || (token.token.charAt(j) == 'b') || (token.token.charAt(j)== 'c') || (token.token.charAt(j) == 'd')
                            || (token.token.charAt(j)== 'e') || (token.token.charAt(j) == 'f') || (token.token.charAt(j) == 'g') || (token.token.charAt(j) == 'h')
                            || (token.token.charAt(j) == 'i') || (token.token.charAt(j)== 'j') || (token.token.charAt(j) == 'k') || (token.token.charAt(j) == 'l')
                            || (token.token.charAt(j)== 'm') || (token.token.charAt(j) == 'n') || (token.token.charAt(j) == 'o') || (token.token.charAt(j)== 'p')
                            || (token.token.charAt(j) == 'q') || (token.token.charAt(j)== 'r') || (token.token.charAt(j) == 's') || (token.token.charAt(j)== 't')
                            || (token.token.charAt(j)== 'u') || (token.token.charAt(j) == 'v') || (token.token.charAt(j) == 'w') || (token.token.charAt(j) == 'x')
                            || (token.token.charAt(j) == 'y') || (token.token.charAt(j) == 'z')||(token.token.charAt(0) == '_')||(token.token.charAt(j) == '$')
                            || (token.token.charAt(j) == '0') || (token.token.charAt(j) == '1') || (token.token.charAt(j) == '2')
                            || (token.token.charAt(j) == '3') || (token.token.charAt(j) == '4') || (token.token.charAt(j) == '5')
                            || (token.token.charAt(j) == '6') || (token.token.charAt(j) == '7') || (token.token.charAt(j) == '8')
                            || (token.token.charAt(j) == '9')) 
                         {
                                
                         }
                         else {
                                error = "ERROR nombre campo no valido";
                                break;
                              }
                       }
                      if(error == "")
                      {
                          tablaSimbolos.setValueAt("id", cont, 0);
                          tablaSimbolos.setValueAt("200", cont, 1);
                          mensaje = mensaje+"Nombre campo -> "+token.token+"\n";
                          cont ++;
                      }
                      else {
                             mensaje = mensaje+error+"\n"; 
                           }
                      error="";

                    }
                        else
                            if((token.token.equals("*")) || (token.token.equals("/")) || (token.token.equals("+")) || (token.token.equals("-"))||(token.token.equals("=")))
                            {
                              mensaje = mensaje+"Operador aritmetico -> "+token.token+"\n";
                              tablaSimbolos.setValueAt(token.token, cont, 0);
                              tablaSimbolos.setValueAt("55", cont, 1);
                              cont ++;
                            }
                            else
                                if((token.token.equals("<")) || (token.token.equals(">")) || (token.token.equals("==")) || (token.token.equals("!="))
                                   || (token.token.equals("<=")) || (token.token.equals(">=")))
                                {
                                  mensaje = mensaje+"Operador relacional -> "+token.token+"\n";
                                  tablaSimbolos.setValueAt(token.token, cont, 0);
                                  tablaSimbolos.setValueAt("66", cont, 1);
                                  cont ++;
                                }
                                else
                                    if((token.token.equals("(")) || (token.token.equals(")")) || (token.token.equals("{")) || (token.token.equals("}"))|| (token.token.equals("[")) || (token.token.equals("]")))
                                    {
                                      mensaje = mensaje+"Simbolo de agrupación -> "+token.token+"\n";
                                      tablaSimbolos.setValueAt(token.token, cont, 0);
                                      tablaSimbolos.setValueAt("50", cont, 1);
                                      cont ++;
                                    }
                                    else
                                        if(token.token.equals(";"))
                                        {
                                          mensaje = mensaje+"Delimitador de sentencia -> "+token.token+"\n";
                                          tablaSimbolos.setValueAt(token.token, cont, 0);
                                          tablaSimbolos.setValueAt("84", cont, 1);
                                          cont ++;
                                        }
                                        else
                                            if(token.equals("="))
                                            {
                                               mensaje = mensaje+"Simbolo de asignación -> "+token.token+"\n";
                                               tablaSimbolos.setValueAt(token.token, cont, 0);
                                               tablaSimbolos.setValueAt("70", cont, 1);
                                               cont ++;
                                            }
                                        else
                                            if(token.token.equals("++")||token.token.equals("--"))
                                            {
                                               mensaje = mensaje+"Operador unario -> "+token.token+"\n";
                                               tablaSimbolos.setValueAt(token.token, cont, 0);
                                               tablaSimbolos.setValueAt("75", cont, 1);
                                               cont ++;
                                            }
                                            else
                                            if(token.token.equals("\""))
                                            {
                                               mensaje = mensaje+"Delimitador de String  -> "+token.token+"\n";
                                               tablaSimbolos.setValueAt(token.token, cont, 0);
                                               tablaSimbolos.setValueAt("25", cont, 1);
                                               cont ++;
                                            }
                                            else if((token.token.charAt(0)  == '0') || (token.token.charAt(0) == '1') || (token.token.charAt(0) == '2')
                                                    || (token.token.charAt(0) == '3') || (token.token.charAt(0) == '4') || (token.token.charAt(0) == '5')
                                                    || (token.token.charAt(0) == '6') || (token.token.charAt(0) == '7') || (token.token.charAt(0) == '8')
                                                    || (token.token.charAt(0) == '9'))
                                                 {
                                                   
                                                    for(int j=0; j<token.token.length(); j++)
                                                    {
                                                       if((token.token.charAt(j) == '0') || (token.token.charAt(j) == '1') || (token.token.charAt(j) == '2')
                                                         || (token.token.charAt(j) == '3') || (token.token.charAt(j) == '4') || (token.token.charAt(j) == '5')
                                                         || (token.token.charAt(j) == '6') || (token.token.charAt(j) == '7') || (token.token.charAt(j) == '8')
                                                         || (token.token.charAt(j) == '9'))
                                                       {
                                                                
                                                       }
                                                       else {
                                                              error = "ERROR entero mal definido";
                                                              break;
                                                            }
                                                    }
                                                    if(error == "")
                                                    {
                                                       tablaSimbolos.setValueAt(token.token, cont, 0);
                                                       tablaSimbolos.setValueAt("300", cont, 1);
                                                       mensaje = mensaje+"Valor entero -> "+token.token+"\n";
                                                       cont ++;
                                                    }
                                                    else {
                                                            mensaje = mensaje+error+"\n";
                                                         }
                                                    error=""; 
                                                 }     
                                                 else {
                                                         mensaje = mensaje+"ERROR el token -> "+token.token+" no pertence al lenguaje"+"\n";
                                                      }
        }
        return mensaje;
    }
    
  
    public void generarTokens(String cadena) {
        tokens2 = new ArrayList<Token>();
        String token = "";
        Token tk;
        int s = 0;
        char c;
        boolean f = true;
        for (int i = 0; i < cadena.length(); i++) {
            c = cadena.charAt(i);
            if (c != '\n' && c != '\r' && c != '\t' && c != ' ') {
                if (c != '(' && c != ')' && c != ';' && c != '{' && c != '}' && c != '+' && c != '-' && c != '*' && c != '/' && c != '%' && c != '=' && c != '!' && c != '<' && c != '>' && c != '"') 
                {
                    token = token + c;
                    if (f) {
                        s = i;
                        f = false;
                    }
                }else if (c == '(' || c == ')' || c == ';' || c == '{' || c == '}' || c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '=' || c == '!' || c == '<' || c == '>' || c == '"') 
                {
                     if (!token.equals("")) {
                        tk = new Token();
                        tk.token = token;
                        tk.inicio = s;
                        tk.fin = i;
                        tokens2.add(tk);
                        token = "";
                    }
                    f = true;
                    token =token+c;
                    tk = new Token();
                    tk.token = token;
                    tk.inicio = s;
                    tk.fin = i;
                    tokens2.add(tk);
                    token = "";
                } else {
                    if (!token.equals("")) {
                        tk = new Token();
                        tk.token = token;
                        tk.inicio = s;
                        tk.fin = i;
                        tokens2.add(tk);
                        token = "";
                    }
                    f = true;
                }
            } else {
                if (!token.equals("")) {
                    tk = new Token();
                    tk.token = token;
                    tk.inicio = s;
                    tk.fin = i;
                    tokens2.add(tk);
                    token = "";
                }
                f = true;
            }
        }
        if (!token.equals("")) {
            tk = new Token();
            tk.token = token;
            tk.inicio = s;
            tk.fin = cadena.length();
            tokens2.add(tk);
            token = "";
        }
    }
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Analizador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Analizador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Analizador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Analizador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Analizador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBorrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tablaSimbolos;
    private javax.swing.JTextPane txtAnalizar;
    private javax.swing.JTextArea txtSalida;
    // End of variables declaration//GEN-END:variables
}
