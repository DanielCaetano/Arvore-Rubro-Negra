
public class ArvoreRN {

    private No raiz;

    public static No nil = new No(0, false); // No sentinela. Todos os nodos no último nível da árvore apontarão para a sentinela

    private Integer cont;

    public ArvoreRN() {
        this.raiz = ArvoreRN.nil;
    }

    public ArvoreRN(int v) {
        this.raiz = new No(v, false);
    }

    private void rotacao_esq(No x) {
       // System.out.println("Esque");
        No y = x.getDir();
        x.setDir(y.getEsq());
        if (y.getEsq() != ArvoreRN.nil) {
            y.getEsq().setP(x);
        }
        y.setP(x.getP());
        if (x.getP() == ArvoreRN.nil) {
            this.raiz = y;
        } else if (x == x.getP().getEsq()) {
            x.getP().setEsq(y);
        } else {
            x.getP().setDir(y);
        }
        y.setEsq(x);
        x.setP(y);
    }

    private void rotacao_dir(No n) {
        //System.out.println("DIREITA");
        No x = n.getEsq();
        n.setEsq(x.getDir());
        if (x.getDir() != ArvoreRN.nil) {
            x.getDir().setP(n);
        }
        x.setP(n.getP());
        if (n.getP() == ArvoreRN.nil) {
            this.raiz = x;
        } else if (n == n.getP().getDir()) {
            n.getP().setDir(x);
        } else {
            n.getP().setEsq(x);
        }
        x.setDir(n);
        n.setP(x);
    }

    public No busca(int n, No x) {
        No y = x;
        while (x != ArvoreRN.nil) {
            y = x;
            if (n < x.getChave()) {
                x = x.getEsq();
            } else if (n > x.getChave()) {
                x = x.getDir();
            } else {
                x = ArvoreRN.nil;
            }
        }
        return y;
    }

    public void inserir(int n) {
        if (this.raiz == ArvoreRN.nil) {
            this.raiz = new No(n, false);
        } else {
            No a = this.busca(n, this.raiz);

            if (n < a.getChave()) {
                a.setEsq(new No(n, true));
                a.getEsq().setP(a);
                this.inserirCorRN(a.getEsq());

            } else if (n > a.getChave()) {
                a.setDir(new No(n, true));
                a.getDir().setP(a);
                this.inserirCorRN(a.getDir());

            } else {
                System.out.println("Valor já inserido.");
            }
        }
    }

    private void inserirCorRN(No z) {
        No y;
        while (z.getP().getCor()) {

            if (z.getP() == z.getP().getP().getEsq()) {
                y = z.getP().getP().getDir();//pega tio
                // CASO 1 (tio yde z é vermelho):
                if (y.getCor()) {
                    z.getP().setCor(false);
                    y.setCor(false);
                    z.getP().getP().setCor(true);
                    z = z.getP().getP();
                } else {
                    //CASO 2 (tio y de z é preto e z é um filho á direita).
                    if (z == z.getP().getDir()) { // A troca com B e gira pra esquerda
                        z = z.getP();
                        this.rotacao_esq(z);
                    }
                    // CASO 3 (tio y de z é preto e z é um filho á esquerda)
                    z.getP().setCor(false);
                    z.getP().getP().setCor(true);
                    this.rotacao_dir(z.getP().getP());
                }
            } else {
                y = z.getP().getP().getEsq();
                if (y.getCor()) { // CASO 1
                    z.getP().setCor(false);
                    y.setCor(false);
                    z.getP().getP().setCor(true);
                    z = z.getP().getP();
                } else {
                    if (z == z.getP().getEsq()) { // CASO 2
                        z = z.getP();
                        this.rotacao_dir(z);
                    }
                    // CASO 3
                    z.getP().setCor(false);
                    z.getP().getP().setCor(true);
                    this.rotacao_esq(z.getP().getP());
                }
            }
        }
        this.raiz.setCor(false);
    }

    public void imprimir() {
        imprima(this.raiz);
    }

    private void imprima(No raiz) {
        System.out.print(raiz.getChave());
        if (raiz.getCor() == true) {
            System.out.print(" Rubro\n");
        } else {
            System.out.print(" Negro\n");
        }
        if (raiz.getEsq() != ArvoreRN.nil) {
            imprima(raiz.getEsq());
        }
        if (raiz.getDir() != ArvoreRN.nil) {
            imprima(raiz.getDir());
        }
    }

