import java.util.ArrayList;
import java.util.HashMap;

public class AnalizadorLexico {
    // Definimos los tokens como constantes
    public static final int IDENTIFICADOR = 0;
    public static final int ENTERO = 1;
    public static final int SUMA = 2;
    public static final int RESTA = 3;
    public static final int MULTIPLICACION = 4;
    public static final int DIVISION = 5;
    public static final int ASIGNACION = 6;
    public static final int ERROR = -1;

    // Definimos un mapa para asignar tokens a palabras clave
    private HashMap<String, String> palabrasClave;

    public AnalizadorLexico() {
        palabrasClave = new HashMap<>();
        palabrasClave.put("int", "palabra_reservada");
        palabrasClave.put("float", "palabra_reservada");
        palabrasClave.put("if", "palabra_reservada");
        palabrasClave.put("else", "palabra_reservada");
        // Agrega más palabras clave si es necesario
    }

    public ArrayList<Token> analizar(String codigoFuente) {
        ArrayList<Token> tokens = new ArrayList<>();
        String[] palabras = codigoFuente.split("\\s+"); // Dividir por espacios en blanco

        for (String palabra : palabras) {
            if (palabrasClave.containsKey(palabra)) {
                if (palabrasClave.get(palabra).equals("tipo_de_datos")) {
                    tokens.add(new Token(palabra, "tipo_de_datos"));
                } else {
                    tokens.add(new Token(palabra, "palabra_reservada"));
                }
            } else if (palabra.matches("[a-zA-Z]+")) {
                tokens.add(new Token(palabra, "variable"));
            } else if (palabra.matches("\\d+")) {
                tokens.add(new Token(palabra, "numero"));
            } else if (palabra.equals("+")) {
                tokens.add(new Token(palabra, "suma"));
            } else if (palabra.equals("-")) {
                tokens.add(new Token(palabra, "resta"));
            } else if (palabra.equals("*")) {
                tokens.add(new Token(palabra, "multiplicacion"));
            } else if (palabra.equals("/")) {
                tokens.add(new Token(palabra, "division"));
            } else if (palabra.equals("=")) {
                tokens.add(new Token(palabra, "simbolo_asignacion"));
            } else if (palabra.endsWith(";")) {
                tokens.add(new Token(palabra.substring(0, palabra.length() - 1), "numero"));
                tokens.add(new Token(";", "simbolo_fin_de_linea"));
            } else if (palabra.equals(";")) {
                tokens.add(new Token(palabra, "simbolo_fin_de_linea"));
            } else {
                tokens.add(new Token(palabra, "error"));
            }
        }

        return tokens;
    }




    public static void main(String[] args) {
        String codigoFuente = "int valor = 12;";
        AnalizadorLexico analizador = new AnalizadorLexico();
        ArrayList<Token> tokens = analizador.analizar(codigoFuente);

        // Mostrar los tokens obtenidos
        for (Token token : tokens) {
            String lexema = token.getLexema();
            String descripcionToken;
            switch (token.getDescripcionToken()) {
                case "palabra_reservada":
                    descripcionToken = "Palabra reservada de tipo de datos";
                    break;
                case "variable":
                    descripcionToken = "Variable o identificador";
                    break;
                case "simbolo_asignacion":
                    descripcionToken = "Símbolo de asignación";
                    break;
                case "numero":
                    descripcionToken = "Número";
                    break;
                case "simbolo_fin_de_linea":
                    descripcionToken = "Símbolo de fin de línea";
                    break;
                default:
                    descripcionToken = "Token no identificado";
                    break;
            }
            System.out.println("Lexema: " + lexema + ", " + descripcionToken);
        }
    }

}







