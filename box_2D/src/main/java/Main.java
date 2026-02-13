public class Main {

    private void main(String[] args){

        int Height = 165;
        double Weight = 90;
        double IMC = Weight / (Height * Height);
        String nome = "Maddox";
        outside(IMC, nome);
    }
    public void outside(double IMC, String nome){
        System.out.println( nome + "caralhos, seu imc é: " + IMC);



    }
}
