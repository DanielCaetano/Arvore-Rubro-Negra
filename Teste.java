
public class Teste {
    public static void main(String[] args) {
        ArvoreRN arv = new ArvoreRN();
        int lis[] = {20, 30, 10, 1, 4, 5, 50};
        int lis2[] = {4, 9, 10, 2, 1, 3, 6, 11, 5, 7, 8};
        int lis3[] = {25, 20, 30, 10, 24, 26, 35, 5, 11, 27, 12};
        int ex1[] = {11, 2, 14, 1, 7, 15, 8, 5, 4};
        int re[] = {20, 30, 10, 35, 25};
        int ex[] = {20, 15, 70, 13, 30, 10, 50, 27, 8, 60, 25}; //remove 15
        int pro[]={10,9,13,12,14,11};
        
        int ins[] = lis3;
        
        for (int i = 0; i < ins.length; i++) {
           // System.out.println("Inserir " + ins[i]);
            arv.inserir(ins[i]);
        }
       arv.imprimir();
       System.out.println("\nRemover 24");
        arv.remove(24);
        //arv.remove(6);
        //arv.remove(9);
        
        arv.imprimir();
    }
}