    private void moverPai(No u, No v) {
        if (u.getP() == ArvoreRN.nil) {
            this.raiz = v;
        } else if (u == u.getP().getEsq()) {
            //System.out.println(" U : "+u.getChave());
            //System.out.println(" U.pai : "+u.getP().getChave());
            u.getP().setEsq(v);
        } else {
            u.getP().setDir(v);
        }
        v.setP(u.getP());
    }

    public void remove(int n) {
        No z = this.busca(n, this.raiz);
            
        No x, y = z;
        boolean y_cor_original = y.getCor();
        System.out.println("y: "+y.getChave()+" "+y.getCor());

        if (z.getChave() == n) {
            //casos base com filho nil
            if (z.getEsq() == ArvoreRN.nil) {
                x = z.getDir();
                this.moverPai(z, z.getDir()); //moverpai
            } else if (z.getDir() == ArvoreRN.nil) {
                x = z.getEsq();
                this.moverPai(z, z.getEsq());

            } else {
                
                y = this.maximoEsq(z.getDir()); //sucessor esquerda
                //System.out.println(y.getChave());
                y_cor_original = y.getCor();
                x = y.getDir();

                if (y.getP() == z) {
                    x.setP(y);
                } else {
                    this.moverPai(y, y.getDir());
                    
                    y.setDir(z.getDir());
                    y.getDir().setP(y);
                }
               // System.out.println("Y: "+y.getChave()+" cor "+y.getCor());
               // System.out.println("Y: "+z.getChave()+" cor "+z.getCor());
                this.moverPai(z, y);
                
                y.setEsq(z.getEsq());
                y.getEsq().setP(y);
                y.setCor(z.getCor());
            }

            if (y_cor_original == false) {
              //  System.out.println("Cahave X: "+x.getChave()+" Cor "+x.getCor());
                this.rotaCorRN(x);
            }
        }
    }

    private No maximoEsq(No x) {
        while (x.getEsq() != ArvoreRN.nil) {
            x = x.getEsq();
        }
        return x;
    }

    private No maximoDir(No x) {
        while (x.getDir() != ArvoreRN.nil) {
            x = x.getDir();
        }
        return x;
    }

    private void rotaCorRN(No x) {//rotaCorRN
        No w;
  
        while (x != this.raiz && x.getCor() == false) { //x.getCor() != true
            
            if (x == x.getP().getEsq()) {
                w = x.getP().getDir();

                if (w.getCor()) { // CASO 1 (tio w de x é vermelho)
                    System.out.println("Caso 1 E");
                    w.setCor(false);
                    x.getP().setCor(true); // aqui é X -------------
                    this.rotacao_esq(x.getP());
                    w = x.getP().getDir();
                }
                if (w.getEsq().getCor() == false && w.getDir().getCor() == false) { // CASO 2 (tio w de x é preto com filhor preto)
                    System.out.println("Caso 2 E");
                    w.setCor(true);
                    x = x.getP();
                } else {
                    if (w.getDir().getCor() == false) { // CASO 3 (tio w de x é preto com filho esq vermelho e dir preto)
                        System.out.println("Caso 3 E");
                        w.getEsq().setCor(false);
                        w.setCor(true);
                        this.rotacao_dir(w);
                        w = x.getP().getDir(); // x.getP não w.getP
                    }
                    // CASO 4 (tio w de x é preto com e o filho a direita é vermelho)
                    System.out.println("Caso 4 E");
                    w.setCor(x.getP().getCor());
                    w.getDir().setCor(false);
                    x.getP().setCor(false);
                    this.rotacao_esq(x.getP());
                    x = this.raiz;
                }
            } else {
                w = x.getP().getEsq();

                if (w.getCor()) { // CASO 1 se w rubro
                    System.out.println("Caso 1 D");
                    w.setCor(false);
                    x.getP().setCor(true);
                    this.rotacao_dir(x.getP());
                    w = x.getP().getEsq();
                }
                if (w.getEsq().getCor() == false && w.getDir().getCor() == false) { // CASO 2
                    System.out.println("Caso 2 D");
                    w.setCor(true);
                    x = x.getP();
                } else {
                    if (w.getEsq().getCor() == false) { // CASO 3
                        System.out.println("Caso 3 D");
                        w.getDir().setCor(false);
                        w.setCor(true);
                        this.rotacao_esq(w);
                        w = x.getP().getEsq();
                    }
                    // CASO 4
                    System.out.println("Caso 4 D");
                    w.setCor(x.getP().getCor());
                    w.getEsq().setCor(false);
                    x.getP().setCor(false);
                    this.rotacao_dir(x.getP());
                    x = this.raiz;
                }
              
            }
        }
        x.setCor(false);
    }
}
