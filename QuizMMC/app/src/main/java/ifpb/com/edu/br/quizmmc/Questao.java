package ifpb.com.edu.br.quizmmc;

public class Questao {

    
    private String pergunta;
    private String opcaoA;
    private String opcaoB;
    private String opcaoC;
    private int correta;


    public Questao(String pergunta, String opcaoA, String opcaoB, String opcaoC, int correta) {
        this.pergunta = pergunta;
        this.opcaoA = opcaoA;
        this.opcaoB = opcaoB;
        this.opcaoC = opcaoC;
        this.correta = correta;
    }

    public Questao() {

    }

    public int  getCorreta() {
        return  correta;
    }

    public void setCorreta(int correta) {
        this.correta = correta;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getOpcaoA() {
        return opcaoA;
    }

    public void setOpcaoA(String opcaoA) {
        this.opcaoA = opcaoA;
    }

    public String getOpcaoB() {
        return opcaoB;
    }

    public void setOpcaoB(String opcaoB) {
        this.opcaoB = opcaoB;
    }

    public String getOpcaoC() {
        return opcaoC;
    }

    public void setOpcaoC(String opcaoC) {
        this.opcaoC = opcaoC;
    }
}
