class Token {
    private String lexema;
    private String descripcionToken;

    public Token(String lexema, String descripcionToken) {
        this.lexema = lexema;
        this.descripcionToken = descripcionToken;
    }

    public String getLexema() {
        return lexema;
    }

    public String getDescripcionToken() {
        return descripcionToken;
    }

    @Override
    public String toString() {
        return "Lexema: " + lexema + ", Descripción del Token: " + descripcionToken;
    }
}